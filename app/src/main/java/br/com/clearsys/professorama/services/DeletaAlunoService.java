package br.com.clearsys.professorama.services;

import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DeletaAlunoService {
    @Headers("Content-Type:Application/json")
    @DELETE("api/aluno/deletar/{ra}")
    Call<Aluno> deletarAluno(@Path("ra") String ra);

}
