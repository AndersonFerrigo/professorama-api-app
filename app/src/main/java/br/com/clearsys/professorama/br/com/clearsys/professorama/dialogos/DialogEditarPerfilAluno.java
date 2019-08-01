package br.com.clearsys.professorama.br.com.clearsys.professorama.dialogos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.br.com.clearsys.professorama.br.com.clearsys.professorama.aluno.AtualizaInformacoesAluno;
import br.com.clearsys.professorama.config.RetrofigConfig;
import br.com.clearsys.professorama.model.Aluno;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogEditarPerfilAluno extends DialogFragment {

    private static final String ATUALIZA_PERFIL_FRAGMENT_ALUNO = "ATUALIZA_PERFIL_FRAGMENT_ALUNO";
    private AtualizaInformacoesAluno atualizaInformacoesAluno ;
    private Aluno aluno;

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder createDialogEditar = new AlertDialog.Builder(getActivity());

        createDialogEditar.getContext().setTheme(R.style.CustomDialogFragment);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        createDialogEditar.setView(inflater.inflate(R.layout.dialog_fragment_edit_aluno, null))

                .setPositiveButton(R.string.positiveBtn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText txtRA = getDialog().findViewById(R.id.insere_ra);
                            if (txtRA != null){
                                String recebeRA = txtRA.getText().toString();
                                Call<Aluno> buscarAlunoRa = new RetrofigConfig().getAlunoRA().buscarAluno(recebeRA);
                                buscarAlunoRa.enqueue(new Callback<Aluno>() {
                                    @Override
                                    public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                                        aluno = response.body();
                                        atualizaInformacoesAluno = new AtualizaInformacoesAluno();
                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("aluno", aluno);
                                        atualizaInformacoesAluno.setArguments(bundle);
                                        changeFragment(atualizaInformacoesAluno, ATUALIZA_PERFIL_FRAGMENT_ALUNO);
                               }

                                    @Override
                                    public void onFailure(Call<Aluno> call, Throwable t) {

                                    }
                                });

                                }else {
                                    Toast.makeText(getActivity(), "Ra digitado é nullo " , Toast.LENGTH_LONG).show();
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
    public  void changeFragment(Fragment fragment , String tagFragmentName){
        FragmentManager fragmentManager = getFragmentManager();
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


}
