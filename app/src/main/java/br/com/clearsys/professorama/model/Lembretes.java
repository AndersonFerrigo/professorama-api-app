package br.com.clearsys.professorama.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Lembretes implements Parcelable {

    private int id;
    private String materia;
    private String serie;
    private String data;
    private String assunto;
    private String descricao;

    public Lembretes() {
    }

    private Lembretes(Parcel in) {
        id = in.readInt();
        materia = in.readString();
        serie = in.readString();
        data = in.readString();
        assunto = in.readString();
        descricao = in.readString();

    }

    public static final Creator<Lembretes> CREATOR = new Creator<Lembretes>() {
        @Override
        public Lembretes createFromParcel(Parcel in) {
            return new Lembretes(in);
        }

        @Override
        public Lembretes[] newArray(int size) {
            return new Lembretes[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Lembretes{" +
                "id=" + id +
                ", materia='" + materia + '\'' +
                ", serie='" + serie + '\'' +
                ", data='" + data + '\'' +
                ", assunto='" + assunto + '\'' +
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
        dest.writeString(materia);
        dest.writeString(serie);
        dest.writeString(data);
        dest.writeString(assunto);
        dest.writeString(descricao);
    }

}
