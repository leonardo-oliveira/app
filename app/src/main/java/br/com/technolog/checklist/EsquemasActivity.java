package br.com.technolog.checklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import models.RelModeloCheckListVeiculos;
import utility.BancoDados;

public class EsquemasActivity extends AppCompatActivity {

	private GridView gridView;
	private GridViewAdapter gridAdapter;
	private BancoDados db;
	private ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> esquemaArrayList;
	private Bundle valores;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Intent intent1 = getIntent();
		valores = intent1.getExtras();
		db = new BancoDados(getApplicationContext());
		esquemaArrayList = getData();

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle("Esquemas");
		System.out.println(esquemaArrayList.size());
		setContentView(R.layout.activity_esquemas);
		gridView = (GridView) findViewById(R.id.gridView);
		gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, esquemaArrayList);
		gridView.setAdapter(gridAdapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				RelModeloCheckListVeiculos.ImagensEsquema item = (RelModeloCheckListVeiculos.ImagensEsquema) adapterView.getItemAtPosition(i);
				//Create intent
				Intent intent = new Intent(EsquemasActivity.this, DetailsActivity.class);
				intent.putExtra("title", item.getDescricaoImagem());
				intent.putExtra("image", item.getUri());

				//Start details activity
				startActivity(intent);
			}
		});
	}

	private ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> getData() {
		ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> imageItems;
		imageItems = db.getListImages(valores.getInt("modelo"));
		return imageItems;
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
}
