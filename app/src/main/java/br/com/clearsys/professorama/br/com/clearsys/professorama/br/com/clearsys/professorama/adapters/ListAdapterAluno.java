package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade.AtividadeViewHolder;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade.AtividadeViewHolderAluno;
import br.com.clearsys.professorama.model.Atividade;

public class ListAdapterAluno extends RecyclerView.Adapter {


    private List<Atividade> atividades;
    private Context context;

    public ListAdapterAluno(List<Atividade> atividade, Context context){
        this.atividades = atividade;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.lista_atividades_aluno, parent,false);

        AtividadeViewHolderAluno atividadeViewHolderAluno = new AtividadeViewHolderAluno(view);

        return atividadeViewHolderAluno;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        AtividadeViewHolderAluno atividadeViewHolderAluno = (AtividadeViewHolderAluno) viewHolder;

        Atividade atividade = atividades.get(position);

        atividadeViewHolderAluno.materia.setText(atividade.getMateria());
        atividadeViewHolderAluno.dataEntrega.setText(atividade.getDataEntrega());
        atividadeViewHolderAluno.serie.setText(atividade.getSerie());
        atividadeViewHolderAluno.descricao.setText(atividade.getDescricao());

        

    }

    public void onClick(){

    }
    @Override
    public int getItemCount() {
        return atividades.size();
    }
}
