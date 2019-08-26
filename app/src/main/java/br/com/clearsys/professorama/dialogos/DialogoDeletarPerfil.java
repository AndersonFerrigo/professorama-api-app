package br.com.clearsys.professorama.dialogos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.config.RetrofitConfig;
import br.com.clearsys.professorama.login.LoginActivity;
import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogoDeletarPerfil extends DialogFragment {
    private RetornaAlunoLogin retornaAlunoLogin = new RetornaAlunoLogin();
    private Aluno aluno;

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder createDialogEditar = new AlertDialog.Builder(getActivity());

        createDialogEditar.getContext().setTheme(R.style.CustomDialogFragment);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        createDialogEditar.setView(inflater.inflate(R.layout.fragment_delete_aluno, null))

                .setPositiveButton(R.string.positiveBtnDeletar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText txtRA = getDialog().findViewById(R.id.insere_ra);
                        if (txtRA != null) {
                            String recebeRA = txtRA.getText().toString();
                            Call<Aluno> deletarAlunoRa = new RetrofitConfig().getDeleteAluno().deletarAluno(recebeRA);
                            deletarAlunoRa.enqueue(new Callback<Aluno>() {
                                @Override
                                public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                                    response.body();
                                    if (response.body().equals(200)) {
                                        retornaAlunoLogin.show(getFragmentManager(), "dialogRetornaAlunoLogin");

                                        Toast.makeText(getActivity(), "Perfil deletado com sucesso ", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getActivity(), "Ra digitado não foi encontrado ", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Aluno> call, Throwable t) {

                                }
                            });

                        } else {
                            Toast.makeText(getActivity(), "Ra digitado é nullo ", Toast.LENGTH_LONG).show();
                        }
                    }
                })

                .setNegativeButton(R.string.cancelBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }
                });

        return createDialogEditar.create();
    }

}
