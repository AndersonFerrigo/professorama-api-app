package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BuscaAlunoRA {
    @Headers("Content-Type:Application/json")
    @GET("api/aluno/ra/{ra}")
    Call<Aluno> buscarAluno(@Path("ra") String ra);

}
