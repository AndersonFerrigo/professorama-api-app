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

import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Lembretes;
import br.com.clearsys.professorama.novaAtividade.MostraAtividadeAluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapterLembreteAluno extends RecyclerView.Adapter<ListAdapterLembreteAluno.MyHolder> {
    Bundle bundle;
    String lembreteId;

    private Lembretes lembrete;
    private List<Lembretes> lembretes;
    private Context context;

    public ListAdapterLembreteAluno(List<Lembretes> lembrete, Context context) {
        this.lembretes = lembrete;
        this.context = context;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView assunto;
        public TextView data;
        public TextView serie;
        public TextView descricao;

        public MyHolder(View v) {
            super(v);
            assunto = v.findViewById(R.id.list_assunto_lembrete_aluno);
            data = v.findViewById(R.id.list_data_lembrete_aluno);
            serie = v.findViewById(R.id.list_serie_lembrete_aluno);
            descricao = v.findViewById(R.id.list_descricao_lembrete_aluno);
        }

        @Override
        public void onClick(View v) {
        }
    }

    @Override
    public ListAdapterLembreteAluno.MyHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_lembrete_aluno_recycler, parent, false);

        ListAdapterLembreteAluno.MyHolder atividadeViewHolder = new ListAdapterLembreteAluno.MyHolder(view);

        return atividadeViewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapterLembreteAluno.MyHolder viewHolder, final int position) {
        lembrete = lembretes.get(position);
        ListAdapterLembreteAluno.MyHolder lembreteViewHolder = viewHolder;

        lembreteViewHolder.data.setText(lembrete.getData());
        lembreteViewHolder.serie.setText(lembrete.getSerie());
        lembreteViewHolder.descricao.setText(lembrete.getDescricao());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                lembreteId = String.valueOf(lembretes.get(position).getId());
                long id = Integer.parseInt(lembreteId);

                Call<Lembretes> buscaAtividadeId = new RetrofitConfig().getLembreteProfessorPorId().buscarLembretePeloId(id);
                buscaAtividadeId.enqueue(new Callback<Lembretes>() {
                    @Override
                    public void onResponse(Call<Lembretes> call, Response<Lembretes> response) {
                        lembrete = response.body();
                        if (lembrete != null) {
                            bundle = new Bundle();
                            bundle.putParcelable("lembrete", lembrete);
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(v.getRootView(), "Lembrete não encontrado", Snackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();
                        }

                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment fragment = new MostraAtividadeAluno();
                        fragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragmentAluno, fragment).addToBackStack(null).commit();
                    }

                    @Override
                    public void onFailure(Call<Lembretes> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(v.getRootView(), "Lembrete não encontrado", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return lembretes.size();
    }
}
