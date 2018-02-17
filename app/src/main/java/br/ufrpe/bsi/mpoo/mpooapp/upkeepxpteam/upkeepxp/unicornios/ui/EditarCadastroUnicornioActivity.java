package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui.EditarCadastroEquipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.CorEnum;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.ElementEnum;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.Unicornio;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.persistencia.UnicornioDAO;
import upkeepxpteam.upkeepxp.R;

/**
 * Created by anton on 16/02/2018.
 * Classe que recupera e edita um unicornio do banco
 *
 * Bibliografia:
 * Solução para pupular os enum foi tirada de:
 * https://stackoverflow.com/questions/5469629/put-enum-values-to-android-spinner
 * https://stackoverflow.com/questions/2390102/how-to-set-selected-item-of-spinner-by-value-not-by-position
 */

public class EditarCadastroUnicornioActivity extends AppCompatActivity{

    private Unicornio unicornio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_unicornios);

        Button btndelete = findViewById(R.id.excluirButton);
        btndelete.setVisibility(View.VISIBLE);

        EditText edtUnicornioNome = findViewById(R.id.unicornioNomeEditText);
        EditText edtUnicornioPeso = findViewById(R.id.pesoEditText);
        EditText edtUnicornioAltura = findViewById(R.id.alturaEditText);
        RadioGroup rdgUnicornioSexo = findViewById(R.id.sexoRadioGroup);
        Spinner spnUnicornioElemento = findViewById(R.id.spn_elemento);
        Spinner spnUnicornioCor = findViewById(R.id.spn_color);
        Button btnSalvar = findViewById(R.id.btn_salvar);

        Bundle args =getIntent().getExtras();   //recupera os arguementos passados

        unicornio = new Unicornio();
        unicornio = args.getParcelable("Unicornio");
        
        //recuperar campos
        if(unicornio != null){
            String unicornioNome = unicornio.getNome();
            edtUnicornioNome.setText(unicornioNome);
            Double unicornioPeso = unicornio.getPeso();
            edtUnicornioPeso.setText(unicornioPeso.toString());
            Double unicornioAltura = unicornio.getAltura();
            edtUnicornioAltura.setText(unicornioAltura.toString());
            String unicornioSexo = unicornio.getGenero();
            if(unicornioSexo.equals("F")){
                rdgUnicornioSexo.check(R.id.femRadioButton);
            }else{
                rdgUnicornioSexo.check(R.id.mascRadioButton);
            }
            //Setar Spinners
            //Spiner Elementos:
            String unicornioElemento = unicornio.getElemento().toString();
            ArrayAdapter<ElementEnum> elementEnumArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ElementEnum.values());
            spnUnicornioElemento.setAdapter(elementEnumArrayAdapter);
            elementEnumArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            if (unicornioElemento != null) {
                int spinnerPosition = elementEnumArrayAdapter.getPosition(ElementEnum.valueOf(unicornioElemento));
                spnUnicornioElemento.setSelection(spinnerPosition);
            }

            //Spinner COR:
            String unicornioCor = unicornio.getCor().toString();
            ArrayAdapter<CorEnum> corEnumArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CorEnum.values());
            spnUnicornioCor.setAdapter(corEnumArrayAdapter);
            corEnumArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            if (unicornioCor != null) {
                int spinnerPosition = corEnumArrayAdapter.getPosition(CorEnum.valueOf(unicornioCor));
                spnUnicornioCor.setSelection(spinnerPosition);
            }

        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvaCadastro(unicornio);
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletaCadastro(unicornio);
            }
        });
    }

    private void deletaCadastro(final Unicornio unicornio) {
        final UnicornioDAO unicornioDAO = new UnicornioDAO(this);
        //alerta para delete de cadastro:
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deseja_Del).setTitle(R.string.delete_title);
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Boolean actionResult = unicornioDAO.delete(unicornio);  //deleta o cadastro no banco
                if(actionResult){
                    Toast.makeText(EditarCadastroUnicornioActivity.this,R.string.delete_sucess, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
                });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                //não precisa fazer nada caso seja cancelado pelo usuário
            }
        });
        builder.show();
    }

    private void salvaCadastro(Unicornio unicornio) {
        UnicornioDAO unicornioDAO = new UnicornioDAO(this);
        Boolean actionResult = unicornioDAO.salva(unicornio);
        if (actionResult){
            Toast.makeText(EditarCadastroUnicornioActivity.this,R.string.edit_sucess, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(EditarCadastroUnicornioActivity.this,R.string.edit_fail, Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
