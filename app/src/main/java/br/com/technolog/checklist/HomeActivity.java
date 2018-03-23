package br.com.technolog.checklist;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import models.GrupoItemVistoria;
import models.ItensVistoria;
import models.LocalizacaoItemVistoria;
import models.ModelosCheckList;
import models.RelModeloCheckListImagemEsquema;
import models.RelModeloCheckListItensVistoria;
import models.RelModeloCheckListVeiculos;
import models.ServiceSyncResp;
import models.Usuarios;
import models.Veiculos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.BancoDados;
import utility.SyncService;
import utility.Utilidades;

public class HomeActivity extends AppCompatActivity {
	static int count = 0;
	Bundle valores;
	ProgressDialog mProgressDialog;
	ProgressDialog pd;
	BancoDados bd;
	private TextView textFooterModulo;
	private String usuario;
	private String codigoImagem;
	private Button btnNewCheck;
	private Button btnSavedCheck;
	//private Button btnSync;
	private Button btnUpload;
	private Button btnCheckSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bd = new BancoDados(getApplicationContext()); //criando banco interno
		setContentView(R.layout.activity_home);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.Checklist));

		final Intent intent1 = getIntent();
		valores = intent1.getExtras();
		TextView textFooterUser = (TextView) findViewById(R.id.textFooterUser);

		Sincronizar();

		usuario = valores.getString("LOGIN") + " | " + valores.getString("RAZAO_SOCIAL");
		textFooterUser.setText(usuario);
		TextView textFooterData = (TextView) findViewById(R.id.textFooterData);
		textFooterData.setText(Utilidades.getDataHora("dd/MM/yyyy"));
		getSupportActionBar().setHomeButtonEnabled(true);
		btnNewCheck = (Button) findViewById(R.id.btnNewCheck);
		btnSavedCheck = (Button) findViewById(R.id.btnRecCheck);
		//btnSync = (Button) findViewById(R.id.btnSync);
		btnUpload = (Button) findViewById(R.id.btnUpload);
		btnCheckSend = (Button) findViewById(R.id.btnRecSend);
		//btnSavedCheck.setEnabled(false);
		//btnSavedCheck.setBackgroundColor(getResources().getColor(R.color.colorGrey));
		//btnUpload.setEnabled(false);
		//btnUpload.setBackgroundColor(getResources().getColor(R.color.colorGrey));
		btnNewCheck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), ListActivity.class);
				Bundle params = intent1.getExtras();
				params.putString("user", usuario);
				intent.putExtras(params);
				startActivity(intent);
			}
		});

