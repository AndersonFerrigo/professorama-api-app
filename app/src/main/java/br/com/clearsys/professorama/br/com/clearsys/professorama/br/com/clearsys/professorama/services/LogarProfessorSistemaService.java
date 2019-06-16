package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginSistema;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LogarProfessorSistemaService {
    @Headers("Content-Type:Application/json")
    @POST("api/login-professor")
    Call<LoginSistema> logarProfessor(@Body LoginSistema sistema);
}
