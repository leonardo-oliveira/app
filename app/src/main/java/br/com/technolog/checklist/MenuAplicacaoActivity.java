package br.com.technolog.checklist;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import utility.Utilidades;

public class MenuAplicacaoActivity extends AppCompatActivity {

	private Bundle valores;
	private String usuario;
	private Button btnCheck;
	private Button btnJornada;
	private Button btnRotograma;
	private Button btnJornadaMacro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_aplicacao);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		final Intent intent1 = getIntent();
        /**
         * Get data from the activity caller
         */
		valores = intent1.getExtras();

        /**
         * Set up the footer data
         */
		TextView textFooterUser = (TextView) findViewById(R.id.textFooterUser);
		usuario = valores.getString("LOGIN") + " | " + valores.getString("RAZAO_SOCIAL");
		textFooterUser.setText(usuario);
		TextView textFooterData = (TextView) findViewById(R.id.textFooterData);
		textFooterData.setText(Utilidades.getDataHora("dd/MM/yyyy"));
		getSupportActionBar().setHomeButtonEnabled(true);

		btnCheck = (Button) findViewById(R.id.btnCheckList);
		btnJornada = (Button) findViewById(R.id.btnJornada);
		btnRotograma = (Button) findViewById(R.id.btnRotograma);

		/**
         * Set on click in button btnCheck, btnJornada and btnRotograma
         */
		setButton();

		btnJornadaMacro = (Button) findViewById(R.id.btnJornadaMacro);
		btnJornadaMacro.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = valores;
				Intent intent = new Intent(getApplicationContext(), MacroActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void setButton() {
        /**
         * set on click to go to checklist function
         */
		btnCheck.setOnClickListener(new View.OnClickListener() {
            // TODO: 28/03/2018 change the name of class HomeActivity to CheckListActivity 
            @Override
			public void onClick(View view) {
				Bundle bundle = valores;
				Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
        /**
         * Disable or enable burron btnJornada
         */
		if (valores.getInt("TIPO_USUARIO") == 2) {
			btnJornada.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Bundle bundle = valores;
					Intent intent = new Intent(getApplicationContext(), JornadaActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);

				}
			});
		} else {
			btnJornada.setEnabled(false);
			btnJornada.setBackgroundColor(getResources().getColor(R.color.colorGrey));
		}

		btnRotograma.setOnClickListener(new View.OnClickListener() {
			@Override
            public void onClick(View view) {
				alertNot();
			}
		});
		btnRotograma.setEnabled(false);
		btnRotograma.setBackgroundColor(getResources().getColor(R.color.colorGrey));
	}

    /**
     * Show an alert if an user click on button btnRotograma
     */
	private void alertNot() {
		new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_warning_amber_800_24dp)
				.setTitle("Alerta!")
				.setMessage("Função ainda não implementada")
				.setNegativeButton("ok", null)
				.show();
	}

    // TODO: 28/03/2018 Descovery what this do
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);

		}
	}
}
