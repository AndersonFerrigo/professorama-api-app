package br.com.clearsys.professorama.professor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.novaAtividade.DescricaoNovaAtividadeFragment;
import br.com.clearsys.professorama.novaAtividade.MostraAtividadeAluno;

public class NovaAtividadeFragment extends Fragment implements View.OnCreateContextMenuListener {

    CalendarView tarefaCalendar;

    public NovaAtividadeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View vNovaTarefaFrag = inflater.inflate(R.layout.fragment_nova_atividade, container, false);
        tarefaCalendar = vNovaTarefaFrag.findViewById(R.id.novaTarefaCAlendar);
        tarefaCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int dia = dayOfMonth;
                int mes = month+1;
                int ano = year;

                String dataSelecionada = dia +"/"+ mes+ "/"+ ano;

                AppCompatActivity activity = (AppCompatActivity) vNovaTarefaFrag.getContext();
                Fragment fragment = new DescricaoNovaAtividadeFragment();
                Bundle bundle = new Bundle();
                bundle.putString("data", dataSelecionada);
                fragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerForFragment, fragment).addToBackStack(null).commit();

            }
        });

        return vNovaTarefaFrag;
    }


}
