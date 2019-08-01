package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.dialogos.DialogEditarPerfilAluno;
import br.com.clearsys.professorama.br.com.clearsys.professorama.dialogos.DialogoDeletarPerfil;

public class FragmentCalendarioAluno extends Fragment {

    Button imgBtnEditarPerfil;
    Button imgBtnDeletarPerfil;

    DialogEditarPerfilAluno dialogEditarPerfilAluno = new DialogEditarPerfilAluno();
    DialogoDeletarPerfil dialogoDeletarPerfil = new DialogoDeletarPerfil();

    public FragmentCalendarioAluno() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendario_aluno, container, false);

        imgBtnEditarPerfil = view.findViewById(R.id.btn_atualiza_aluno);
        imgBtnDeletarPerfil = view.findViewById(R.id.btn_deletar_perfil_aluno);

        imgBtnDeletarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoDeletarPerfil.show(getFragmentManager(), "dialogDeletarPerfilAluno ");
            }
        });

        imgBtnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEditarPerfilAluno.show(getFragmentManager(), "dialogEditarPerfilAluno");
            }
        });
        return view;
    }
}