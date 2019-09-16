package br.com.clearsys.professorama.aluno;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;

public class FragmentHomeAluno extends Fragment {

    TextView nomeAluno;
    TextView serieAluno;
    String nome;
    String serie;

    public FragmentHomeAluno() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            nome = bundle.getString("nome");
            serie = bundle.getString("serie");
        } else {
            Toast.makeText(getContext(), "Não foi possivel carregar o perfil", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_aluno, container, false);

        nomeAluno = view.findViewById(R.id.nome_aluno);
        nomeAluno.setText(nome);
        serieAluno = view.findViewById(R.id.serie_aluno);
        serieAluno.setText(serie);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("nome", nome);
        outState.putSerializable("serie", serie);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            nome = String.valueOf(savedInstanceState.getSerializable(nome));
            serie = String.valueOf(savedInstanceState.getSerializable(serie));
        }
    }
}
