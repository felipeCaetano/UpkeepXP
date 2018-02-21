package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import upkeepxpteam.upkeepxp.R;

/**
 * Created by Felipe on 05/02/2018.
 * para personalizar a visualização de um equipamento
 */

public class EquipamentoAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Equipamento> elementos;

    public EquipamentoAdapter(Context context, ArrayList<Equipamento> elementos) {
        super(context, R.layout.equipamentolistview, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    /**
     * Retorna a view com o layout equipamentoListview para o objeto rowview; carregando na tela o
     * cóodigo do equipamento, o defeito e o status.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.equipamentolistview, parent, false);
        TextView equipamento = rowView.findViewById(R.id.txt_equipamentolist);
        TextView codigo = rowView.findViewById(R.id.txt_codigolist);
        TextView defeito = rowView.findViewById(R.id.txt_defeitolist);
        TextView status = rowView.findViewById(R.id.txt_statuslist);
        equipamento.setText(elementos.get(position).getNome());
        codigo.setText(elementos.get(position).getCodigo());
        defeito.setText(elementos.get(position).getDefeito());
        status.setText(elementos.get(position).getStatus());

        return rowView;
    }
}
