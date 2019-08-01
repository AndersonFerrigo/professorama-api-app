package br.com.clearsys.professorama.br.com.clearsys.professorama.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.login.LoginActivity;

public class RetornaAlunoLogin extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder createDialogEditar = new AlertDialog.Builder(getActivity());
        createDialogEditar.getContext().setTheme(R.style.CustomDialogFragment);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        createDialogEditar.setView(inflater.inflate(R.layout.information_retorna_aluno_login, null))
                .setPositiveButton(R.string.confirmaRetornoLogin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });

        return createDialogEditar.create();
        }

    }

