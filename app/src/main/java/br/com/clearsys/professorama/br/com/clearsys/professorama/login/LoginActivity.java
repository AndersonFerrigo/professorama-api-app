package br.com.clearsys.professorama.br.com.clearsys.professorama.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno.AlunoHomeActivity;
import br.com.clearsys.professorama.br.com.clearsys.professorama.novoAluno.CadastroNovoAluno;
import br.com.clearsys.professorama.br.com.clearsys.professorama.professor.ProfessorHomeActivity;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Aluno;
import br.com.clearsys.professorama.model.Professor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    LoginSistema sistema = new LoginSistema();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText user = findViewById(R.id.textInfoUser);
        final EditText password = findViewById(R.id.textInfoPassword);

        final CheckBox chcbxAluno = findViewById(R.id.chc_box_aluno);

        final Button btnCadastrarNovoAluno = findViewById(R.id.btnCadastraNovoAluno);
        final Button btnAcessar = findViewById(R.id.btnAcessaSistema);

        btnCadastrarNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastraNovoAluno = new Intent(getApplicationContext(),CadastroNovoAluno.class);
                startActivity(cadastraNovoAluno);
            }
        });


        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sistema.setUsuario(user.getText().toString());
                sistema.setSenha(password.getText().toString());

                if ((sistema.getUsuario().equals("")) && (sistema.getSenha().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Os campos devem ser preenchidos", Toast.LENGTH_LONG).show();

                } else if (chcbxAluno.isChecked()) {

                    Toast.makeText(getApplicationContext(), "Buscando em aluno", Toast.LENGTH_LONG).show();
                    Call<LoginSistema> loginAluno = new RetrofigConfig().getAlunoLoginSistema().logarAluno(sistema);
                    loginAluno.enqueue(new Callback<LoginSistema>() {
                        @Override
                        public void onResponse(Call<LoginSistema> loginAluno, Response<LoginSistema> response) {

                            sistema = response.body();
                            if (response.isSuccessful()) {
                                Intent intentAcessaSistemaAluno = new Intent(getApplicationContext(), AlunoHomeActivity.class);
                                startActivity(intentAcessaSistemaAluno);
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginSistema> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Usuario ou senha não encontrado", Toast.LENGTH_LONG).show();
                            Log.e("AlunoService   ", "Erro ao buscar aluno:" + t.getMessage());
                        }
                    });

                } else {
                    Toast.makeText(getApplicationContext(), "Buscando em professor", Toast.LENGTH_LONG).show();

                    Call<LoginSistema> loginProfessor = new RetrofigConfig().getProfessorLoginSistema().logarProfessor(sistema);
                    loginProfessor.enqueue(new Callback<LoginSistema>() {

                        @Override
                        public void onResponse(Call<LoginSistema> loginProfessor, Response<LoginSistema> response) {
                            sistema = response.body();

                            if (response.isSuccessful()) {
                                Intent intentAcessaSistemaProfessor = new Intent(getApplicationContext(), ProfessorHomeActivity.class);
                                startActivity(intentAcessaSistemaProfessor);
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginSistema> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Usuario ou senha não encontrado", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });

        }
    }


