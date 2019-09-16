package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Lembretes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CadastraLembreteService {
    @Headers("Content-Type: Application/json")
    @POST("api/lembrete")
    Call<Lembretes> cadastrarLembrete(@Body Lembretes lembretes);
}
