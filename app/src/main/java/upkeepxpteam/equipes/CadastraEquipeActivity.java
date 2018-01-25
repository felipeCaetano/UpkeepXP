package upkeepxpteam.equipes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.CustomAdapter;
import upkeepxpteam.UserModel;
import upkeepxpteam.equipes.equipeDAO.EquipeDAO;
import upkeepxpteam.equipes.equipebase.Equipe;
import upkeepxpteam.upkeepxp.R;
import upkeepxpteam.usuario.usuariobase.Usuario;

public class CadastraEquipeActivity extends Activity {

    private Button btnSalvar;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_equipe);
        ListView listView = (ListView) findViewById(R.id.listView_Membros);
        btnSalvar = (Button) findViewById(R.id.btn_confirmar);

        final List<UserModel> users = new ArrayList<>();
        addItensOnUsers(users);

        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);

        final List<Usuario> usersequipe = new ArrayList<>();


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Equipe equipe = new Equipe();
                equipe.setNome("Equipe 1");
                equipe.setUsers(usersequipe);
                EquipeDAO equipeDAO = new EquipeDAO(CadastraEquipeActivity.this);
                equipeDAO.equipeSave(equipe);
                Intent intent = new Intent(CadastraEquipeActivity.this, Equipe.class);
                startActivity(intent);
                finish();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i);
                if (model.isSelected()) {
                    model.setSelected(false);
                    usersequipe.remove(users.get(i).getUsuario());
                }
                else {
                    model.setSelected(true);
                    usersequipe.add(users.get(i).getUsuario());
                }
                users.set(i, model);

                adapter.updateRecords(users);

            }
        });
    }

        public void addItensOnUsers(List users) {
            EquipeDAO equipeDAO = new EquipeDAO(this);
            List itens = equipeDAO.listarusuarios();
            int cont = 0;
            while (cont <= itens.size() - 1) {
                Usuario usuario = (Usuario) itens.get(cont);
                users.add(new UserModel(false, usuario));
                cont += 1;
            }
        }

    }




