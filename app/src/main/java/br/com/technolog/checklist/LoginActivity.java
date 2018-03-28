package br.com.technolog.checklist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;
import models.Usuarios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utility.BancoDados;
import utility.LoginService;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

	/**
	 * Id to identity READ_CONTACTS permission request.
	 */
	private static final int REQUEST_READ_CONTACTS = 0;

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	// UI references.
	private AutoCompleteTextView mEmailView;
	private EditText mPasswordView;
	private View mProgressView;
	private View mLoginFormView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		final View view2 = this.findViewById(android.R.id.content);
		setContentView(R.layout.activity_login);
		// Set up the login form.
		mEmailView = (AutoCompleteTextView) findViewById(R.id.inputUser);
		mPasswordView = (EditText) findViewById(R.id.inputPass);
		Button mEmailSignInButton = (Button) findViewById(R.id.btnEntrar);
		/**
		 * Interface definition for a callback to be invoked when a view is clicked.
         * @param OnClickListener() function
         * @see setOnClickListener
		 */
		mEmailSignInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View view) {
				login(view.getContext());
			}
		});
		mProgressView = findViewById(R.id.login_progress);
		fixMediaDir();
        TextView txtView = (TextView) findViewById(R.id.textViewForget);
        /**
         * Interface definition for a callback to be invoked when a view is clicked.
         * @param OnClickListener() function
         * @see setOnClickListener
         */
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Create a new Intent with ACTION_VIE(Display the data to the user. This is the most common action performed on data)
                 * and an URI with the request link to recover the password
                 * @see URI
                 * @see Intent
                 */
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://solutions.technolog.com.br/darwin/portal__interface/recuperar_senha_tablet" + ".php"));
                startActivity(browserIntent);

            }
        });
	}

    /**
     * Login function
     * @param context
     */
	private void login(final Context context) {
        /**
         * Set up the loading screen
         */
		final ProgressDialog mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setMessage("Carregando...");
		mProgressDialog.show();

		/**
         * Get data from input component
         */
		String email = mEmailView.getText().toString().trim();
		String senha = mPasswordView.getText().toString().trim();

		BancoDados db = new BancoDados(this);

		/**
         * Get user email and password from internal data base
         */
		Usuarios usuario = db.getUsuario(email, senha);

        /**
         *  Verify if have a user in the var usuario if is NULL call the function to CPF
         *
         */
		if (usuario.getLogin() == null) {
			String cpf = toCPF(email);
            /**
             * if cpf is different of null search on internal data base the user
             * passing the user cpf and user password
             */
			if (cpf != null) {
				usuario = db.getUsuario(cpf, senha);
			}
		}
        /**
         * If isn't null create a Bundle(a mapping from String keys to various Parcelable values)
         * with user information to pass to another Activity using the Intent
         * @see Intent
         */
		if (usuario.getSenha() != null) {
			Bundle bundle = new Bundle();
			bundle.putInt("COD_CLIENTE", usuario.getCod_cliente());
			bundle.putString("LOGIN", usuario.getLogin());
			bundle.putString("EMAIL", email);
			bundle.putString("SENHA", senha);
			bundle.putString("RAZAO_SOCIAL", usuario.getRazao_social());
			bundle.putString("COD_USUARIO", usuario.getCod());
			bundle.putInt("TIPO_USUARIO", usuario.getTipoUsuario());
			bundle.putString("LOGO", usuario.getCaminho_logo());
			Intent intent = new Intent(context, MenuAplicacaoActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			finish();

			/**
             * Verify if the loading screen is showing
             */
			if (mProgressDialog.isShowing())
				mProgressDialog.dismiss();

		} else {
			Log.e("ONLINE", "login: TA ONLINE ");
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl(LoginService.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();
			LoginService service = retrofit.create(LoginService.class);
			final Call<Usuario> requestLogin = service.login(email, senha);
			requestLogin.enqueue(new Callback<Usuario>() {
				@Override
				public void onResponse(Call<Usuario> call, Response<Usuario> response) {
					if (!response.isSuccessful()) {
						//TODO: Fazer uma função que exiba uma mensagem quando entrar nesse if
						Log.e("TAB", "errorrr: " + response.code());
						if (mProgressDialog.isShowing())
							mProgressDialog.dismiss();
					} else {
						System.out.println(response.body().toString());
						Usuario user = response.body();
						System.out.println(user.toString());
						Bundle bundle = new Bundle();
						bundle.putInt("COD_CLIENTE", user.getCod_cliente());
						bundle.putString("LOGIN", user.getLogin());
						bundle.putString("RAZAO_SOCIAL", user.getRazao_social());
						bundle.putString("COD_USUARIO", user.getCod());
						bundle.putInt("TIPO_USUARIO", user.getTipoUsuario());
						bundle.putString("LOGO", user.getCaminho_logo());
						Intent intent = new Intent(context, MenuAplicacaoActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
						if (mProgressDialog.isShowing())
							mProgressDialog.dismiss();
					}
				}

				@Override
				public void onFailure(Call<Usuario> call, Throwable t) {
					Log.e("TAG", "erro: " + t.getMessage());
					erroLogin();
					if (mProgressDialog.isShowing())
						mProgressDialog.dismiss();
					//TODO: Fazer uma função que exiba uma mensagem quando entrar nesse if
				}
			});
		}
	}

	private String toCPF(String email) {
		if (email.length() != 11)
			return null;
		String cpf = email;
		String bloco1 = cpf.substring(0, 3);
		String bloco2 = cpf.substring(3, 6);
		String bloco3 = cpf.substring(6, 9);
		String bloco4 = cpf.substring(9, 11);
		cpf = bloco1 + "." + bloco2 + "." + bloco3 + "-" + bloco4;
		return cpf;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	private void attemptLogin(Context context) {
		login(context);
	}

	private boolean isEmailValid(String email) {
		//TODO: Replace this with your own logic
		return email.contains("@");
	}

	private boolean isPasswordValid(String password) {
		//TODO: Replace this with your own logic
		return password.length() > 4;
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

		mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		mLoginFormView.animate().setDuration(shortAnimTime).alpha(
				show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
			}
		});

		mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
		mProgressView.animate().setDuration(shortAnimTime).alpha(
				show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
			}
		});
	}

	@Override
	public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
		return new CursorLoader(this,
				// Retrieve data rows for the device user's 'profile' contact.
				Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
						ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

				// Select only email addresses.
				ContactsContract.Contacts.Data.MIMETYPE +
						" = ?", new String[]{ContactsContract.CommonDataKinds.Email
				.CONTENT_ITEM_TYPE},

				// Show primary email addresses first. Note that there won't be
				// a primary email address if the user hasn't specified one.
				ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
	}

	@Override
	public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
		List<String> emails = new ArrayList<>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			emails.add(cursor.getString(ProfileQuery.ADDRESS));
			cursor.moveToNext();
		}

		addEmailsToAutoComplete(emails);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> cursorLoader) {

	}

	private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
		//Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
		ArrayAdapter<String> adapter =
				new ArrayAdapter<>(LoginActivity.this,
						android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

		mEmailView.setAdapter(adapter);
	}

	private void startSecondActivity(Class aClass) {

		Intent secondActivity = new Intent(this, aClass);
		startActivity(secondActivity);
		finish();
	}

	public void erroLogin() {
		new AlertDialog.Builder(this)
				.setIcon(R.drawable.cancel)
				.setTitle(R.string.erroLoginTitle)
				.setMessage(R.string.erroLogin)
				.setNeutralButton(R.string.OK, null)
				.show();
		mPasswordView.setError(getString(R.string.error_incorrect_password));
		mPasswordView.requestFocus();
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */

	public boolean isOnline() {
		ConnectivityManager cm =
				(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	void fixMediaDir() {
		File sdcard = Environment.getExternalStorageDirectory();
		if (sdcard != null) {
			File mediaDir = new File(sdcard, "DCIM/Camera");
			if (!mediaDir.exists()) {
				mediaDir.mkdirs();
			}
		}
	}

	private interface ProfileQuery {
		String[] PROJECTION = {
				ContactsContract.CommonDataKinds.Email.ADDRESS,
				ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
		};

		int ADDRESS = 0;
		int IS_PRIMARY = 1;
	}

}

