package utility;

import models.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by William on 20/01/17.
 * Classe java para a
 */

public interface LoginService {

	String BASE_URL = "http://solutions.technolog.com.br/darwin/mobile/darwin_tablet/";

	@POST("login_interface.php")
	@FormUrlEncoded
	Call<Usuario> login(@Field("usuario") String user,
	                    @Field("senha") String pass);
}
