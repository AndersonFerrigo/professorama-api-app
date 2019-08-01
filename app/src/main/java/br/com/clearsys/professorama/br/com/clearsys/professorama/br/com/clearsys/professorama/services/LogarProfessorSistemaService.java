package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginSistema;
import br.com.clearsys.professorama.model.Aluno;
import br.com.clearsys.professorama.model.Professor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LogarProfessorSistemaService {
    @Headers("Content-Type:Application/json")
    @GET("api/login-professor/login/usuario/{usuario}/senha/{senha}")
    Call<Professor> logarProfessor(@Path("usuario") String usuario , @Path("senha") String senha);

}
