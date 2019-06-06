package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.clearsys.professorama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescricaoNovaAtividadeFragment extends Fragment {

    Bundle recebeData = new Bundle();
    TextView txtRecebeDataSelecionada;

    public DescricaoNovaAtividadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_descricao_nova_atividade, container, false);


        return v;
    }

}
