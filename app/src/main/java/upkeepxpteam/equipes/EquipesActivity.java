package upkeepxpteam.equipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.CustomEquipeAdapter;
import upkeepxpteam.EquipeModel;
import upkeepxpteam.equipes.equipeDAO.EquipeDAO;
import upkeepxpteam.equipes.equipebase.Equipe;
import upkeepxpteam.upkeepxp.R;

public class EquipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes);
        this.setTitle("Equipes");
        ListView lista = (ListView) findViewById(R.id.listview_Equipes);

        final List<EquipeModel> equipeModels = new ArrayList<>();
        addItensOnEquipeModels(equipeModels);

        final CustomEquipeAdapter adapter = new CustomEquipeAdapter(this, equipeModels);
        lista.setAdapter(adapter);
    }

    public void addEquipe(View view){
        Intent intent = new Intent(this, CadastraEquipeActivity.class);
        startActivity(intent);
        onPause();
    }

    public void addItensOnEquipeModels(List equipeModels) {
        EquipeDAO equipeDAO = new EquipeDAO(this);
        List itens = equipeDAO.listarequipe();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipe equipe = (Equipe) itens.get(cont);
            equipeModels.add(new EquipeModel(equipe));
            cont += 1;
        }
    }

}
