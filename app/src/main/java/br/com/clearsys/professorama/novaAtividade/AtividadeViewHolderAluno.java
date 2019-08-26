package br.com.clearsys.professorama.novaAtividade;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.clearsys.professorama.R;

public class AtividadeViewHolderAluno extends RecyclerView.ViewHolder {
    public TextView materia;
    public TextView serie;
    public TextView dataEntrega;
    public TextView descricao;

    public AtividadeViewHolderAluno(View itemView) {
        super(itemView);
        materia = itemView.findViewById(R.id.list_materia_aluno);
        dataEntrega = itemView.findViewById(R.id.list_data_entrega_aluno);
        descricao = itemView.findViewById(R.id.list_descricao_aluno);
    }
}
