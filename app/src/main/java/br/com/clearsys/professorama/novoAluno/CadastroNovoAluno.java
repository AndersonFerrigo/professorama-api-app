package br.com.clearsys.professorama.novoAluno;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.login.LoginActivity;
import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroNovoAluno extends AppCompatActivity {
    private Aluno aluno;
    private String itemSelecionado;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_aluno);

        Spinner spSalas = findViewById(R.id.spinner_salas);
        ArrayAdapter<CharSequence> salasAdapter = ArrayAdapter.createFromResource(this,
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

        Button btnCadastraAluno = findViewById(R.id.btnCadastraNovoAluno);

        btnCadastraAluno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                aluno = new Aluno();
                EditText txtNome = findViewById(R.id.nome);
                aluno.setNome(txtNome.getText().toString());

                if (index == 0) {
                    Snackbar snackbar = Snackbar
                            .make(v.getRootView(), "Escolha uma sala", Snackbar.LENGTH_LONG);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();

                    } else {
                            aluno.setSerie(itemSelecionado);
                        }

                EditText txtRa = findViewById(R.id.ra);
                aluno.setRa(txtRa.getText().toString());

                EditText txtUsuario = findViewById(R.id.usuario);
                aluno.setUsuario(txtUsuario.getText().toString());

                EditText txtSenha = findViewById(R.id.senha);
                aluno.setSenha(txtSenha.getText().toString());

                Call<Aluno> alunoCall = new RetrofitConfig().getAlunoService().cadastrarAluno(aluno);
                alunoCall.enqueue(new Callback<Aluno>() {
                    @Override
                    public void onResponse(Call<Aluno> alunoCall1, Response<Aluno> response) {
                        aluno = response.body();

                        if (response.isSuccessful()) {
                            Snackbar snackbar = Snackbar
                                    .make(v.getRootView(), "Cadastrado realizado com sucesso", Snackbar.LENGTH_LONG);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();

                            Intent retornaTelaPrincipal = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(retornaTelaPrincipal);
                        }
                    }

                    @Override
                    public void onFailure(Call<Aluno> alunoCall1, Throwable t) {
                        Snackbar snackbar = Snackbar
                                .make(v.getRootView(), "Erro ao cadastrar ", Snackbar.LENGTH_LONG);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();
                    }
                });
            }
        });
    }
}
