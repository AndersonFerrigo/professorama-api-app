package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginSistema;
import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LogarAlunoSistemaService {
    @Headers("Content-Type:Application/json")
    @POST("api/login-aluno")
    Call<LoginSistema> logarAluno(@Body Aluno aluno);
}
