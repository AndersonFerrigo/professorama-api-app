package br.com.clearsys.professorama.professor;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;

public class HomeFragment extends Fragment {
    TextView nomeProfessor;
    TextView materiaProfessor;

    private String nome;
    private String materia;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;
        bundle = getArguments();

           if (bundle != null) {
                nome = bundle.getString("nome");
                materia = bundle.getString("materia");
              } else {
                    Toast.makeText(getContext(), "Não foi possível recuperar dados", Toast.LENGTH_LONG).show();
                 }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        nomeProfessor = view.findViewById(R.id.recebe_nome_professor);
        nomeProfessor.setText(nome);
        materiaProfessor = view.findViewById(R.id.recebe_materia_professor);
        materiaProfessor.setText(materia);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("nome", nome);
        outState.putSerializable("materia", materia);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            nome = String.valueOf(savedInstanceState.getSerializable(nome));
            materia = String.valueOf(savedInstanceState.getSerializable(materia));
        }
    }
}
