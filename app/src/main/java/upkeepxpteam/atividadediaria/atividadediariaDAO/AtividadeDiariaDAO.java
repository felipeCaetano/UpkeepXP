package upkeepxpteam.atividadediaria.atividadediariaDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import upkeepxpteam.atividadediaria.atividadediariabase.AtividadeDiaria;
import upkeepxpteam.persistence.UpKeepDataBaseContract;
import upkeepxpteam.persistence.UpkeepDbHelper;

/**
 * Created by Felipe on 20/12/2017.
 */

public class AtividadeDiariaDAO {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.AtividadeDiariaTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.AtividadeDiariaTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.AtividadeDiariaTable.COLUMN_NAME_DATA + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.AtividadeDiariaTable.COLUMN_NAME_USUARIOS + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.AtividadeDiariaTable.COLUMN_NAME_LOCAL + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.AtividadeDiariaTable.COLUMN_NAME_DESCRICAO + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME;
    private final SQLiteDatabase dbWriter;
    private final SQLiteDatabase dbReader;

    public AtividadeDiariaDAO(Context ctx){
        UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(ctx);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public Boolean saveAtividade(AtividadeDiaria atividadeDiaria){
        ContentValues cv = new ContentValues();

        cv.put("Nome", atividadeDiaria.getNome());
        //cv.put("Data", atividadeDiaria.getData());
        cv.put("Usuarios", atividadeDiaria.getUsuariosNomes());
        cv.put("Local", atividadeDiaria.getLocal());
        cv.put("Descricao", atividadeDiaria.getDescricao());

        return dbWriter.insert(UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME,null,cv)>0;
       }

    public void findAtividade(AtividadeDiaria atividadeDiaria){

    }

    public void findAllAtividade(){

    }

    public void atualizaAtividade(AtividadeDiaria atividadeDiaria){

    }

    public void destroiAtividade(AtividadeDiaria atividadeDiaria){

    }

    public void destroiAllAtividade(){

    }

    private List<AtividadeDiaria> toList(Cursor c){
        List<AtividadeDiaria> atividadeDiarias = new ArrayList<AtividadeDiaria>();
        if(c.moveToFirst()){
            do{
                AtividadeDiaria atividadeDiaria = new AtividadeDiaria();
                atividadeDiarias.add(atividadeDiaria);
                //recupera os dados de cada atividade diária
                atividadeDiaria.setId(c.getInt(c.getColumnIndex("_id")));
                atividadeDiaria.setNome(c.getString(c.getColumnIndex("Nome")));
                atividadeDiaria.setData(c.getString(c.getColumnIndex("Data")));
                atividadeDiaria.setLocal(c.getString(c.getColumnIndex("Local")));
                atividadeDiaria.setDescricao(c.getString(c.getColumnIndex("Descricao")));
                //atividadeDiaria.setUsuarios(c.getInt(c.getColumnIndex("_id")));
            }while (c.moveToNext());
        }
        return atividadeDiarias;
    }

    public List<AtividadeDiaria> selectActivitiesbyDay(Calendar calendar){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try{
            Cursor c = dbReader.query(UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME,null,"data=?",new String[]{simpleDateFormat.format(calendar)},null,null,null);
            return toList(c);
        }finally {
            dbReader.close();
        }
    }   //em criação

}
