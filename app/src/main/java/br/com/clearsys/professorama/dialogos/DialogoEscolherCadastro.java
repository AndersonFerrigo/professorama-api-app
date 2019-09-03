package br.com.clearsys.professorama.dialogos;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.clearsys.professorama.R;
import br.com.clearsys.professorama.lembretes.CadastraLembreteFragment;
import br.com.clearsys.professorama.novaAtividade.DescricaoNovaAtividadeFragment;

public class DialogoEscolherCadastro extends DialogFragment {

    private static final String DESCRICAO_NOVA_ATIVIDADE_FRAGMENT = "DESCRICAO_NOVA_ATIVIDADE_FRAGMENT";
    DescricaoNovaAtividadeFragment descricaoNovaAtividadeFragment = new DescricaoNovaAtividadeFragment();

    private static final String DESCRICAO_LEMBRETE_FRAGMENT = "DESCRICAO_LEMBRETE_FRAGMENT";
    CadastraLembreteFragment cadastraLembreteFragment = new CadastraLembreteFragment();

    RadioGroup rdoGrpOpcoesCadastro;

    RadioButton rdoBtnAtividade;
    RadioButton rdoBtnLembrete;

    Button btnEscolher;
    Button btnCancelar;


    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        dialog.getContext().setTheme(R.style.CustomDialogFragment);
         LayoutInflater inflater = getActivity().getLayoutInflater();
         View view = inflater.inflate(R.layout.options_menu_professor, null);
         dialog.setView(view);


         rdoGrpOpcoesCadastro = (RadioGroup) view.findViewById(R.id.myRadioGroup);
        if(rdoGrpOpcoesCadastro != null) {
            rdoGrpOpcoesCadastro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // find which radio button is selected
                    if (checkedId == R.id.rdo_btn_atividade) {
                        Toast.makeText(getContext(), "choice: Atividade",
                                Toast.LENGTH_SHORT).show();
                    } else if (checkedId == R.id.rdo_btn_lembrete) {
                        Toast.makeText(getContext(), "choice: Lembrete",
                                Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }else {
            Toast.makeText(getContext(), "Radio nulo",
                    Toast.LENGTH_SHORT).show();

        }
        rdoBtnAtividade = (RadioButton) view.findViewById(R.id.rdo_btn_atividade);
        rdoBtnLembrete = (RadioButton) view.findViewById(R.id.rdo_btn_lembrete);

        dialog.setPositiveButton("Selecionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(rdoBtnAtividade.isChecked()){
                    Toast.makeText(getContext(), "Redirecionando para tela de atividade",
                            Toast.LENGTH_SHORT).show();

                   managerFragment(descricaoNovaAtividadeFragment, DESCRICAO_NOVA_ATIVIDADE_FRAGMENT);

                }else if(rdoBtnLembrete.isChecked()) {
                    Toast.makeText(getContext(), "Redirecionando para tela de lembrete",
                            Toast.LENGTH_SHORT).show();
                    managerFragment(cadastraLembreteFragment, DESCRICAO_LEMBRETE_FRAGMENT);
                }
            }
        })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                    }
                });
/*
        btnEscolher = (Button)view.findViewById(R.id.chooseBtn);
        btnEscolher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = rdoGrpOpcoesCadastro.getCheckedRadioButtonId();

                // find which radioButton is checked by id
                if(selectedId == rdoBtnAtividade.getId()) {
                    String itemSelecionado = "Atividade";
                    Toast.makeText(getContext(),itemSelecionado,
                            Toast.LENGTH_SHORT).show();

                } else if(selectedId == rdoBtnLembrete.getId()) {
                    String itemSelecionado1 = "Lembrete";
                    Toast.makeText(getContext(), itemSelecionado1,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelar = view.findViewById(R.id.calcelBtn);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


 */
        return dialog.create();
    }
    private void managerFragment(Fragment fragment, String tag) {


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerForFragment, fragment, tag);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
