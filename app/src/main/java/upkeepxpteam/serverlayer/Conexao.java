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

    private static OutputStreamWriter outputStreamWriter;
    private static URL url;
    private static HttpURLConnection connection = null;
    private static InputStream inputStream;
    private static BufferedReader bufferedReader;
    private static String linha;
    private static StringBuffer resposta;

    public static String postDados(String urlUsuario, String parametroUsuario) {

        try
        {   url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();
            //configurar conexão
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length","" +
                    Integer.toString(parametroUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language","pt-BR");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            outputStreamWriter = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            outputStreamWriter.write(parametroUsuario);
            outputStreamWriter.flush();

            //obter informação
            inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }
            bufferedReader.close();

            return resposta.toString();

        } catch (Exception err) {
            return  err.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
