package br.com.clearsys.professorama.br.com.clearsys.professorama.professor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.clearsys.professorama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TarefaAgendadaFragment extends Fragment {


    public TarefaAgendadaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarefa_agendada, container, false);
    }

}
