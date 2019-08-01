package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;

public class AtividadeViewHolder extends RecyclerView.ViewHolder  {
    public TextView materia;
    public TextView serie;
    public TextView dataEntrega;
    public TextView descricao;
    public TextView edit;
    public TextView delete;

    public AtividadeViewHolder(final View itemView) {
        super(itemView);
        materia = itemView.findViewById(R.id.list_materia);
        dataEntrega = itemView.findViewById(R.id.list_data_entrega);
        serie = itemView.findViewById(R.id.list_serie);
        descricao = itemView.findViewById(R.id.list_descricao);


    }






}
