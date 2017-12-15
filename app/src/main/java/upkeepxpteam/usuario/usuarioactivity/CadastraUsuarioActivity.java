package upkeepxpteam.usuario.usuarioactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import upkeepxpteam.upkeepxp.R;
import upkeepxpteam.usuario.usuariopersistence.UsuarioDAO;

public class CadastraUsuarioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button btnConfirmar = (Button) findViewById(R.id.confirmarButton);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = (EditText) findViewById(R.id.emailEditText);
                EditText nomeEditText = (EditText) findViewById(R.id.nomeEditText);
                EditText sobrenomeEditText = (EditText) findViewById(R.id.sobrenomeEditText);
                EditText nascimentoEditText = (EditText) findViewById(R.id.nascimentoEditText);
                RadioButton mascRadioButton = (RadioButton) findViewById(R.id.mascRadioButton);
                EditText foneEditText = (EditText) findViewById(R.id.foneEditText);
                EditText especialidadeEditText = (EditText) findViewById(R.id.especialidadeEditText);
                EditText cepEditText = (EditText) findViewById(R.id.cepEditText);
                EditText numeroEditText = (EditText) findViewById(R.id.numeroEditText);
                Spinner ufSpinner = (Spinner) findViewById(R.id.ufSpinner);
                Spinner funcaoSpinner = (Spinner) findViewById(R.id.funcaoSpinner);

                String email = emailEditText.getText().toString();
                String nome = nomeEditText.getText().toString();
                String sobrenome = sobrenomeEditText.getText().toString();
                String nascimento = nascimentoEditText.getText().toString();
                String sexo = mascRadioButton.isSelected() ? "M" : "F";
                String fone = foneEditText.getText().toString();
                String especialidade = especialidadeEditText.getText().toString();
                String cep = cepEditText.getText().toString();
                String numero = numeroEditText.getText().toString();
                String uf = ufSpinner.getSelectedItem().toString();
                String funcao = funcaoSpinner.getSelectedItem().toString();

                UsuarioDAO dao = new UsuarioDAO(getBaseContext());
                boolean sucesso = dao.salvar(email, nome, sobrenome, nascimento, sexo, fone,
                        especialidade, cep, numero, uf, funcao);

                if (sucesso) {
                    emailEditText.setText("");
                    nomeEditText.setText("");
                    sobrenomeEditText.setText("");
                    nascimentoEditText.setText("");
                    mascRadioButton.setSelected(false);
                    RadioButton femRadioButton = (RadioButton) findViewById(R.id.femRadioButton);
                    femRadioButton.setSelected(false);
                    foneEditText.setText("");
                    especialidadeEditText.setText("");
                    cepEditText.setText("");
                    numeroEditText.setText("");
                    ufSpinner.setSelection(0);
                    funcaoSpinner.setSelection(0);

                    Snackbar.make(v, "Usuário Salvo!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {
                    Snackbar.make(v, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_contatos) {

        }

        /*if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
