package br.com.technolog.checklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import models.CheckLists;
import utility.BancoDados;
import utility.Utilidades;

public class ListCheckSavedActivity extends AppCompatActivity {

	private BancoDados db;
	private TextView head;
	private ListView listView;
	private Bundle valores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		setContentView(R.layout.activity_list_check_saved);

		final Intent intent1 = getIntent();
		valores = intent1.getExtras();

		TextView textFooterData = (TextView) findViewById(R.id.textFooterData);
		TextView textFooterUser = (TextView) findViewById(R.id.textFooterUser);
		String user = valores.getString("LOGIN") + " | " + valores.getString("RAZAO_SOCIAL");

		textFooterUser.setText(user);
		textFooterData.setText(Utilidades.getDataHora("dd/MM/yyyy"));

		db = new BancoDados(getApplicationContext());
		ArrayList<CheckLists> list = db.getCheckSavedList();
		head = (TextView) findViewById(R.id.textViewHead);
		if (list.size() == 0){
			head.setText(R.string.sem_check_salvo);
			new AlertDialog.Builder(this)
					.setIcon(R.drawable.
							ic_warning_amber_800_24dp)
					.setTitle("Alerta!")
					.setMessage(R.string.sem_check_salvo)
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
			head.setText(R.string.save);
			load(list);
		}
	}

	private void load(ArrayList<CheckLists> list) {

		SavedCheckArrayAdapter adapter = new SavedCheckArrayAdapter(getApplicationContext(), list, this);
		listView = (ListView) findViewById(R.id.lista);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				CheckLists check = (CheckLists) listView.getItemAtPosition(position);
				Integer codigoCheck = check.getCod();
				Log.e("Codigo que ta indo:", "Numero: " + codigoCheck);
				valores.putInt("CODIGO_CHECK", codigoCheck);
				valores.putInt("CODIGO_MODELO", check.getCodModeloCheckList());
				valores.putInt("COD_VEICULO", check.getCodVeiculo());
				valores.putInt("COD_VEICULO_W", check.getCodVeiculoW());
				valores.putString("PLACA", check.getPlacaVeiculo());

				valores.putString("DATAINICIO", check.getDataInicioVistoria());

				valores.putString("CAMINHO_FT_PLACA", check.getCaminhoArquivoFotoPlacaVeiculo());

				valores.putString("COMENTARIO", check.getComentarios());
				valores.putString("MANIFESTO", check.getNumeroManifesto());
				valores.putString("SISTEMAS", check.getIdentificadorSistemasTerceiros());
				valores.putString("CONHECIMENTO", check.getNumeroConhecimentoTransporte());
				valores.putBoolean("SOMENTERBQ", check.getFlgSomenteReboques());
				System.out.println(check.getFlgSomenteReboques());
				Log.e("AQUELE ERRO", "onItemClick: " + check.getFlgSomenteReboques());
				valores.putInt("STATUS_CHECK", check.getStatusCheckList());
				valores.putInt("BLOCKBUTTON", 0);
				Intent intent = new Intent(getApplicationContext(), SavedCheckActivity.class);
				intent.putExtras(valores);
				startActivity(intent);
			}
		});
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

			case android.R.id.home:
				finish(); // Finaliza a Activity atual
				break;
			case R.id.action_placas:
				break;
			default:
				break;
		}
		return true;
	}


}


