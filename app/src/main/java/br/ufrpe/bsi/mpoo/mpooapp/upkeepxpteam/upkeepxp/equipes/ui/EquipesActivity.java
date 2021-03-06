package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.CustomEquipeAdapter;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.EquipeModel;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio.Equipe;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.negocio.EquipeNegocio;
import upkeepxpteam.upkeepxp.R;

public class EquipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.bringToFront();
        Toolbar toolbar = findViewById(R.id.tb_equipes);
        toolbar.setTitle(R.string.Equipes_title);

        ListView lista = findViewById(R.id.listview_Equipes);

        final List<EquipeModel> modeloEquipe = new ArrayList<>();
        addItensListaModeloEquipe(modeloEquipe);

        final CustomEquipeAdapter adapter = new CustomEquipeAdapter(this, modeloEquipe);
        lista.setAdapter(adapter);

    }

    /**
     * Chama a Activity de cadastro de equipes.
     * @param view
     */
    public void addEquipe(View view){
        Intent intent = new Intent(this, CadastraEquipeActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Recebe um objeto euipeModels e adiciona equipe a essa lista.
     * @param equipeModels
     */

    public void addItensListaModeloEquipe(List equipeModels) {
        EquipeNegocio equipeNegocio = new EquipeNegocio(this);
        List itens = equipeNegocio.buscarTodasEquipes();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipe equipe = (Equipe) itens.get(cont);
            equipeModels.add(new EquipeModel(equipe));
            cont += 1;
        }
    }

}
