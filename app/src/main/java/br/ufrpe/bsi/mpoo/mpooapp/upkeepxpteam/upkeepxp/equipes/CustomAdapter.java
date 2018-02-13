package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import upkeepxpteam.upkeepxp.R;

public class CustomAdapter extends BaseAdapter {

    Activity activity;
    List<UserModel> users;
    LayoutInflater inflater;

    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<UserModel> users) {
        this.activity = activity;
        this.users = users;
        inflater = activity.getLayoutInflater();
    }

    public int getCount(){
        return users.size();
    }

    public Object getItem(int i){
        return i;
    }

    public long getItemId(int i){
        return i;
    }

    /**
     * Retorna a view com o layout rowusers para o objeto rowview; carregando na tela o userName e
     * o checkbox com respectivo estado (se está selecionado ou não).
     * Atualiza o listview de usuários (
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    public View getView(int i, View view, ViewGroup viewGroup){
        ViewHolder holder = null;

        if (view == null){
            view = inflater.inflate(R.layout.rowusers, viewGroup, false);
            holder = new ViewHolder();
            holder.tvUserName = view.findViewById(R.id.textView_NomeUsuario);
            holder.ivCheckBox = view.findViewById(R.id.checkBox_AddMembro);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

            UserModel model = users.get(i);

            holder.tvUserName.setText("" + model.getUsuario().getNome() + " " + model.getUsuario().getSobrenome());

        if (model.isSelected()){
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);
        } else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);
        return view;
    }

    public void  updateRecords(List<UserModel> users){
        this.users = users;

        notifyDataSetChanged();
    }

    /**
     * Classe ViewHolder que permite reutilização em outros adapters
     */
    static class ViewHolder{
        TextView tvUserName;
        ImageView ivCheckBox;
    }
}
