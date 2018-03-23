package br.com.technolog.checklist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import models.ModelosCheckList;


/**
 * Created by William on 31/01/17.
 * Classe java para a
 */

public class ModeloArrayAdapter extends ArrayAdapter {

	private Context context;
	private List<ModelosCheckList> itens;

	public ModeloArrayAdapter(Context context, ArrayList<ModelosCheckList> objects) {
		super(context, 0, objects);
		this.context = context;
		this.itens = objects;
	}

	public View getView(final int position, final View convertView, final ViewGroup parent) {
		final ModelosCheckList property = itens.get(position);
		//get the inflater and inflate the XML layout for each item
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		final View viewList = inflater.inflate(R.layout.modelo_layout, null);
		final View rowView = convertView;
		// pega o layout
		TextView nome = (TextView) viewList.findViewById(R.id.textNomeModelo);
		Button infos = (Button) viewList.findViewById(R.id.infos);
		//Define os nomes
		nome.setText(property.getNome());

		infos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {
				//System.out.println("DANDO CERTO");
				System.out.println(property.toString());

				AlertDialog.Builder alert = new AlertDialog.Builder(context);
				LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
				View dialogView = inflater2.inflate(R.layout.alert, null);
				TextView nome = (TextView) dialogView.findViewById(R.id.name);
				nome.setText(property.getNome());
				TextView desc = (TextView) dialogView.findViewById(R.id.textDesc);
				desc.setText(property.getDescricao());
				TextView msg = (TextView) dialogView.findViewById(R.id.textMsg);
				//msg.setText(property.getMensagensGeraisExecutorCheckList());
				msg.setText(property.getMensagensGeraisExecutorCheckList());
				alert.setView(dialogView);
				alert.setTitle(R.string.infos);
				alert.setPositiveButton(R.string.Preencher, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						if (context instanceof ListActivity) {
							((ListActivity) context).performClickWill(position);
						}
					}
				});
				alert.setNeutralButton(R.string.Cancel, null);
				//alert.setMessage(property.getDescricao()).setTitle("Informações").setNeutralButton(R.string.OK, null);
				alert.show();
			}
		});


		return viewList;
	}

	private void performClickInView(int position, View convertView) {
		System.out.println(position);

		//listView.performItemClick(convertView,position,position);

	}
}
