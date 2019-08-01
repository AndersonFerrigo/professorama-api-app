package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.dialogos.RetornaAlunoLogin;
import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginActivity;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtualizaInformacoesAluno extends Fragment {
    private RetornaAlunoLogin retornaAlunoLogin = new RetornaAlunoLogin();
    private Aluno aluno;

    int idAluno;
    String nomeAluno;
    String raAluno;
    String serieAluno;
    String usuarioAluno;
    String senhaAluno;

    TextView recebeId;
    EditText recebeNome;
    EditText recebeSerie;
    EditText recebeRa;
    EditText recebeUsuario;
    EditText recebeSenha;

    Button atualizarPerfilAluno;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle = getArguments();

        Toast.makeText(getContext(), "Recebido do bundle: " + bundle , Toast.LENGTH_SHORT ).show();
        if(bundle!=null) {
            aluno = bundle.getParcelable("aluno");
        }else {
            Toast.makeText(getContext(), "Bundle nulo", Toast.LENGTH_LONG).show();
        }

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =  inflater.inflate(R.layout.fragment_atualiza_aluno, container, false);

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

        recebeSerie = view.findViewById(R.id.recebe_serie);
        recebeSerie.setText(serieAluno);

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
                aluno.setSerie(recebeSerie.getText().toString());
                aluno.setRa(recebeRa.getText().toString());
                aluno.setUsuario(recebeUsuario.getText().toString());
                aluno.setSenha(recebeSenha.getText().toString());

                Call<Aluno> atualizaAluno  = new RetrofigConfig().getAtualizaAluno().buscarAluno(idAluno,aluno);
                atualizaAluno.enqueue(new Callback<Aluno>() {
                    @Override
                    public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                        aluno = response.body();
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Aluno atualizado : " + aluno, Toast.LENGTH_LONG).show();

                            retornaAlunoLogin.show(getFragmentManager(), "dialogRetornaAlunoLogin");

                            AtualizaInformacoesAluno.this.isDetached();

                      //      Intent intent = new Intent(getContext(), LoginActivity.class);
                        //    startActivity(intent);
                        }
                    }@Override
                    public void onFailure(Call<Aluno> call, Throwable t) {
                        Toast.makeText(getContext(), "Usuario não atualizado", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    return view;
    }
}
