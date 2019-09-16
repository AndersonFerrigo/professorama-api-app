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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.dialogos.InformaAtividadeCadastrada;
import br.com.clearsys.professorama.model.Atividade;
import br.com.clearsys.professorama.model.Lembretes;
import br.com.clearsys.professorama.professor.NovaAtividadeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescricaoNovaAtividadeFragment extends Fragment {
    private Atividade atividade;

    private int indexSala;
    private int indexMateria;

    private String salaSelecionada;
    private String materiaSelecionada;
    private String data;


    EditText txtRecebeDataEntrega;
    EditText txtMateriaProfessor;
    EditText txtSerieAtividade;
    EditText txtDescricaoAtividade;

    Button btnIncluirAtividade;
    Button btnCancelarAtividade;

    public DescricaoNovaAtividadeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_descricao_nova_atividade, container, false);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
           data = bundle.getString("data");
        } else {
            Toast.makeText(getContext(), "Bundle nulo", Toast.LENGTH_LONG).show();
        }

        Spinner spMaterias = v.findViewById(R.id.spinner_materias);
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



        Spinner spSalas = v.findViewById(R.id.spinner_salas);
        ArrayAdapter<CharSequence> salasAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.salas, R.layout.spinner_item);
        salasAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spSalas.setAdapter(salasAdapter);
        spSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int interneIndex = parent.getSelectedItemPosition();
                indexSala = interneIndex;
                String pegaItem = parent.getSelectedItem().toString();
                salaSelecionada = pegaItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Snackbar snackbar = Snackbar
                        .make(parent, "Escolha uma sala", Snackbar.LENGTH_LONG);
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();
            }
        });

        txtRecebeDataEntrega = v.findViewById(R.id.data_entrega);
        txtRecebeDataEntrega.setText(data);


        btnIncluirAtividade = v.findViewById(R.id.btnIncluiNovaAtividade);
        btnIncluirAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                atividade = new Atividade();

                atividade.setDataEntrega(txtRecebeDataEntrega.getText().toString());

                if (indexMateria == 0) {
                    Snackbar snackbar = Snackbar
                            .make(getView().getRootView(), "Escolha uma sala", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    atividade.setMateria(materiaSelecionada);
                }


                if (indexSala == 0) {
                    Snackbar snackbar = Snackbar
                            .make(getView().getRootView(), "Escolha uma sala", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                } else {
                    atividade.setSerie(salaSelecionada);
                }

                txtDescricaoAtividade = getView().findViewById(R.id.descricao);
                atividade.setDescricao(txtDescricaoAtividade.getText().toString());


                Call<Atividade> atividadeCall = new RetrofitConfig().getCadastroAtividadeService().cadastrarAtividade(atividade);
                atividadeCall.enqueue(new Callback<Atividade>() {
                    @Override
                    public void onResponse(Call<Atividade> call, Response<Atividade> response) {
                        atividade = response.body();
                        if (response.isSuccessful()) {
                            InformaAtividadeCadastrada informaAtividadeCadastrada = new InformaAtividadeCadastrada();
                            informaAtividadeCadastrada.show(getFragmentManager(), "novaAtividadeFragment");
                        }
                    }

                    @Override
                    public void onFailure(Call<Atividade> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(getView(), "Erro ao cadastrar atividade", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();
                    }
                });
            }
        });

        btnCancelarAtividade = v.findViewById(R.id.btn_cancela_atual_atividade);
        btnCancelarAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.containerForFragment, new NovaAtividadeFragment());
                fragmentTransaction.commit();
            }
        });
        return v;
    }
}
