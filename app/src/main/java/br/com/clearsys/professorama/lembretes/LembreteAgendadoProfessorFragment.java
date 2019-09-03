package br.com.clearsys.professorama.lembretes;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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
import br.com.clearsys.professorama.adapters.ListAdapterLembreteProfessor;
import br.com.clearsys.professorama.adapters.ListAdapterView;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import br.com.clearsys.professorama.model.Lembretes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LembreteAgendadoProfessorFragment extends Fragment {

    String materia;

    View view;
    List<Lembretes> lembretes;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layout;
    public LembreteAgendadoProfessorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        view = inflater.inflate(R.layout.fragment_lembrete_agendado_professor, container, false);

        recyclerView = view.findViewById(R.id.recycler_tarefa);

        registerForContextMenu(recyclerView);
        String materiaRecebida = materia;

        final Call<List<Lembretes>> listaLembretes = new RetrofitConfig().getLembreteProfessorPorMateria().buscarLembretePelaMateria(materiaRecebida);
        listaLembretes.enqueue(new Callback<List<Lembretes>>() {
            @Override
            public void onResponse(Call<List<Lembretes>> call, Response<List<Lembretes>> response) {
                lembretes = response.body();
                if (response.isSuccessful()) {
                    List<Lembretes> lembretesResponse = new ArrayList<>();

                    for (Lembretes lembrete : lembretes) {
                        lembrete.getMateria();
                        lembrete.getData();
                        lembrete.getSerie();
                        lembrete.getDescricao();
                        lembretesResponse.add(lembrete);
                    }
                    recyclerView.setAdapter(new ListAdapterLembreteProfessor(lembretesResponse, getContext()));
                    layout = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);

                } else {
                    Snackbar snackbar = Snackbar
                            .make(view, "Erro ao buscar lembrete", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<List<Lembretes>> call, Throwable t) {
                Snackbar snackbar = Snackbar
                        .make(view, "Erro ao buscar lembrete", Snackbar.LENGTH_LONG);
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
