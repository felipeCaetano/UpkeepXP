package upkeepxpteam.usuario.usuarioactivity;

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
import upkeepxpteam.infraestrutura.DatePickerFragment;
import upkeepxpteam.upkeepxp.R;
import upkeepxpteam.usuario.usuariopersistence.UsuarioDAO;


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
    private Button btnConfirmar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cadastra_usuario, container, false);

        nascimentoEditText = view.findViewById(R.id.nascimentoEditText);
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
        btnConfirmar = view.findViewById(R.id.confirmarButton);

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

                if (validarEntrada(email, nome, sobrenome) == true){
                    UsuarioDAO dao = new UsuarioDAO(getContext());
                    boolean sucesso = dao.salvar(email, nome, sobrenome, nascimento, sexo, fone,
                            especialidade, cep, numero, uf, funcao);
                    if (sucesso) {

                        limparCampos();

                    } else {

                        Toast.makeText(getActivity(), "Erro ao salvar, consulte os logs!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        return view;
    }

    public boolean validarEntrada(String email, String nome, String sobrenome){
        if (!(email.matches("^(\\w+?@\\w+?\\x2E.+)$"))) {

            Toast.makeText(getActivity(), "Email inváldo", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if (nome.isEmpty() || sobrenome.isEmpty()) {

            Toast.makeText(getActivity(), "Campos Nome ou Sobrenome vazios", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void limparCampos(){

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

        Toast.makeText(getActivity(), "Usuário Salvo", Toast.LENGTH_SHORT).show();

    }
}
