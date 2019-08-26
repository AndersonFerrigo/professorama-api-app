package br.com.clearsys.professorama.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.professor.NovaAtividadeFragment;

public class InformaAtividadeCadastrada extends DialogFragment {
    NovaAtividadeFragment novaAtividadeFragment = new NovaAtividadeFragment();
    private static final String NOVA_ATIVIDADE_FRAGMENT = "NOVA_ATIVIDADE_FRAGMENT";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder createDialogEditar = new AlertDialog.Builder(getActivity());
        createDialogEditar.getContext().setTheme(R.style.CustomDialogFragment);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        createDialogEditar.setView(inflater.inflate(R.layout.informa_atividade_cadastrada, null))
                .setPositiveButton(R.string.confirmaRetornoLogin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        changeFragment();
                    }
                });

        return createDialogEditar.create();
    }

    public void changeFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerForFragment, new NovaAtividadeFragment());
        fragmentTransaction.commit();
    }

}
