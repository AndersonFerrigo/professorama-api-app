package br.com.clearsys.professorama.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Aluno {

    private int id;
    private String nome;
    private String serie;
    private String perfil;
    private String usuario;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Aluno = {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                ", serie = '" + serie + '\'' +
                ", perfil = '" + perfil + '\'' +
                ", usuario = '" + usuario + '\'' +
                ", senha = '" + senha + '\'' +
                '}';
    }
}
