package br.com.clearsys.professorama.novaAtividade;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.aluno.FragmentTarefasAluno;
import br.com.clearsys.professorama.model.Atividade;

public class MostraAtividadeAluno extends Fragment {
    FragmentTarefasAluno tarefasAluno = new FragmentTarefasAluno();
    Atividade atividade;

    View view;

    private long id;
    private String dataInicio;
    private String dataEntrega;
    private String materia;
    private String serie;
    private String descricao;

    TextView txtRecebeId;
    EditText txtRecebeDataInicio;
    EditText txtRecebeDataEntrega;
    EditText txtMateriaProfessor;
    EditText txtSerieAtividade;
    EditText txtDescricaoAtividade;

    Button btnFechar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle = getArguments();

        if (bundle != null) {
            atividade = bundle.getParcelable("atividade");

        } else {
            Snackbar snackbar = Snackbar
                    .make(view, "Não foi possivel recuperar a atividade", Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(Color.WHITE);
            snackbar.show();
        }

    }

    public MostraAtividadeAluno() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mostra_atividade_aluno, container, false);
        if (atividade != null) {
            id = atividade.getId();
            dataInicio = atividade.getDataInicio();
            dataEntrega = atividade.getDataEntrega();
            materia = atividade.getMateria();
            serie = atividade.getSerie();
            descricao = atividade.getDescricao();

            } else {
                    Snackbar snackbar = Snackbar
                            .make(view, "Escolha uma sala", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
              }

        txtRecebeDataInicio = view.findViewById(R.id.recebe_data_inicio_atual);
        txtRecebeDataInicio.setText(dataInicio);

        txtRecebeDataEntrega = view.findViewById(R.id.recebe_data_entrega_atual);
        txtRecebeDataEntrega.setText(dataEntrega);

        txtMateriaProfessor = view.findViewById(R.id.recebe_materia_atual);
        txtMateriaProfessor.setText(materia);

        txtSerieAtividade = view.findViewById(R.id.recebe_serie_atual);
        txtSerieAtividade.setText(serie);

        txtDescricaoAtividade = view.findViewById(R.id.recebe_descricao_atual);
        txtDescricaoAtividade.setText(descricao);

        btnFechar = view.findViewById(R.id.btn_confirma_atual_atividade);

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atividade.setDataInicio(txtRecebeDataInicio.getText().toString());
                atividade.setDataEntrega(txtRecebeDataEntrega.getText().toString());
                atividade.setMateria(txtMateriaProfessor.getText().toString());
                atividade.setSerie(txtSerieAtividade.getText().toString());
                atividade.setDescricao(txtDescricaoAtividade.getText().toString());

                final FragmentManager _manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction _transaction = _manager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("serie", serie);
                tarefasAluno.setArguments(bundle);

                _transaction.replace(R.id.containerForFragmentAluno, tarefasAluno);
                _transaction.addToBackStack(null);
                _transaction.commit();
            }


        });

        return view;
    }


}
