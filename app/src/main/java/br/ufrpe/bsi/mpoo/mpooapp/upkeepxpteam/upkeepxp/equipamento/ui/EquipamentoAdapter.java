package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import upkeepxpteam.upkeepxp.R;

/**
 * Created by Felipe on 05/02/2018.
 */

public class EquipamentoAdapter extends ArrayAdapter<Equipamento> implements Filterable {

    private final Context context;
    private ArrayList<Equipamento> elementos;


    public EquipamentoAdapter(Context context, ArrayList<Equipamento> elementos) {
        super(context, R.layout.equipamentolistview, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    public void setElementos(ArrayList<Equipamento> elementos) {
        this.elementos = elementos;
    }


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

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults resultadoFiltragem = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    resultadoFiltragem.values = elementos;
                    resultadoFiltragem.count = elementos.size();
                }else{
                    List<Equipamento> aux = new ArrayList<>();
                    for(Equipamento equipamento : elementos){
                        if(equipamento.getNome().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            aux.add(equipamento);
                        }
                        else if (equipamento.getCodigo().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            aux.add(equipamento);
                        }
                        else if (equipamento.getDefeito().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            aux.add(equipamento);
                        }
                    }
                    resultadoFiltragem.values = aux;
                    resultadoFiltragem.count = aux.size();
                }
                return resultadoFiltragem;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                if (filterResults.count == 0){
                    notifyDataSetInvalidated();
                }else{
                    elementos = (ArrayList<Equipamento>)filterResults.values;
                    notifyDataSetChanged();
                }
            }
        };
        return filter;
    }
}
