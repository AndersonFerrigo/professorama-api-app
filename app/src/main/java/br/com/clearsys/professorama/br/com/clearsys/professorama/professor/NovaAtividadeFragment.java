package br.com.clearsys.professorama.br.com.clearsys.professorama.professor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.novaAtividade.DescricaoNovaAtividadeFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class NovaAtividadeFragment extends Fragment {

    private static final String DESCRICAO_NOVA_ATIVIDADE_FRAGMENT = "DESCRICAO_NOVA_ATIVIDADE_FRAGMENT";
    DescricaoNovaAtividadeFragment descricaoNovaAtividadeFragment = new DescricaoNovaAtividadeFragment();
    String dataCustom;
    Bundle enviaData;
    CalendarView tarefaCalendar;

    public NovaAtividadeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View vNovaTarefaFrag = inflater.inflate(R.layout.fragment_nova_atividade, container, false);

        tarefaCalendar = vNovaTarefaFrag.findViewById(R.id.novaTarefaCAlendar);

        tarefaCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView v, int year, int month, int day) {

                int dia = day;
                int mes = month + 1;
                int ano = year;

                dataCustom = dia + "/ " + mes + "/ " + year;
                Toast.makeText(getActivity(), dataCustom, Toast.LENGTH_LONG).show();

                managerFragment(descricaoNovaAtividadeFragment, DESCRICAO_NOVA_ATIVIDADE_FRAGMENT);
            }
        });
        return vNovaTarefaFrag;
    }


    private void managerFragment(Fragment fragment, String tag) {

        Bundle bundle = new Bundle();
        String myMessage = dataCustom;
        bundle.putString("data", myMessage);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerForFragment, fragment, tag);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}
