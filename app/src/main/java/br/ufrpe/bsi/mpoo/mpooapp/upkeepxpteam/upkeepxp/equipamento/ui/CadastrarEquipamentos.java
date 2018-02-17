package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia.EquipamentoDAO;
import upkeepxpteam.upkeepxp.R;

public class CadastrarEquipamentos extends AppCompatActivity {

    private EditText nome;
    private EditText modelo;
    private EditText falha;
    private EditText numero;
    private EditText descricao;
    private EditText fabricante;
    private EditText tipo;
    private EditText disponibilidade;
    private Spinner spinner;
    private Button btnSalvar;
    private Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_equipamentos);
        Toolbar toolbar = findViewById(R.id.tb_equipamento);
        toolbar.setTitle(getString(R.string.title_activity_cadastrar_equipamento));
        setSupportActionBar(toolbar);
        cadastraBD();
    }

    /**
     * Invoca o método salvarCadastro ao clicar no botão btnSalvar
     */

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

    /**
     * Salva os valores preenchidos no formulário nos atributos do objeto Equipamento por meio
     * do método salva() da Classe EquipamentoDAO.
     */

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

        disponibilidade = findViewById(R.id.edt_disponibilidade);
        equipamento.setDisponibilidade(disponibilidade.getText().toString());

        spinner = findViewById(R.id.spn_status);
        ArrayAdapter<String> spinadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinadapter.add("opção");
        spinner.setAdapter(spinadapter);

        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        equipamentoDAO.salva(equipamento);

        Toast.makeText(this,R.string.salvo, Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this,BuscarEquipamentosActivity.class);
        startActivity(it);
        finish();
    }

}
