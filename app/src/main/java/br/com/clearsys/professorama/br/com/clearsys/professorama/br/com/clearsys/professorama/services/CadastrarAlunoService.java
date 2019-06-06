package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CadastrarAlunoService {
    @Headers("Content-Type: Application/json")
    @POST("api/cadastrar-aluno")
    Call <Aluno> cadastrarAluno(@Body Aluno aluno);

}
