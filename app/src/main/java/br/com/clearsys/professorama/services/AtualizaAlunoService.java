package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AtualizaAlunoService {
    @Headers("Content-Type:Application/json")
    @PUT("api/aluno/atualiza/{id}")
    Call<Aluno> buscarAluno(@Path("id") int id, @Body Aluno aluno);

}
