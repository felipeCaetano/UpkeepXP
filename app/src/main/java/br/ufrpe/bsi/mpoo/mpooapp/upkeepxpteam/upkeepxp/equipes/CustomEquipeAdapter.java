package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.persistencia.EquipeDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.persistencia.EquipeIdDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.ui.EditarEquipeActivity;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.ui.EquipesActivity;
import upkeepxpteam.upkeepxp.R;

import static android.content.Context.MODE_PRIVATE;

public class CustomEquipeAdapter extends BaseAdapter {

    Activity activity;
    List<EquipeModel> equipeModels;
    LayoutInflater inflater;

    /**
     * Construtor para classe
     * @param activity
     * @param equipeModels
     */
    public CustomEquipeAdapter(Activity activity, List<EquipeModel> equipeModels){
        this.activity = activity;
        this.equipeModels = equipeModels;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return equipeModels.size();
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
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null){
            view = inflater.inflate(R.layout.rowequipe,viewGroup,false);
            holder = new ViewHolder();
            holder.tvEquipe = view.findViewById(R.id.textView_Equipe);
            holder.btnExcluir = view.findViewById(R.id.btn_excluir);
            holder.btnEditar = view.findViewById(R.id.btn_confirmar);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final EquipeModel equipeModel = equipeModels.get(i);
        final String nome = equipeModel.getEquipe().getNome();
        int id = equipeModel.getEquipe().getId();

        EquipeIdDAO equipeIdDAO = new EquipeIdDAO(activity);
        String operarios = equipeIdDAO.getStringOperarioEquipeId(id);

        holder.tvEquipe.setText("Equipe: "+nome+"\n"+"Operarios: "+operarios);
        holder.tvEquipe.setTextColor(Color.parseColor("#000000"));

        holder.btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EquipeDAO equipeDAO = new EquipeDAO(activity);
                equipeDAO.excluirEquipe(nome);
                Intent intent = new Intent(activity, EquipesActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences userDetails = activity.getSharedPreferences("idEquipePreference", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.clear();
                edit.putInt("idEquipePreferences", equipeModel.getEquipe().getId());
                edit.apply(); // trocado de commite para apply
                Intent intent = new Intent(activity, EditarEquipeActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
        });

        return view;
    }
    static class ViewHolder{
        TextView tvEquipe;
        Button btnExcluir;
        Button btnEditar;
    }
}
