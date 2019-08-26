package br.com.clearsys.professorama.aluno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.adapters.ListAdapterAluno;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTarefasAluno extends Fragment {

    String serie;
    View view;
    Atividade atividade;
    List<Atividade> atividades;
    RecyclerView recyclerView;

    public FragmentTarefasAluno() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            serie = bundle.getString("serie");
        } else {
            Toast.makeText(getContext(), "Não foi possivel recuperar a serie", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tarefas_aluno, container, false);

        recyclerView = view.findViewById(R.id.recycler_tarefa_aluno);

        Call<List<Atividade>> listaAtividadeBySerie = new RetrofitConfig().getAtividadeBySerie().buscarAtividadePelaSerie(serie);
        listaAtividadeBySerie.enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                atividades = response.body();

                List<Atividade> atividadesResponse = null;
                if (response.isSuccessful()) {
                    atividadesResponse = new ArrayList<>();

                    for (Atividade atividade : atividades) {

                        atividade.getMateria();
                        atividade.getDataEntrega();
                        atividade.getDescricao();

                        atividadesResponse.add(atividade);
                    }
                }
                recyclerView.setAdapter(new ListAdapterAluno(atividadesResponse, getContext()));
                RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL, false);

                recyclerView.setLayoutManager(layout);
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
            }
        });
        return view;
    }
}
