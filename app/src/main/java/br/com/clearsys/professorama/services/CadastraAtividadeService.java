package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CadastraAtividadeService {
    @Headers("Content-Type: Application/json")
    @POST("api/atividade")
    Call<Atividade> cadastrarAtividade(@Body Atividade atividade);
}
