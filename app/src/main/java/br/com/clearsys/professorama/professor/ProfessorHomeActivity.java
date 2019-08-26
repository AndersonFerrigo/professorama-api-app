package br.com.clearsys.professorama.professor;

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
import br.com.clearsys.professorama.login.LoginActivity;
import br.com.clearsys.professorama.model.Professor;


public class ProfessorHomeActivity extends AppCompatActivity {

    private Professor professor;

    private static long back_pressed;

    private String materia;

    HomeFragment homeFragment = new HomeFragment();
    NovaAtividadeFragment novaAtividadeFragment = new NovaAtividadeFragment();
    TarefaAgendadaFragment tarefaAgendadaFragment = new TarefaAgendadaFragment();
    CronometroAulaFragment cronometroAulaFragment = new CronometroAulaFragment();

    public FrameLayout containerForFragment;

    private static final String HOME_FRAGMENT = "HOME_FRAGMENT";
    private static final String NOVA_ATIVIDADE_FRAGMENT = "NOVA_ATIVIDADE_FRAGMENT";
    private static final String TAREFA_AGENDADA_FRAGMENT = "TAREFA_AGENDADA_FRAGMENT";
    private static final String CRONOMETRO_AULA_FRAGMENT = "CRONOMETRO_AULA_FRAGMENT";


    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);

        Intent intent = getIntent();
        professor = intent.getParcelableExtra("professor");

        String nome = professor.getNome();
        materia = professor.getMateria();

        containerForFragment = findViewById(R.id.containerForFragment);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //implementing fragment
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("nome", nome);
        bundle.putString("materia", materia);
        homeFragment.setArguments(bundle);
        changeFragment(homeFragment, HOME_FRAGMENT);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_home:
                    changeFragment(homeFragment, HOME_FRAGMENT);
                    return true;

                case R.id.ic_new_task:
                    changeFragment(novaAtividadeFragment, NOVA_ATIVIDADE_FRAGMENT);
                    return true;

                case R.id.ic_tasks_scheduled:

                    Bundle bundle = new Bundle();
                    bundle.putString("materia", materia);
                    tarefaAgendadaFragment.setArguments(bundle);
                    changeFragment(tarefaAgendadaFragment, TAREFA_AGENDADA_FRAGMENT);

                    return true;

                case R.id.ic_crono:
                    changeFragment(cronometroAulaFragment, CRONOMETRO_AULA_FRAGMENT);
                    return true;
            }
            return true;
        }
    };

    public void changeFragment(Fragment fragment, String tagFragmentName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
            if (currentFragment != null) {
                fragmentTransaction.detach(currentFragment);
            }
        Fragment fragmentTemp = fragmentManager.findFragmentByTag(tagFragmentName);
            if (fragmentTemp == null) {
                fragmentTemp = fragment;
                fragmentTransaction.add(R.id.containerForFragment, fragmentTemp, tagFragmentName);
                } else {
                    fragmentTransaction.attach(fragmentTemp);
                }
        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitAllowingStateLoss();
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

