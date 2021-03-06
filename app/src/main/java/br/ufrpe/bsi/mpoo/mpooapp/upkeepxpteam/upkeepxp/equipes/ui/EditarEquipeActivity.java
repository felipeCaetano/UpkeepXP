package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.CustomAdapter;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.UserModel;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio.Equipe;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.negocio.EquipeNegocio;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.persistencia.UsuarioDAO;
import upkeepxpteam.upkeepxp.R;

public class EditarEquipeActivity extends AppCompatActivity {

    private EditText edtnomeequipe;
    Equipe equipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_equipe);

        ListView listView = findViewById(R.id.listView_Membros);
        Button btnEditar = findViewById(R.id.btn_confirmar);
        btnEditar.setText(R.string.txt_editar);
        edtnomeequipe = findViewById(R.id.editText_nome_equipe);

        final List<UserModel> listaUsuarios = new ArrayList<>();
        addItensListaUsuarios(listaUsuarios);

        final CustomAdapter adapter = new CustomAdapter(this, listaUsuarios);
        listView.setAdapter(adapter);

        final List<Usuario> usersequipe = new ArrayList<>();

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences userDetails = EditarEquipeActivity.this.
                        getSharedPreferences("idEquipePreference", MODE_PRIVATE);
                int idEquipeEdit = userDetails.getInt("idEquipePreferences", 0);
                equipe = montarObjetoEquipe();
                equipe.setUsers(usersequipe);
                EquipeNegocio equipeNegocio = new EquipeNegocio(EditarEquipeActivity.this);
                equipeNegocio.editarEquipe(equipe,idEquipeEdit);
                chamarEquipesActivity();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = listaUsuarios.get(i);
                if (model.isSelected()) {
                    model.setSelected(false);
                    usersequipe.remove(listaUsuarios.get(i).getUsuario());
                }
                else {
                    model.setSelected(true);
                    usersequipe.add(listaUsuarios.get(i).getUsuario());
                }
                listaUsuarios.set(i, model);

                adapter.updateRecords(listaUsuarios);

            }
        });
    }

    /**
     * Recebe um objeto lista de usuários e adiciona usuários a essa lista.
     * @param users
     */
    public void addItensListaUsuarios(List users) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        List itens = usuarioDAO.buscarTodosUsuarios();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Usuario usuario = (Usuario) itens.get(cont);
            users.add(new UserModel(false, usuario));
            cont += 1;
        }
    }

    /**
     * Retorna um objeto equipe com o nome 'nomeequipe'
     * @return
     */
    public Equipe montarObjetoEquipe(){

        String nomeequipe = edtnomeequipe.getText().toString();
        Equipe equipe = new Equipe();
        equipe.setNome(nomeequipe);
        return equipe;

    }

    /**
     * Invoca a Activity de Equipes a partir da Activity de cadastro de equipes.
     */
    public void chamarEquipesActivity(){

        Intent intent = new Intent(EditarEquipeActivity.this, EquipesActivity.class);
        startActivity(intent);
        finish();

    }

}


