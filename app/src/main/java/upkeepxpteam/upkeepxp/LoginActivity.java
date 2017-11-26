package upkeepxpteam.upkeepxp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private TextView esqueceuSenha;
    private Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_password);
        logar = findViewById(R.id.btn_logar);
        esqueceuSenha = findViewById(R.id.txt_forgotpass);

        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent chamaCadastro = new Intent(LoginActivity.this, TelaCadastroActivity.class);
                startActivity(chamaCadastro);
            }
        });



    }
}
