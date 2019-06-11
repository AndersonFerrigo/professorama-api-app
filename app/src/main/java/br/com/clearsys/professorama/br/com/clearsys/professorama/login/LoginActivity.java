package br.com.clearsys.professorama.br.com.clearsys.professorama.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


    Aluno sistema = new Aluno();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText user = findViewById(R.id.textInfoUser);
        final EditText password = findViewById(R.id.textInfoPassword);

        Button btnCadastrarNovoAluno = findViewById(R.id.btnCadastraNovoAluno);
        btnCadastrarNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastraNovoAluno = new Intent(getApplicationContext(),CadastroNovoAluno.class);
                startActivity(cadastraNovoAluno);
            }
        });

        Button btnAcessar = findViewById(R.id.btnAcessaSistema);
        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sistema.setUsuario(user.getText().toString());
                sistema.setSenha(password.getText().toString());

                if((sistema.getUsuario().equals("")) && (sistema.getSenha().equals(""))){
                        Toast.makeText(getApplicationContext(), "Os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "usuario" + sistema.getUsuario()+"senha"+sistema.getSenha(), Toast.LENGTH_LONG).show();

                    Call<Aluno> loginAluno = new RetrofigConfig().getAlunoLoginSistema().logarAluno(sistema);

                    loginAluno.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> loginAluno, Response<Aluno> response) {
                            sistema = response.body();
                                if (response.isSuccessful()){
                                    Intent intentAcessaSistemaAluno = new Intent(getApplicationContext(), AlunoHomeActivity.class);
                                    startActivity(intentAcessaSistemaAluno);
                                }else{
                                    Call<LoginSistema> loginProfessor = new RetrofigConfig().getProfessorLoginSistema().logarProfessor(sistema);
                                    loginProfessor.enqueue(new Callback<Professor>() {
                                        @Override
                                        public void onResponse(Call<Professor> loginProfessor, Response<Professor> response) {
                                            sistema = response.body();
                                            if(response.isSuccessful()){
                                                Intent intentAcessaSistemaProfessor = new Intent(getApplicationContext(), ProfessorHomeActivity.class);
                                                startActivity(intentAcessaSistemaProfessor);
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<Professor> call, Throwable t) {
                                            Toast.makeText(getApplicationContext(), "Usuario ou senha não encontrado", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                        }
                        @Override
                        public void onFailure(Call<LoginSistema> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Usuario ou senha não encontrado", Toast.LENGTH_LONG).show();
                            Log.e("AlunoService   ", "Erro ao buscar aluno:" + t.getMessage());
                        }
                    });
                }
            }
        });
    }
}
