package br.com.clearsys.professorama.professor;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.zip.Inflater;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.dialogos.DialogEditarPerfilAluno;
import br.com.clearsys.professorama.dialogos.DialogoEscolherCadastro;
import br.com.clearsys.professorama.novaAtividade.DescricaoNovaAtividadeFragment;

public class NovaAtividadeFragment extends Fragment implements View.OnCreateContextMenuListener {

    private static final String DESCRICAO_NOVA_ATIVIDADE_FRAGMENT = "DESCRICAO_NOVA_ATIVIDADE_FRAGMENT";
    DescricaoNovaAtividadeFragment descricaoNovaAtividadeFragment = new DescricaoNovaAtividadeFragment();

    DialogoEscolherCadastro dialogoEscolherCadastro = new DialogoEscolherCadastro();
    Bundle enviaData;
    CalendarView tarefaCalendar;

    private String dataCustom;
    private String itemSelecionado;

    private int index;
    private int dia;
    private int mes;
    private int ano;

    public NovaAtividadeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vNovaTarefaFrag = inflater.inflate(R.layout.fragment_nova_atividade, container, false);
        tarefaCalendar = vNovaTarefaFrag.findViewById(R.id.novaTarefaCAlendar);
        tarefaCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                String dataEntrega;

                int dia = dayOfMonth;
                int mes = month + 1;
                int ano = year;

                dataEntrega = dia +"/"+mes+"/"+ano;

                // Pegando data Atual do sistema para preencher campo na atividade
                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy");
                String dateString = sdf.format(date);

                Toast.makeText(getContext(), "Data Atual == " + dateString, Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "Data Entrega == " + dataEntrega, Toast.LENGTH_LONG).show();


                // DialogoEscolherCadastro dialogoEscolherCadastro = new DialogoEscolherCadastro();
               //dialogoEscolherCadastro.show(getFragmentManager(), "DialogoEscolheCadastro");
            }
        });

        return vNovaTarefaFrag;
    }


}
