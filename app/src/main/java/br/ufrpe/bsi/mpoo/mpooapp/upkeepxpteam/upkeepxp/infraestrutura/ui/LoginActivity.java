package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.Validacao;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.serverlayer.Conexao;
import upkeepxpteam.upkeepxp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private String url = "";//necessarios para conexão com a classe Conexao
    private String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_password);
        Button logar = findViewById(R.id.btn_logar);
        TextView esqueceuSenha = findViewById(R.id.txt_forgotpass);

        //Para recuperar a senha
        esqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent chamaCadastro = new Intent(LoginActivity.this, TelaCadastroActivity.class);
                startActivity(chamaCadastro);
            }
        });

        //Para logar no sistema
        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //snippet para verificar o status da conexão
                ConnectivityManager cm =
                        (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                //aqui pode gerar exception??
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (isConnected){

                    String emailUser = email.getText().toString();
                    String senhaUser = senha.getText().toString();

                    if(emailUser.isEmpty() || senhaUser.isEmpty()){
                        Toast.makeText(LoginActivity.this, getString(R.string.campo_vazio), Toast.LENGTH_SHORT).show();

                    } else if (new Validacao().validarEmail(emailUser)) {
                        Toast.makeText(LoginActivity.this, getString(R.string.campo_vazio), Toast.LENGTH_SHORT).show();

                    } else {
                        url = "http://179.106.9.69:8090/upkeepxp/login/logar.php";
                        parametros = "email=" + emailUser +"&senha=" + senhaUser;
                        new SolicitaDados().execute(url);
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, getString(R.string.connection_failed), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    Usa asyncTasks!
    A classe interna a seguir conecta a internet e envia informações em segundo plano
     */
    private class SolicitaDados extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... url) {

            return Conexao.postDados(url[0], parametros);
        }

        //exibe os resultados
        @Override
        protected void onPostExecute(String results){

            //Criado para tratar a nova String vinda do Servidor;

            String[] resultado = results.split(", ");

            if(resultado[0].contains("login_ok")){
                //exibir toast apenas para verificar os dados q chegam do servidor
                Intent autentication = new Intent(LoginActivity.this,MainActivity.class);
                autentication.putExtra("nome",resultado[1]);
                autentication.putExtra("snome",resultado[2]);
                autentication.putExtra("email",resultado[3]);
                autentication.putExtra("acesso",resultado[4]);
                startActivity(autentication);
            }
            else {
                Toast.makeText(LoginActivity.this, getString(R.string.userPass_failed), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
