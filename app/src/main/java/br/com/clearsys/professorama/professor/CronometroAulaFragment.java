package br.com.clearsys.professorama.professor;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import br.com.clearsys.professorama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CronometroAulaFragment extends Fragment {

    Chronometer chronometerAula;
    Button btnComecarAula;
    Button btnPausarAula;
    Button btnZerarAula;
    boolean isClickPause = false;
    long tempoParado = 0;

    public CronometroAulaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cronometro_aula, container, false);

        chronometerAula = v.findViewById(R.id.aulaCronometro);
        btnComecarAula = v.findViewById(R.id.iniciarAula);
        btnPausarAula = v.findViewById(R.id.pausarAula);
        btnZerarAula = v.findViewById(R.id.zerarAula);

        btnComecarAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClickPause) {
                    chronometerAula.setBase(SystemClock.elapsedRealtime() + tempoParado);
                    chronometerAula.start();
                    tempoParado = 0;
                    isClickPause = false;

                } else {
                    chronometerAula.setBase(SystemClock.elapsedRealtime());
                    chronometerAula.start();
                    tempoParado = 0;
                }
            }
        });

        btnPausarAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClickPause == false) {
                    tempoParado = chronometerAula.getBase() - SystemClock.elapsedRealtime();
                }
                chronometerAula.stop();
                isClickPause = true;
            }
        });

        btnZerarAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometerAula.stop();
                chronometerAula.setText("00:00");
                tempoParado = 0;
            }
        });
        return v;
    }
}
