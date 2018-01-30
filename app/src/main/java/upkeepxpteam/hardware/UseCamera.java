package upkeepxpteam.hardware;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;


public class UseCamera extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 0;
    private static final int PERMISSAO_REQUEST = 4; //pode ser >=0
    private ImageView imageView;
    private Bitmap bitmap;
    private File foto = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // trecho adiciona permissão de gravar arquivos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_CAMERA:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    tirarFoto();

                }
            }

        }
    }

    public void tirarFoto() {

        /*
        Cria um ExternalStorage que guardará a foto na galeria.
        Ao ser criado com sucesso o arquivo de foto é inserido na Intent passada ao método e então o
        metédo inicia a intent q deve ser atendida pelo app nativo de fotos.
        o resutlado é tratato em onActivityResult
         */

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try{
            ExternalStorage externalStorage = new ExternalStorage();
            foto = externalStorage.criarArquivo();
        }catch (IOException e) {   // Manipulação em caso de falha de criação do arquivo
            e.printStackTrace();
        }
        if(foto!= null) {
            Uri photoURI= FileProvider.getUriForFile(getBaseContext(),getBaseContext().getApplicationContext().getPackageName() + ".provider", foto);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== 0 && resultCode== RESULT_OK) {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(foto)));
        }
        //coloca a imagem no ImageView.
        if(data!=null){
            Bundle bundle = data.getExtras();
            if(bundle!=null){
                bitmap = (Bitmap) bundle.get("data");
                //return bitmap criando um metodo de saida
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
