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
        TextView tvValorDisponibilidade = (TextView) findViewById(R.id.textView_valor_disponibilidade);
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this); // Ajeitar, por no DAO relação disponibilidade
        // Chamar spinner pra pegar o valor
        final List<EquipamentoModel> equipamentoList = new ArrayList<>();
        addItensListaModeloEquipamento(equipamentoList);

        final CustomEquipamentoAdapter adapter = new CustomEquipamentoAdapter(this, equipamentoList);
        listView.setAdapter(adapter);

        tvValorDisponibilidade.setText("0%");

    }

    public void addItensListaModeloEquipamento(List equipeModels) {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        List itens = equipamentoDAO.findAll();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipamento equipamento = (Equipamento) itens.get(cont);
            EquipamentoModel equipamentoModel = new EquipamentoModel(equipamento);
             // setar com valor que vem do spinner
            equipeModels.add(equipamentoModel);
            cont += 1;
        }
    }

    public int calcularDisponibilidade(){
        int disponibilidade = 0;
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        List falhas = equipamentoDAO.getRelacaoFalhas(); //melhorar nome
        if (falhas.size() != 0) {
            Toast.makeText(this, "Mano", Toast.LENGTH_SHORT).show();
            Equipamento equipamentoAtual = equipamentoDAO.equipamentoPorId(Integer.valueOf(String.valueOf(falhas.get(0))));
            Equipamento equipamentoProx = equipamentoDAO.equipamentoPorId(Integer.valueOf(String.valueOf(falhas.get(1))));
            if (falhas.get(2).equals("Série")) {
                Toast.makeText(this, "Mano do", Toast.LENGTH_SHORT).show();
                disponibilidade += Integer.valueOf(equipamentoAtual.getDisponibilidade()) * Integer.valueOf(equipamentoProx.getDisponibilidade()) / 100;
                return disponibilidade;
            }
            else if (falhas.get(2).equals("Paralelo")) {
                Toast.makeText(this, "Mano do céu", Toast.LENGTH_SHORT).show();
                disponibilidade += 1 - (1 - Integer.valueOf(equipamentoAtual.getDisponibilidade())) * (1 - Integer.valueOf(equipamentoProx.getDisponibilidade())) / 100;
                if (disponibilidade < 0){
                    disponibilidade *= -1;
                }
                return disponibilidade;
            }
        }
        return disponibilidade;
    }

    public void btnCalcular(View view){
        int disponibilidade = calcularDisponibilidade();
        TextView tvValorDisponibilidade = findViewById(R.id.textView_valor_disponibilidade);
        tvValorDisponibilidade.setText(""+disponibilidade+"%");
        tvValorDisponibilidade.setTextColor(Color.parseColor("#ffffff"));
    }

}
