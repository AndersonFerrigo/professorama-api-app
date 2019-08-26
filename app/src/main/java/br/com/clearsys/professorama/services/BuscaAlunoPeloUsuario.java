package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BuscaAlunoPeloUsuario {

    @Headers("Content-Type:Application/json")
    @GET("api/login-aluno/{usuario}")
    Call<Aluno> buscarAluno(@Path("usuario") String usuario);

}
