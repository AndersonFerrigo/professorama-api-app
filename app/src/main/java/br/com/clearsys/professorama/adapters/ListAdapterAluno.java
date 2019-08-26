package br.com.clearsys.professorama.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import br.com.clearsys.professorama.novaAtividade.MostraAtividadeAluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapterAluno extends RecyclerView.Adapter<ListAdapterAluno.MyHolder> {
    Bundle bundle;
    String atividadeId;

    private Atividade atividade;
    private List<Atividade> atividades;
    private Context context;

    public ListAdapterAluno(List<Atividade> atividade, Context context) {
        this.atividades = atividade;
        this.context = context;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView materia;
        public TextView dataEntrega;
        public TextView descricao;

        public MyHolder(View v) {
            super(v);
            materia = v.findViewById(R.id.list_materia_aluno);
            dataEntrega = v.findViewById(R.id.list_data_entrega_aluno);
            descricao = v.findViewById(R.id.list_descricao_aluno);
        }

        @Override
        public void onClick(View v) {
        }
    }

    @Override
    public ListAdapterAluno.MyHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_atividades_aluno, parent, false);

        ListAdapterAluno.MyHolder atividadeViewHolder = new ListAdapterAluno.MyHolder(view);

        return atividadeViewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapterAluno.MyHolder viewHolder, final int position) {
        atividade = atividades.get(position);
        ListAdapterAluno.MyHolder atividadeViewHolder = viewHolder;

        atividadeViewHolder.materia.setText(atividade.getMateria());
        atividadeViewHolder.dataEntrega.setText(atividade.getDataEntrega());
        atividadeViewHolder.descricao.setText(atividade.getDescricao());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                atividadeId = String.valueOf(atividades.get(position).getId());
                long id = Integer.parseInt(atividadeId);

                Call<Atividade> buscaAtividadeId = new RetrofitConfig().getAtividadePeloId().buscarAtividadePeloId(id);
                buscaAtividadeId.enqueue(new Callback<Atividade>() {
                    @Override
                    public void onResponse(Call<Atividade> call, Response<Atividade> response) {
                        atividade = response.body();
                        if (atividade != null) {
                            bundle = new Bundle();
                            bundle.putParcelable("atividade", atividade);
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(v.getRootView(), "Atividade não encontrada", Snackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();
                        }

                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment fragment = new MostraAtividadeAluno();
                        fragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragmentAluno, fragment).addToBackStack(null).commit();
                    }

                    @Override
                    public void onFailure(Call<Atividade> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(v.getRootView(), "Atividade não encontrada", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }
}

