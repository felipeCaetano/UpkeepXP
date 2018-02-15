package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

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
            holder.tvEquipamentoAtual = view.findViewById(R.id.textView_Equipe);
            holder.spinnerAtual = view.findViewById(R.id.spinner2);
            holder.spinnerProx = view.findViewById(R.id.spinner3);
            holder.ligacao = view.findViewById(R.id.spinner4);
            view.setTag(holder);
        } else {
            holder = (CustomEquipamentoAdapter.ViewHolder) view.getTag();
        }

        ArrayAdapter<EquipamentoModel> equipamentoModelArrayAdapter = new ArrayAdapter<EquipamentoModel>(activity, android.R.layout.simple_spinner_item, equipamentoModels);
        equipamentoModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ligacaoAdapter = ArrayAdapter.createFromResource(activity, R.array.ligacao, android.R.layout.simple_spinner_item);
        ligacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        holder.spinnerAtual.setAdapter(equipamentoModelArrayAdapter);
        holder.spinnerProx.setAdapter(equipamentoModelArrayAdapter);
        holder.ligacao.setAdapter(ligacaoAdapter);


        return view;
    }

    static class ViewHolder {
        TextView tvEquipamentoAtual;
        Spinner spinnerAtual;
        Spinner spinnerProx;
        Spinner ligacao;
    }

}



