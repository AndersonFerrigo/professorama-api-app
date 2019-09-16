package br.com.clearsys.professorama.services;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DeleteLembretePeloId {
    @Headers("Content-Type:Application/json")
    @DELETE("api/lembrete/deletarLembrete/{id}")
    Call<Void> deletarLembrete(@Path("id") long id);
}
