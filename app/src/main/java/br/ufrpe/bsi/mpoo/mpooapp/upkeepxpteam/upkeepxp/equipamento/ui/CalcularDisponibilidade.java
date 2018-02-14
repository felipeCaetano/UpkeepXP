package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import upkeepxpteam.upkeepxp.R;

public class CalcularDisponibilidade extends AppCompatActivity {

    private EditText equipamentoAtual;
    private EditText getEquipamentoNovo;
    private Button btnCalcularDisponibilidade;
    private Button btnEditarSistema;
    private Spinner tipoAssociacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_disponibilidade);


    }
}
