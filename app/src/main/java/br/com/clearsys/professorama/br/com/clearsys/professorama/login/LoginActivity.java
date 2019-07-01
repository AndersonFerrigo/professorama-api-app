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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno.AlunoHomeActivity;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno.FragmentHomeAluno;
import br.com.clearsys.professorama.br.com.clearsys.professorama.novoAluno.CadastroNovoAluno;
import br.com.clearsys.professorama.br.com.clearsys.professorama.professor.ProfessorHomeActivity;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Aluno;
import br.com.clearsys.professorama.model.Professor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ObjectMapper mapper = new ObjectMapper();
    private Aluno aluno ;
    Professor professor = new Professor();
    String alunoObjsct;

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
                Intent cadastraNovoAluno = new Intent(getApplicationContext(), CadastroNovoAluno.class);
                startActivity(cadastraNovoAluno);
            }
        });


        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aluno = new Aluno();
                aluno.setUsuario(user.getText().toString());
                aluno.setSenha(password.getText().toString());

                Toast.makeText(getApplicationContext(), "Usuario : " + aluno.getUsuario() + " " + "senha: " + aluno.getSenha()
                        , Toast.LENGTH_LONG).show();

                if ((aluno.getUsuario().equals("")) || (aluno.getSenha().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Os campos devem ser preenchidos", Toast.LENGTH_LONG).show();

                } else if (chcbxAluno.isChecked()) {
                    Call<Aluno> loginAluno  = new RetrofigConfig().getAlunoLoginSistema().logarAluno(aluno.getUsuario(),aluno.getSenha());
                    loginAluno.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                            aluno = response.body();

                            Toast.makeText(getApplicationContext(), "Aluno : " + aluno, Toast.LENGTH_LONG).show();

                            if (response.isSuccessful()) {

                                Intent intent = new Intent(getApplicationContext(), AlunoHomeActivity.class);
                                intent.putExtra("aluno", aluno);
                                startActivity(intent);

                            }
                        }@Override
                        public void onFailure(Call<Aluno> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Usuario não encontrado", Toast.LENGTH_LONG).show();
                        }
                   });

               } else {
                    Toast.makeText(getApplicationContext(), "Buscando em professor", Toast.LENGTH_LONG).show();

                    professor.setUsuario(user.getText().toString());
                    professor.setSenha(password.getText().toString());

                    Call<Professor> loginProfessor = new RetrofigConfig().getProfessorLoginSistema().logarProfessor(professor);
                    loginProfessor.enqueue(new Callback<Professor>() {

                        @Override
                        public void onResponse(Call<Professor> loginProfessor, Response<Professor> response) {
                            professor = response.body();

                            if (response.isSuccessful()) {
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
        });

    }

}













