package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.clearsys.professorama.R;

public class FragmentTarefasAluno extends Fragment {

    public FragmentTarefasAluno() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefas_aluno, container, false);
    }
}