//		btnSync.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Sincronizar();
//			}
//		});

		btnUpload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), SendToServerActivity.class);
				Bundle params = intent1.getExtras();
				params.putString("user", usuario);
				intent.putExtras(params);
				startActivity(intent);

			}
		});
		btnSavedCheck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), ListCheckSavedActivity.class);
				Bundle params = intent1.getExtras();
				params.putString("user", usuario);
				intent.putExtras(params);
				startActivity(intent);
			}
		});

		btnCheckSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(), ListCheckSendActivity.class);
				Bundle params = intent1.getExtras();
				params.putString("user", usuario);
				intent.putExtras(params);
				startActivity(intent);
			}
		});

	}

	private void alert() {
		new AlertDialog.Builder(this)
				.setIcon(R.drawable.cancel)
				.setTitle("Alerta!")
				.setMessage("Função não implementada!")
				.setNegativeButton("OK", null)
				.show();
	}

	public void Sincronizar() {
		//btnSync.setEnabled(false);
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setMessage("Sincronizando...");
		mProgressDialog.setCancelable(false);
		mProgressDialog.show();

		//Abre uma conexão com o servidor e recupera campos do checklist
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(SyncService.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		SyncService service = retrofit.create(SyncService.class);
		Call<ServiceSyncResp> requestSync = service.sincro(valores.getInt("COD_CLIENTE"));
		requestSync.enqueue(new Callback<ServiceSyncResp>() {
			@Override
			public void onResponse(Call<ServiceSyncResp> call, Response<ServiceSyncResp> response) {
				if (!response.isSuccessful()) {

					alertSync(false);
				} else {
					ServiceSyncResp resposta = response.body();

					mProgressDialog.setMessage("Sincronizando itens...");
					for (ItensVistoria i : resposta.itensVistoriaList) {

						bd.insereItensVistoria(i);

					}
					for (RelModeloCheckListItensVistoria i : resposta.relModeloCheckListItensVistoriaList) {

						bd.insereRelModeloCheckListItensVistoria(i);

					}
					for (GrupoItemVistoria i : resposta.grupoItemVistoriaList) {
						bd.insereGrupoItemVistoria(i);

					}
					for (LocalizacaoItemVistoria i : resposta.localizacaoItemVistoriaList) {
						bd.insereLocalizacaoItemVistoria(i);

					}
					mProgressDialog.setMessage("Sincronizando modelos...");
					for (ModelosCheckList i : resposta.modelosCheckListList) {
						bd.insereModelosCheckList(i);

					}

					for (RelModeloCheckListVeiculos i : resposta.relModeloCheckListVeiculosList) {
						bd.insereRelModeloCheckListVeiculos(i);

					}
					for (RelModeloCheckListImagemEsquema i : resposta.relModeloCheckListImagemEsquemaList) {
						bd.insereRelModeloCheckListImagemEsquema(i);

					}
					System.out.println(resposta.veiculosList.size());
					mProgressDialog.setMessage("Sincronizando veículos...");
					for (Veiculos i : resposta.veiculosList) {
						bd.insereVeiculos(i);

					}
					for (Usuarios i : resposta.users) {
						bd.insereUsuarios(i);

					}
					mProgressDialog.setMessage("Recebendo imagens de esquema...");
					for (RelModeloCheckListVeiculos.ImagensEsquema i : resposta.imagensEsquemaList) {

						imgDownload(i.getLinkImagem(), i.getCodigoImagem());
						//i.setCaminhoArquivoImagem();
						i.setCaminhoArquivoImagem(Environment.getExternalStoragePublicDirectory(
								Environment.DIRECTORY_PICTURES) + "/" + i.getCodigoImagem() + ".jpg");
						bd.insereImagensEsquema(i);
					}


					bd.closeDB();
					if (mProgressDialog.isShowing())
						mProgressDialog.dismiss();
					alertSync(true);
				}
			}

			@Override
			public void onFailure(Call<ServiceSyncResp> call, Throwable t) {
				if (mProgressDialog.isShowing())
					mProgressDialog.dismiss();
				alertSync(false);
			}

		});
	}


	private void imgDownload(String linkImagem, String nameForImagem) {
		Log.e("Link correto ? ", "imgDownload: " + linkImagem + " Nome do item: " + nameForImagem);
		DownloadImage teste = new DownloadImage();
		teste.setNome(nameForImagem);
		teste.execute(linkImagem);
	}


	private void alertSync(boolean b) {
		if (b) {
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.checked)
					.setTitle("Alerta!")
					.setMessage("Sincronizado com sucesso!")
					.setNegativeButton("OK", null)
					.show();
		} else {
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.cancel)
					.setTitle("Alerta!")
					.setMessage("Não foi possivel sincronizar!")
					.setNegativeButton("OK", null)
					.show();
		}
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

			case android.R.id.home:

				//Intent mIntent = new Intent(this, LoginActivity.class);;

				//startActivity(mIntent);

				finish(); // Finaliza a Activity atual

				break;

			default:
				break;
		}
		return true;
	}

	// DownloadImage AsyncTask
	private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
		String nome;

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			//mProgressDialog = new ProgressDialog(HomeActivity.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Download imagens");
			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();
		}

		@Override
		protected Bitmap doInBackground(String... URL) {

			String imageURL = URL[0];

			Bitmap bitmap = null;
			try {
				// Download Image from URL
				InputStream input = new java.net.URL(imageURL).openStream();
				// Decode Bitmap
				bitmap = BitmapFactory.decodeStream(input);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// Set the bitmap into ImageView
			File path = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_PICTURES);

			if (!path.exists()) {
				Log.e("HUE", "Criou a pasta?:" + path.mkdirs());
			}
			File file = new File(path, this.getNome() + ".jpg");

			try {
				if (file.createNewFile()) {
					FileOutputStream ostream = new FileOutputStream(file);
					result.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
					ostream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Close progressdialog
			mProgressDialog.dismiss();
		}
	}
}

