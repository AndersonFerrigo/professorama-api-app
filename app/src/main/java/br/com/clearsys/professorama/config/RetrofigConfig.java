package br.com.clearsys.professorama.config;

import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services.CadastrarAlunoService;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services.LogarAlunoSistemaService;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services.LogarProfessorSistemaService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofigConfig {

    private final Retrofit retrofit;


    public RetrofigConfig(){
        this.retrofit =  new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://192.168.0.17:8080/")
                .build();
    }

    public CadastrarAlunoService getAlunoService(){
        return this.retrofit.create(CadastrarAlunoService.class);
    }

    public LogarAlunoSistemaService getAlunoLoginSistema(){
        return this.retrofit.create(LogarAlunoSistemaService.class);
    }

    public LogarProfessorSistemaService getProfessorLoginSistema(){
        return this.retrofit.create(LogarProfessorSistemaService.class);
    }

}
