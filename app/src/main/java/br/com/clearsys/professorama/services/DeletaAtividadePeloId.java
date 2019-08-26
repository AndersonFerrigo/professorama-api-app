package br.com.clearsys.professorama.services;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DeletaAtividadePeloId {
    @Headers("Content-Type:Application/json")
    @DELETE("api/atividade/deletarAtividade/{id}")
    Call<Void> deletarAtividade(@Path("id") long id);

}
