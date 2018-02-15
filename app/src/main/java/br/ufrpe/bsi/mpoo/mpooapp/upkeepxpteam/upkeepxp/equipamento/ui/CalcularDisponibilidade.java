package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.CustomEquipamentoAdapter;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia.EquipamentoDAO;
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
        ListView listView = (ListView) findViewById(R.id.listView_calcular_disponibilidade);
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);

        List equipamentoList = equipamentoDAO.findAll();

        final CustomEquipamentoAdapter adapter = new CustomEquipamentoAdapter(this, equipamentoList);
        listView.setAdapter(adapter);

    }
}
