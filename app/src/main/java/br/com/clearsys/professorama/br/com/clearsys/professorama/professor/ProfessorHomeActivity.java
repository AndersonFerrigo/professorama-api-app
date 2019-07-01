package br.com.clearsys.professorama.br.com.clearsys.professorama.professor;

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


public class ProfessorHomeActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    NovaAtividadeFragment novaAtividadeFragment = new NovaAtividadeFragment();
    TarefaAgendadaFragment tarefaAgendadaFragment = new TarefaAgendadaFragment();
    CronometroAulaFragment cronometroAulaFragment = new CronometroAulaFragment();

    public FrameLayout containerForFragment;

    private static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    private static final String NOVA_ATIVIDADE_FRAGMENT = "NOVA_ATIVIDADE_FRAGMENT";
    private static final String TAREFA_AGENDADA_FRAGMENT = "TAREFA_AGENDADA_FRAGMENT";
    private static final String CRONOMETRO_AULA_FRAGMENT = "CRONOMETRO_AULA_FRAGMENT";


    private static long back_pressed;
    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);

        containerForFragment = findViewById(R.id.containerForFragment);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //implementing fragment
        HomeFragment homeFragment = new HomeFragment();
        managerFragment(homeFragment, HOME_FRAGMENT);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_home:
                    //  Toast.makeText(getApplicationContext(), "HOME", Toast.LENGTH_SHORT).show();
                    managerFragment(homeFragment, HOME_FRAGMENT);
                    return true;

                case R.id.ic_new_task:
                    // Toast.makeText(getApplicationContext(), "NOVA ATIVIDADE", Toast.LENGTH_SHORT).show();
                    managerFragment(novaAtividadeFragment, NOVA_ATIVIDADE_FRAGMENT);
                    return true;

                case R.id.ic_tasks_scheduled:
                    //Toast.makeText(getApplicationContext(), "ATIVIDADE AGENDADA", Toast.LENGTH_SHORT).show();
                    managerFragment(tarefaAgendadaFragment, TAREFA_AGENDADA_FRAGMENT);
                    return true;

                case R.id.ic_crono:
                    //Toast.makeText(getApplicationContext(), "CRONOMETRO", Toast.LENGTH_SHORT).show();
                    managerFragment(cronometroAulaFragment, CRONOMETRO_AULA_FRAGMENT);
                    return true;
            }

            return true;
        }
    };

    public void managerFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerForFragment, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment mfragmentA = fragmentManager.findFragmentByTag(HOME_FRAGMENT);
        navigation.setSelectedItemId(R.id.ic_home);

        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(getBaseContext(), "Pressione voltar novamente para sair", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }

}

