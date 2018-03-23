package br.com.technolog.checklist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import models.ModelosCheckList;
import models.NextMe;
import models.NextMeResp;
import models.Veiculos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.BancoDados;
import utility.GPSTracker;
import utility.ListVeic;
import utility.ModelosCheckListResp;
import utility.SyncService;
import utility.Utilidades;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ProgressDialog mProgressDialog;
    ArrayList<NextMe> arrayResp = new ArrayList<>();
    GPSTracker gps;
    private String[] values;
    private BancoDados db;
    private Bundle valores;
    private TextView head;
    private Integer codVeiculo;
    private Integer codVeiculoW;
    private ArrayList<Veiculos> veiculosList;
	private FloatingActionButton fab;
	private TextView placaHead;
	private Integer tipoSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        assert getSupportActionBar() != null;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.veiculos));

        TextView textFooterData = (TextView) findViewById(R.id.textFooterData);
        TextView textFooterUser = (TextView) findViewById(R.id.textFooterUser);
        TextView textFooterModulo = (TextView) findViewById(R.id.textFooterModulo);

        listView = (ListView) findViewById(R.id.lista); // referecia aolistView

        textFooterData.setText(Utilidades.getDataHora("dd/MM/yyyy"));

        head = (TextView) findViewById(R.id.textViewHead);
	    placaHead = (TextView) findViewById(R.id.textPlacaShow);

	    final Intent intent1 = getIntent();

	    valores = intent1.getExtras();

	    textFooterUser.setText(valores.getString("user"));
	    textFooterModulo.setText(R.string.checklist_modulo);
	    listView = (ListView) findViewById(R.id.lista);

	    db = new BancoDados(getApplicationContext());
	    veiculosList = new ArrayList<>();
	    System.out.println("entrou aqui");
	    fab = (android.support.design.widget.FloatingActionButton) findViewById(R.id.fabReload);
	    fab.bringToFront();
	    fab.animate();
	    fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });
        load();
    }


    private void startSecondActivity(Class aClass) {

        Intent secondActivity = new Intent(this, aClass);
        startActivity(secondActivity);
        //finish();
    }

    public ArrayList<NextMe> nextMe(final Integer cod_cliente, Double lat, Double longi, Integer raio) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);

        //mProgressDialog.setProgressStyle();
        mProgressDialog.setMessage("Procurando veiculos por perto...");
        mProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SyncService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SyncService service = retrofit.create(SyncService.class);
        Call<NextMeResp> requestSync = service.nextMe(cod_cliente, lat, longi, raio, 1);
        requestSync.enqueue(new Callback<NextMeResp>() {
            @Override
            public void onResponse(Call<NextMeResp> call, Response<NextMeResp> response) { //pegando resposta do web server
                if (!response.isSuccessful()) {
                    Log.i("FUDEU DNV", "ERRO: " + response.code());
                } else {
                    NextMeResp resposta = response.body();
                    ArrayList<NextMe> arrayList = new ArrayList<>();
                    Log.e("UE?", " teste : " + resposta.nextMeList.size());
                    for (NextMe i : resposta.nextMeList) {
                        System.out.println(i.getCodVeiculo() + " w :" + i.getCodVeiculoW());
                        arrayList.add(i);
                        //adicionando veiculo no arralist
                    }
                    ArrayAdapter adapter = new nextMeArrayAdapter(getApplicationContext(), arrayList); // adicionando os veículos na listView

                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) { // ação quando clicar no item da lista
                            NextMe veiculo = (NextMe) listView.getItemAtPosition(position);

                            System.out.println(veiculo.getCodVeiculo());
                            System.out.println(veiculo.getCodVeiculoW());
                            codVeiculo = veiculo.getCodVeiculo();
                            codVeiculoW = veiculo.getCodVeiculoW();
                            //modelosCheck(veiculo.getVehicle_name());
                            getModelos(cod_cliente, veiculo.getVehicle_name(), valores.getInt("TIPO_USUARIO"));
                        }
                    });

	                if (resposta.nextMeList.size() == 0) {
		                Toast.makeText(getApplicationContext(), "Não há nenhum veiculo proximo, recuperando lista de veículos.", Toast.LENGTH_LONG).show();
		                getWithOutnxt();
	                }
                    mProgressDialog.hide();
                }
            }

            @Override
            public void onFailure(Call<NextMeResp> call, Throwable t) {
                mProgressDialog.hide();
            }
        });
        return arrayResp;
    }

    private void load() {
        head.setText(R.string.select_placa);
	    placaHead.setVisibility(View.INVISIBLE);

	    assert getSupportActionBar() != null;
        gps = new GPSTracker(this); // pegou referencia gps
        if (isOnline()) { //caso online
            Log.e("MAS O QUE ?", "ENTROU NO ONLY");

            gps.getLocation();
            if (gps.canGetLocation()) {
                getSupportActionBar().setTitle("Veiculos próximos");
                // passa sua latitude e longitude para duas variaveis
	            try {
		            double lat = gps.getLatitude();
		            double longi = gps.getLongitude();

		            Integer codCliente = valores.getInt("COD_CLIENTE");
		            nextMe(codCliente, lat, longi, 5000);
	            } catch (NullPointerException e) {
		            Log.e(e.getLocalizedMessage(), "load: " + e.getMessage());
		            getWithOutnxt();
	            }
            } else {
                Toast.makeText(getApplicationContext(), "Não foi possivel obter sua localização", Toast.LENGTH_LONG).show();
                getWithOutnxt();
            }
        } else {
            getWithOutnxt();
        }
    }

	public void performClickWill(int pos) {
		System.out.println(pos);
		listView.performItemClick(null, pos, pos);
	}

	private void modelosCheck(final String placa) { // quando clica no item da lista
		head.setText(getString(R.string.select_modelo));

		placaHead.setText(placa);
		placaHead.setVisibility(View.VISIBLE);
		assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(R.string.ModelosCheck);

        ArrayList<ModelosCheckList> modelosList = db.getModelos(placa, valores.getInt("TIPO_USUARIO"));
		if (modelosList.size() <= 0) { // caso não tenha nenhum modelo nã sera possivel prencher nenhum checklist, então colcoar mensagem de erro
			head.setText(R.string.you_cant); // mensagem de que nnhum poderá ser  cadastrado
			placaHead.setVisibility(View.INVISIBLE); // hide na placa
		}
        ModeloArrayAdapter adapter = new ModeloArrayAdapter(this, modelosList); // cria a listView de quando clica no item na tela de moledos chckist
        listView.setAdapter(adapter);

	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //  quando clica no item
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ModelosCheckList modelo = (ModelosCheckList) listView.getItemAtPosition(position);
                System.out.println(modelo);
                Bundle bundle = valores;
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                bundle.putInt("COD_CHECK", modelo.getCod());
                bundle.putString("MODELO", modelo.getNome());
                bundle.putString("PLACA", placa);
                bundle.putInt("COD_VEICULO", codVeiculo);
                bundle.putInt("COD_VEICULO_W", codVeiculoW);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    private void getWithOutnxt() {

        if (isOnline()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SyncService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            SyncService getPlacas = retrofit.create(SyncService.class);
            Integer codCliente = valores.getInt("COD_CLIENTE");
            Call<ListVeic> get = getPlacas.getVeic("placas", codCliente);
            get.enqueue(new Callback<ListVeic>() {
                @Override
                public void onResponse(Call<ListVeic> call, Response<ListVeic> response) {
                    if (response.isSuccessful()) {
                        setListPlacas(response.body().veiculosList);
                        Log.d("ahsuahasu", "sucesso");
                    } else {
                        veiculosList = db.getVeiculos();
                        Log.d("ahsuahasu", "onResponse: FAIO");
                    }
                }

                @Override
                public void onFailure(Call<ListVeic> call, Throwable t) {
                    veiculosList = db.getVeiculos();
                    Log.d("ahsuahasu", "onResponse: QUE MERDA");
                }
            });
        } else {
            veiculosList = db.getVeiculos();
            Log.d("ahsuahasu", "onResponse:OSSO"); // se ele está sem internet ele apenas pega do banco local os veículos
            setListPlacas(veiculosList);
        }


    }

    private void setListPlacas(ArrayList<Veiculos> veiculosList) {
        getSupportActionBar().setTitle("Veiculos");
        veiculosArrayAdapter adapter = new veiculosArrayAdapter(this, veiculosList);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Veiculos veiculo = (Veiculos) listView.getItemAtPosition(position);
                codVeiculo = veiculo.getCod();
                codVeiculoW = veiculo.getCodW();

                modelosCheck(veiculo.getPlaca());

            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private ModelosCheckList getModelos(Integer cod_cliente, final String placa, Integer tipoUser) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Pesquisando modelos de CheckList do veiculo");
        mProgressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SyncService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SyncService service = retrofit.create(SyncService.class);
        Call<ModelosCheckListResp> requestSync = service.getModels(cod_cliente, placa, tipoUser, 2);
        requestSync.enqueue(new Callback<ModelosCheckListResp>() {
            @Override
            public void onResponse(Call<ModelosCheckListResp> call, Response<ModelosCheckListResp> response) {
                if (!response.isSuccessful()) {
                    Log.i("FUDEU DNV", "ERRO: " + response.code());
                } else {
	                head.setText(getString(R.string.select_modelo));
	                placaHead.setText(placa);
	                placaHead.setVisibility(View.VISIBLE);
	                ModelosCheckListResp resposta = response.body();

                    ArrayList<ModelosCheckList> modelosList = resposta.modelosList;
                    Log.e("UE?", " teste : " + modelosList.size());
                    setList(modelosList, placa);
                }

            }

            @Override
            public void onFailure(Call<ModelosCheckListResp> call, Throwable t) {

            }

        });
        mProgressDialog.hide();
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.placas, menu);
        return true;
    }


    private void setList(final ArrayList<ModelosCheckList> modelosList, final String placa) {
        ModeloArrayAdapter adapter = new ModeloArrayAdapter(this, modelosList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ModelosCheckList modelo = (ModelosCheckList) listView.getItemAtPosition(position);
                Bundle bundle = valores;
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                bundle.putInt("COD_CHECK", modelo.getCod());
                bundle.putString("PLACA", placa);
                bundle.putString("NOME", modelo.getNome());
                bundle.putInt("COD_VEICULO", codVeiculo);
                bundle.putInt("COD_VEICULO_W", codVeiculoW);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                if (head.getText() == getText(R.string.select_placa)) {
                    finish(); // Finaliza a Activity atual
                } else {
                    load();
                }

                break;
            case R.id.action_placas:
                getWithOutnxt();
                break;
            default:
                break;
        }
        return true;
    }

}
