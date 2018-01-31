package upkeepxpteam.serverlayer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Felipe on 26/11/2017.
 */

public class Conexao {

    private static HttpURLConnection connection = null;

    public static String postDados(String urlUsuario, String parametroUsuario){

<<<<<<< Updated upstream
        try{
            URL url = new URL(urlUsuario);
=======
<<<<<<< HEAD
        try
        {   url = new URL(urlUsuario);
=======
        try{
            URL url = new URL(urlUsuario);
>>>>>>> Dev
>>>>>>> Stashed changes
            connection = (HttpURLConnection) url.openConnection();
            //configurar conexão
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length","" + Integer.toString(parametroUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language","pt-BR");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            outputStreamWriter.write(parametroUsuario);
            outputStreamWriter.flush();

            //obter informação
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuffer resposta = new StringBuffer();

<<<<<<< Updated upstream
            String linha;
            while((linha = bufferedReader.readLine()) != null){
=======
<<<<<<< HEAD
            while ((linha = bufferedReader.readLine()) != null) {
=======
            String linha;
            while((linha = bufferedReader.readLine()) != null){
>>>>>>> Dev
>>>>>>> Stashed changes
                resposta.append(linha);
                resposta.append('\r');
            }
            bufferedReader.close();

            return resposta.toString();

        }catch (Exception err){
            return  null;
        }finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }
}
