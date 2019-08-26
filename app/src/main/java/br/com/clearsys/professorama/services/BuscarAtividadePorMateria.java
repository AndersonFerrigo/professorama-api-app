package br.com.clearsys.professorama.services;

import java.util.List;

import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BuscarAtividadePorMateria {
    @Headers("Content-Type:Application/json")
    @GET("api/atividade/buscarPorMateria/{materia}")
    Call<List<Atividade>> buscarAtividadePelaMAteria(@Path("materia") String materia);

}
