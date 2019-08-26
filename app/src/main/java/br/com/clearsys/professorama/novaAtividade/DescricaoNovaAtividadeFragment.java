package br.com.clearsys.professorama.novaAtividade;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.dialogos.InformaAtividadeCadastrada;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescricaoNovaAtividadeFragment extends Fragment {
    private Atividade atividade;

    private int index;

    private String itemSelecionado;

    Bundle recebeData = new Bundle();

    EditText txtRecebeDataInicio;
    EditText txtRecebeDataSelecionada;
    EditText txtRecebeDataEntrega;
    EditText txtMateriaProfessor;
    EditText txtSerieAtividade;
    EditText txtDescricaoAtividade;

    Button btnIncluirAtividade;

    public DescricaoNovaAtividadeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_descricao_nova_atividade, container, false);

        Spinner spSalas = v.findViewById(R.id.spinner_salas);
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

        txtRecebeDataSelecionada = v.findViewById(R.id.data_inicio);
        Bundle bundle = this.getArguments();
        String mydata = bundle.getString("data");
        txtRecebeDataSelecionada.setText(mydata);

        btnIncluirAtividade = v.findViewById(R.id.btnIncluiNovaAtividade);
        btnIncluirAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                atividade = new Atividade();

                txtRecebeDataInicio = txtRecebeDataSelecionada;
                atividade.setDataInicio(txtRecebeDataInicio.getText().toString());

                txtRecebeDataEntrega = getView().findViewById(R.id.data_entrega);
                atividade.setDataEntrega(txtRecebeDataEntrega.getText().toString());

                txtMateriaProfessor = getView().findViewById(R.id.materia);
                atividade.setMateria(txtMateriaProfessor.getText().toString());

                if (index == 0) {
                    Snackbar snackbar = Snackbar
                            .make(v.getRootView(), "Escolha uma sala", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                    } else {
                        atividade.setSerie(itemSelecionado);
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
                                .make(v, "Erro ao cadastrar atividade", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();
                    }
                });
            }
        });
        return v;
    }

}
