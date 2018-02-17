package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.atividadediaria.ui.AtividadeDiariaActivity;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia.EquipamentoDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.ui.EquipesActivity;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.ui.CadastraUsuarioActivity;
import upkeepxpteam.upkeepxp.R;


public class BuscarEquipamentosActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "sql";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_equip);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_activity_buscar_equip));
        setSupportActionBar(toolbar);

        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(this);
        final ArrayList<Equipamento> lista;
        lista = (ArrayList<Equipamento>)equipamentoDAO.findAll();

        EditText pesquisar = findViewById(R.id.busca);

        ListView listaequip = findViewById(R.id.lvequip);
        final ArrayAdapter<Equipamento> adaptadorEquip =
                new EquipamentoAdapter(BuscarEquipamentosActivity.this,lista);

        //ativa a pesquisa no campo de texto pesquisar:
        pesquisar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptadorEquip.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        listaequip.setAdapter(adaptadorEquip);

        listaequip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Equipamento equip = adaptadorEquip.getItem(position);

                Intent intent = new Intent(BuscarEquipamentosActivity.this, EditarCadastroEquipamento.class);
                intent.putExtra("Equipamento", equip);
                startActivity(intent);
            }
        });


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chamar a intent que vai habilitar o cadastro no BD
                Intent it = new Intent(BuscarEquipamentosActivity.this, CadastrarEquipamentos.class);
                startActivity(it);
                finish();
            }
        });

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.manapp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Responsável por responder à seleção feita no menu
     * @param item
     * @return
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_agenda) {
            // Handle the agenda action
            intent = new Intent(BuscarEquipamentosActivity.this, AtividadeDiariaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contatos) {

        } else if (id == R.id.nav_equipes) {
            intent = new Intent(BuscarEquipamentosActivity.this, EquipesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_membros) {
            intent =  new Intent(BuscarEquipamentosActivity.this, CadastraUsuarioActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_disponibilidade) {
            intent = new Intent(BuscarEquipamentosActivity.this, CalcularDisponibilidade.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_registro) {
            intent =  new Intent(BuscarEquipamentosActivity.this, BuscarEquipamentosActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_unicorn) {
            intent = new Intent(BuscarEquipamentosActivity.this, br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.ui.BuscarUnicorniosActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

