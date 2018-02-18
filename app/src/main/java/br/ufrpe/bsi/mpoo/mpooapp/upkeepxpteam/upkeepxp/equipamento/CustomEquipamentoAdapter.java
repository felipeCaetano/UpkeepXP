package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia.EquipamentoDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui.CalcularDisponibilidade;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.ui.MainActivity;
import upkeepxpteam.upkeepxp.R;


public class CustomEquipamentoAdapter extends BaseAdapter {

    Activity activity;
    List<EquipamentoModel> equipamentoModels;
    LayoutInflater inflater;
    ArrayAdapter<CharSequence> ligacaoAdapter;

    /**
     * Construtor para classe
     *
     * @param activity
     * @param equipamentoModels
     */
    public CustomEquipamentoAdapter(Activity activity, List<EquipamentoModel> equipamentoModels) {
        this.activity = activity;
        this.equipamentoModels = equipamentoModels;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return equipamentoModels.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Retorna uma view
     *
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        CustomEquipamentoAdapter.ViewHolder holder = null;

        if (view == null) {
            view = inflater.inflate(R.layout.rowequipamento, viewGroup, false);
            holder = new CustomEquipamentoAdapter.ViewHolder();
            holder.tvEquipamentoAtual = view.findViewById(R.id.textView_Equipamento_Atual);
            holder.spinnerAtual = view.findViewById(R.id.spinner2);
            holder.spinnerProx = view.findViewById(R.id.spinner3);
            holder.ligacao = view.findViewById(R.id.spinner4);
            view.setTag(holder);
        } else {
            holder = (CustomEquipamentoAdapter.ViewHolder) view.getTag();
        }

        EquipamentoModel equipamentoModel = equipamentoModels.get(i);

        List<String> lista = new ArrayList<>();
        addItensListaModeloEquipamento(lista);

        final ArrayAdapter<String> equipamentoModelArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, lista);
        equipamentoModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ligacaoAdapter = ArrayAdapter.createFromResource(activity, R.array.ligacao, android.R.layout.simple_spinner_item); // USAR ENUM
        ligacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.spinnerAtual.setAdapter(equipamentoModelArrayAdapter);
        // melhorar equipamentoModelArrayAdapter para retirar um item selecionado
        holder.spinnerProx.setAdapter(equipamentoModelArrayAdapter);
        holder.ligacao.setAdapter(ligacaoAdapter);

        holder.spinnerAtual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(activity, String.valueOf(equipamentoModelArrayAdapter.getItem(i)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        holder.spinnerProx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(activity, String.valueOf(equipamentoModelArrayAdapter.getItem(i)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        holder.ligacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stringLigacaoAdapter = String.valueOf(ligacaoAdapter.getItem(i));
                if(stringLigacaoAdapter.equals("Paralelo")){
                    Toast.makeText(activity, String.valueOf(ligacaoAdapter.getItem(i)), Toast.LENGTH_SHORT).show();
                    EquipamentoDAO equipamentoDAO = new EquipamentoDAO(activity);
                    Equipamento equipamentoSpinnerAtual = equipamentoDAO.equipamentoPorNome(String.valueOf(ViewHolder.spinnerAtual.getSelectedItem()));
                    Equipamento equipamentoSpinnerProx = equipamentoDAO.equipamentoPorNome(String.valueOf(ViewHolder.spinnerProx.getSelectedItem()));
                    equipamentoDAO.execSQL(EquipamentoDAO.deleteMyTable2());
                    equipamentoDAO.execSQL(EquipamentoDAO.createMyTable2());
                    equipamentoDAO.salvaDisponibilidade(equipamentoSpinnerAtual.getId(), equipamentoSpinnerProx.getId() , stringLigacaoAdapter);
                }
                else if (stringLigacaoAdapter.equals("Série")){
                    Toast.makeText(activity, String.valueOf(ligacaoAdapter.getItem(i)), Toast.LENGTH_SHORT).show();
                    EquipamentoDAO equipamentoDAO = new EquipamentoDAO(activity);
                    Equipamento equipamentoSpinnerAtual = equipamentoDAO.equipamentoPorNome(String.valueOf(ViewHolder.spinnerAtual.getSelectedItem()));
                    Equipamento equipamentoSpinnerProx = equipamentoDAO.equipamentoPorNome(String.valueOf(ViewHolder.spinnerProx.getSelectedItem()));
                    equipamentoDAO.execSQL(EquipamentoDAO.deleteMyTable2());
                    equipamentoDAO.execSQL(EquipamentoDAO.createMyTable2());
                    equipamentoDAO.salvaDisponibilidade(equipamentoSpinnerAtual.getId(), equipamentoSpinnerProx.getId(), stringLigacaoAdapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    static class ViewHolder {
        TextView tvEquipamentoAtual;
        static Spinner spinnerAtual;
        static Spinner spinnerProx;
        Spinner ligacao;
    }

    public void addItensListaModeloEquipamento(List equipeModels) {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(activity);
        List itens = equipamentoDAO.findAll();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipamento equipamento = (Equipamento) itens.get(cont);
            equipeModels.add(equipamento.getNome());
            cont += 1;
        }
    }

    public void newActivity(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

}



