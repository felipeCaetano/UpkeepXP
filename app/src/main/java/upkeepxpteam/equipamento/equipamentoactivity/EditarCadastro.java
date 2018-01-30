package upkeepxpteam.equipamento.equipamentoactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import upkeepxpteam.equipamento.equipamentobase.Equipamento;
import upkeepxpteam.equipamento.equipamentodao.EquipamentoDAO;
import upkeepxpteam.upkeepxp.R;

public class EditarCadastro extends AppCompatActivity {

    private EditText edtnome;
    private EditText edtid;
    private EditText edtmodelo;
    private EditText edtfabricante;
    private EditText edtfalha;
    private EditText edttipo;
    private EditText edtobservacao;
    private EditText spstatus;
    private Button btnSalvar;
    private Button btndelete;

    private Equipamento equip = new Equipamento();
    private EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
    private static final String TAG = "sql";
    private EditarCadastro activity;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_equipamentos);

        btndelete = findViewById(R.id.btn_excluir);
        btndelete.setVisibility(View.VISIBLE);

        Bundle args =getIntent().getExtras();   //recupera os arguementos passados

        equip = args.getParcelable("Equipamento");

        //recuperar todas as edit texts:
        if (equip != null) {
            id = equip.getId();
            String ids = id.toString();
            Log.d(TAG, ids);

            edtnome = findViewById(R.id.edt_equipamento);
            String nome = equip.getNome();
            edtnome.setText(nome);

            edtid = findViewById(R.id.edt_codigo);
            String num = equip.getCodigo();
            edtid.setText(num);

            edtmodelo = findViewById(R.id.edt_modelo);
            String modelo = equip.getModelo();
            edtmodelo.setText(modelo);

            edtfabricante = findViewById(R.id.edt_fabricante);
            String unidade = equip.getFabricante();
            edtfabricante.setText(unidade);

            edtfalha = findViewById(R.id.edt_defeito);
            String falha = equip.getDefeito();
            edtfalha.setText(falha);

            edttipo = findViewById(R.id.edt_tipo);
            String tipo = equip.getTipo();
            edttipo.setText(tipo);

            //spstatus = findViewById(R.id.edtSolucao);
            //String solucao = equip.status;
            //spstatus.setText(solucao);

            edtobservacao = findViewById(R.id.edt_descricao);
            String obs = equip.getDescricao();
            edtobservacao.setText(obs);
        }

        btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                salvarCadastro(id);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deletarCadastro(equip);
            }
        });
    }


    private void deletarCadastro(Equipamento equipamento) {

        final Equipamento equip = equipamento;


        //alerta para delete de cadastro:
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deseja_Del).setTitle(R.string.delete_title);
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            EquipamentoDAO db = new EquipamentoDAO(EditarCadastro.this);
            public void onClick(DialogInterface dialog, int id) {
                db.delete(equip);
                Toast.makeText(EditarCadastro.this,R.string.delete_sucess, Toast.LENGTH_SHORT).show();
                returnActivity();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                returnActivity();
            }
        });

        builder.show();
    }

    private void returnActivity(){
        Intent it = new Intent(EditarCadastro.this,BuscarEquipActivity.class);
        startActivity(it);
    }

    private void salvarCadastro(Long id){
        equip.setId(id);
        equip.setNome(edtnome.getText().toString());
        equip.setCodigo(edtid.getText().toString());
        equip.setModelo(edtmodelo.getText().toString());
        equip.setFabricante(edtfabricante.getText().toString());
        equip.setDefeito(edtfalha.getText().toString());
        equip.setTipo(edttipo.getText().toString());
        equip.setStatus(spstatus.getText().toString());
        equip.setDescricao(edtobservacao.getText().toString());
        equipamentoDAO.salva(equip);
        Toast.makeText(EditarCadastro.this,R.string.edit_sucess, Toast.LENGTH_SHORT).show();

        returnActivity();
    }
}
