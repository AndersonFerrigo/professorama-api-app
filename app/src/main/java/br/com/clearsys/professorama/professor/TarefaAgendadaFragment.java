package br.com.clearsys.professorama.professor;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.adapters.ListAdapterView;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TarefaAgendadaFragment extends Fragment {
    private String materia;

    View view;
    List<Atividade> atividades;
    RecyclerView recyclerView;
    LayoutManager layout;

    public TarefaAgendadaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            materia = bundle.getString("materia");
            } else {
                Toast.makeText(getContext(), "Bundle nulo", Toast.LENGTH_LONG).show();
                }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tarefa_agendada, container, false);
        recyclerView = view.findViewById(R.id.recycler_tarefa);

        registerForContextMenu(recyclerView);
        String materiaRecebida = materia;

        final Call<List<Atividade>> listaAtividade = new RetrofitConfig().getAtividadeByMateria().buscarAtividadePelaMAteria(materiaRecebida);
        listaAtividade.enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                atividades = response.body();
                if (response.isSuccessful()) {
                    List<Atividade> atividadesResponse = new ArrayList<>();

                    for (Atividade atividade : atividades) {
                        atividade.getMateria();
                        atividade.getDataEntrega();
                        atividade.getSerie();
                        atividade.getDescricao();
                        atividadesResponse.add(atividade);
                    }
                    recyclerView.setAdapter(new ListAdapterView(atividadesResponse, getContext()));
                    layout = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);

                } else {
                    Snackbar snackbar = Snackbar
                            .make(view, "Erro ao buscar Atividade", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {
                Snackbar snackbar = Snackbar
                        .make(view, "Erro ao buscar Atividade", Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("materia", materia);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            materia = String.valueOf(savedInstanceState.getSerializable(materia));
        }
    }

}


