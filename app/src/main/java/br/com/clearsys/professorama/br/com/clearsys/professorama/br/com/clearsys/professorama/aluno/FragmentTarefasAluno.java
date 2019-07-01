package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.adapters.ListAdapterView;
import br.com.clearsys.professorama.model.Atividade;

public class FragmentTarefasAluno extends Fragment {

    RecyclerView recyclerView;

    public FragmentTarefasAluno() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_tarefas_aluno, container, false);


        recyclerView = view.findViewById(R.id.recycler);
        //recyclerView.setAdapter(new ListAdapterView(atividades, this);

      //  RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

       // recyclerView.setLayoutManager(manager);

        return view;
    }
}
