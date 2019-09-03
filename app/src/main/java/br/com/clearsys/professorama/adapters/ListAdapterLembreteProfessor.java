package br.com.clearsys.professorama.adapters;

import android.annotation.SuppressLint;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import br.com.clearsys.professorama.model.Lembretes;
import br.com.clearsys.professorama.novaAtividade.AtualizarAtividadeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapterLembreteProfessor extends RecyclerView.Adapter<ListAdapterLembreteProfessor.MyHolder>{

    Bundle bundle;
    String atividadeId;
    int posicao;

    private Lembretes lembrete;
    private static List<Lembretes> lembretes;
    private static Context context;

    public ListAdapterLembreteProfessor(List<Lembretes> lembrete, Context context) {
        lembretes = lembrete;
        ListAdapterLembreteProfessor.context = context;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView assunto;
        public TextView serie;
        public TextView dataEntrega;
        public TextView descricao;

        public MyHolder(View v) {
            super(v);
            assunto = v.findViewById(R.id.list_assunto_lembrete_professor);
            dataEntrega = v.findViewById(R.id.list_data_lembrete_professor);
            serie = v.findViewById(R.id.list_serie_lembrete_professor);
            descricao = v.findViewById(R.id.list_descricao_lembrete_professor);

            v.setOnCreateContextMenuListener(this); //REGISTER ONCREATE MENU LISTENER
        }

        @SuppressLint("ResourceType")
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v,                        //CREATE MENU BY THIS METHOD
                                        ContextMenu.ContextMenuInfo menuInfo) {

            v = LayoutInflater.from(context).inflate(R.layout.options_menu_professor, null);
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
                            atividadeId = String.valueOf(lembretes.get(posicao).getId());
                            long id = Integer.parseInt(atividadeId);

                            Call<Lembretes> buscaAtividadeId = new RetrofitConfig().getLembreteProfessorPorId().buscarLembretePeloId(id);
                            buscaAtividadeId.enqueue(new Callback<Lembretes>() {
                                @Override
                                public void onResponse(Call<Lembretes> call, Response<Lembretes> response) {
                                    lembrete = response.body();
                                    if (lembrete != null) {
                                        bundle = new Bundle();
                                        bundle.putParcelable("atividade", lembrete);

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
                                public void onFailure(Call<Lembretes> call, Throwable t) {
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

                            atividadeId = String.valueOf(lembretes.get(posicao).getId());
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
                                        lembretes.remove(posicao);
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
    public MyHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_atividade_recycler, parent, false);

        MyHolder atividadeViewHolder = new MyHolder(view);
        return atividadeViewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapterLembreteProfessor.MyHolder viewHolder,
                                 int position) {

        ListAdapterLembreteProfessor.MyHolder atividadeViewHolder = viewHolder;
        Lembretes lembrete = lembretes.get(position);

        atividadeViewHolder.assunto.setText(lembrete.getAssunto());
        atividadeViewHolder.dataEntrega.setText(lembrete.getData());
        atividadeViewHolder.serie.setText(lembrete.getSerie());
        atividadeViewHolder.descricao.setText(lembrete.getDescricao());
    }

    @Override
    public int getItemCount() {
        return lembretes.size();
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
