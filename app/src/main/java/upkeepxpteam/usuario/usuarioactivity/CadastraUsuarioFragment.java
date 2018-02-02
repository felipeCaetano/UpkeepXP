package upkeepxpteam.usuario.usuarioactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import upkeepxpteam.usuario.usuariobase.Usuario;
import upkeepxpteam.usuario.usuariopersistence.UsuarioDAO;

/**
 * Created by herma on 18/12/2017.
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
    private Button btnConfirmar;

    private UsuarioDAO usuarioDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getContext();
        usuarioDAO = new UsuarioDAO(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cadastra_usuario, container, false);



 /*       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/
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


        Button btnConfirmar = view.findViewById(R.id.confirmarButton);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvaUsuario();

                /*REGRAS DE VALIDAÇÃO para negocios?
                if (!(email.matches("^(\\w+?@\\w+?\\x2E.+)$"))) {

                    Toast.makeText(getActivity(), "Email inváldo", Toast.LENGTH_SHORT).show();

                } else if (nome.isEmpty() || sobrenome.isEmpty()) {

                    Toast.makeText(getActivity(), "Campos Nome ou Sobrenome vazios", Toast.LENGTH_SHORT).show();

                } else {
                 }*/
            }
        });

        return view;
    }

    private void salvaUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail(emailEditText.getText().toString());
        usuario.setNome(nomeEditText.getText().toString());
        usuario.setSobrenome(sobrenomeEditText.getText().toString());
        usuario.setNascimento(nascimentoEditText.getText().toString());
        usuario.setSexo(mascRadioButton.isSelected() ? "M" : "F");
        usuario.setTelefone(foneEditText.getText().toString());
        usuario.setEspecialidade(especialidadeEditText.getText().toString());
        usuario.setCep(cepEditText.getText().toString());
        usuario.setNumero(numeroEditText.getText().toString());
        usuario.setUf(ufSpinner.getSelectedItem().toString());
        usuario.setFuncao(funcaoSpinner.getSelectedItem().toString());

        Boolean sucesso = usuarioDAO.salva(usuario);
        if(sucesso){
                limparCampos();
                Toast.makeText(getContext(), R.string.salvo, Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getContext(), R.string.falha, Toast.LENGTH_LONG).show();
            }
        }

    private void limparCampos() {
        emailEditText.setHint(emailEditText.getHint());
        nomeEditText.setHint(nomeEditText.getHint());
        sobrenomeEditText.setHint(sobrenomeEditText.getHint());
        nascimentoEditText.setHint(nascimentoEditText.getHint());
        mascRadioButton.setSelected(false);

        femRadioButton.setSelected(false);
        foneEditText.setText("");
        especialidadeEditText.setText("");
        cepEditText.setText("");
        numeroEditText.setText("");
        ufSpinner.setSelection(0);
        funcaoSpinner.setSelection(0);
    }
}
