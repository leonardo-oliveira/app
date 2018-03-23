package utility;

import java.util.List;

import models.CheckLists;
import models.Result;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by William on 17/02/17.
 * Classe java para a
 */

public interface SendCheckService {
	String BASE_URL = "http://solutions.technolog.com.br/darwin/mobile/darwin_tablet/";

	@Multipart
	@POST("save_check.php")
	//@FormUrlEncoded
	Call<Result> save(@Part("cod_cliente") Integer cod,
	                  @Part("case") Integer caso,
	                  @Part("checklist") CheckLists check,
	                  @Part("itens") String itens,
	                  @Part MultipartBody.Part fotoPlaca,
	                  @Part MultipartBody.Part assinatura,
	                  @Part List<MultipartBody.Part> files);

}
