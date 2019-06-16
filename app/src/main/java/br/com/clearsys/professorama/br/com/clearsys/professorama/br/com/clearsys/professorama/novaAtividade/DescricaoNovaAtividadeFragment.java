package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginActivity;
import br.com.clearsys.professorama.br.com.clearsys.professorama.professor.NovaAtividadeFragment;
import br.com.clearsys.professorama.br.com.clearsys.professorama.professor.ProfessorHomeActivity;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Atividade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescricaoNovaAtividadeFragment extends Fragment {
    private Atividade atividade ;

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

        txtRecebeDataSelecionada = v.findViewById(R.id.data_inicio);
        Bundle bundle = this.getArguments();
        String mydata = bundle.getString("data");
        txtRecebeDataSelecionada.setText(mydata);

        btnIncluirAtividade = v.findViewById(R.id.btnIncluiNovaAtividade);
        btnIncluirAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atividade = new Atividade();

                txtRecebeDataInicio = txtRecebeDataSelecionada;
                atividade.setDataInicio(txtRecebeDataInicio.getText().toString());

                txtRecebeDataEntrega = getView().findViewById(R.id.data_entrega);
                atividade.setDataEntrega(txtRecebeDataEntrega.getText().toString());

                txtMateriaProfessor = getView().findViewById(R.id.materia);
                atividade.setMateria(txtMateriaProfessor.getText().toString());

                txtSerieAtividade = getView().findViewById(R.id.serie);
                atividade.setSerie(txtSerieAtividade.getText().toString());

                txtDescricaoAtividade = getView().findViewById(R.id.descricao);
                atividade.setDescricao(txtDescricaoAtividade.getText().toString());

                Toast.makeText(getActivity(), "Atividade " + atividade.toString(), Toast.LENGTH_SHORT).show();
                Call<Atividade> atividadeCall = new RetrofigConfig().getCadastroAtividadeService().cadastrarAtividade(atividade);
                atividadeCall.enqueue(new Callback<Atividade>() {
                    @Override
                    public void onResponse(Call<Atividade> call, Response<Atividade> response) {
                        atividade = response.body();
                        if(response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Atividade cadastrada com sucesso", Toast.LENGTH_SHORT).show();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            Fragment novaTarefaAgendada = new Fragment();
                            // essa linha é responsável por adicionar o fragment ao stack
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.replace(R.id.ic_new_task, novaTarefaAgendada);
                            fragmentTransaction.commit();
                        }

                    }

                    @Override
                    public void onFailure(Call<Atividade> call, Throwable t) {
                        Log.e("atividadeService", "Erro ao cadastrar atividade:" + t.getMessage());
                    }
                });
            }
        });
        return v;
    }

}
