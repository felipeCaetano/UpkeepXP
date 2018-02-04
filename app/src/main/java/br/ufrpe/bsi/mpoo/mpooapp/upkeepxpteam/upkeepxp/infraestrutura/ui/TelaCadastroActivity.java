package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import upkeepxpteam.upkeepxp.R;

public class TelaCadastroActivity extends AppCompatActivity {

    private EditText senha;
    private EditText reptSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        senha = findViewById(R.id.edt_senha);
        reptSenha = findViewById(R.id.edt_senharepet);
        Button btSalvar = findViewById(R.id.btn_confirmar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(senha.getText().toString().equals(reptSenha.getText().toString()));
                //chamar metodo para conectar com o servidor e dar update na tabela de login onde o email
                //de usuário está definido
            }
        });
    }
}
