package br.com.technolog.checklist;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import models.CheckLists;
import models.ItensCheckList;
import models.Result;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.BancoDados;
import utility.SendCheckService;
import utility.Utilidades;

public class SendToServerActivity extends AppCompatActivity {

	private ListView listView;
	private TextView head;
	private Bundle valores;
	private BancoDados db;
	private ArrayList<CheckLists> endList;
	private Button selectAll;
	private FloatingActionButton fab;

	public final static Bitmap getUnRotatedImage(String imahePath, Bitmap rotattedBitmap) {
		int rotate = 0;
		try {
			File imageFile = new File(imahePath);
			ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate = 270;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate = 90;
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


		Matrix matrix = new Matrix();
		matrix.postRotate(rotate);
		return Bitmap.createBitmap(rotattedBitmap, 0, 0, rotattedBitmap.getWidth(), rotattedBitmap.getHeight(), matrix,
				true);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_to_server);
		assert getSupportActionBar() != null;

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.sendCheckTitle));

		TextView textFooterData = (TextView) findViewById(R.id.textFooterData);
		TextView textFooterUser = (TextView) findViewById(R.id.textFooterUser);
		TextView textFooterModulo = (TextView) findViewById(R.id.textFooterModulo);

		selectAll = (Button) findViewById(R.id.selectAll);
		listView = (ListView) findViewById(R.id.lista);

		textFooterData.setText(Utilidades.getDataHora("dd/MM/yyyy"));

		head = (TextView) findViewById(R.id.textViewHead);

		final Intent intent1 = getIntent();

		valores = intent1.getExtras();

		textFooterUser.setText(valores.getString("user"));
		textFooterModulo.setText(R.string.checklist_modulo);

		//Log.e("LEONARDO","" + valores.getInt("COD_CLIENTE"));

		listView = (ListView) findViewById(R.id.lista);
		db = new BancoDados(getApplicationContext());
		endList = db.getCheckEndList();

		fab = (FloatingActionButton) findViewById(R.id.fabUpload);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getChecksToSendServer();
			}
		});

		System.out.println(endList.size());


		if (endList.size() == 0){
			head.setText(R.string.sem_check_para_enviar);
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_warning_amber_800_24dp)
					.setTitle("Alerta!")
					.setMessage(R.string.sem_check_para_enviar)
					.setNegativeButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
							intent.putExtras(valores);
							startActivity(intent);
						}
					})
					.show();

		}else {
			loadList(endList);
			selectAll.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (getText(R.string.select_all) == selectAll.getText()) {
						selectAll(true);
						selectAll.setText("Remover todas seleções");
					} else {
						selectAll(false);
						selectAll.setText(getText(R.string.select_all));
					}
				}
			});
		}
	}

	private void selectAll(boolean b) {
		int tamanho = listView.getCount();
		for (int i = 0; i < tamanho; i++) {
			View view = listView.getChildAt(i);
			CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkBox);
			checkbox.setChecked(b);
		}
	}

	/**
	 *
	 */
	private void getChecksToSendServer() {
		System.out.println("kkkkkk eae men!");
		getItensSendToServer(getSelectedItens());
	}

	private void getItensSendToServer(List<Integer> selectedChecks) {
		ArrayList<ItensCheckList> listItens = new ArrayList<>();
		int tamanho = selectedChecks.size();
		for (int i = 0; i < tamanho; i++) {

			CheckLists check = db.getCheckByCod(selectedChecks.get(i));
			System.out.println(check.toString());

			listItens = db.getSavedItens(check.getCod(), check.getCodModeloCheckList());
			sendServer(check, listItens, i, tamanho);
		}
	}

	private void sendServer(CheckLists check, ArrayList<ItensCheckList> listItens, int atual, int total) {
		final ProgressDialog progressDialog;

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getString(R.string.string_title_upload_progressbar_));
		progressDialog.setCancelable(false);
		progressDialog.show();

		List<MultipartBody.Part> map = new ArrayList<>();

		ArrayList<ItensCheckList> itens = new ArrayList<>();
		int tamanhoi = listItens.size();
		for (int i = 0; i < tamanhoi; i++) {
			itens.add(listItens.get(i));
			if (listItens.get(i).getOpcaoAnexo() != null) {
				File anexo = new File(listItens.get(i).getOpcaoAnexo());
				BitmapFactory.Options bmOptions = new BitmapFactory.Options();

				Bitmap bitmap = BitmapFactory.decodeFile(listItens.get(i).getOpcaoAnexo(), bmOptions);
				bitmap = getUnRotatedImage(listItens.get(i).getOpcaoAnexo(), bitmap);
				try {
					saveBitmapToJPG(bitmap, anexo);
				} catch (IOException e) {
					e.printStackTrace();
				}
				RequestBody requestBodyAnexos = RequestBody.create(MediaType.parse("image/jpeg"), anexo);
				MultipartBody.Part anexoFileBody = MultipartBody.Part.createFormData(listItens.get(i).getNomeItem(), anexo.getName(), requestBodyAnexos);
				map.add(anexoFileBody);


				//builderAnexos.addFormDataPart("anexos",anexo.getName(),requestBodyAnexos);
			}
		}

		final OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.readTimeout(120, TimeUnit.SECONDS)
				.connectTimeout(120, TimeUnit.SECONDS)
				.build();
		//Log.e("teste",itens.toString());
		Retrofit retrofit = new Retrofit.Builder()

				.baseUrl(SendCheckService.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(okHttpClient)
				.build();
		SendCheckService sendCheck = retrofit.create(SendCheckService.class);
		Gson gson = new Gson();
		String ok = gson.toJson(itens);

		//METODOS PARA CONSEGUIR MANDAR A IMAGEM
		//foto placa
		MultipartBody.Part imageFileBodyFotoPlaca = null;
		if (check.getCaminhoArquivoFotoPlacaVeiculo() != null) {
			File ftPlaca = new File(check.getCaminhoArquivoFotoPlacaVeiculo());
			RequestBody requestBodyFotoPlaca = RequestBody.create(MediaType.parse("multipart/form-data"), ftPlaca);
			imageFileBodyFotoPlaca = MultipartBody.Part.createFormData("ftPlaca", ftPlaca.getName(), requestBodyFotoPlaca);
		}
		//assinatura
		File assinatura = new File(check.getCaminhoArquivoImagemAssinaturaExecutor());
		RequestBody requestBodyAssinatura = RequestBody.create(MediaType.parse("multipart/form-data"), assinatura);
		MultipartBody.Part imageFileBodyAssinatura = MultipartBody.Part.createFormData("assinatura", assinatura.getName(), requestBodyAssinatura);

		//Converter os anexos
		//MultipartBody requestBody = builderAnexos.build();



		//Log.e("aaaa",sendCheck.save(valores.getInt("COD_CLIENTE"),1,check,ok,imageFileBodyFotoPlaca,imageFileBodyAssinatura).request().url().toString());
		final Integer cod = check.getCod();
		Call<Result> sendServer = sendCheck.save(valores.getInt("COD_CLIENTE"), 1, check, ok, imageFileBodyFotoPlaca, imageFileBodyAssinatura, map);
		sendServer.enqueue(new Callback<Result>() {
			@Override
			public void onResponse(Call<Result> call, Response<Result> response) {
				if (!response.isSuccessful()) {
					Log.i("FUDEU DNV", "ERRO: " + response.code());
					//respEnviaServer(false);
				} else {
					Log.e("vamos la", response.body().toString());
					if (Objects.equals(response.body().getResult(), "fail")) {
						respEnviaServer(false);
					} else {
						db.updateStatusCheck(cod);
						respEnviaServer(true);
					}
				}
				progressDialog.hide();

			}

			@Override
			public void onFailure(Call<Result> call, Throwable t) {

				Log.e(t.getLocalizedMessage(), t.getMessage());

				Log.e("olha aqui", "aaa");
				progressDialog.hide();
				respEnviaServer(false);
			}

		});
	}

	private void respEnviaServer(Boolean result) {
		if (result) {
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.checked)
					.setTitle(R.string.send)
					.setMessage(R.string.sucess_send)
					.setNeutralButton("ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							//finish();
							endList = db.getCheckEndList();
							loadList(endList);
						}
					})
					.show();
		} else {
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.cancel)
					.setTitle(R.string.send)
					.setMessage(R.string.erro_save_check)
					.setNeutralButton(R.string.try_again, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							getChecksToSendServer();
						}
					})
					.setPositiveButton(getString(R.string.later_send), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							finish();
						}
					})
					.show();
		}

	}

	private void loadList(ArrayList<CheckLists> endList) {
		SendArrayAdapter adapter = new SendArrayAdapter(this, endList);
		listView.setAdapter(adapter);
	}

	private List<Integer> getSelectedItens() {
		List<Integer> list = new ArrayList<>();
		int tamanho = listView.getCount();
		for (int i = 0; i < tamanho; i++) {
			View view = listView.getChildAt(i);
			CheckBox check = (CheckBox) view.findViewById(R.id.checkBox);
			if (check.isChecked()) {
				System.out.println("item");
				CheckLists checklist = (CheckLists) listView.getItemAtPosition(i);
				list.add(checklist.getCod());
			}
		}

		return list;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:

				//Intent mIntent = new Intent(this, LoginActivity.class);;
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);

		}
	}

	public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
		Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(newBitmap);
		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(bitmap, 0, 0, null);
		OutputStream stream = new FileOutputStream(photo);
		newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
		stream.close();
	}
}
