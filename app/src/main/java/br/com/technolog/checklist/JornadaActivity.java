package br.com.technolog.checklist;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import models.Combustivel;
import models.InfoJornada;
import models.JornadaServiceResp;
import models.LastAbast;
import models.LastStop;
import models.Motorista;
import models.RPM;
import models.TimeJorney;
import models.VeiculosUtilizado;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.JornadaService;

public class JornadaActivity extends AppCompatActivity {

	private static String link = "http://solutions.technolog.com.br/darwin/manager/arquivos/fotos_motoristas/";
	private ViewPager viewPager;
	private MyViewPagerAdapter myViewPagerAdapter;
	private LinearLayout dotsLayout;
	private TextView[] dots;
	private int[] layouts;
	private Motorista moto;
	private LineChart chart;
	private PieChart pieChart;
	private Bundle valores;
	private ImageView logoImage;
	private ImageView fotoMotorista;
	private TextView nomeMotorista;
	private TextView veiculoAtual;
	private TextView ultimoPoi;
	private TextView ultimaParada;
	private TextView veiculoAnterior;
	private TextView startMove;
	private InfoJornada jornada;
	private TextView intraJorney;
	private Combustivel comb;
	private LastAbast ultAbast;
	private PerfilCond perfil;
	private LastStop lastStop;
	private List<RPM> rpmList;
	private TextView timeDirIni;
	private TextView lastPoi;
	private ImageButton btnMacros;
	private ProgressDialog mProgressDialog;
	private TimeJorney timeJorney;
	long delay = 5 * 60 * 1000; // delay in milliseconds
	LoopTask task = new LoopTask();
	Timer timer = new Timer("TaskName");
    private ImageButton btnRoute;


    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jornada);
		nomeMotorista = (TextView) findViewById(R.id.nomeMotorista);
		veiculoAtual = (TextView) findViewById(R.id.veiculoAtual);
		ultimoPoi = (TextView) findViewById(R.id.textViewLocation);
		ultimaParada = (TextView) findViewById(R.id.textViewParada);
		veiculoAnterior = (TextView) findViewById(R.id.veiculoAnterior);
		startMove = (TextView) findViewById(R.id.textViewMove);
		intraJorney = (TextView) findViewById(R.id.textViewIntra);
		fotoMotorista = (ImageView) findViewById(R.id.fotoMotorista);
		timeDirIni = (TextView) findViewById(R.id.textInint);
		//btnMacros = (ImageButton) findViewById(R.id.imageMacros);
        btnRoute = (ImageButton) findViewById(R.id.imageRoute);
		lastPoi = (TextView) findViewById(R.id.textViewLocation) ;
		Intent intent1 = getIntent();
		valores = intent1.getExtras();
		String logo = valores.getString("LOGO");
		logo = logo.replace("/var/www/html/", "http://solutions.technolog.com.br/");
		logoImage = (ImageView) findViewById(R.id.logoCliente);
		Picasso.with(this).load(logo).into(logoImage);
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
		//R.layout.alert_jornada,
		layouts = new int[]{
				R.layout.info_combustivel,
				R.layout.perfil_cond,
				R.layout.gauge_jornada,
				R.layout.gauge_rpm
		};
        btnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("placa",veiculoAtual.getText().toString().trim());
				intent.putExtra("cod",valores.getInt("COD_CLIENTE"));
                startActivity(intent);
            }
        });
		// adding bottom dots
		addBottomDots(0);
		/*
		btnMacros.setVisibility(View.INVISIBLE);
		btnMacros.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AlertDialog.Builder builderSingle = new AlertDialog.Builder(JornadaActivity.this);
				builderSingle.setIcon(R.drawable.ic_warning_amber_800_24dp);
				builderSingle.setTitle("Selecione uma mensagem: ");

				final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(JornadaActivity.this, android.R.layout.select_dialog_singlechoice);
				arrayAdapter.add("Socorro");
				arrayAdapter.add("Quebrou ");
				arrayAdapter.add("Acidente na rodovida");
				arrayAdapter.add("Caminhão tombou");
				arrayAdapter.add("Queda de barreiras");

				builderSingle.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});


				builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String strName = arrayAdapter.getItem(which);
						AlertDialog.Builder builderInner = new AlertDialog.Builder(JornadaActivity.this);
						builderInner.setMessage(strName);
						builderInner.setTitle("Você selecionou:");
						builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.dismiss();
							}
						});
						builderInner.show();
					}
				});
				builderSingle.show();
			}
				
		});*/
		myViewPagerAdapter = new MyViewPagerAdapter();
		viewPager.setAdapter(myViewPagerAdapter);
		viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
		//getInfos();
		showDialog();
		start();
	}

	private void showDialog() {
		this.runOnUiThread(new Runnable() {
			public void run() {
				mProgressDialog = new ProgressDialog(JornadaActivity.this);
				mProgressDialog.setIndeterminate(true);
				mProgressDialog.setMessage("Carregando informações...");
				mProgressDialog.setCancelable(false);
				mProgressDialog.show();
			}
		});
	}


	private void getInfos() {
		try {
			OkHttpClient client = new OkHttpClient.Builder()
					.connectTimeout(100, TimeUnit.SECONDS)
					.readTimeout(100,TimeUnit.SECONDS).build();

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(JornadaService.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.client(client)
					.build();

			JornadaService service = retrofit.create(JornadaService.class);
			Call<JornadaServiceResp> requestJornada = service.jornada(valores.getInt("COD_CLIENTE"),
					valores.getString("COD_USUARIO"));
			requestJornada.enqueue(new Callback<JornadaServiceResp>() {
				@Override
				public void onResponse(Call<JornadaServiceResp> call, Response<JornadaServiceResp> response) {
					if (response.isSuccessful()) {
						JornadaServiceResp resp = response.body();
						moto = resp.getMotorista();
						jornada = resp.getInfoJornada();
						ultAbast = resp.getLastAbast();
						perfil = resp.getPerfil();
						lastStop = resp.getLastStop();
						comb = resp.getCombustivel();
						rpmList = resp.getRpm();
						timeJorney = resp.getTimeJorney();
						nomeMotorista.setText(moto.getNome());

						Picasso.with(getBaseContext())
								.load(link + moto.getCaminhoArquivoFoto())
								.into(fotoMotorista);
						List<VeiculosUtilizado> veic = resp.getVeiculos();
						//ultimoPoi.setText(lastStop.getInfo());
						ultimaParada.setText(lastStop.getInfo());

						Log.e("aquele teste para", "onResponse: "+lastStop.getInfo());
						veiculoAtual.setText(veic.get(0).getVehicleName());
						if(veic.size() > 1){
							veiculoAnterior.setText(veic.get(1).getVehicleName());
						} else{
                            veiculoAnterior.setText(R.string.none);
                        }
						startMove.setText(jornada.getInfos());
						intraJorney.setText(jornada.getInfoIntra());
						timeDirIni.setText(lastStop.getTempoSemParar());
						lastPoi.setText(resp.getLastPoi().getLastPoi());
						int pageBack = viewPager.getCurrentItem();
						viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % layouts.length);
						viewPager.setCurrentItem(pageBack);
						mProgressDialog.dismiss();
					}

				}

				@Override
				public void onFailure(Call<JornadaServiceResp> call, Throwable t) {
					Log.e("kkk", "onFailure: " + t);
					t.printStackTrace();
					mProgressDialog.dismiss();
					error();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			error();
			mProgressDialog.dismiss();
		} finally {
		}
	}





	private void error(){

		new AlertDialog.Builder(this)
				.setIcon(R.drawable.cancel)
				.setTitle(R.string.error_info)
				.setMessage(R.string.try_again)
				.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						showDialog();
						getInfos();
					}
				})
				.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						finish();
					}
				})
				.show();

	}

	
	private void addBottomDots(int currentPage) {
		dots = new TextView[layouts.length];

		int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
		int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

		dotsLayout.removeAllViews();
		for (int i = 0; i < dots.length; i++) {
			dots[i] = new TextView(this);
			dots[i].setText(Html.fromHtml("&#8226;"));
			dots[i].setTextSize(35);
			dots[i].setTextColor(colorsInactive[currentPage]);
			dotsLayout.addView(dots[i]);
		}

		if (dots.length > 0)
			dots[currentPage].setTextColor(colorsActive[currentPage]);
	}

	/*
	* Funções para chamar o webservice a cada 15 minutos
	*/

	public void start() {
		timer.cancel();
		timer = new Timer("TaskName");
		Date executionDate = new Date(); // no params = now
		timer.scheduleAtFixedRate(task, executionDate, delay);
	}

	private class LoopTask extends TimerTask {
		public void run() {
			Log.e("atualizou!!!", "run: w"+"ueue" );
			getInfos();
		}
	}


	//  viewpager change listener
	ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			addBottomDots(position);

			// changing the next button text 'NEXT' / 'GOT IT'

			if (position == layouts.length - 1) {
				// last page. make button text to GOT IT
				Log.e("teste", "onPageSelected: Qual pagina?" + position);

			} else {
				// still pages are left
			}

			//Swtich para verificar qual page está na view
			switch (layouts[position]) {

				case R.layout.info_combustivel:
					Log.e("teste", "onPageSelected: Qual pagina?" + position);
					//System.out.println("entrou 0");
					try {
						TextView fuel = (TextView) findViewById(R.id.volume_atual_infos);
						TextView consumo = (TextView) findViewById(R.id.consumo_info);
						TextView media = (TextView) findViewById(R.id.media_info);
						TextView lastAbast = (TextView) findViewById(R.id.last_abast);
						TextView autonomia = (TextView) findViewById(R.id.autonomia_info);fuel.setText(comb.getFuel());
						consumo.setText(comb.getConsumo());
						media.setText(comb.getMedia());
						lastAbast.setText(ultAbast.getInfo());

						Log.e("huehue", "onPageSelected: " + ultAbast.getInfo());
						autonomia.setText(comb.calcAutonomia());
					} catch (Exception e) {
						e.printStackTrace();
					}

					break;
				case R.layout.alert_jornada:
					Log.e("teste", "onPageSelected: Qual pagina?" + position);
					break;
				case R.layout.perfil_cond:
					try {
						Log.e("teste", "onPageSelected: Qual pagina?" + position);
						TextView picos = (TextView) findViewById(R.id.picos);
						TextView dentroFaixa = (TextView) findViewById(R.id.dentroFaixa);
						TextView foraFaixa = (TextView) findViewById(R.id.foraFaixa);
						TextView paradoLigado = (TextView) findViewById(R.id.paradoLigado);
						TextView kmPerco = (TextView) findViewById(R.id.kmPerco);
						TextView rolamento = (TextView) findViewById(R.id.textViewRolamento);
						picos.setText(comb.getPicos());
						dentroFaixa.setText(perfil.getFaixaEco());
						foraFaixa.setText(perfil.getForaFaixaEco());
						paradoLigado.setText(minutosHoras(Math.round(comb.getTempoParadoLigado())));
						kmPerco.setText(perfil.getKmPercorrido());
						rolamento.setText(perfil.getRolamentoString());
						try {
							System.out.println("entrou 2");
							Utils.init(getApplicationContext());
							chart = (LineChart) findViewById(R.id.chart);
							List<Entry> entries = new ArrayList<>();
							//Random gerador = new Random();

							for (int i = 0; i < 240; i++) {
								Integer y = rpmList.get(239-i).getRpm();
								entries.add(new Entry(i, y));
							}

							LineDataSet dataSet = new LineDataSet(entries, "RPM");
							dataSet.setDrawValues(false);
							dataSet.setLineWidth(2f);
							dataSet.setColor(getResources().getColor(R.color.colorAccent));
							dataSet.setDrawCircles(false);
							dataSet.setDrawCircleHole(false);
							LineData lineData = new LineData(dataSet);
							Description desc = new Description();
							desc.setText("RPM das últimas duas horas");
							chart.setDescription(desc);
							chart.setTouchEnabled(false);
							chart.setPinchZoom(false);
							chart.setScaleEnabled(false);
							chart.setAutoScaleMinMaxEnabled(true);
							chart.setDrawGridBackground(false);
							chart.setDrawBorders(false);
							chart.setDoubleTapToZoomEnabled(false);
							XAxis xAxis = chart.getXAxis();
							xAxis.setEnabled(false);
							xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
							xAxis.setValueFormatter(new MyValueFormatter());
							chart.setData(lineData);
							chart.invalidate();

						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case R.layout.gauge_jornada:
					try{
						pieChart = (PieChart) findViewById(R.id.pie_chart);
						pieChart.setCenterText("Jornada");
						float[] yData = {timeJorney.getTempoMov(), timeJorney.getTempoInter(),
								timeJorney.getTempoIntra(),timeJorney.getTempoAlmoco()};
						String[] xData = {"Tempo de movimento", "Interjornada" , "Intrajonada","Almoço"};

						ArrayList<PieEntry> yEntrys = new ArrayList<>();


						for (int i = 0; i <yData.length; i++) {
							PieEntry entry = new PieEntry(yData[i]);
							entry.setLabel(xData[i]);
							yEntrys.add(entry);
						}
						//create the data set
						PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
						//add colors to dataset
						ArrayList<Integer> colors = new ArrayList<>();
						colors.add(Color.BLUE);
						colors.add(Color.RED);
						colors.add(Color.GREEN);
						colors.add(Color.MAGENTA);
						pieDataSet.setColors(colors);
						pieDataSet.setValueLinePart1OffsetPercentage(90.f);
						pieDataSet.setValueLinePart1Length(0.5f);
						pieDataSet.setValueLinePart2Length(.3f);
						pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

						pieChart.setRotationEnabled(false);
						PieData pieData = new PieData(pieDataSet);
						Description desc = new Description();
						desc.setText("");
						pieChart.setUsePercentValues(true);
						pieChart.setDescription(desc);
						pieChart.setCenterTextColor(Color.BLACK);
						pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
						pieChart.setEntryLabelColor(Color.BLACK);
						pieDataSet.setValueTextColor(Color.BLACK);
						pieChart.spin( 500,0,-360f, Easing.EasingOption.EaseInOutQuad);
						pieChart.setData(pieData);
						pieChart.setNoDataText("Não conseguimos obter dados!");
						pieChart.invalidate();
						pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
							@Override
							public void onValueSelected(Entry e, Highlight h) {
								Toast toast = Toast.makeText(getApplicationContext(),
										minutosHoras((int) e.getY()) ,
										Toast.LENGTH_LONG);
								toast.show();

							}

							@Override
							public void onNothingSelected() {

							}
						});
					}catch (Exception e){
						e.printStackTrace();
					}
					break;
				case R.layout.gauge_rpm:
					try{
						pieChart = (PieChart) findViewById(R.id.pie_chart_rpm);
						pieChart.setCenterText("RPM");
						float[] yData = {perfil.getForaFaixaEcoInt(),perfil.getFaixaEcoInt()};
						String[] xData = {"Fora da faixa","Dentro da faixa"};

						ArrayList<PieEntry> yEntrys = new ArrayList<>();


						for (int i = 0; i <yData.length; i++) {
							PieEntry entry = new PieEntry(yData[i]);
							entry.setLabel(xData[i]);
							yEntrys.add(entry);
						}
						//create the data set
						PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
						//add colors to dataset
						ArrayList<Integer> colors = new ArrayList<>();
						colors.add(Color.RED);
						colors.add(Color.GREEN);
						colors.add(Color.YELLOW);
						pieDataSet.setColors(colors);
						pieDataSet.setValueLinePart1OffsetPercentage(90.f);
						pieDataSet.setValueLinePart1Length(0.5f);
						pieDataSet.setValueLinePart2Length(.3f);
						pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

						pieChart.setRotationEnabled(false);
						PieData pieData = new PieData(pieDataSet);
						Description desc = new Description();
						desc.setText("");
						pieChart.setUsePercentValues(true);
						pieChart.setDescription(desc);
						pieChart.setCenterTextColor(Color.BLACK);
						pieChart.setCenterTextTypeface(Typeface.DEFAULT_BOLD);
						pieChart.setEntryLabelColor(Color.BLACK);
						pieDataSet.setValueTextColor(Color.BLACK);
						pieChart.spin( 500,0,-360f, Easing.EasingOption.EaseInOutQuad);
						pieChart.setData(pieData);
						pieChart.setNoDataText("Não conseguimos obter dados!");
						pieChart.invalidate();
					}catch (Exception e){
						e.printStackTrace();
					}
					break;
				default:
					break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	/**
	 * View pager adapter
	 */
	public class MyViewPagerAdapter extends PagerAdapter {
		private LayoutInflater layoutInflater;

		public MyViewPagerAdapter() {
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View view = layoutInflater.inflate(layouts[position], container, false);
			container.addView(view);
			switch (layouts[position]) {
				case R.layout.info_combustivel:
					System.out.println("entrou 0");
					//texto.setText("kkk");
					break;
				case R.layout.alert_jornada:
					break;
				case R.layout.perfil_cond:
					break;
				default:
					break;
			}
			return view;
		}

		@Override
		public int getCount() {
			return layouts.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}


		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = (View) object;
			container.removeView(view);
		}
	}

	public String minutosHoras(Integer minutos){
		Integer horas= minutos/60;
		Integer newMinutes = minutos%60;
		if(horas == 1){
			return String.valueOf(horas)+" hora e "+String.valueOf(newMinutes)+" minutos";
		}else if(horas == 0){
			return String.valueOf(newMinutes)+" minutos";
		}
		return String.valueOf(horas)+" horas e "+String.valueOf(newMinutes)+" minutos";
	}
}
