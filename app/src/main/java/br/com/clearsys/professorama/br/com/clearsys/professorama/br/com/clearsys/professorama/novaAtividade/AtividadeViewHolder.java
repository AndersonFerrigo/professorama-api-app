package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.clearsys.professorama.R;

public class AtividadeViewHolder extends RecyclerView.ViewHolder {
     public TextView materia;
     public TextView dataEntrega;
     public TextView descricao;

    public AtividadeViewHolder(View itemView) {
        super(itemView);
        materia = itemView.findViewById(R.id.materia);
        dataEntrega = itemView.findViewById(R.id.data_entrega);
        descricao = itemView.findViewById(R.id.descricao);
    }
}
