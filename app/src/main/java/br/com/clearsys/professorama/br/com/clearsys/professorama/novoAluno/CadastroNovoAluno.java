package br.com.clearsys.professorama.br.com.clearsys.professorama.novoAluno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginActivity;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroNovoAluno extends AppCompatActivity {

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_aluno);


        Button btnCadastraAluno = findViewById(R.id.btnCadastraNovoAluno);

        btnCadastraAluno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText txtNome = findViewById(R.id.nome);
                aluno.setNome(txtNome.getText().toString());

                EditText txtSerie = findViewById(R.id.serie);
                aluno.setSerie(txtSerie.getText().toString());


                TextView txtPerfil = findViewById(R.id.perfil);
                aluno.setPerfil(txtPerfil.getText().toString());

                EditText txtUsuario = findViewById(R.id.usuario);
                aluno.setUsuario(txtUsuario.getText().toString());

                EditText txtSenha = findViewById(R.id.senha);
                aluno.setSenha(txtSenha.getText().toString());

                Toast.makeText(getApplicationContext(), "Antes do OnResponse" + aluno.toString(), Toast.LENGTH_SHORT).show();


                Call<Aluno> alunoCall = new RetrofigConfig().getAlunoService().cadastrarAluno(aluno);
                alunoCall.enqueue(new Callback<Aluno>() {
                    @Override
                    public void onResponse(Call<Aluno> alunoCall1, Response<Aluno> response) {
                        aluno = response.body();
                        Toast.makeText(getApplicationContext(), "Entrou no OnResponse", Toast.LENGTH_SHORT).show();

                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Aluno Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            Intent retornaTelaPrincipal = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(retornaTelaPrincipal);
                        }
                    }

                    @Override
                    public void onFailure(Call<Aluno> alunoCall1, Throwable t) {
                        Log.e("AlunoService   ", "Erro ao cadastrar aluno:" + t.getMessage());
                    }

                });
            }
        });

    }

}
