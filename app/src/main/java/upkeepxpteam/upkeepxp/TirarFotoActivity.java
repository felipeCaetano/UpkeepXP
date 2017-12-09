package upkeepxpteam.upkeepxp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TirarFotoActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnSalvar;
    private FloatingActionButton fabGaleria;
    private FloatingActionButton fabTirarFoto;
    private TextView txtNome;
    private TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent tirarFoto = getIntent();
        String nome = tirarFoto.getStringExtra("nome");
        String sobrenome = tirarFoto.getStringExtra("snome");
        String email = tirarFoto.getStringExtra("email");
        setContentView(R.layout.activity_tirar_foto);

        imageView = findViewById(R.id.img_user);
        txtNome = findViewById(R.id.txt_foto_userName);
        txtNome.setText(nome + " " + sobrenome);
        txtEmail = findViewById(R.id.txt_foto_userMail);
        txtEmail.setText(email);

        fabGaleria = findViewById(R.id.fab_abrirGaleria);
        //setar listener para chamar a galeria
        fabGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chamar intent da galeria abreGaleria();
            }
        });

        fabTirarFoto = findViewById(R.id.fab_tirarFoto);
        //setar listener para tirar foto
        fabTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chamar intente de tirar foto com tiraFoto();
            }
        });

        btnSalvar = findViewById(R.id.btn_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Salvar Imagem no banco de dados
            }
        });
    }
}
