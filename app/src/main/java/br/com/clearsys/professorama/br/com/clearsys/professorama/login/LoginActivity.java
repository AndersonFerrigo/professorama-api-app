package br.com.clearsys.professorama.br.com.clearsys.professorama.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno.AlunoHomeActivity;
import br.com.clearsys.professorama.br.com.clearsys.professorama.novoAluno.CadastroNovoAluno;
import br.com.clearsys.professorama.br.com.clearsys.professorama.professor.ProfessorHomeActivity;

public class LoginActivity extends AppCompatActivity {

    LoginSistema sistema = new LoginSistema();

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
                sistema.setUsuario( user.getText().toString());
                sistema.setSenha(password.getText().toString());

                if(sistema.getSenha().equals("admin")) {
                    Intent intentAcessaSistemaProfessor = new Intent(getApplicationContext(), ProfessorHomeActivity.class);
                    startActivity(intentAcessaSistemaProfessor);
                }else {
                    Intent intentAcessaSistemaAluno = new Intent(getApplicationContext(), AlunoHomeActivity.class);
                    startActivity(intentAcessaSistemaAluno);
                }
            }
        });

    }



}
