package upkeepxpteam.atividadediaria.atividadediariaDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.atividadediaria.atividadediariabase.AtividadeDiaria;
import upkeepxpteam.persistence.UpKeepDataBaseContract;
import upkeepxpteam.persistence.UpkeepDbHelper;

/**
 * Created by Felipe on 20/12/2017
 * Classe AtividadeDAO para CRUD de Atividades Diarias.
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
    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;
    private final UpkeepDbHelper upkeepDbHelper;

    public AtividadeDiariaDAO(Context ctx){
        this.upkeepDbHelper = new UpkeepDbHelper(ctx);
        this.dbWriter = upkeepDbHelper.getWritableDatabase();
        this.dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public Boolean salva(AtividadeDiaria atividadeDiaria){
        ContentValues cv = new ContentValues();

        cv.put("Nome", atividadeDiaria.getNome());
        cv.put("Data", atividadeDiaria.getData());
        cv.put("Equipe", atividadeDiaria.getEquipeNome());
        cv.put("Local", atividadeDiaria.getLocal());
        cv.put("Descricao", atividadeDiaria.getDescricao());
        cv.put("Situação",atividadeDiaria.getSituacao());

        return dbWriter.insert(UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME,null,cv)>0;
       }

    public void buscarAtividade(AtividadeDiaria atividadeDiaria){

    }

    public List<AtividadeDiaria> buscarTodasAtividades(){
        dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor cursor = dbReader.query
                    (UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME,
                            null,null,null,null,null,null);
            return toList(cursor);
        }finally {
            dbReader.close();
        }
    }

    public void atualizaAtividade(AtividadeDiaria atividadeDiaria){

    }

    public Boolean destroiAtividade(AtividadeDiaria atividadeDiaria){
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            return dbWriter.delete
                    (UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME,
                            "_id=?", new String[]{String.valueOf(atividadeDiaria.getId())})>0;
        }finally {
            dbWriter.close();
        }

    }

    public void destroiTodasAtividades(){
        //DROP TABLE UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME;
        dbWriter.execSQL(SQL_DELETE_ENTRIES);
    }

    private List<AtividadeDiaria> toList(Cursor c){
        List<AtividadeDiaria> atividadeDiarias = new ArrayList<>();
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
                atividadeDiaria.setEquipeNome(c.getString(c.getColumnIndex("Equipe")));
                atividadeDiaria.setSituacao(c.getString(c.getColumnIndex("Situacao")));
            }while (c.moveToNext());
        }
        return atividadeDiarias;
    }

    public List<AtividadeDiaria> selecionaAtividaPorDia(String date){
            dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor c = dbReader.query(UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME,null,"data='"+date+"'",null,null,null,null);
            return toList(c);
        }finally {
            Log.w("Banco", "Insucesso");
            dbReader.close();
        }
    }   //em criação

}
