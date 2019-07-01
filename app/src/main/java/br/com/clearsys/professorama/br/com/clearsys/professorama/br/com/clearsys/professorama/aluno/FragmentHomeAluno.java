package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.model.Aluno;

public class FragmentHomeAluno extends Fragment {

    Aluno aluno;
    TextView nomeAluno;
    TextView serieAluno;

    public FragmentHomeAluno() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_aluno, container, false);


        return view;

    }

}
