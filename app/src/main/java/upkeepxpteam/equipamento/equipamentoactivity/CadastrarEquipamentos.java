package upkeepxpteam.equipamento.equipamentoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import upkeepxpteam.equipamento.equipamentobase.Equipamento;
import upkeepxpteam.equipamento.equipamentodao.EquipamentoDAO;
import upkeepxpteam.upkeepxp.R;

public class CadastrarEquipamentos extends AppCompatActivity {

    private EditText nome;
    private EditText modelo;
    private EditText falha;
    private EditText numero;
    private EditText descricao;
    private EditText fabricante;
    private EditText tipo;
    private Button btnSalvar;
    private Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_equipamentos);
        Toolbar toolbar = findViewById(R.id.tb_equipamento);
        setSupportActionBar(toolbar);
        cadastraBD();
    }

    private void cadastraBD(){

        btnDelete = findViewById(R.id.btn_excluir);
        btnDelete.setVisibility(View.INVISIBLE);

        btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarCadastro();
            }
        });
    }

    private void salvarCadastro(){

        Equipamento equipamento = new Equipamento();

        nome = findViewById(R.id.edt_equipamento);
        equipamento.setNome(nome.getText().toString());

        numero = findViewById(R.id.edt_codigo);
        equipamento.setCodigo(numero.getText().toString());

        modelo = findViewById(R.id.edt_modelo);
        equipamento.setModelo(modelo.getText().toString());

        tipo = findViewById(R.id.edt_tipo);
        equipamento.setTipo(tipo.getText().toString());

        falha = findViewById(R.id.edt_defeito);
        equipamento.setDefeito(falha.getText().toString());

        fabricante = findViewById(R.id.edt_fabricante);
        equipamento.setFabricante(fabricante.getText().toString());

        descricao = findViewById(R.id.edt_descricao);
        equipamento.setDescricao(descricao.getText().toString());

        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        equipamentoDAO.salva(equipamento);

        Toast.makeText(this,R.string.salvo, Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this,BuscarEquipamentoActivity.class);
        startActivity(it);
        finish();
    }
//@: verificar o empilhamento de telas.
}
