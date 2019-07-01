package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade.AtividadeViewHolder;
import br.com.clearsys.professorama.model.Atividade;

public class ListAdapterView extends RecyclerView.Adapter {

    private List<Atividade> atividades;
    private Context context;

    public ListAdapterView(List<Atividade> atividades, Context context){
        this.atividades = atividades;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.fragment_tarefas_aluno, parent,false);
        AtividadeViewHolder atividadeViewHolder = new AtividadeViewHolder(view);
        return atividadeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                                        int position) {

        AtividadeViewHolder atividadeViewHolder = (AtividadeViewHolder) viewHolder;
        Atividade atividade = atividades.get(position);
        atividadeViewHolder.materia.setText(atividade.getMateria());
        atividadeViewHolder.dataEntrega.setText(atividade.getDataEntrega());
        atividadeViewHolder.descricao.setText(atividade.getDescricao());

    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }
}
