package utility;

import models.JornadaServiceResp;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by William on 18/05/17.
 * Classe java para a
 */

public interface JornadaService {
	String BASE_URL = "http://solutions.technolog.com.br/darwin/mobile/darwin_tablet/";

	@POST("jornada.php")
	@FormUrlEncoded
	Call<JornadaServiceResp> jornada(@Field("cod_cliente") Integer cod,
	                                 @Field("identificador") String identificador);
    @POST("rota.php")
    @FormUrlEncoded
    Call<RouteResp> rota(@Field("cod_cliente") Integer cod,@Field("placa") String placa);
}
