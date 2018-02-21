package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.DatePickerFragment;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.persistencia.UsuarioDAO;
import upkeepxpteam.upkeepxp.R;

/**
 * Created by herma on 18/12/2017.
 * para o cadastro os usuários no crud
 */

public class CadastraUsuarioFragment extends Fragment {

    private static final int REQUEST_DATE = 0;
    private static final String DIALOG_DATE = "DialogDate";
    private EditText emailEditText;
    private EditText nomeEditText;
    private EditText sobrenomeEditText;
    private RadioButton mascRadioButton;
    private EditText foneEditText;
    private EditText especialidadeEditText;
    private EditText cepEditText;
    private EditText numeroEditText;
    private Spinner ufSpinner;
    private Spinner funcaoSpinner;
    private EditText nascimentoEditText;
    private RadioButton femRadioButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cadastra_usuario, container, false);

        nascimentoEditText =  view.findViewById(R.id.nascimentoEditText);
        nascimentoEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(new Date());
                dialog.setTargetFragment(CadastraUsuarioFragment.this,REQUEST_DATE );
                dialog.show(manager, DIALOG_DATE);
            }
        });

        emailEditText = view.findViewById(R.id.emailEditText);
        nomeEditText = view.findViewById(R.id.nomeEditText);
        sobrenomeEditText = view.findViewById(R.id.sobrenomeEditText);
        mascRadioButton = view.findViewById(R.id.mascRadioButton);
        foneEditText = view.findViewById(R.id.foneEditText);
        especialidadeEditText = view.findViewById(R.id.especialidadeEditText);
        cepEditText = view.findViewById(R.id.cepEditText);
        numeroEditText = view.findViewById(R.id.numeroEditText);
        ufSpinner = view.findViewById(R.id.ufSpinner);
        funcaoSpinner = view.findViewById(R.id.funcaoSpinner);
        femRadioButton = view.findViewById(R.id.femRadioButton);

        Button btnConfirmar = view.findViewById(R.id.confirmarButton);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = emailEditText.getText().toString();
                String nome = nomeEditText.getText().toString();
                String sobrenome = sobrenomeEditText.getText().toString();
                String nascimento = nascimentoEditText.getText().toString();
                String sexo = mascRadioButton.isSelected() ? "M" : "F";
                String fone = foneEditText.getText().toString();
                String especialidade = especialidadeEditText.getText().toString();
                String cep = cepEditText.getText().toString();
                String numero = numeroEditText.getText().toString();
                String uf = ufSpinner.getSelectedItem().toString();
                String funcao = funcaoSpinner.getSelectedItem().toString();

                if (!(email.matches("^(\\w+?@\\w+?\\x2E.+)$"))) {

                    Toast.makeText(getActivity(), "Email inváldo", Toast.LENGTH_SHORT).show();

                } else if (nome.isEmpty() || sobrenome.isEmpty()) {

                    Toast.makeText(getActivity(), "Campos Nome ou Sobrenome vazios", Toast.LENGTH_SHORT).show();

                } else {

                    UsuarioDAO dao = new UsuarioDAO(getContext());
                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setNome(nome);
                    usuario.setSobrenome(sobrenome);
                    usuario.setNascimento(nascimento);
                    usuario.setSexo(sexo);
                    usuario.setTelefone(fone);
                    usuario.setEspecialidade(especialidade);
                    usuario.setCep(cep);
                    usuario.setNumero(numero);
                    usuario.setUf(uf);
                    usuario.setFuncao(funcao);
                    boolean sucesso = dao.salvar(usuario);
                    if (sucesso) {
                        emailEditText.setText("");
                        nomeEditText.setText("");
                        sobrenomeEditText.setText("");
                        nascimentoEditText.setText(" ");
                        mascRadioButton.setSelected(false);
                        femRadioButton.setSelected(false);
                        foneEditText.setText("");
                        especialidadeEditText.setText("");
                        cepEditText.setText("");
                        numeroEditText.setText("");
                        ufSpinner.setSelection(0);
                        funcaoSpinner.setSelection(0);

                        Snackbar.make(v, "Usuário Salvo!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Snackbar.make(v, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
        return view;
    }
}
