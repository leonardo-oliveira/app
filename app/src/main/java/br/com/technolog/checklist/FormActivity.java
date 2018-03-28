package br.com.technolog.checklist;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import models.CheckLists;
import models.GrupoItemVistoria;
import models.ItensCheckList;
import models.Result;
import models.Usuarios;
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
import utility.GPSTracker;
import utility.ItensResp;
import utility.SendCheckService;
import utility.SyncService;
import utility.Utilidades;

import static utility.Utilidades.formatar;

public class FormActivity extends AppCompatActivity {

	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	static Integer CAMERA_PLACA = 40000;
	private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
	TabHost host;
	ListView listView;
	ArrayList<ArrayList<ItensCheckList>> eita = new ArrayList<>();
	itensChecklistArrayAdapter adapter;
	private String caminhoFotoVeiculo;
	private String caminhoAssinatura;
	private SignaturePad mSignaturePad;
	private Button mClearButton;
	private Button mSaveButton;
	private Button btnBack;
	private Button btnNext;
	private ImageButton btnGetPlaca;
	private Button btnSave;
	private Button btnEnd;
	private Bundle valores;
	private Integer nButton;
	private Integer atual;
	private Integer position;
	private Integer codigoCheck;
	private BancoDados db;
	private String imageTempName = "NomTemporario";
	private ArrayList<ItensCheckList> itensCheckList = new ArrayList<>();
	private ArrayList<GrupoItemVistoria> grupoItens = new ArrayList<>();
	private String naoPreenchidos;
	private Uri imageUri;
	private ContentValues values;
	private String latInicio;
	private String longInicio;
	private String dataInicio;
	private String comentario;
	private EditText comentEdit;
	private EditText manifestoEdit;
	private EditText sistemasEdit;
	private EditText conhecimentoEdit;
	private Boolean flgSmtReboque;
	private Integer flgStatCheck;
	private TextView textVolumeInicio;
	private TextView textStatusInicio;
	private ProgressDialog saveProgressDialog;
	private boolean enviado;
	private TextView statusCheck;
	private Spinner sp_engate1;
	private Spinner sp_engate2;
	private Spinner sp_engate3;

	//private EditText kmAtual;

	/**
	 * Checks if the app has permission to write to device storage
	 * <p/>
	 * If the app does not has permission then the user will be prompted to grant permissions
	 *
	 * @param activity the activity from which permissions are checked
	 */
	public static void verifyStoragePermissions(Activity activity) {
		// Check if we have write permission
		int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

		if (permission != PackageManager.PERMISSION_GRANTED) {
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(
					activity,
					PERMISSIONS_STORAGE,
					REQUEST_EXTERNAL_STORAGE
			);
		}
	}

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
		final Intent intent1 = getIntent();
		valores = intent1.getExtras();

		enviado = false;

		setContentView(R.layout.activity_form);
		assert getSupportActionBar() != null;

		sp_engate1 = (Spinner) findViewById(R.id.sp_engate1);
		sp_engate2 = (Spinner) findViewById(R.id.sp_engate2);
		sp_engate3 = (Spinner) findViewById(R.id.sp_engate3);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle(getString(R.string.preenchimento));

		nButton = 0;
		TextView textFooterData = (TextView) findViewById(R.id.textFooterData);
		TextView textFooterUser = (TextView) findViewById(R.id.textFooterUser);

		String user = valores.getString("LOGIN") + " | " + valores.getString("RAZAO_SOCIAL");
		textFooterUser.setText(user);

		TextView textFooterModulo = (TextView) findViewById(R.id.textFooterModulo);
		TextView textPlaca = (TextView) findViewById(R.id.placa);

		// = (EditText) findViewById(R.id.km);

		textFooterData.setText(Utilidades.getDataHora("dd/MM/yyyy"));

		btnGetPlaca = (ImageButton) findViewById(R.id.btnGetPlaca);
		TextView textInicioVistoria = (TextView) findViewById(R.id.textDataInicio);

		dataInicio = Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss");
		comentEdit = (EditText) findViewById(R.id.editTextComentario);

		textInicioVistoria.setText(Utilidades.getDataHora("dd/MM/yyyy HH:mm"));

		textVolumeInicio = (TextView) findViewById(R.id.textVolumeInicio);
		textStatusInicio = (TextView) findViewById(R.id.textStatusInicio);

		saveProgressDialog = new ProgressDialog(this);
		saveProgressDialog.setMessage("Salvando CheckList");
		saveProgressDialog.setCancelable(false);
		manifestoEdit = (EditText) findViewById(R.id.editTextNumManifesto);
		sistemasEdit = (EditText) findViewById(R.id.editTextSistemasTerceiros);
		conhecimentoEdit = (EditText) findViewById(R.id.editTextConhecimento);

		RadioGroup smtRboque = (RadioGroup) findViewById(R.id.reboqueRadio);
		RadioGroup statCheck = (RadioGroup) findViewById(R.id.statCheck);

