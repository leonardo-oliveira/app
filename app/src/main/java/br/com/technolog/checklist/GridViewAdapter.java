package br.com.technolog.checklist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import models.RelModeloCheckListVeiculos;

/**
 * Created by William on 04/04/17.
 * Classe java para a
 */

public class GridViewAdapter extends ArrayAdapter {

	private Context context;
	private int layoutResourceId;
	private ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> imagensList = new ArrayList<>();


	public GridViewAdapter(Context context, int layoutResourceId, ArrayList<RelModeloCheckListVeiculos.ImagensEsquema> imagensList) {
		super(context, layoutResourceId, imagensList);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.imagensList = imagensList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ViewHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.imageTitle = (TextView) row.findViewById(R.id.text);
			holder.image = (ImageView) row.findViewById(R.id.image);
			row.setTag(holder);
		} else {
			holder = (ViewHolder) row.getTag();
		}

		RelModeloCheckListVeiculos.ImagensEsquema item = imagensList.get(position);
		holder.imageTitle.setText(item.getCodigoImagem());
		holder.image.setImageURI(item.getUri());
		return row;
	}

	private static class ViewHolder {
		TextView imageTitle;
		ImageView image;
	}
}
