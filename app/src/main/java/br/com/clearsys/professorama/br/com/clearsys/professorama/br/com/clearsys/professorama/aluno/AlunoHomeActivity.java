package br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginActivity;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoHomeActivity extends AppCompatActivity {

    private Aluno aluno;
    FragmentHomeAluno homeAluno = new FragmentHomeAluno();
    FragmentCalendarioAluno calendarioAluno = new FragmentCalendarioAluno();
    FragmentTarefasAluno tarefasAluno = new FragmentTarefasAluno();
    FragmentLembretesAluno lembretesAluno = new FragmentLembretesAluno();

    public FrameLayout containerForFragmentAluno;


    private static final String HOME_ALUNO = "HOME_FRAGMENT_ALUNO";
    private static final String CALENDARIO_ALUNO = "CALENDARIO_FRAGMENT_ALUNO";
    private static final String TAREFA_AGENDADA_ALUNO = "TAREFA_AGENDADA_FRAGMENT_ALUNO";
    private static final String LEMBRETE_ALUNO = "LEMBRETE_FRAGMENT_ALUNO";


    private static long back_pressed_aluno;

    BottomNavigationView navigationAluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_home);

        Intent intent = getIntent();
        aluno = intent.getParcelableExtra("aluno");

        Toast.makeText(getApplicationContext(), "Informação recebeida apos login usuario: " + aluno, Toast.LENGTH_LONG).show();

        containerForFragmentAluno = findViewById(R.id.containerForFragmentAluno);
        navigationAluno = findViewById(R.id.navigation_aluno);
        navigationAluno.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerAluno);

        //implementing fragment
        FragmentHomeAluno homeAluno = new FragmentHomeAluno();
         managerFragment(homeAluno, HOME_ALUNO);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListenerAluno
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_home_aluno:
                    //  Toast.makeText(getApplicationContext(), "HOME", Toast.LENGTH_SHORT).show();
                    managerFragment(homeAluno, HOME_ALUNO);
                    return true;

                case R.id.ic_new_task:
                    // Toast.makeText(getApplicationContext(), "NOVA ATIVIDADE", Toast.LENGTH_SHORT).show();
                    managerFragment(calendarioAluno, CALENDARIO_ALUNO);
                    return true;

                case R.id.ic_tasks_scheduled:
                    //Toast.makeText(getApplicationContext(), "ATIVIDADE AGENDADA", Toast.LENGTH_SHORT).show();

                    managerFragment(tarefasAluno, TAREFA_AGENDADA_ALUNO);
                    return true;

                case R.id.ic_lembretes_aluno:
                    //Toast.makeText(getApplicationContext(), "CRONOMETRO", Toast.LENGTH_SHORT).show();
                    managerFragment(lembretesAluno, LEMBRETE_ALUNO);
                    return true;
            }

            return true;
        }
    };

    private void managerFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerForFragmentAluno, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment mfragmentA = fragmentManager.findFragmentByTag(HOME_ALUNO);
        navigationAluno.setSelectedItemId(R.id.ic_home_aluno);

        if (back_pressed_aluno + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(getBaseContext(), "Pressione voltar novamente para sair", Toast.LENGTH_SHORT).show();
        back_pressed_aluno = System.currentTimeMillis();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
