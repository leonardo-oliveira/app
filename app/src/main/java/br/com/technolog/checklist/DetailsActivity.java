package br.com.technolog.checklist;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle("Imagem");

		String title = getIntent().getStringExtra("title");
		Uri uri = getIntent().getParcelableExtra("image");

		TextView titleTextView = (TextView) findViewById(R.id.title);
		titleTextView.setText(title);

		ImageView imageView = (ImageView) findViewById(R.id.image);
		imageView.setImageURI(uri);
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
