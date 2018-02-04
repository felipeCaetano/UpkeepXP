package upkeepxpteam;

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

import upkeepxpteam.equipes.gui.EditarEquipeActivity;
import upkeepxpteam.equipes.gui.EquipesActivity;
import upkeepxpteam.equipes.equipeDAO.EquipeDAO;
import upkeepxpteam.equipes.equipeDAO.EquipeIdDAO;
import upkeepxpteam.equipes.equipebase.EquipeId;
import upkeepxpteam.upkeepxp.R;
import upkeepxpteam.usuario.usuariobase.Usuario;
import upkeepxpteam.usuario.usuariopersistence.UsuarioDAO;

import static android.content.Context.MODE_PRIVATE;

public class CustomEquipeAdapter extends BaseAdapter {

    Activity activity;
    List<EquipeModel> equipeModels;
    LayoutInflater inflater;
    String PREFERENCE_NAME = "EditarPreferences";

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
        }else {
            holder = (ViewHolder) view.getTag();
        }
        final EquipeModel equipeModel = equipeModels.get(i);

        final String nome = equipeModel.getEquipe().getNome();
        int id = equipeModel.getEquipe().getId();
        EquipeIdDAO equipeIdDAO = new EquipeIdDAO(activity);
        List<EquipeId> equipeIdList = equipeIdDAO.buscarTodasEquipesId(id);
        String operarios = "";
        for (EquipeId equipeId: equipeIdList){
            int userid = equipeId.getIdUsuario();
            UsuarioDAO usuarioDAO = new UsuarioDAO(activity);
            List<Usuario> usuarioList = usuarioDAO.getUsuarioPorId(userid);
            operarios += usuarioList.get(0).toString()+"\n";
        }
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
                edit.commit();
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
