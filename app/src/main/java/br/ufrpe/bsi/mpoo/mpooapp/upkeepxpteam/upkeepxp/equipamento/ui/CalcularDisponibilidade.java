package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private Spinner spinnerLigacao;
    private Spinner spinnerAtual;
    private Spinner spinnerProx;
    int disponibilidade = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_disponibilidade);
        //ListView listView = (ListView) findViewById(R.id.listView_calcular_disponibilidade);
        spinnerAtual = findViewById(R.id.spinnerAtual);
        spinnerProx = findViewById(R.id.spinnerProx);
        spinnerLigacao = findViewById(R.id.spinnerLigacao);
        TextView tvValorDisponibilidade = (TextView) findViewById(R.id.textView_valor_disponibilidade);
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this); // Ajeitar, por no DAO relação disponibilidade
        // Chamar spinner pra pegar o valor
        final List<EquipamentoModel> equipamentoList = new ArrayList<>();
        addItensListaModeloEquipamento(equipamentoList);

        ArrayAdapter<EquipamentoModel> adapter = new ArrayAdapter<EquipamentoModel>(this, android.R.layout.simple_spinner_item, equipamentoList);
        ArrayAdapter adapterLigacao = ArrayAdapter.createFromResource(this, R.array.ligacao, android.R.layout.simple_spinner_item);
        spinnerAtual.setAdapter(adapter);
        spinnerProx.setAdapter(adapter);
        spinnerLigacao.setAdapter(adapterLigacao);
        //final CustomEquipamentoAdapter adapter = new CustomEquipamentoAdapter(this, equipamentoList);
        //listView.setAdapter(adapter);

        tvValorDisponibilidade.setText("0%");
        equipamentoDAO.execSQL(EquipamentoDAO.deleteMyTable2());
        equipamentoDAO.execSQL(EquipamentoDAO.createMyTable2());
    }

    public void addItensListaModeloEquipamento(List equipeModels) {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        List itens = equipamentoDAO.findAll();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipamento equipamento = (Equipamento) itens.get(cont);
            equipeModels.add(equipamento.getNome());
            cont += 1;
        }
    }

    public int calcularDisponibilidade(){
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        List<EquipamentoModel> falhas = equipamentoDAO.getRelacaoFalhas(); //melhorar nome
        // Percorrer toda a lista
        for (EquipamentoModel equipamentoModel: falhas){
            if (disponibilidade == 0) {
                if (equipamentoModel.getLigacao().equals("Série")) {
                    Toast.makeText(this, "Mano do 1", Toast.LENGTH_SHORT).show();
                    disponibilidade += ((Integer.valueOf(equipamentoModel.getEquipamento().getDisponibilidade())) * (Integer.valueOf(equipamentoModel.getProxEquipamento().getDisponibilidade()))) / 100;
                    return disponibilidade;
                } else if (equipamentoModel.getLigacao().equals("Paralelo")) {
                    Toast.makeText(this, "Mano do céu", Toast.LENGTH_SHORT).show();
                    disponibilidade += 1 - (1 - Integer.valueOf(equipamentoModel.getEquipamento().getDisponibilidade())) * (1 - Integer.valueOf(equipamentoModel.getProxEquipamento().getDisponibilidade())) / 100;
                    if (disponibilidade < 0) {
                        disponibilidade *= -1;
                    }
                    return disponibilidade;
                }
            }else {
                if (equipamentoModel.getLigacao().equals("Série")) {
                    Toast.makeText(this, "Mano do 2", Toast.LENGTH_SHORT).show();
                    disponibilidade = Integer.valueOf(disponibilidade) * Integer.valueOf(equipamentoModel.getProxEquipamento().getDisponibilidade()) / 100;
                } else if (equipamentoModel.getLigacao().equals("Paralelo")) {
                    Toast.makeText(this, "Mano do céu", Toast.LENGTH_SHORT).show();
                    disponibilidade = 1 - (1 - Integer.valueOf(disponibilidade)) * (1 - Integer.valueOf(equipamentoModel.getProxEquipamento().getDisponibilidade())) / 100;
                    if (disponibilidade < 0) {
                        disponibilidade *= -1;
                    }
                }
            }
        }
        return disponibilidade;
    }

    public void btnCalcular(View view){
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        EquipamentoModel equipamentoModel = new EquipamentoModel();
        Equipamento equipamentoSpinnerAtual = equipamentoDAO.equipamentoPorNome(String.valueOf(spinnerAtual.getSelectedItem()));
        Equipamento equipamentoSpinnerProx = equipamentoDAO.equipamentoPorNome(String.valueOf(spinnerProx.getSelectedItem()));
        equipamentoModel.setEquipamento(equipamentoSpinnerAtual);
        equipamentoModel.setProxEquipamento(equipamentoSpinnerProx);
        equipamentoModel.setLigacao(String.valueOf(spinnerLigacao.getSelectedItem()));
        equipamentoDAO.salvaDisponibilidade(equipamentoModel);
        int disponibilidade = calcularDisponibilidade();
        TextView tvValorDisponibilidade = findViewById(R.id.textView_valor_disponibilidade);
        tvValorDisponibilidade.setText(""+disponibilidade+"%");
        tvValorDisponibilidade.setTextColor(Color.parseColor("#ffffff"));
    }

}
