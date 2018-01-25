package upkeepxpteam;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import upkeepxpteam.upkeepxp.R;

public class CustomEquipeAdapter extends BaseAdapter {

    Activity activity;
    List<EquipeModel> equipeModels;
    LayoutInflater inflater;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view == null){
            view = inflater.inflate(R.layout.rowequipe,viewGroup,false);
            holder = new ViewHolder();
            holder.tvEquipe = (TextView) view.findViewById(R.id.textView_Equipe);
            view.setTag(holder);
        }else
            holder = (ViewHolder) view.getTag();
        EquipeModel equipeModel = equipeModels.get(i);

        holder.tvEquipe.setText(equipeModel.getEquipe().getNome() +"\n"+ equipeModel.getEquipe().getUsuario());
        holder.tvEquipe.setTextColor(Color.parseColor("#000000"));
        return view;
    }

    class ViewHolder{
        TextView tvEquipe;
    }
}
