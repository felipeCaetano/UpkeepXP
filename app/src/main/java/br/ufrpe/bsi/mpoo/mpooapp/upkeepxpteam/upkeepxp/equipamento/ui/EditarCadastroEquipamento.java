package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

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
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia.EquipamentoDAO;
import upkeepxpteam.upkeepxp.R;

public class EditarCadastroEquipamento extends AppCompatActivity {

    private EditText edtnome;
    private EditText edtid;
    private EditText edtmodelo;
    private EditText edtfabricante;
    private EditText edtfalha;
    private EditText edttipo;
    private EditText edtobservacao;
    private Equipamento equip;
    private static final String TAG = "sql";
    private EditarCadastroEquipamento activity;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_equipamentos);

        Button btndelete = findViewById(R.id.btn_excluir);
        btndelete.setVisibility(View.VISIBLE);

        Bundle args =getIntent().getExtras();   //recupera os arguementos passados
        equip = new Equipamento();
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

            edtobservacao = findViewById(R.id.edt_descricao);
            String obs = equip.getDescricao();
            edtobservacao.setText(obs);
        }

        Button btnSalvar = findViewById(R.id.btn_salvar);
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

    /**
     * Remove um cadastro de equipamento recebendo o objeto equip como argumento e invocando o
     * método equipamentoDAO.delete(equip) que por sua vez instancia um objeto dbwriter e remove do
     * banco o equipamento com um id recuperado.
     * @param equipamento
     */


    private void deletarCadastro(Equipamento equipamento) {

        final Equipamento equip = equipamento;


        //alerta para delete de cadastro:
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deseja_Del).setTitle(R.string.delete_title);
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            final EquipamentoDAO equipamentoDAO = new EquipamentoDAO(EditarCadastroEquipamento.this);
            public void onClick(DialogInterface dialog, int id) {
                equipamentoDAO.delete(equip);
                Toast.makeText(EditarCadastroEquipamento.this,R.string.delete_sucess, Toast.LENGTH_SHORT).show();
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

    /**
     * Retorna para Activity BuscarEquipamentosActivity
     */

    private void returnActivity(){
        Intent it = new Intent(EditarCadastroEquipamento.this,BuscarEquipamentosActivity.class);
        startActivity(it);
    }

    /**
     * Recebe o id como argumento e invoca o método salva() da classe EquipamentoDAO. Com o id
     * recebido, monta o objeto equip com os valores passados no cadastro e invoca salva(), que por
     * sua vez
     * @param id
     */

    private void salvarCadastro(Long id){
        equip.setId(id);
        equip.setNome(edtnome.getText().toString());
        equip.setCodigo(edtid.getText().toString());
        equip.setModelo(edtmodelo.getText().toString());
        equip.setFabricante(edtfabricante.getText().toString());
        equip.setDefeito(edtfalha.getText().toString());
        equip.setTipo(edttipo.getText().toString());
        //equip.setStatus(spstatus.getText().toString()); //precisa ser preenchido o spinner
        equip.setDescricao(edtobservacao.getText().toString());
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        Boolean salvou = equipamentoDAO.salva(equip);
        if (salvou){
            Toast.makeText(EditarCadastroEquipamento.this,R.string.edit_sucess, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(EditarCadastroEquipamento.this,R.string.edit_fail, Toast.LENGTH_SHORT).show();
        }
        returnActivity();
    }
}
