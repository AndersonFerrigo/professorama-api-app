package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Lembretes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AtualizaLembretePeloId {
    @Headers("Content-Type:Application/json")
    @PUT("api/lembrete/atualizaLembrete/{id}")
    Call<Lembretes> atualizaLembrete(@Path("id") long id, @Body Lembretes lembrete);

}
