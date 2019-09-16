package br.com.clearsys.professorama.services;

import java.util.List;

import br.com.clearsys.professorama.model.Lembretes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BuscarLembretePorMateria {
    @Headers("Content-Type:Application/json")
    @GET("api/lembrete/buscarPorMateria/{materia}")
    Call<List<Lembretes>> buscarLembretePelaMateria(@Path("materia") String materia);
}
