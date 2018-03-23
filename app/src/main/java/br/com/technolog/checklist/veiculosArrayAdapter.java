package br.com.technolog.checklist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import models.Veiculos;

/**
 * Created by William on 26/01/17.
 * Classe java para a
 */

public class veiculosArrayAdapter extends ArrayAdapter {
	private Context context;
	private List<Veiculos> itens;

	//constructor, call on creation
	public veiculosArrayAdapter(Context context, ArrayList<Veiculos> objects) {

		super(context, 0, objects);
		this.context = context;
		this.itens = objects;
	}

	//called when rendering the list
	public View getView(int position, View convertView, ViewGroup parent) {

		//get the inflater and inflate the XML layout for each item
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.placas_layout, null);

		// pega o layout
		TextView nome = (TextView) view.findViewById(R.id.placa);

		//Define os nomes
		nome.setText(itens.get(position).getPlaca());

		return view;
	}
}
