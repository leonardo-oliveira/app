package br.com.technolog.checklist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import models.CheckLists;

/**
 * Created by William on 16/03/17.
 * Classe java para a
 */

public class SavedCheckArrayAdapter extends ArrayAdapter {

	private final Context context;
	private final ArrayList<CheckLists> check;
	private final Activity activity;

	public SavedCheckArrayAdapter(Context context, ArrayList<CheckLists> objects, Activity activity) {
		super(context, 0, objects);

		this.context = context;
		this.check = objects;
		this.activity = activity;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		//get the inflater and inflate the XML layout for each item
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.saved_check_layout, null);

		// pega o layout
		TextView nome = (TextView) view.findViewById(R.id.model_name);

		//Define os nomes
		String nomeModel = check.get(position).toStringInfos();
		nome.setText(nomeModel);

		return view;
	}
}

