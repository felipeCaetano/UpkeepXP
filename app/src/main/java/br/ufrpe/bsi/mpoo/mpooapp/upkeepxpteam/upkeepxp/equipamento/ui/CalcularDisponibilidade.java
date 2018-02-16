package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.CustomEquipamentoAdapter;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.EquipamentoModel;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia.EquipamentoDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.EquipeModel;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio.Equipe;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.negocio.EquipeNegocio;
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
        Spinner spinnerAtual = (Spinner) findViewById(R.id.spinner2);
        Button calcular = (Button) findViewById(R.id.btn_calcular);
        TextView tvValorDisponibilidade = (TextView) findViewById(R.id.textView_valor_disponibilidade);
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);

        final List equipamentoList = new ArrayList<>();
        addItensListaModeloEquipamento(equipamentoList);

        final CustomEquipamentoAdapter adapter = new CustomEquipamentoAdapter(this, equipamentoList);
        listView.setAdapter(adapter);

        int disponibilidade = calcularDisponibilidade();
        tvValorDisponibilidade.setText(""+disponibilidade+"%");
        tvValorDisponibilidade.setTextColor(Color.parseColor("#ffffff"));
    }

    public void addItensListaModeloEquipamento(List equipeModels) {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        List itens = equipamentoDAO.findAll();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipamento equipamento = (Equipamento) itens.get(cont);
            EquipamentoModel equipamentoModel = new EquipamentoModel(equipamento);
            equipamentoModel.setSelected(true);
            equipeModels.add(equipamentoModel);
            cont += 1;
        }
    }

    public int calcularDisponibilidade(){
        int disponibilidade = 0;
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        List falhas = equipamentoDAO.getRelacaoFalhas();
        if (falhas.size() != 0) {
            Equipamento equipamentoAtual = equipamentoDAO.equipamentoPorId((Integer) falhas.get(0));
            Equipamento equipamentoProx = equipamentoDAO.equipamentoPorId((Integer) falhas.get(1));
            if (falhas.get(2).equals("serie")) {
                disponibilidade += Integer.valueOf(equipamentoAtual.getDisponibilidade()) * Integer.valueOf(equipamentoProx.getDisponibilidade()) / 100;
                return disponibilidade;
            }
            else if (falhas.get(2).equals("paralelo")) {
                disponibilidade += 1 - (1 - Integer.valueOf(equipamentoAtual.getDisponibilidade())) * (1 - Integer.valueOf(equipamentoProx.getDisponibilidade())) / 100;
                return disponibilidade;
            }
        }
        return disponibilidade;
    }

    public void btnCalcular(View view){
        Intent intent = new Intent(this, CalcularDisponibilidade.class);
        startActivity(intent);
        finish();
    }

}
