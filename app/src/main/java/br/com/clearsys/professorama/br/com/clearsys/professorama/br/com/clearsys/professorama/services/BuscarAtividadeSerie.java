package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services;

import java.util.List;

import br.com.clearsys.professorama.model.Aluno;
import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BuscarAtividadeSerie {
    @Headers("Content-Type:Application/json")
    @GET("api/atividade/buscarAtividade/{serie}")
    Call<List<Atividade>> buscarAtividadePelaSerie(@Path("serie") String serie);

}

