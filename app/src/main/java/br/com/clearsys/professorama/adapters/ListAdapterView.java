package br.com.clearsys.professorama.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import br.com.clearsys.professorama.novaAtividade.AtualizarAtividadeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapterView extends RecyclerView.Adapter<ListAdapterView.MyHolder> {
    Bundle bundle;
    String atividadeId;
    int posicao;

    private Atividade atividade;
    private static List<Atividade> atividades;
    private static Context context;

    public ListAdapterView(List<Atividade> atividade, Context context) {
        atividades = atividade;
        ListAdapterView.context = context;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
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

            MenuItem Edit = menu.add(Menu.NONE, 1, 1, "Editar");
            MenuItem Delete = menu.add(Menu.NONE, 2, 2, "Deletar");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        //ADD AN ONMENUITEM LISTENER TO EXECUTE COMMANDS ONCLICK OF CONTEXT MENU TASK
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        atividadeId = null;
                        posicao = getAdapterPosition();

                        if (posicao != RecyclerView.NO_POSITION) {
                            atividadeId = String.valueOf(atividades.get(posicao).getId());
                            long id = Integer.parseInt(atividadeId);

                            Call<Atividade> buscaAtividadeId = new RetrofitConfig().getAtividadePeloId().buscarAtividadePeloId(id);
                            buscaAtividadeId.enqueue(new Callback<Atividade>() {
                                @Override
                                public void onResponse(Call<Atividade> call, Response<Atividade> response) {
                                    atividade = response.body();
                                    if (atividade != null) {
                                        bundle = new Bundle();
                                        bundle.putParcelable("atividade", atividade);

                                        if (bundle == null) {
                                            Snackbar snackbar = Snackbar
                                                    .make(itemView.getRootView(), "Nennhum parametro passado para atividade", Snackbar.LENGTH_LONG);
                                            snackbar.setActionTextColor(Color.WHITE);
                                            snackbar.show();

                                        }

                                        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
                                        Fragment fragment = new AtualizarAtividadeFragment();
                                        fragment.setArguments(bundle);
                                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragment, fragment).addToBackStack(null).commit();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Atividade> call, Throwable t) {
                                    Snackbar snackbar = Snackbar
                                            .make(itemView.getRootView(), "Atividade incorreta", Snackbar.LENGTH_LONG);
                                    snackbar.setActionTextColor(Color.WHITE);
                                    snackbar.show();

                                }
                            });
                        }
                     break;

                    case 2:
                        atividadeId = null;
                        posicao = getAdapterPosition();
                        if (posicao != RecyclerView.NO_POSITION) {

                            atividadeId = String.valueOf(atividades.get(posicao).getId());
                            long id = Integer.parseInt(atividadeId);

                            Call<Void> deletaAtividadeId = new RetrofitConfig().deletaAtividadePeloId().deletarAtividade(id);
                            deletaAtividadeId.enqueue(new Callback<Void>() {

                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    response.code();
                                    if (response.code()==200) {

                                        Snackbar snackbar = Snackbar
                                                .make(itemView.getRootView(), "Atividade deletada", Snackbar.LENGTH_LONG);
                                        snackbar.setActionTextColor(Color.WHITE);
                                        snackbar.show();
                                        atividades.remove(posicao);
                                        notifyItemRemoved(posicao);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Snackbar snackbar = Snackbar
                                            .make(itemView.getRootView(), "Não foi possivel deletar atividade", Snackbar.LENGTH_LONG);
                                    snackbar.setActionTextColor(Color.WHITE);
                                    snackbar.show();
                                }
                            });
                        }
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

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}






