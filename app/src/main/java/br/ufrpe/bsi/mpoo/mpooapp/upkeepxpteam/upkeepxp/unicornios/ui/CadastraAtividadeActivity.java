package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.atividadediaria.dominio.AtividadeDiaria;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.atividadediaria.persistencia.AtividadeDiariaDAO;
import upkeepxpteam.upkeepxp.R;

public class CadastraAtividadeActivity extends AppCompatActivity {

    private EditText edtEquipeNome;
    private EditText edtLocal;
    private EditText edtHora;
    private EditText descricao;
    private Spinner spnEquipe;
    private EditText edtData;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


    private AtividadeDiariaDAO atividadeDiariaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_atividade);

        Context context = this.getApplicationContext();

        //UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(context);
        atividadeDiariaDAO = new AtividadeDiariaDAO(context);

        Button btnSave = findViewById(R.id.btn_salvar);
        Button btnClear = findViewById(R.id.btn_limpar);
        edtEquipeNome = findViewById(R.id.edt_atv_name);
        edtLocal = findViewById(R.id.edt_local);
        EditText edtHora = findViewById(R.id.edt_horario);
        descricao = findViewById(R.id.edt_descricao);
        spnEquipe = findViewById(R.id.spn_equipes);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context,R.array.Equipes, android.R.layout.simple_spinner_item);
        spnEquipe.setAdapter(adapter);
        edtData = findViewById(R.id.edt_data);

        Date today = new Date();
        edtData.setText(dateFormat.format(today));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               salvaAtividade();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });
    }

    /**
     * Grava a atividade no DAO de atividade e notifica via TOAST
     */
    private void salvaAtividade(){
        AtividadeDiaria atividade = new AtividadeDiaria();
        atividade.setNome(edtEquipeNome.getText().toString());
        atividade.setDescricao(descricao.getText().toString());
        atividade.setLocal(edtLocal.getText().toString());
        atividade.setEquipeNome(spnEquipe.getSelectedItem().toString());
        atividade.setData(edtData.getText().toString());
        //objeto precisa ser melhor preenchido
        Boolean result = atividadeDiariaDAO.salva(atividade);
        if(result){
            Toast.makeText(CadastraAtividadeActivity.this, R.string.salvo,Toast.LENGTH_SHORT).show();
            Log.d("Atividade salva: ", atividade.getNome()+" "+atividade.getData());
        }else{
            Toast.makeText(CadastraAtividadeActivity.this, R.string.falha,Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Limpa os campos da tela de cadastro após ter salvo os dados atuais
     */
    private void limpaCampos(){
        //melhorar
        edtEquipeNome.setText(edtEquipeNome.getHint());
        descricao.setText(descricao.getHint());
        edtLocal.setText(edtLocal.getHint());
    }
}
