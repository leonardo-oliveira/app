package br.com.technolog.checklist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import models.ItensCheckList;
import utility.Utilidades;

/**
 * Created by William on 19/01/17.
 * Classe java para a
 */

public class itensChecklistArrayAdapter extends ArrayAdapter {
	static final int REQUEST_IMAGE_CAPTURE = 1;
	Activity activity;
	private Context context;
	private ArrayList<ItensCheckList> itens;
	private View view;
	private String texto;
	private ContentValues values;
	private Uri imageUri;

	//constructor, call on creation
	public itensChecklistArrayAdapter(Context context, ArrayList<ItensCheckList> objects, Activity activity) {
		super(context, 0, objects);

		this.context = context;
		this.itens = objects;
		this.activity = activity;
	}

	public final static Bitmap getUnRotatedImage(String imahePath, Bitmap rotattedBitmap) {
		int rotate = 0;
		try {
			File imageFile = new File(imahePath);
			ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
			int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate = 270;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate = 90;
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}


		Matrix matrix = new Matrix();
		matrix.postRotate(rotate);
		return Bitmap.createBitmap(rotattedBitmap, 0, 0, rotattedBitmap.getWidth(), rotattedBitmap.getHeight(), matrix,
				true);
	}

	//called when rendering the list
	public View getView(final int position, final View convertView, ViewGroup parent) {
		final ItensCheckList property = itens.get(position);
		//get the inflater and inflate the XML layout for each item
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.itens_checklist_layout, null);

		// pega o layout

		TextView nome = (TextView) view.findViewById(R.id.textNomeItem);

		if (!property.getFlgOpcaoAnexo()) {
			view.findViewById(R.id.btnAnexo).setVisibility(View.INVISIBLE);
		}
		if (!property.getFlgOpcaoNaoEfet()) {
			view.findViewById(R.id.Efet).setVisibility(View.INVISIBLE);
		}
		if (!property.getFlgOpcaoNaoOK()) {
			view.findViewById(R.id.NOK).setVisibility(View.INVISIBLE);
		}
		if (!property.getFlgOpcaoOK()) {
			view.findViewById(R.id.OK).setVisibility(View.INVISIBLE);
		}
		if (!property.getFlgOpcaoObs()) {
			view.findViewById(R.id.editComentario).setVisibility(View.INVISIBLE);
		}

		ImageButton btnAnexo = (ImageButton) view.findViewById(R.id.btnAnexo);

		if (property.getOpcaoAnexo() != null) {
			BitmapFactory.Options bmOptions = new BitmapFactory.Options();

			Bitmap bitmap = BitmapFactory.decodeFile(property.getOpcaoAnexo(), bmOptions);
			System.out.println(parent.getWidth());
			//bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
			bitmap = getUnRotatedImage(property.getOpcaoAnexo(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
			btnAnexo.setImageBitmap(bitmap);
		}

		btnAnexo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				System.out.println("WORK WORK WORK");
				values = new ContentValues();
				values.put(MediaStore.Images.Media.TITLE, "New Picture");
				values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
				imageUri = getContext().getContentResolver().insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
				Intent intent = new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				activity.startActivityForResult(intent, position);

				//activity.startActivityForResult();

			}
		});
		//Faz os comentarios serem salvos para serem recuperados depois
		final EditText comentario = (EditText) view.findViewById(R.id.editComentario);
		comentario.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				texto = comentario.getText().toString();
				itens.get(position).setOpcaoObs(texto);
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});
		comentario.setText(itens.get(position).getOpcaoObs());


		//Salva os radio button para conseguir recuperar mais tarde
		// Fazer a logica de ir salvando os radio button quando forem alterados para conseguir recuperar eles
		//TODO: UTILIZAR OS SETS DA CLASSES ITENSCHECKLIST pois eles estão definindo os outros como falso
		//TODO: SE o o outro for verdadeiro

		//Define os nomes
		nome.setText(property.getNomeItem());

		nome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//TODO: EDITAR PARA MOSTRAR LOCALIZAÇÃO DO ITEM
				System.out.println(property.getMensagensGerais());

				Log.e("TAG", "onClick: " + property.getMensagensGerais());
				if (Objects.equals(property.getMensagensGerais().trim(), "")) {
					property.setMensagensGerais(context.getString(R.string.not_info));
				}
				new AlertDialog.Builder(activity)
						.setIcon(R.drawable.ic_info_blue_800_24dp)
						.setTitle(property.getNomeItem())
						.setMessage(property.getMensagensGerais())
						.setNeutralButton("OK", null)
						.show();
				//AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
				//alert.setTitle("Teste");
				//alert.show();
			}
		});

		RadioGroup rg = (RadioGroup) view.findViewById(R.id.radioGroup);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				if (radioGroup.getCheckedRadioButtonId() == -1) {
					// no radio buttons are checked
				} else {
					if (view.findViewById(R.id.OK).getId() == radioGroup.getCheckedRadioButtonId()) {
						itens.get(position).setOpcaoOK(true);
					} else if (view.findViewById(R.id.NOK).getId() == radioGroup.getCheckedRadioButtonId()) {
						itens.get(position).setOpcaoNaoOK(true);
					} else if (view.findViewById(R.id.Efet).getId() == radioGroup.getCheckedRadioButtonId()) {
						itens.get(position).setOpcaoNaoEfet(true);
					} else {
						System.out.println("deu ruim");
					}
					itens.get(position).setDataAcao(Utilidades.getDataHora("yyyy-MM-dd HH:mm:ss"));
				}

			}
		});


		//Fazer que os botões estejam selecionados
		if (itens.get(position).getOpcaoOK()) {
			rg.check(R.id.OK);

		} else if (itens.get(position).getOpcaoNaoOK()) {
			rg.check(R.id.NOK);
		} else if (itens.get(position).getOpcaoNaoEfet()) {
			rg.check(R.id.Efet);
		} else {
			System.out.println("nenhum selecionado");
		}
		return view;
	}

	public void getValues() {
		Integer tamanho = itens.size();
		for (int i = 0; i < tamanho; i++) {
			RadioGroup radio = (RadioGroup) view.findViewById(R.id.radioGroup);
			System.out.println(radio.getCheckedRadioButtonId());
			TextView texto = (TextView) view.findViewById(R.id.textNomeItem);
			System.out.println(texto.getText());
		}
	}

	@Override
	public Object getItem(int position) {
		return itens.get(position);
	}

	@Override
	public int getCount() {
		return itens.size();
	}

	public ArrayList<ItensCheckList> getList() {
		return itens;
	}

	public void setImageInItem(Integer position, Bitmap imageBitmap, String picturePath) {
		itens.get(position).setOpcaoAnexo(picturePath);
		notifyDataSetChanged();
	}

	public Uri getImageUri() {
		return imageUri;
	}

	public void setImageUri(Uri imageUri) {
		this.imageUri = imageUri;
	}

}

