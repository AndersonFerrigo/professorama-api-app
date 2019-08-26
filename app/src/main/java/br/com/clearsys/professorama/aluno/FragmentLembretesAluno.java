package br.com.clearsys.professorama.aluno;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.clearsys.professorama.R;

public class FragmentLembretesAluno extends Fragment {

    public FragmentLembretesAluno() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lembretes_aluno, container, false);
    }
}
