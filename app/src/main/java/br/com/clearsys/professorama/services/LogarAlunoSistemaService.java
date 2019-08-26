package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LogarAlunoSistemaService {
    @Headers("Content-Type:Application/json")
    @GET("api/login-aluno/login/usuario/{usuario}/senha/{senha}")
    Call<Aluno> logarAluno(@Path("usuario") String usuario, @Path("senha") String senha);

}
