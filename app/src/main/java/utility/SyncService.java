package utility;

import models.NextMeResp;
import models.ServiceSyncResp;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by William on 23/01/17.
 * Classe java para a
 */

@SuppressWarnings("SameParameterValue")
public interface SyncService {
	String BASE_URL = "http://solutions.technolog.com.br/darwin/mobile/darwin_tablet/";

	@POST("sync_check.php")
	@FormUrlEncoded
	Call<ServiceSyncResp> sincro(@Field("cod_cliente") Integer cod);

	@POST("get_infos.php")
	@FormUrlEncoded
	Call<NextMeResp> nextMe(@Field("cod_cliente") Integer cod,
	                        @Field("lat") Double latitude,
	                        @Field("long") Double longitude,
	                        @Field("raio") Integer raio,
	                        @Field("case") Integer caso);

	@POST("get_infos.php")
	@FormUrlEncoded
	Call<ModelosCheckListResp> getModels(@Field("cod_cliente") Integer codCliente,
	                                     @Field("placa") String Placa,
	                                     @Field("tipoUser") Integer TipoUser,
	                                     @Field("case") Integer caso);

	@POST("sync_check.php")
	@FormUrlEncoded
	Call<ListVeic> getVeic(@Field("case") String caso,
	                       @Field("cod_cliente") Integer codCliente);


	@POST("sync_check.php")
	@FormUrlEncoded
	Call<ItensResp> getItens(@Field("case") String caso,
	                         @Field("cod_veic") Integer codVeic,
	                         @Field("date") String date,
	                         @Field("cod_check") Integer codCheck,
	                         @Field("cod_cliente") Integer codCliente);
}

