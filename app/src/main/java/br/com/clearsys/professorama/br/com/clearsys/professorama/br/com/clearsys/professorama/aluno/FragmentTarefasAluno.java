package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters.ListAdapterAluno;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters.ListAdapterView;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTarefasAluno extends Fragment {

    String serie;
    View view;
    Atividade atividade;
    List<Atividade> atividades ;
    RecyclerView recyclerView;

    public FragmentTarefasAluno() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;
        bundle = getArguments();

        Toast.makeText(getContext(), "Recebido do bundle: " + bundle, Toast.LENGTH_SHORT).show();
        if (bundle != null) {
            serie = bundle.getString("serie");
        } else {
            Toast.makeText(getContext(), "Bundle nulo", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  =  inflater.inflate(R.layout.fragment_tarefas_aluno, container, false);

        recyclerView = view.findViewById(R.id.recycler_tarefa_aluno);

        Call<List<Atividade>> listaAtividadeBySerie = new RetrofigConfig().getAtividadeBySerie().buscarAtividadePelaSerie(serie);
        Toast.makeText(getActivity(), "Buscando atividade", Toast.LENGTH_LONG).show();
        listaAtividadeBySerie.enqueue(new Callback<List<Atividade>>() {
            @Override
            public void onResponse(Call<List<Atividade>> call, Response<List<Atividade>> response) {
                Toast.makeText(getActivity(), "Buscando atividade" + response.body(), Toast.LENGTH_LONG).show();
                atividades = response.body();
                Toast.makeText(getActivity(), "Buscando atividade" + atividades, Toast.LENGTH_LONG).show();

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
                    Toast.makeText(getContext(), "Atividades " + atividades, Toast.LENGTH_LONG).show();

                    recyclerView.setAdapter(new ListAdapterAluno(atividades, getContext()));

                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getContext(),
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
