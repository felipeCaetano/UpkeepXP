package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpKeepDataBaseContract;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpkeepDbHelper;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.CorEnum;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.ElementEnum;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio.Unicornio;

/**
 * Created by anton on 16/02/2018.
 * DAO do objeto Unicornio:
 * Salva e Atualiza, Deleta, Busca
 */

public class UnicornioDAO {
    private final UpkeepDbHelper upkeepDbHelper;

    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.UnicorniosTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.UnicorniosTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.UnicorniosTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UnicorniosTable.COLUMN_NAME_PESO + DOUBLE_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UnicorniosTable.COLUMN_NAME_ALTURA + DOUBLE_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UnicorniosTable.COLUMN_NAME_COR + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UnicorniosTable.COLUMN_NAME_ELEMENTO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UnicorniosTable.COLUMN_NAME_GENENRO + TEXT_TYPE+ " )";
    

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.UnicorniosTable.TABLE_NAME;

    /**
     * Construtor do objeto UnicornioDAO que instancia objetos da Classe UpKeepDbHelper
     * utilizados para persistência
     * @param context
     */

    public UnicornioDAO(Context context){
        
        this.upkeepDbHelper = new UpkeepDbHelper(context);
    }

    /**
     * Classe de UpKeepDbHelper que cria a tabela de acordo com o atributo SQL_CREATE_ENTRIES da
     * linha 29
     * @return
     */

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public static String deleteMyTable() { return SQL_DELETE_ENTRIES; }


    /**
     * Salva no banco os valores do registro
     * @param unicornio
     * @return
     */
    public Boolean salva(Unicornio unicornio){
        long id = unicornio.getId();
        SQLiteDatabase dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("Nome", unicornio.getNome());
            values.put("Altura", unicornio.getAltura());
            values.put("Peso", unicornio.getPeso());
            values.put("Cor", unicornio.getCor());
            values.put("Elemento", unicornio.getElemento());
            values.put("Genero", unicornio.getGenero());
            //Atualiza um unicornio
            if (id!=0){
                String _id = String.valueOf(id);
                String[] whereArgs = new String[]{_id};
                return dbWriter.update(UpKeepDataBaseContract.UnicorniosTable.TABLE_NAME, values, "_id=?", whereArgs)>0;
                //Salva novo unicórnio
            }else{
                Log.w("Banco", "Sucesso");
                return dbWriter.insert(UpKeepDataBaseContract.UnicorniosTable.TABLE_NAME,"",values)>0;
            }
        }finally {
            dbWriter.close();
        }
    }

    /**
     * Deleta do banco o um registro com id específico
     * @param unicornio
     * @return
     */
    public Boolean delete(Unicornio unicornio){
        SQLiteDatabase dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            return dbWriter.delete(UpKeepDataBaseContract.UnicorniosTable.TABLE_NAME,
                    "_id=?", new String[]{String.valueOf(unicornio.getId())})>0;
        }finally {
            dbWriter.close();
        }
    }

    /**
     * Busca todos os unicornios registrados
     * @return
     */

    public List<Unicornio> findAll(){
        SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor cursor = dbReader.query(UpKeepDataBaseContract.UnicorniosTable.TABLE_NAME,
                    null,null,null,null,null,null);
            return toList(cursor);
        }finally {
            dbReader.close();
        }
    }

    /**
     * Cria uma lista de unicornios de acordo os dados dos registros
     * @param c
     * @return
     */
    private List<Unicornio> toList(Cursor c){
        List<Unicornio> unicornios = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                Unicornio unicornio = new Unicornio();
                unicornios.add(unicornio);
                unicornio.setId(c.getInt(c.getColumnIndex("_id")));
                unicornio.setNome(c.getString(c.getColumnIndex("Nome")));
                unicornio.setPeso(c.getDouble(c.getColumnIndex("Peso")));
                unicornio.setAltura(c.getDouble(c.getColumnIndex("Altura")));
                unicornio.setCor(c.getString(c.getColumnIndex("Cor")));
                unicornio.setElemento(c.getString(c.getColumnIndex("Elemento")));
                unicornio.setGenero(c.getString(c.getColumnIndex("Genero")));
            }while(c.moveToNext());
        }
        return unicornios;
    }

    /**
     * Verifica um determnado unicornio pelo nome
     * @param nome
     * @return
     */
    public boolean exists(String nome){
        Cursor c = null;
        SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            c = dbReader.query("unicornios", null, "nome=?",
                    new String[]{nome}, null, null, null, null);
            return c.getCount()>0;
        }finally {
            assert c != null; //foi colocado pelo AS - verificar sobre asserts
            c.close();
            dbReader.close();
        }
    }

    /**
     * Executa comando sql
     * @param sql
     */
    public void execSQL(String sql){
        SQLiteDatabase dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            dbWriter.execSQL(sql);
        }finally {
            dbWriter.close();
        }
    }

    /**
     * Executa comando sql recebendo objeto como arguemtno adicional
     * @param sql
     * @param args
     */
    public void execSQL(String sql, Object[] args){
        SQLiteDatabase dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            dbWriter.execSQL(sql, args);
        }finally {
            dbWriter.close();
        }
    }

    public Unicornio findById(long id){

    SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
    try{
        Cursor c =
        dbReader.query(UpKeepDataBaseContract.UnicorniosTable.TABLE_NAME,null,"_id=?",
                new String[]{String.valueOf(id)},null,null,null);
        List<Unicornio> lista = toList(c);
        return lista.get(0);
       }
       finally{
            dbReader.close();
            }
    }

}
