package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AtualizaAtividadeService {
    @Headers("Content-Type:Application/json")
    @PUT("api/atividade/atualizaAtividade/{id}")
    Call<Atividade> atualizaAtividade(@Path("id") long id, @Body Atividade atividade);

}
