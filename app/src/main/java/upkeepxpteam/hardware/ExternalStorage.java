package upkeepxpteam.hardware;

import android.content.ContentProvider;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Felipe on 08/12/2017.
 */

public class ExternalStorage {

    private File arquivoFoto = null;
    private ContentProvider contentResolver;

    public File criarImagem() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pasta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imagem = new File(pasta.getPath() + File.separator + "upkeep_" + timeStamp + ".jpg");
        return imagem;
    }
}