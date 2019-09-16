package br.com.clearsys.professorama.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Atividade implements Parcelable {

    private int id;
    private String tipoCadastro;
    private String dataEntrega;
    private String materia;
    private String serie;
    private String descricao;

    public Atividade() {
    }

    private Atividade(Parcel in) {
        id = in.readInt();
        tipoCadastro = in.readString();
        dataEntrega = in.readString();
        materia = in.readString();
        serie = in.readString();
        descricao = in.readString();

    }

    public static final Creator<Atividade> CREATOR = new Creator<Atividade>() {
        @Override
        public Atividade createFromParcel(Parcel in) {
            return new Atividade(in);
        }

        @Override
        public Atividade[] newArray(int size) {
            return new Atividade[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(String tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
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
                "tipoCadastro='" + tipoCadastro + '\'' +
                ", dataEntrega='" + dataEntrega + '\'' +
                ", materia='" + materia + '\'' +
                ", serie='" + serie + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tipoCadastro);
        dest.writeString(dataEntrega);
        dest.writeString(materia);
        dest.writeString(serie);
        dest.writeString(descricao);
    }

}
