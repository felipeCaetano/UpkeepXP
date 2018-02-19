package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.atividadediaria.dominio.AtividadeDiaria;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.atividadediaria.persistencia.AtividadeDiariaDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.CorEnum;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.ElementEnum;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.Unicornio;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.persistencia.UnicornioDAO;
import upkeepxpteam.upkeepxp.R;

public class CadastraUnicornioActivity extends AppCompatActivity {

    private UnicornioDAO unicornioDAO;
    private Unicornio unicornio;

    private EditText edtUnicornioNome;
    private EditText edtUnicornioPeso;
    private EditText edtUnicornioAltura;
    private RadioButton rdgUnicornioSexo;
    private RadioButton rdgUnicornioSexoM;
    private Spinner spnUnicornioElemento;
    private Spinner spnUnicornioCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_unicornios);

        edtUnicornioNome = findViewById(R.id.unicornioNomeEditText);
        edtUnicornioPeso = findViewById(R.id.pesoEditText);
        edtUnicornioAltura = findViewById(R.id.alturaEditText);
        rdgUnicornioSexo = findViewById(R.id.femRadioButton);
        rdgUnicornioSexoM = findViewById(R.id.mascRadioButton);
        spnUnicornioElemento = findViewById(R.id.spn_elemento);
        spnUnicornioCor = findViewById(R.id.spn_color);
        Button btnSalvar = findViewById(R.id.btn_salvar);
        Button btndelete = findViewById(R.id.btn_excluir);
        btndelete.setText(R.string.bt_limpar);
        //Setar Spinners
        //Spiner Elementos:
        ArrayAdapter<ElementEnum> elementEnumArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ElementEnum.values());
        spnUnicornioElemento.setAdapter(elementEnumArrayAdapter);
        elementEnumArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Spinner COR:
        ArrayAdapter<CorEnum> corEnumArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CorEnum.values());
        spnUnicornioCor.setAdapter(corEnumArrayAdapter);
        corEnumArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvaCadastro();
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
            }
        });
        }

    private void salvaCadastro() {

        final Context context = this.getApplicationContext();

        unicornio = new Unicornio();
        unicornio.setNome( edtUnicornioNome.getText().toString());
        //inserir regra de validação: se for vazio assumir zero para peso ou altura
        if(edtUnicornioPeso.getText().toString().equals("")){
            edtUnicornioPeso.setText("1");
        }
        unicornio.setPeso( Double.parseDouble(edtUnicornioPeso.getText().toString()));
        if(edtUnicornioAltura.getText().toString().equals("")){
            edtUnicornioAltura.setText("1");
        }
        unicornio.setAltura(Double.parseDouble(edtUnicornioAltura.getText().toString()));

        //checagem do RadioGroup:
        CharSequence sexo = null;
        if(rdgUnicornioSexo.isChecked()){
            sexo = rdgUnicornioSexo.getText();
        }
        else if(rdgUnicornioSexoM.isChecked()){
            sexo = rdgUnicornioSexoM.getText();
        }
        Log.w("Banco", sexo.toString());
        unicornio.setGenero(sexo.toString());

        //pegar a escolha dos Spinners:
        //Spinner Elemento:
        String elemento = spnUnicornioElemento.getSelectedItem().toString();
        unicornio.setElemento(elemento);
        //Spinner Cor:
        String cor = spnUnicornioCor.getSelectedItem().toString();
        unicornio.setCor(cor);

        unicornioDAO = new UnicornioDAO(context);
        Boolean actionResult = unicornioDAO.salva(unicornio);   //salva no banco
        if (actionResult){
            Toast.makeText(CadastraUnicornioActivity.this,R.string.edit_sucess, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(CadastraUnicornioActivity.this,R.string.edit_fail, Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    /**
     * Limpa os campos da tela de cadastro após ter salvo os dados atuais
     */
    private void limpaCampos(){
        edtUnicornioNome.setText(edtUnicornioNome.getHint());
        edtUnicornioAltura.setText(edtUnicornioAltura.getHint());
        edtUnicornioPeso.setText(edtUnicornioPeso.getHint());
        spnUnicornioCor.setSelection(0);
        spnUnicornioElemento.setSelection(0);
        rdgUnicornioSexo.setSelected(false);
    }
}