		smtRboque.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				if (radioGroup.getCheckedRadioButtonId() == -1) {
					// no radio buttons are checked
				} else {
					if (findViewById(R.id.radioSim).getId() == radioGroup.getCheckedRadioButtonId()) {
						System.out.println("true");
						flgSmtReboque = true;
					} else if (findViewById(R.id.radioNao).getId() == radioGroup.getCheckedRadioButtonId()) {
						flgSmtReboque = false;
						System.out.println("false");
					} else {
						System.out.println("deu ruim");
					}
				}
			}
		});


		statCheck.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				if (radioGroup.getCheckedRadioButtonId() == -1) {
					// no radio buttons are checked
				} else {
					if (findViewById(R.id.aprovado).getId() == radioGroup.getCheckedRadioButtonId()) {
						flgStatCheck = 1;
					} else if (findViewById(R.id.reprovado).getId() == radioGroup.getCheckedRadioButtonId()) {
						flgStatCheck = 2;
					} else if (findViewById(R.id.indefinido).getId() == radioGroup.getCheckedRadioButtonId()) {
						flgStatCheck = 3;
					} else {
						System.out.println("deu ruim");
					}
				}
			}
		});
		//textFooterUser.setText("");
		GPSTracker gps = new GPSTracker(this);
		gps.getLocation();
		if (gps.canGetLocation()) {
			//set lat e long inicial
			try {
				latInicio = gps.getLatitude().toString();
				longInicio = gps.getLongitude().toString();
			} catch (NullPointerException e) {
				Log.e(e.getLocalizedMessage(), "onCreate: " + e.getMessage());
			}
		} else {
			Toast.makeText(getApplicationContext(), "Não foi possivel obter sua localização", Toast.LENGTH_LONG).show();
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_warning_amber_800_24dp)
					.setTitle("Atenção!")
					.setMessage("Não foi possivel obter sua localização. \nPor favor verifique nas configurações se a localização está habilitada!")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							finish();
						}
					})
					.show();

		}
		textFooterModulo.setText(R.string.modulo_check);
		db = new BancoDados(getApplicationContext());

		System.out.println(valores.getInt("COD_CLIENTE"));
		//System.out.println(valores.getInt("COD_CHECK"));
		textPlaca.setText(valores.getString("MODELO") +" - "+ valores.getString("PLACA"));

		host = (TabHost) findViewById(R.id.tabHost);
		host.setup();
		//Tab 1
		TabHost.TabSpec spec = host.newTabSpec(getString(R.string.general));
		spec.setContent(R.id.dados);
		spec.setIndicator(getString(R.string.general));
		host.addTab(spec);

		//Tab 2
		spec = host.newTabSpec(getString(R.string.check));
		spec.setContent(R.id.itens);
		spec.setIndicator(getString(R.string.check));
		host.addTab(spec);
		listView = (ListView) findViewById(R.id.lista2);

		//Tab 3
		spec = host.newTabSpec(getString(R.string.confirm));
		spec.setContent(R.id.Finalização);
		spec.setIndicator(getString(R.string.confirm));
		host.addTab(spec);

		//Tab 4
		spec = host.newTabSpec(getString(R.string.status));
		spec.setContent(R.id.status);
		spec.setIndicator(getString(R.string.status));
		host.addTab(spec);

		//Tab 5
		spec = host.newTabSpec(getString(R.string.engates));
		spec.setContent(R.id.engates);
		spec.setIndicator(getString(R.string.engates));
		host.addTab(spec);

		//função que define assinatura
		assinatura();
		statusCheck = (TextView) findViewById(R.id.checkListStatus);
		btnBack = (Button) findViewById(R.id.btnBack);
		btnNext = (Button) findViewById(R.id.btnNext);
		btnSave = (Button) findViewById(R.id.btnSave);
		btnEnd = (Button) findViewById(R.id.btnEnd);
		checkList(valores.getInt("COD_CHECK"));
	}

	private void checkList(Integer codCheck) {
		if (isOnline()) {
			//TODO: Chamar metodos do retrofit que procura o checkList
			Log.e("CODIGO QUE VEM", "" + codCheck);
			itensCheckList = db.getItens(codCheck);
			setItens(itensCheckList);

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(SyncService.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
			SyncService service = retrofit.create(SyncService.class);
			int codVeic = valores.getInt("COD_VEICULO_W");
			String date = Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss");
			Call<ItensResp> serviceResquest = service.getItens("itens", codVeic, date, codCheck, valores.getInt("COD_CLIENTE"));

			serviceResquest.enqueue(new Callback<ItensResp>() {
				@Override
				public void onResponse(Call<ItensResp> call, Response<ItensResp> response) {
					if (response.isSuccessful()) {
						System.out.println(response.body().itensList.size());
						itensCheckList = response.body().itensList;
						System.out.println();
						System.out.println(response.body().volumeTanque);
						System.out.println(response.body().data_info);
						String data = response.body().data_info;
						NumberFormat formatarFloat = new DecimalFormat("0.00");
						String dataFormat = formatar(data);
						textStatusInicio.setText(formatarFloat.format(response.body().statusTanque) + "% " + dataFormat);
						textVolumeInicio.setText(response.body().volumeTanque + " Litros " + dataFormat);
						//setItens(itensCheckList);

					} else {
						//RESPOSTA FAIL ACHOU O SERVIDOR

					}
				}

				@Override
				public void onFailure(Call<ItensResp> call, Throwable t) {
					//FALHA AO CHAMAR
					Log.e("ERRO", "NAO ACHOU O SERVER");
				}
			});


		} else {
			textVolumeInicio.setText(R.string.erro_info_offline);
			textStatusInicio.setText(R.string.erro_info_offline);
			Log.e("CODIGO QUE VEM", "" + codCheck);
			itensCheckList = db.getItens(codCheck);
			setItens(itensCheckList);
		}
	}

	private void setItens(ArrayList<ItensCheckList> itensCheckList) {
		Integer tamanho = itensCheckList.size();
		Log.e("ta dando erro aqui", "" + tamanho);
		if (tamanho > 0) {
			ArrayList<Integer> codigosGrupos = new ArrayList<>();
			for (int i = 0; i < tamanho; i++) {
				if (!codigosGrupos.contains(itensCheckList.get(i).getCodGrupoItem())) {
					codigosGrupos.add(itensCheckList.get(i).getCodGrupoItem());
				}
			}
			//TODO: Modificar para buscar no servidor
			grupoItens = db.getGrupoItem(codigosGrupos);
			System.out.println(grupoItens.size());
			popular(itensCheckList, codigosGrupos);
			botoes();
		}

	}

	/**
	 * @param itensList     Lista de itens que pertecem ao checklist
	 * @param codigosGrupos Lista de grupos que pertecem ao checklist
	 */
	private void popular(ArrayList<ItensCheckList> itensList, ArrayList<Integer> codigosGrupos) {
		for (int i = 0; i < codigosGrupos.size(); i++) {
			ArrayList<ItensCheckList> list = new ArrayList<>();
			for (int j = 0; j < itensList.size(); j++) {
				if (itensList.get(j).getCodGrupoItem() == codigosGrupos.get(i)) {
					list.add(itensList.get(j));
				}
			}
			eita.add(list);
		}

	}

	/**
	 * Define o que os botoes fazem
	 */
	private void botoes() {

		final HorizontalScrollView mHList = (HorizontalScrollView) findViewById(R.id.group);

		atual = 0;
		final Integer tamanho2 = grupoItens.size();
		TextView myAwesomeTextView = (TextView) findViewById(R.id.textNDN);
		myAwesomeTextView.setText(1 + " de " + tamanho2);
		//Criação do grupo de botões
		System.out.println(tamanho2);
		for (int i = 0; i < tamanho2; i++) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			Button btn = new Button(this);
			btn.setId(i);
			btn.setMinHeight(100);
			btn.setMinWidth(200);
			final int id_ = btn.getId();
			btn.setText(grupoItens.get(i).getNome());
			btn.setBackgroundResource(R.color.colorGreyBlue);
			btn.setMaxLines(10);
			btn.setMaxWidth((mHList.getWidth()) / 3);
			LinearLayout linear = (LinearLayout) findViewById(R.id.grupoItens);
			linear.addView(btn, params);
			Button btn1 = ((Button) findViewById(id_));
			final int finalI = i;
			btn1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					if (atual != id_) {
						Log.e("COMECA", "OS TSTS COMECAM AQUI");

						//ESQUEMA DE ATUALIZAR O VETOR EITA
						nButton = id_;
						findViewById(id_).setBackgroundResource(R.color.colorPrimary);
						findViewById(atual).setBackgroundResource(R.color.colorGreyBlue);
						atual = id_;
						TextView myAwesomeTextView = (TextView) findViewById(R.id.textNDN);
						myAwesomeTextView.setText((id_ + 1) + " de " + tamanho2);
						Integer tamanho = (mHList.getWidth()) / 3; // nitens
						mHList.scrollTo((tamanho * id_) - tamanho, 0);
						// Atualizar a list view para mostrar os itens
						contentList(id_);

					}
					Log.e("heuehueh", "Codigo: " + grupoItens.get(finalI).getCod());
				}
			});
		}

		//Botoes desabilitados inicialmente
		Button btn1;
		//btn1.setVisibility(View.INVISIBLE);
		btn1 = ((Button) findViewById(atual));
		btn1.setBackgroundResource(R.color.colorPrimary);
		btnBack.setEnabled(false);
		btnBack.setBackgroundResource(R.color.colorGrey);
		btnEnd.setEnabled(false);
		btnEnd.setBackgroundResource(R.color.colorGrey);

		//Botoes Listener
		//Botao back on click
		btnBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO: volta para o item anterior do checklist
				switch (host.getCurrentTab()) {
					case 0:
						break;
					case 1:
						if (nButton == 0) {
							btnBack.setEnabled(false);
							btnBack.setBackgroundResource(R.color.colorGrey);
							host.setCurrentTab(0);
						} else {
							nButton--;
							//findViewById(nButton).setBackgroundResource(R.color.colorPrimary);
							findViewById(nButton).performClick();
							//findViewById(atual).setBackgroundResource(R.color.colorGreyBlue);
							atual = nButton;
						}
						break;
					case 2:
						host.setCurrentTab(1);
						break;
					case 3:
						nButton = tamanho2;
						host.setCurrentTab(2);
						break;
                    case 4:
                        host.setCurrentTab(3);
                        break;
					default:
						break;
				}

			}
		});

		//btngetplaca
		btnGetPlaca.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				System.out.println("click");
				values = new ContentValues();
				values.put(MediaStore.Images.Media.TITLE, "New Picture");
				values.put(MediaStore.Images.Media.DESCRIPTION, "Placa");
				imageUri = getContentResolver().insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent, CAMERA_PLACA);
			}
		});

		//Botao Next on click
		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO: Vai para o proximo grupo de itens do checklist
				try {
					System.out.println(host.getCurrentTab());
					switch (host.getCurrentTab()) {
						case 0:
							host.setCurrentTab(1);
							btnBack.setEnabled(true);
							contentList(atual);
							btnBack.setBackgroundResource(R.color.colorPrimary);
							break;
						case 1:
							if (nButton == tamanho2 - 1) {
								host.setCurrentTab(2);
								//TODO: proxima
							} else {
								nButton++;
								findViewById(nButton).performClick();
								atual = nButton;
							}
							break;
						case 2:
							host.setCurrentTab(3);
							break;
						case 3:
                            host.setCurrentTab(4);
							break;
						default:
							System.out.println("error");
							break;
					}
				} catch (Throwable t) {
					System.out.println(t.toString());
				}
			}
		});
		//tab dados gerais on click

		//tab itens on click
		btnSave.setEnabled(true);
		//Botao save on click
		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				saveProgressDialog.show();
				salvarCheckList(1);

			}
		});
		//Botao end on click
		btnEnd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				comentario = comentEdit.getText().toString();

				// TODO: Envia para o servidor se estiver online ou salva no bd interno se estiver offline
				if (verificaCampos()) {
					new AlertDialog.Builder(view.getContext())
							.setIcon(R.drawable.ic_warning_amber_800_24dp)
							.setTitle("Atenção!")
							.setMessage("O processo de envio para o servidor pode ser demorado, " +
									"recomendamos que você esteja em uma rede estavel antes de começar o envio.\n" +
									"Se você estiver em uma rede movel ou instavel você pode salvar e enviar quando estiver em uma conexão estavel.")
							.setPositiveButton("Salvar e enviar agora!", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									//METODO PARA ENVIAR AO SERVIDOR
									enviaParaServer();
									makeToast("Enviando para o servidor");
								}
							})
							.setNeutralButton("Cancelar", null)
							.setNegativeButton("Salvar e enviar mais tarde", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {
									//TODO: METODO PARA SALVAR NO BD
									salvarCheckList(2);
									makeToast("Salvando");
								}
							})
							.show();
				}else {


					AlertDialog dialog = new AlertDialog.Builder(view.getContext())
							.setIcon(R.drawable.cancel)
							.setTitle(R.string.Atencao)
							.setMessage(getString(R.string.campos_nao) +
									naoPreenchidos)
							.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialogInterface, int i) {

								}
							})
							.show();

					TextView textView = (TextView) dialog.findViewById(android.R.id.message);
					textView.setMaxLines(8);
					textView.setScroller(new Scroller(view.getContext()));
					textView.setVerticalScrollBarEnabled(true);
					textView.setMovementMethod(new ScrollingMovementMethod());
				}
			}
		});

		host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {

				int i = host.getCurrentTab();
				switch (i) {
					case 0:
						btnBack.setEnabled(false);
						btnBack.setBackgroundResource(R.color.colorGrey);
						btnNext.setEnabled(true);
						btnNext.setBackgroundResource(R.color.colorPrimary);
						break;
					case 1:
						contentList(atual);
						btnNext.setEnabled(true);
						btnNext.setBackgroundResource(R.color.colorPrimary);
						btnBack.setEnabled(true);
						btnBack.setBackgroundResource(R.color.colorPrimary);
						break;
					case 4:
						btnNext.setEnabled(false);
						btnNext.setBackgroundResource(R.color.colorGrey);
						btnBack.setEnabled(true);
						btnBack.setBackgroundResource(R.color.colorPrimary);

						// TODO: Trocar essas duas linhas por uma logica que verifique se os formularios estão preenchidos

						btnEnd.setEnabled(true);
						btnEnd.setBackgroundResource(R.color.colorPrimary);
						break;

					default:
						btnNext.setEnabled(true);
						btnNext.setBackgroundResource(R.color.colorPrimary);
						btnBack.setEnabled(true);
						btnBack.setBackgroundResource(R.color.colorPrimary);
						break;
				}

			}
		});
	}

	private boolean enviaParaServer() { // botao de enviar chama a função
		final ProgressDialog progressDialog;
		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage(getString(R.string.string_title_upload_progressbar_));
		progressDialog.setCancelable(false);
		progressDialog.show();

		//System.out.println(valores.getInt("COD_CLIENTE"));

		CheckLists check = new CheckLists(); // criando o checklist
		//Dados gerais
		check.setComentarios(comentario);
		check.setCod(codigoCheck);
		check.setCodVeiculoW(valores.getInt("COD_VEICULO_W"));
		check.setCaminhoArquivoImagemAssinaturaExecutor(caminhoAssinatura);
		check.setCaminhoArquivoFotoPlacaVeiculo(caminhoFotoVeiculo);
		check.setPlacaVeiculo(valores.getString("PLACA"));
		check.setDataInicioVistoria(dataInicio);
		check.setCodModeloCheckList(valores.getInt("COD_CHECK"));
		Log.e("ERRO AQUI OOOH", "" + valores.getInt("COD_CHECK"));
		check.setCodVeiculo(valores.getInt("COD_VEICULO"));
		check.setFlgAtivo(true);
		check.setFlgSomenteReboques(flgSmtReboque);
		check.setDataStatusPreenchimento(Utilidades.getDataHora("dd/MM/yyyy HH:mm:ss"));
		check.setStatusPreenchimento(3);

		Usuarios usuario = db.getUsuario(valores.getString("EMAIL"), valores.getString("SENHA"));
		check.setCodUsuarioClienteResp(Integer.parseInt(usuario.getCod()));

		//check.setKmAtual(Double.parseDouble(kmAtual.getText().toString()));


		check.setDataFimVistoria(Utilidades.getDataHora("dd/MM/yyyy HH:mm:ss"));
		check.setDataStatusPreenchimento(Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss"));

		check.setLatitudeDispositivoCheckList(latInicio);
		check.setLongitudeDispositivoCheckList(longInicio);

		check.setDataFimVistoria(Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss"));
		check.setNumeroConhecimentoTransporte(conhecimentoEdit.getText().toString());
		check.setNumeroManifesto(manifestoEdit.getText().toString());
		check.setIdentificadorSistemasTerceiros(sistemasEdit.getText().toString());

		check.setStatusCheckList(flgStatCheck);
		check.setTipoPreenchimento(1);
		check.setTipoSelecaoVeiculo(valores.getInt("TIPOSELECT"));

		int typeUser = valores.getInt("TIPO_USUARIO");
		System.out.println("tipo usuario: " + typeUser);
		if (typeUser == 1) {
			System.out.println("aqui");
			check.setCodUsuarioClienteVistoria(Integer.parseInt(valores.getString("COD_USUARIO")));
		} else if (typeUser == 2) {
			System.out.println("ALA");
			check.setIdentificadorDigitalMotoristaVistoria(valores.getString("COD_USUARIO"));
		} else {
			System.out.println("huebr deu ruim");
		}
		// Pegando anexos para enviar para o servidor
		//MultipartBody.Builder builderAnexos = new MultipartBody.Builder();
		//builderAnexos.setType(MediaType.parse("multipart/form-data"));
		List<MultipartBody.Part> map = new ArrayList<>();

		ArrayList<ItensCheckList> itens = new ArrayList<>();
		int tamanhoi = eita.size();
		for (int i = 0; i < tamanhoi; i++) {
			int tamanhoj = eita.get(i).size();
			for (int j = 0; j < tamanhoj; j++) {
				//eita.get(i).get(j).converter();
				itens.add(eita.get(i).get(j));
				if (eita.get(i).get(j).getOpcaoAnexo() != null) {

					File anexo = new File(eita.get(i).get(j).getOpcaoAnexo());
					BitmapFactory.Options bmOptions = new BitmapFactory.Options();

					Bitmap bitmap = BitmapFactory.decodeFile(eita.get(i).get(j).getOpcaoAnexo(), bmOptions);
					bitmap = getUnRotatedImage(eita.get(i).get(j).getOpcaoAnexo(), bitmap);
					try {
						saveBitmapToJPG(bitmap, anexo);
					} catch (IOException e) {
						e.printStackTrace();
					}
					RequestBody requestBodyAnexos = RequestBody.create(MediaType.parse("image/jpeg"), anexo);
					MultipartBody.Part anexoFileBody = MultipartBody.Part.createFormData(eita.get(i).get(j).getNomeItem(), anexo.getName(), requestBodyAnexos);
					map.add(anexoFileBody);
					//builderAnexos.addFormDataPart("anexos",anexo.getName(),requestBodyAnexos);
				}
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
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();

			Bitmap bitmap = BitmapFactory.decodeFile(check.getCaminhoArquivoFotoPlacaVeiculo(), bmOptions);
			bitmap = getUnRotatedImage(check.getCaminhoArquivoFotoPlacaVeiculo(), bitmap);
			try {
				saveBitmapToJPG(bitmap, ftPlaca);
			} catch (IOException e) {
				e.printStackTrace();
			}
			RequestBody requestBodyFotoPlaca = RequestBody.create(MediaType.parse("image/jpeg"), ftPlaca);
			imageFileBodyFotoPlaca = MultipartBody.Part.createFormData("ftPlaca", ftPlaca.getName(), requestBodyFotoPlaca);
		}
		//assinatura
		File assinatura = new File(check.getCaminhoArquivoImagemAssinaturaExecutor());
		RequestBody requestBodyAssinatura = RequestBody.create(MediaType.parse("multipart/form-data"), assinatura);
		MultipartBody.Part imageFileBodyAssinatura = MultipartBody.Part.createFormData("assinatura", assinatura.getName(), requestBodyAssinatura);

		//Converter os anexos
		//MultipartBody requestBody = builderAnexos.build();

		if (codigoCheck == null) {
			System.out.println("entrou la");
			Long valor = db.saveCheckList(eita, check);
			codigoCheck = valor.intValue();

		} else {
			check.setCod(codigoCheck);
			System.out.println("entrou aqui");
			db.updateCheckList(eita, check);
		}

		//Log.e("aaaa",sendCheck.save(valores.getInt("COD_CLIENTE"),1,check,ok,imageFileBodyFotoPlaca,imageFileBodyAssinatura).request().url().toString());
		Call<Result> sendServer = sendCheck.save(valores.getInt("COD_CLIENTE"), 1, check, ok, imageFileBodyFotoPlaca, imageFileBodyAssinatura, map);
		sendServer.enqueue(new Callback<Result>() {
			@Override
			public void onResponse(Call<Result> call, Response<Result> response) {
				if (!response.isSuccessful()) {
					Log.i("FUDEU DNV", "ERRO: " + response.code());
					salvarCheckList(2);
					respEnviaServer(false);
				} else {
					Log.e("vamos la", response.body().toString());
					if (Objects.equals(response.body().getResult(), "fail")) {
						respEnviaServer(false);
						salvarCheckList(2);
						statusCheck.setText(R.string.checklist_n_o_enviado);
					} else {
						respEnviaServer(true);
						enviado = true;
						statusCheck.setText(R.string.check_enviado);
					}
				}
				progressDialog.hide();

			}

			@Override
			public void onFailure(Call<Result> call, Throwable t) { //caso não consiga subir para o servidor vai gravar n

				//Log.e(t.getLocalizedMessage(), t.getMessage());
				progressDialog.hide();
				respEnviaServer(false);
				statusCheck.setText(R.string.checklist_n_o_enviado);
				salvarCheckList(2);
			}


		});

		return true;
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
							Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
							startActivity(intent);
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
							enviaParaServer();
						}
					})
					.setPositiveButton(getString(R.string.send_later), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							salvarCheckList(2);
							makeToast("Salvando");
						}
					})
					.show();
		}

	}

	/**
	 * @param preenchimento recebe 1 ser estiver salvo,
	 *                      2 se estiver encerrrado,
	 *                      3 se estiver sido enviado ao servidor
	 */
	private void salvarCheckList(Integer preenchimento) {
		saveProgressDialog.show();
		CheckLists check = new CheckLists();
		//Dados gerais
		//check.setCod(codigoCheck);
		check.setCodModeloCheckList(valores.getInt("COD_CHECK"));
		check.setCodVeiculo(valores.getInt("COD_VEICULO"));
		check.setCodVeiculoW(valores.getInt("COD_VEICULO_W"));

		check.setCaminhoArquivoImagemAssinaturaExecutor(caminhoAssinatura);
		check.setCaminhoArquivoFotoPlacaVeiculo(caminhoFotoVeiculo);
		check.setPlacaVeiculo(valores.getString("PLACA"));
		check.setDataInicioVistoria(dataInicio);
		check.setFlgSomenteReboques(flgSmtReboque);
		Log.e("teste pra q te quero", "salvarCheckList: " + flgSmtReboque);
		check.setFlgAtivo(true);

		check.setDataStatusPreenchimento(Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss"));
		check.setStatusPreenchimento(preenchimento);
		check.setLatitudeVeiculoInicioVistoria(latInicio);
		check.setLongitudeVeiculoInicioVistoria(longInicio);
		check.setComentarios(comentEdit.getText().toString());
		check.setNumeroConhecimentoTransporte(conhecimentoEdit.getText().toString());
		check.setNumeroManifesto(manifestoEdit.getText().toString());
		check.setIdentificadorSistemasTerceiros(sistemasEdit.getText().toString());
		check.setTipoPreenchimento(1);
		check.setStatusCheckList(flgStatCheck);

		//TODO: Alterações
		check.setDataAcao(Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss"));

		check.setTipoSelecaoVeiculo(valores.getInt("TIPOSELECT"));
		GPSTracker gps = new GPSTracker(this);
		gps.getLocation();
		if (gps.canGetLocation()) {
			//set lat e long inicial
			try {
				check.setLatitudeDispositivoCheckList(gps.getLatitude().toString());
				check.setLongitudeDispositivoCheckList(gps.getLongitude().toString());
			} catch (NullPointerException e) {
				Log.e(e.getLocalizedMessage(), "onCreate: " + e.getMessage());
			}
		}
		if (preenchimento == 2) {
			check.setDataFimVistoria(Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss"));
			check.setLatitudeVeiculoFimVistoria(gps.getLatitude().toString());
			check.setLongitudeVeiculoFimVistoria(gps.getLongitude().toString());
			//TODO: GET LAT LONG;
		} else {
			//Dados finalizacao
			check.setStatusPreenchimento(1);
			check.setDataFimVistoria(null);
		}
		check.setTipoSelecaoVeiculo(valores.getInt("TIPOSELECT"));
		int typeUser = valores.getInt("TIPO_USUARIO");
		System.out.println("tipo usuario: " + typeUser);
		if (typeUser == 1) {
			System.out.println("aqui");
			check.setCodUsuarioClienteVistoria(Integer.parseInt(valores.getString("COD_USUARIO")));
		} else if (typeUser == 2) {
			System.out.println("ALA");
			check.setIdentificadorDigitalMotoristaVistoria(valores.getString("COD_USUARIO"));
		} else {
			System.out.println("huebr deu ruim");
		}

		System.out.println(codigoCheck);
		if (codigoCheck == null) {
			System.out.println("entrou la");
			Long valor = db.saveCheckList(eita, check);
			codigoCheck = valor.intValue();

		} else {
			check.setCod(codigoCheck);
			System.out.println("entrou aqui");
			db.updateCheckList(eita, check);
		}

		statusCheck.setText(R.string.check_saved);

		new AlertDialog.Builder(this)
				.setIcon(R.drawable.checked)
				.setTitle("Checklist salvo!")
				.setMessage("O checkList foi salvo com sucesso!\nDeseja sair ?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
						startActivity(intent);
					}

				})
				.setNegativeButton("Não", null)
				.show();

		saveProgressDialog.hide();
	}

	private boolean verificaCampos() {
		naoPreenchidos = "";
		boolean retorno = true;
		/*
		if (caminhoFotoVeiculo == null) {
			naoPreenchidos += getString(R.string.ft_placa);
			retorno = false;
		}*/
		if (flgStatCheck == null) {
			naoPreenchidos += getString(R.string.stat_check);
			retorno = false;
		}
		if (flgSmtReboque == null) {
			naoPreenchidos += getString(R.string.only_rbt);
			retorno = false;
		}
		if (caminhoAssinatura == null) {
			naoPreenchidos += getString(R.string.assi_img);
			retorno = false;
		}
		int tamanho = eita.size();
		for (int i = 0; i < tamanho; i++) {
			int tamanho2 = eita.get(i).size();
			for (int j = 0; j < tamanho2; j++) {
				if (!eita.get(i).get(j).getCampoSelecionado()) {
					naoPreenchidos += eita.get(i).get(j).getNomeItem() + "\n";
					retorno = false;
					//return false;
				}
			}
		}
		return retorno;
	}

	private void makeToast(String texto) {
		Toast.makeText(this,
				texto, Toast.LENGTH_SHORT).show();
	}

	private void contentList(Integer id) {
		listView = (ListView) findViewById(R.id.lista2);
		adapter = new itensChecklistArrayAdapter(getApplicationContext(), eita.get(id), this);
		listView.setAdapter(adapter);

	}

	public View getViewByPosition(int position, ListView listView) {
		final int firstListItemPosition = listView.getFirstVisiblePosition();
		final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

		if (position < firstListItemPosition || position > lastListItemPosition) {
			return listView.getAdapter().getView(position, listView.getChildAt(position), listView);
		} else {
			final int childIndex = position - firstListItemPosition;
			return listView.getChildAt(childIndex);
		}
	}

	private void setValues(ArrayList<ItensCheckList> itens) {
		eita.set(atual, itens);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       @NonNull String permissions[], @NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_EXTERNAL_STORAGE: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length <= 0
						|| grantResults[0] != PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(FormActivity.this, "Sem permissão para salvar a foto", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.esquemas, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(R.drawable.cancel)
				.setTitle("Checklist não salvo!")
				.setMessage("Você realmente deseja sair sem salvar?")
				.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}

				})
				.setNegativeButton("Não", null)
				.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:

				//Intent mIntent = new Intent(this, LoginActivity.class);;

				//startActivity(mIntent);
				new AlertDialog.Builder(this)
						.setIcon(R.drawable.cancel)
						.setTitle("Checklist não salvo!")
						.setMessage("Você realmente deseja sair sem salvar?")
						.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								finish();
							}

						})
						.setNegativeButton("Não", null)
						.show();
				return true;

			case R.id.action_esquemas:
				Intent intent = new Intent(this, EsquemasActivity.class);
				System.out.println(valores.getInt("COD_CHECK"));
				int cod = valores.getInt("COD_CHECK");
				intent.putExtra("modelo", cod);
				startActivity(intent);

				return true;
			default:
				return super.onOptionsItemSelected(item);

		}
	}

	private void assinatura() {
		//Assinatura
		mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
		mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {

			@Override
			public void onStartSigning() {
				//Toast.makeText(FormActivity.this, "Começou a escrever", Toast.LENGTH_SHORT).show();
				mSaveButton.setVisibility(View.VISIBLE);
				mSaveButton.setClickable(true);
			}

			@Override
			public void onSigned() {
				//Event triggered when the pad is signed
				mSaveButton.setEnabled(true);
				mClearButton.setEnabled(true);
			}

			@Override
			public void onClear() {
				mSaveButton.setEnabled(false);
				mClearButton.setEnabled(false);
			}
		});

		mClearButton = (Button) findViewById(R.id.clear_button);
		mSaveButton = (Button) findViewById(R.id.save_button);
		//mSaveButton.setHighlightColor(getResources().getColor(R.color.colorPrimary));
		mClearButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mSignaturePad.clear();
				mSaveButton.setClickable(true);
				mClearButton.setClickable(true);
			}
		});

		mSaveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
				if (addJpgSignatureToGallery(signatureBitmap)) {
					Toast.makeText(FormActivity.this, getString(R.string.sucessoSalvar), Toast.LENGTH_SHORT).show();
					//mSignaturePad.setVisibility(View.INVISIBLE);
					//mClearButton.setVisibility(View.INVISIBLE);
					mSaveButton.setClickable(false);
					mClearButton.setClickable(true);

				} else {
					Toast.makeText(FormActivity.this, getString(R.string.naoSalvar), Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	public File getAlbumStorageDir() {
		// Get the directory for the user's public pictures directory.
		File file = new File(Environment.getExternalStoragePublicDirectory(
				Environment.DIRECTORY_PICTURES), "Assinaturas");
		if (!file.mkdirs()) {
			Log.e("SignaturePad", "Directory not created");
		}
		return file;
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

	public boolean addJpgSignatureToGallery(Bitmap signature) {
		boolean result = false;
		try {
			File photo = new File(getAlbumStorageDir(), String.format("Assinatura_%d.jpg", System.currentTimeMillis()));
			saveBitmapToJPG(signature, photo);
			System.out.println();
			scanMediaFile(photo);
			caminhoAssinatura = getRealPathFromURI(getImageUri(this, signature, "assinatura"));
			Log.e("teste MAROTO", caminhoAssinatura);
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void scanMediaFile(File photo) {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri contentUri = Uri.fromFile(photo);
		mediaScanIntent.setData(contentUri);
		FormActivity.this.sendBroadcast(mediaScanIntent);
	}

	public boolean isOnline() {
		ConnectivityManager cm =
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {

		Bitmap thumbnail;
		if (requestCode == CAMERA_PLACA) {
			if (resultCode == RESULT_OK) {
				try {
					thumbnail = MediaStore.Images.Media.getBitmap(
							getContentResolver(), imageUri);
					caminhoFotoVeiculo = getRealPathFromURI(imageUri);

					btnGetPlaca.setImageURI(imageUri);


					//onCameraPhoto(intent);
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				Toast.makeText(this, R.string.err_msg_salvar, Toast.LENGTH_SHORT).show();
			}
			return;
		}
		position = requestCode;
		if (resultCode == RESULT_OK) {
			Toast.makeText(this, R.string.msg_salvar, Toast.LENGTH_SHORT).show();
			//Uri image = intent.getData();
			//System.out.println(image.toString())
			try {
				imageUri = adapter.getImageUri();
				thumbnail = MediaStore.Images.Media.getBitmap(
						getContentResolver(), imageUri);

				String picturePath = getRealPathFromURI(imageUri);

				adapter.setImageInItem(position, thumbnail, picturePath);
				//onCaptureImageResult(intent);
			} catch (IOException e) {
				e.printStackTrace();
			}


		} else {
			Toast.makeText(this, R.string.err_msg_salvar, Toast.LENGTH_SHORT).show();
			adapter.setImageInItem(position, null, null);
		}
	}

	private void onCameraPhoto(Intent data) {
		Bundle extras = data.getExtras();
		Bitmap imageBitmap = (Bitmap) extras.get("data");
		// CALL THIS METHOD TO GET THE URI FROM THE BITMAP
		Uri tempUri = getImageUri(getApplicationContext(), imageBitmap, imageTempName);
		String picturePath = getRealPathFromURI(tempUri);
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		Bitmap bitmap = BitmapFactory.decodeFile(picturePath, bmOptions);
		Log.e("TAMANHO ANTES:", bitmap.getHeight() + " " + bitmap.getWidth());
		bitmap = Bitmap.createScaledBitmap(bitmap, bmOptions.outWidth, bmOptions.outHeight, true);
		Log.e("TAMANHO DPS:", bitmap.getHeight() + " " + bitmap.getWidth());
		btnGetPlaca.setImageBitmap(bitmap);
		caminhoFotoVeiculo = getRealPathFromURI(getImageUri(getApplicationContext(), imageBitmap, imageTempName));

		//adapter.setImageInItem(position, imageBitmap, picturePath);
	}

	private void onCaptureImageResult(Intent data) {
		Bundle extras = data.getExtras();
		Bitmap imageBitmap = (Bitmap) extras.get("data");

		// CALL THIS METHOD TO GET THE URI FROM THE BITMAP
		Uri tempUri = getImageUri(getApplicationContext(), imageBitmap, imageTempName);
		String picturePath = getRealPathFromURI(tempUri);

		adapter.setImageInItem(position, imageBitmap, picturePath);
	}

	public Uri getImageUri(Context inContext, Bitmap inImage, String imageName) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, imageName, null);
		System.out.println(path);
		return Uri.parse(path);
	}

	public String getRealPathFromURI(Uri uri) {

		Cursor cursor = getContentResolver().query(uri, null, null, null, null);
		assert cursor != null;
		cursor.moveToFirst();
		int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);

		return cursor.getString(idx);

	}

	public Bitmap convertSrcToBitmap(String imageSrc) {
		Bitmap myBitmap = null;
		File imgFile = new File(imageSrc);
		if (imgFile.exists()) {
			myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		}
		return myBitmap;
	}

}


