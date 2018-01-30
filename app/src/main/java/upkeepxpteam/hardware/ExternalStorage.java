package upkeepxpteam.hardware;

import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Felipe on 08/12/2017.
 */

public class ExternalStorage {


    public File criarArquivo() throws IOException {
        /*
        Cria arquivo de foto chamado upkeep_ acrescido da data e hora da imagem para torna-la unica
        feito isso o arquivo é salvo na galeria em formato jpg
         */
        String timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pasta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagem =  new File(pasta.getPath() + File.separator+ "upkeep_" + timeStamp+ ".jpg");
        return imagem;
    }
}