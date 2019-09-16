package br.com.clearsys.professorama.login;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.aluno.AlunoHomeActivity;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.model.Aluno;
import br.com.clearsys.professorama.model.Professor;
import br.com.clearsys.professorama.novoAluno.CadastroNovoAluno;
import br.com.clearsys.professorama.professor.ProfessorHomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Aluno aluno;
    private Professor professor;
    View v;

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
                if (isConnected(getApplicationContext())) {
                    if (chcbxAluno.isChecked()) {
                        aluno = new Aluno();
                        aluno.setUsuario(user.getText().toString());
                        aluno.setSenha(password.getText().toString());

                        if ((aluno.getUsuario().equals("")) || (aluno.getSenha().equals(""))) {
                            infoCamposNulos();
                            } else {
                                loginAluno(v);
                            }

                    } else if (!chcbxAluno.isChecked()) {

                        professor = new Professor();
                        professor.setUsuario(user.getText().toString());
                        professor.setSenha(password.getText().toString());

                        if ((professor.getUsuario().equals("")) || (professor.getSenha().equals(""))) {
                            infoCamposNulos();
                            } else {
                                loginProfessor(v);
                            }
                    }

                } else {
                    Snackbar snackbar = Snackbar
                            .make(v, "Sem acesso a internet, verificar sua conexão ", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }
            }

            @TargetApi(value = Build.VERSION_CODES.M)
            boolean isConnected(Context context) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager != null) {
                    connectivityManager.getActiveNetwork();
                }
                //Verifica internet pela WIFI
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                    return true;
                }

                //Verifica se tem internet móvel
                return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected();
            }


            public void loginAluno(final View v) {

                Call<Aluno> loginAluno = new RetrofitConfig().getAlunoLoginSistema().logarAluno(aluno.getUsuario(), aluno.getSenha());
                loginAluno.enqueue(new Callback<Aluno>() {

                    @Override
                    public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                        aluno = response.body();
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), AlunoHomeActivity.class);
                            intent.putExtra("aluno", aluno);
                            startActivity(intent);
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(v, "Usuario ou senha incorretos", Snackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Aluno> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(v, "Usuario ou senha incorretos", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();

                    }
                });
            }

            public void loginProfessor(final View v) {
                Call<Professor> loginProfessor = new RetrofitConfig().getProfessorLoginSistema().logarProfessor(professor.getUsuario(), professor.getSenha());
                loginProfessor.enqueue(new Callback<Professor>() {

                    @Override
                    public void onResponse(Call<Professor> loginProfessor, Response<Professor> response) {
                        professor = response.body();
                        if (response.isSuccessful()) {

                            Intent intentAcessaSistemaProfessor = new Intent(getApplicationContext(), ProfessorHomeActivity.class);
                            intentAcessaSistemaProfessor.putExtra("professor", professor);
                            startActivity(intentAcessaSistemaProfessor);
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(v, "Usuario ou senha incorretos", Snackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Professor> call, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(v, "Usuario ou senha incorretos", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();

                    }
                });
            }

        });
    }

    public void infoCamposNulos(){
        Snackbar snackbar = Snackbar
                .make(v, "Os campos usuario e senha devem ser preenchidos", Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();

    }

}













