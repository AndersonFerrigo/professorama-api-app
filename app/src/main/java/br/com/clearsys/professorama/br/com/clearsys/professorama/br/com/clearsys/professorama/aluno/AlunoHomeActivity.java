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
import br.com.clearsys.professorama.model.Aluno;

public class AlunoHomeActivity extends AppCompatActivity {
    private Aluno aluno;
    private String serie;
    public FrameLayout containerForFragmentAluno;

    private static long back_pressed_aluno;

    BottomNavigationView navigationAluno;

    FragmentHomeAluno homeAluno = new FragmentHomeAluno();
    FragmentCalendarioAluno calendarioAluno = new FragmentCalendarioAluno();
    FragmentTarefasAluno tarefasAluno = new FragmentTarefasAluno();
    FragmentLembretesAluno lembretesAluno = new FragmentLembretesAluno();

    private static final String HOME_ALUNO = "HOME_ALUNO";
    private static final String CALENDARIO_ALUNO = "CALENDARIO_ALUNO";
    private static final String TAREFA_AGENDADA_ALUNO = "TAREFA_AGENDADA";
    private static final String LEMBRETE_ALUNO = "LEMBRETE_ALUNO";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_home);

        Intent intent = getIntent();
        aluno = intent.getParcelableExtra("aluno");

        String nome = aluno.getNome();
        serie = aluno.getSerie();


        Toast.makeText(getApplicationContext(), "Informação recebeida apos login usuario: " + aluno, Toast.LENGTH_LONG).show();

        containerForFragmentAluno = findViewById(R.id.containerForFragmentAluno);
        navigationAluno = findViewById(R.id.navigation_aluno);
        navigationAluno.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerAluno);


        //implementing fragment
        FragmentHomeAluno homeAluno = new FragmentHomeAluno();
        Bundle bundle = new Bundle();
        bundle.putString("nome", nome);
        bundle.putString("serie", serie);
        homeAluno.setArguments(bundle);
        changeFragment(homeAluno, HOME_ALUNO);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListenerAluno
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ic_home_aluno:
                    changeFragment(homeAluno, HOME_ALUNO);
                    return true;

                case R.id.ic_calendario_aluno:
                    changeFragment(calendarioAluno, CALENDARIO_ALUNO);
                    return true;

                case R.id.ic_tasks_pendentes_aluno:
                    Bundle bundle = new Bundle();

                    bundle.putString("serie", serie);
                    tarefasAluno.setArguments(bundle);
                    changeFragment(tarefasAluno, TAREFA_AGENDADA_ALUNO);
                    return true;

                case R.id.ic_lembretes_aluno:
                    changeFragment(lembretesAluno, LEMBRETE_ALUNO);
                    return true;
            }
            return false;
        }
    };

    public  void changeFragment(Fragment fragment , String tagFragmentName){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment currentFragment = fragmentManager.getPrimaryNavigationFragment();
        if(currentFragment != null){
            fragmentTransaction.detach(currentFragment);
        }
        Fragment fragmentTemp = fragmentManager.findFragmentByTag(tagFragmentName);
        if(fragmentTemp == null){
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.containerForFragmentAluno, fragmentTemp, tagFragmentName);
        }else{
            fragmentTransaction.attach(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.findFragmentByTag(HOME_ALUNO);
        navigationAluno.setSelectedItemId(R.id.ic_home_aluno);

        if (back_pressed_aluno + 2000 > System.currentTimeMillis())
            {
                super.onBackPressed();
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            } else
                    Toast.makeText(getBaseContext(), "Pressione voltar novamente para sair", Toast.LENGTH_SHORT).show();
                    back_pressed_aluno = System.currentTimeMillis();
    }

}
