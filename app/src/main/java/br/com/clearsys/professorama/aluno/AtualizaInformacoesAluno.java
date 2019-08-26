package br.com.clearsys.professorama.aluno;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import br.com.clearsys.professorama.dialogos.RetornaAlunoLogin;
import br.com.clearsys.professorama.model.Aluno;
import br.com.clearsys.professorama.professor.TarefaAgendadaFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtualizaInformacoesAluno extends Fragment {

    TarefaAgendadaFragment tarefaAgendadaFragment = new TarefaAgendadaFragment();
    private static final String NOVA_ATIVIDADE_FRAGMENT = "NOVA_ATIVIDADE_FRAGMENT";
    private RetornaAlunoLogin retornaAlunoLogin = new RetornaAlunoLogin();
    private Aluno aluno;

    int index;
    int idAluno;

    String itemSelecionado;
    String nomeAluno;
    String raAluno;
    String serieAluno;
    String usuarioAluno;
    String senhaAluno;

    TextView recebeId;
    EditText recebeNome;
    Spinner recebeSerie;
    EditText recebeRa;
    EditText recebeUsuario;
    EditText recebeSenha;

    Button atualizarPerfilAluno;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;
        bundle = getArguments();
        if (bundle != null) {
            aluno = bundle.getParcelable("aluno");
        } else {
            Toast.makeText(getContext(), "Nenhum aluno encontrado", Toast.LENGTH_LONG).show();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atualiza_aluno, container, false);

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

        idAluno = aluno.getId();
        nomeAluno = aluno.getNome();
        raAluno = aluno.getRa();
        serieAluno = aluno.getSerie();
        usuarioAluno = aluno.getUsuario();
        senhaAluno = aluno.getSenha();

        recebeId = view.findViewById(R.id.recebe_id);
        recebeId.setText(String.valueOf(idAluno));

        recebeNome = view.findViewById(R.id.recebe_nome);
        recebeNome.setText(nomeAluno);

        recebeRa = view.findViewById(R.id.recebe_ra);
        recebeRa.setText(raAluno);

        recebeUsuario = view.findViewById(R.id.recebe_usuario);
        recebeUsuario.setText(usuarioAluno);

        recebeSenha = view.findViewById(R.id.recebe_senha);
        recebeSenha.setText(senhaAluno);

        atualizarPerfilAluno = view.findViewById(R.id.btn_atualiza_aluno);
        atualizarPerfilAluno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                aluno.setId(recebeId.getId());
                aluno.setNome(recebeNome.getText().toString());
                aluno.setSerie(itemSelecionado);
                aluno.setRa(recebeRa.getText().toString());
                aluno.setUsuario(recebeUsuario.getText().toString());
                aluno.setSenha(recebeSenha.getText().toString());

                Call<Aluno> atualizaAluno = new RetrofitConfig().getAtualizaAluno().buscarAluno(idAluno, aluno);
                atualizaAluno.enqueue(new Callback<Aluno>() {

                    @Override
                    public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                        aluno = response.body();
                        if (response.isSuccessful()) {
                            retornaAlunoLogin.show(getFragmentManager(), "dialogRetornaAlunoLogin");
                            AtualizaInformacoesAluno.this.isDetached();
                        }
                    }

                    @Override
                    public void onFailure(Call<Aluno> call, Throwable t) {
                        Toast.makeText(getContext(), "Usuario não atualizado", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return view;
    }

}
