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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import br.com.clearsys.professorama.professor.TarefaAgendadaFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtualizarAtividadeFragment extends Fragment {

    TarefaAgendadaFragment tarefaAgendadaFragment = new TarefaAgendadaFragment();
    private static final String NOVA_ATIVIDADE_FRAGMENT = "NOVA_ATIVIDADE_FRAGMENT";

    Atividade atividade;
    View view;

    private int index;
    private long id;
    private int indexMateria;

    private String itemSelecionado;
    private String dataEntrega;
    private String materia;
    private String serie;
    private String descricao;
    private String materiaSelecionada;


    TextView txtRecebeId;
    EditText txtRecebeDataInicio;
    EditText txtRecebeDataEntrega;
    EditText txtMateriaProfessor;
    EditText txtSerieAtividade;
    EditText txtDescricaoAtividade;

    Button atualiza;
    Button cancelaAtualizar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle ;
        bundle = getArguments();

        Toast.makeText(getContext(), "Recebido do bundle: " + bundle, Toast.LENGTH_SHORT).show();
        if (bundle != null) {
            atividade = bundle.getParcelable("atividade");
        } else {
            Toast.makeText(getContext(), "Não foi possível recuperar as atividades", Toast.LENGTH_LONG).show();
        }
    }

    public AtualizarAtividadeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_recebe_atividade_atualizar, container, false);

        if (atividade != null) {
            id = atividade.getId();
            dataEntrega = atividade.getDataEntrega();
            materia = atividade.getMateria();
            serie = atividade.getSerie();
            descricao = atividade.getDescricao();

        } else {
                Toast.makeText(getContext(), "Intent de atividade nulo ", Toast.LENGTH_LONG).show();
            }

        Spinner spMaterias = view.findViewById(R.id.spinner_materias);
        ArrayAdapter<CharSequence> materiaAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.materias, R.layout.spinner_item);
        materiaAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spMaterias.setAdapter(materiaAdapter);
        spMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int interneIndex = parent.getSelectedItemPosition();
                indexMateria = interneIndex;
                String pegaItem = parent.getSelectedItem().toString();
                materiaSelecionada = pegaItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar snackbar = Snackbar
                        .make(parent, "Escolha uma sala", Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();
            }
        });

        Spinner spSalas = view.findViewById(R.id.spinner_salas);
        ArrayAdapter<CharSequence> salasAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.salas, R.layout.spinner_item);
        salasAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spSalas.setAdapter(salasAdapter);
        spSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int interneIndex = parent.getSelectedItemPosition();
                index = interneIndex;
                String pegaItem = parent.getSelectedItem().toString();
                itemSelecionado = pegaItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar snackbar = Snackbar
                        .make(parent, "Escolha uma sala", Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();

            }
        });

        txtRecebeDataEntrega = view.findViewById(R.id.recebe_data_entrega_atual);
        txtRecebeDataEntrega.setText(dataEntrega);

        txtDescricaoAtividade = view.findViewById(R.id.recebe_descricao_atual);
        txtDescricaoAtividade.setText(descricao);

        atualiza = view.findViewById(R.id.btn_atualiza_atividade);
        cancelaAtualizar = view.findViewById(R.id.btn_cancela_atual_atividade);

        atualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atividade.setTipoCadastro(txtRecebeDataInicio.getText().toString());
                atividade.setDataEntrega(txtRecebeDataEntrega.getText().toString());
                atividade.setMateria(txtMateriaProfessor.getText().toString());

                if (index == 0) {
                    Snackbar snackbar = Snackbar
                            .make(view.getRootView(), "Escolha uma sala", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    } else {
                        atividade.setSerie(itemSelecionado);
                        }

                if (indexMateria == 0) {
                    Snackbar snackbar = Snackbar
                            .make(getView().getRootView(), "Escolha uma materia", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    atividade.setMateria(materiaSelecionada);
                }

                atividade.setDescricao(txtDescricaoAtividade.getText().toString());

                final Call<Atividade> atualizaAtividade = new RetrofitConfig().getAtualizaAtividade().atualizaAtividade(id, atividade);
                atualizaAtividade.enqueue(new Callback<Atividade>() {

                    @Override
                    public void onResponse(Call<Atividade> call, Response<Atividade> response) {
                        atividade = response.body();
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "atividade atualizada : " + atividade.toString(), Toast.LENGTH_LONG).show();
                            final FragmentManager _manager = getActivity().getSupportFragmentManager();
                            final FragmentTransaction _transaction = _manager.beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putString("materia", materia);
                            tarefaAgendadaFragment.setArguments(bundle);

                            _transaction.replace(R.id.containerForFragment, tarefaAgendadaFragment);
                            _transaction.addToBackStack(null);
                            _transaction.commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<Atividade> call, Throwable t) {
                        Toast.makeText(getContext(), "Atividade não atualizada", Toast.LENGTH_LONG).show();
                        final FragmentManager _manager = getActivity().getSupportFragmentManager();
                        final FragmentTransaction _transaction = _manager.beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putString("materia", materia);
                        tarefaAgendadaFragment.setArguments(bundle);

                        _transaction.replace(R.id.containerForFragment, tarefaAgendadaFragment);
                        _transaction.addToBackStack(null);
                        _transaction.commit();
                    }
                });
            }
        });

        cancelaAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentManager _manager = getActivity().getSupportFragmentManager();
                final FragmentTransaction _transaction = _manager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("materia", materia);
                tarefaAgendadaFragment.setArguments(bundle);

                _transaction.replace(R.id.containerForFragment, tarefaAgendadaFragment);
                _transaction.addToBackStack(null);
                _transaction.commit();
            }

        });

        return view;
    }

}
