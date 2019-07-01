package br.com.clearsys.professorama.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Atividade implements Serializable {

    private String dataInicio;
    private String dataEntrega;
    private String materia;
    private String serie;
    private String descricao;

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Atividade{" +
                "dataInicio='" + dataInicio + '\'' +
                ", dataEntrega='" + dataEntrega + '\'' +
                ", materia='" + materia + '\'' +
                ", serie='" + serie + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
