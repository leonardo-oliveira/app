package br.com.technolog.checklist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

import models.CheckLists;

/**
 * Created by William on 27/03/17.
 * Classe java para a
 */

public class SendArrayAdapter extends ArrayAdapter {
	private final Context context;
	private final ArrayList<CheckLists> check;


	public SendArrayAdapter(Context context, ArrayList<CheckLists> objects) {
		super(context, 0, objects);

		this.context = context;
		this.check = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		//get the inflater and inflate the XML layout for each item
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.adapter_check_send, null);

		// pega o layout
		CheckBox nome = (CheckBox) view.findViewById(R.id.checkBox);

		//Define os nomes
		String nomeModel = check.get(position).toStringInfos();
		nome.setText(nomeModel);

		return view;
	}

}
