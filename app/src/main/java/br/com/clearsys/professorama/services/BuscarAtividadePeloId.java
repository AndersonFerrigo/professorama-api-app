package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BuscarAtividadePeloId {
    @Headers("Content-Type:Application/json")
    @GET("api/atividade/buscarAtividadeAtualizar/{id}")
    Call<Atividade> buscarAtividadePeloId(@Path("id") long id);

}
