package br.com.clearsys.professorama.br.com.clearsys.professorama.professor;


import android.os.Bundle;
import android.service.autofill.UserData;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters.ListAdapterView;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.services.ItemClickListener;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TarefaAgendadaFragment extends Fragment {
    String materia;
    View view;
    Atividade atividade;
    List<Atividade> atividades ;
    RecyclerView recyclerView;
    ListAdapterView listAdapterView;

    AlertDialog.Builder builder;

    AlertDialog dialog;

    Button add,btn_update,btn_cancel;

    public TarefaAgendadaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        final Call<List<Atividade>> listaAtividade = new RetrofigConfig().getAtividadeByMateria().buscarAtividadePelaMAteria(materia);
        listaAtividade.enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                atividades = response.body();

                if (response.isSuccessful()) {
                    List<Atividade>atividadesResponse = response.body();

                    for (Atividade atividade: new ArrayList<Atividade>(atividadesResponse)){
                        atividade = new Atividade();

                        atividade.getMateria();
                        atividade.getDataEntrega();
                        atividade.getSerie();
                        atividade.getDescricao();

                        atividades.add(atividade);
                    }

                    recyclerView.setAdapter(new ListAdapterView(atividades, getContext()));

                    LayoutManager layout = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);

                }
                else {
                    Toast.makeText(getContext(), "Erro ao buscar Atividade = " + atividades, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Atividade>> call, Throwable t) {

            }

        });

        return view;
    }

}
