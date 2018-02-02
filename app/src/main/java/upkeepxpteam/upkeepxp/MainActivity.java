package upkeepxpteam.upkeepxp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

import upkeepxpteam.atividadediaria.atividadediariaactivity.AtividadeDiariaActivity;
import upkeepxpteam.equipamento.equipamentoactivity.BuscarEquipActivity;
import upkeepxpteam.equipes.gui.EquipesActivity;
import upkeepxpteam.usuario.usuarioactivity.CadastraUsuarioActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String nome;
    private String segundoNome;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap imageUser = null;

        //recupera dados vindos da intent autentication
        Intent autentication = getIntent();
        nome = autentication.getStringExtra("nome");
        segundoNome = autentication.getStringExtra("snome");
        email = autentication.getStringExtra("email");


        //recupera dados vindos da intent tirarfotos
        Intent tirarFotos = getIntent();
        if(tirarFotos.getExtras()!=null){
            nome = tirarFotos.getStringExtra("nome");
            segundoNome = tirarFotos.getStringExtra("snome");
            email = tirarFotos.getStringExtra("email");
            byte[] imagemBytes = tirarFotos.getByteArrayExtra("Bitmap");
            if(imagemBytes!=null){
                ByteArrayInputStream imageStream = new ByteArrayInputStream(imagemBytes);
                imageUser = BitmapFactory.decodeStream(imageStream);
            }
        }

        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View hdview = navigationView.getHeaderView(0);
        ImageView fotoUser = hdview.findViewById(R.id.img_profile);

        fotoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tirarFoto = new Intent(MainActivity.this, TirarFotoActivity.class);
                tirarFoto.putExtra("nome", nome);
                tirarFoto.putExtra("snome", segundoNome);
                tirarFoto.putExtra("email", email);
                startActivity(tirarFoto);
            }
        });
        setNavUserName(navigationView, nome, segundoNome);
        setUserEmail(navigationView, email);
        setUserProfileImage(navigationView, imageUser);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUserProfileImage(NavigationView navView, Bitmap image) {
        if(image!=null){

            View headerView = navView.getHeaderView(0);
            ImageView imageProfile = headerView.findViewById(R.id.img_profile);
            imageProfile.setImageBitmap(image);
        }
        else{
            View headerView = navView.getHeaderView(0);
            ImageView imageProfile = headerView.findViewById(R.id.img_profile);
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            imageProfile.setImageBitmap(bm);
        }
    }

    private void setUserEmail(NavigationView navView, String email) {

        View headerView = navView.getHeaderView(0);
        TextView userEmail = headerView.findViewById(R.id.txt_nav_userEmail);
        userEmail.setText(email);
        userEmail.setTextColor(getResources().getColor(R.color.textColorPrimary));
    }

    private void setNavUserName(NavigationView navView, String nome, String segundoNome) {

        View headerView = navView.getHeaderView(0);
        TextView userName = headerView.findViewById(R.id.txt_nav_userName);
        userName.setText(String.format("%s %s", nome, segundoNome));
        userName.setTextColor(getResources().getColor(R.color.textColorPrimary));
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
        Intent intent;

        escolheItemMenu(id);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void escolheItemMenu(int id) {
        Intent intent;
        if (id == R.id.nav_agenda) {
            // Handle the agenda action
            intent = new Intent(MainActivity.this, AtividadeDiariaActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contatos) {



        } else if (id == R.id.nav_equipes) {
            intent = new Intent(MainActivity.this, EquipesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_membros) {
            intent =  new Intent(MainActivity.this, CadastraUsuarioActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_registro) {
            intent =  new Intent(MainActivity.this, BuscarEquipActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_sobre) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
