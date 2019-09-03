package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Atividade;
import br.com.clearsys.professorama.model.Lembretes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BuscaLembreteProfessorPeloId {
    @Headers("Content-Type:Application/json")
    @GET("api/lembrete/buscarLembrete/{id}")
    Call<Lembretes> buscarLembretePeloId(@Path("id") long id);
}
