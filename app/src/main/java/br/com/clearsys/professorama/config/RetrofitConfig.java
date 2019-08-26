package br.com.clearsys.professorama.config;

import br.com.clearsys.professorama.services.AtualizaAlunoService;
import br.com.clearsys.professorama.services.AtualizaAtividadeService;
import br.com.clearsys.professorama.services.BuscaAlunoPeloUsuario;
import br.com.clearsys.professorama.services.BuscaAlunoRA;
import br.com.clearsys.professorama.services.BuscarAtividadePeloId;
import br.com.clearsys.professorama.services.BuscarAtividadePorMateria;
import br.com.clearsys.professorama.services.BuscarAtividadeSerie;
import br.com.clearsys.professorama.services.CadastraAtividadeService;
import br.com.clearsys.professorama.services.CadastrarAlunoService;
import br.com.clearsys.professorama.services.DeletaAlunoService;
import br.com.clearsys.professorama.services.DeletaAtividadePeloId;
import br.com.clearsys.professorama.services.LogarAlunoSistemaService;
import br.com.clearsys.professorama.services.LogarProfessorSistemaService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                // wifi casa
                .baseUrl("http://192.168.0.17:8080/")

                // 4G celular
                //.baseUrl("http://192.168.43.26:8080/")
                .build();
    }

    public CadastrarAlunoService getAlunoService() {
        return this.retrofit.create(CadastrarAlunoService.class);
    }

    public LogarAlunoSistemaService getAlunoLoginSistema() {
        return this.retrofit.create(LogarAlunoSistemaService.class);
    }

    public LogarProfessorSistemaService getProfessorLoginSistema() {
        return this.retrofit.create(LogarProfessorSistemaService.class);
    }

    public CadastraAtividadeService getCadastroAtividadeService() {
        return this.retrofit.create(CadastraAtividadeService.class);
    }

    public BuscaAlunoPeloUsuario getAlunoPeloUsuario() {
        return this.retrofit.create(BuscaAlunoPeloUsuario.class);
    }

    public BuscaAlunoRA getAlunoRA() {
        return this.retrofit.create(BuscaAlunoRA.class);
    }

    public AtualizaAlunoService getAtualizaAluno() {
        return this.retrofit.create(AtualizaAlunoService.class);
    }

    public DeletaAlunoService getDeleteAluno() {
        return this.retrofit.create(DeletaAlunoService.class);
    }

    public BuscarAtividadeSerie getAtividadeBySerie() {
        return this.retrofit.create(BuscarAtividadeSerie.class);
    }

    public BuscarAtividadePorMateria getAtividadeByMateria() {
        return this.retrofit.create(BuscarAtividadePorMateria.class);
    }

    public BuscarAtividadePeloId getAtividadePeloId() {
        return this.retrofit.create(BuscarAtividadePeloId.class);
    }

    public AtualizaAtividadeService getAtualizaAtividade() {
        return this.retrofit.create(AtualizaAtividadeService.class);
    }

    public DeletaAtividadePeloId deletaAtividadePeloId() {
        return this.retrofit.create(DeletaAtividadePeloId.class);
    }
}
