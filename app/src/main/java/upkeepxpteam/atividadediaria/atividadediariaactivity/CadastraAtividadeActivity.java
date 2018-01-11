package upkeepxpteam.atividadediaria.atividadediariaactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import upkeepxpteam.atividadediaria.atividadediariaDAO.AtividadeDiariaDAO;
import upkeepxpteam.atividadediaria.atividadediariabase.AtividadeDiaria;
import upkeepxpteam.upkeepxp.R;

public class CadastraAtividadeActivity extends AppCompatActivity {

    private Button btnSave;
    private Button btnClear;
    private EditText edtEquipeNome;
    private EditText edtLocal;
    private EditText edtHora;
    private EditText descricao;
    private Spinner spnEquipe;



    AtividadeDiariaDAO atividadeDiariaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_atividade);

        Context context = this.getApplicationContext();

        //UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(context);
        atividadeDiariaDAO = new AtividadeDiariaDAO(context);

        btnSave = findViewById(R.id.btn_salvar);
        btnClear = findViewById(R.id.btn_limpar);
        edtEquipeNome = findViewById(R.id.edt_atv_name);
        edtLocal = findViewById(R.id.edt_local);
        edtHora = findViewById(R.id.edt_horario);
        descricao = findViewById(R.id.edt_descricao);
        //spnEquipe;

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

    private void salvaAtividade(){
        AtividadeDiaria atividade = new AtividadeDiaria();
        atividade.setNome(edtEquipeNome.getText().toString());
        atividade.setDescricao(descricao.getText().toString());
        atividade.setLocal(edtLocal.getText().toString());
        //objeto precisa ser melhor preenchido
        Boolean result = atividadeDiariaDAO.saveAtividade(atividade);
        if(result){
            Toast.makeText(CadastraAtividadeActivity.this,"Salvo com Sucesso!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(CadastraAtividadeActivity.this,"Falha",Toast.LENGTH_SHORT).show();
        }
    }

    private void limpaCampos(){
        edtEquipeNome.setText(edtEquipeNome.getHint());
        descricao.setText(descricao.getHint());
        edtLocal.setText(edtLocal.getHint());
    }
}
