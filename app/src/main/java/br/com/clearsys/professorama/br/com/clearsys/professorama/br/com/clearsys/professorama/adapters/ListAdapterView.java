package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.model.Atividade;

public class ListAdapterView extends RecyclerView.Adapter<ListAdapterView.MyHolder> {

    private static List<Atividade> atividades;
    private Context context;

    public ListAdapterView(List<Atividade> atividade, Context context) {
        this.atividades = atividade;
        this.context = context;
    }

    public static class MyHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView materia;
        public TextView serie;
        public TextView dataEntrega;
        public TextView descricao;


        public MyHolder(View v) {
            super(v);
            materia = v.findViewById(R.id.list_materia);
            dataEntrega = v.findViewById(R.id.list_data_entrega);
            serie = v.findViewById(R.id.list_serie);
            descricao = v.findViewById(R.id.list_descricao);


            v.setOnCreateContextMenuListener(this); //REGISTER ONCREATE MENU LISTENER

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,                        //CREATE MENU BY THIS METHOD
                                        ContextMenu.ContextMenuInfo menuInfo) {
            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Delete");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);


        }

        //ADD AN ONMENUITEM LISTENER TO EXECUTE COMMANDS ONCLICK OF CONTEXT MENU TASK
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1:
                        atividades.get(item.getItemId());
                        Toast.makeText(itemView.getContext(), "Position no edit =" +item.getItemId(), Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        atividades.remove(item.getItemId());
                        Toast.makeText(itemView.getContext(), "Position no remove =" + item.getItemId(), Toast.LENGTH_LONG).show();

                        break;
                }
                return true;
            }
        };


    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent,
                                       int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_atividade_recycler, parent, false);

        MyHolder atividadeViewHolder = new MyHolder(view);

        return atividadeViewHolder;

    }

    @Override
    public void onBindViewHolder(MyHolder viewHolder,
                                 int position) {

        MyHolder atividadeViewHolder = viewHolder;

        Atividade atividade = atividades.get(position);

        atividadeViewHolder.materia.setText(atividade.getMateria());
        atividadeViewHolder.dataEntrega.setText(atividade.getDataEntrega());
        atividadeViewHolder.serie.setText(atividade.getSerie());
        atividadeViewHolder.descricao.setText(atividade.getDescricao());

    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }
}






