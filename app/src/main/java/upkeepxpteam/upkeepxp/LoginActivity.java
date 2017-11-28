package upkeepxpteam.upkeepxp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import upkeepxpteam.serverlayer.Conexao;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private TextView esqueceuSenha;
    private Button logar;

    //necessarios para conexão com a classe Conexao
    private String url = "";
    private String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.edt_email);
        senha = findViewById(R.id.edt_password);
        logar = findViewById(R.id.btn_logar);
        esqueceuSenha = findViewById(R.id.txt_forgotpass);

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
                    }
                    else {
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
            if(results.contains("login_ok")){
                Intent autentication = new Intent(LoginActivity.this,TelaInicialActivity.class);
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
