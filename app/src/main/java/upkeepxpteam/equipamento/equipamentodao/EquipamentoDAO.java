package upkeepxpteam.equipamento.equipamentodao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.equipamento.equipamentobase.Equipamento;
import upkeepxpteam.persistence.UpKeepDataBaseContract;
import upkeepxpteam.persistence.UpkeepDbHelper;

/**
 * Created by Felipe on 27/05/2017.
 */

public class EquipamentoDAO {

    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;
    private final UpkeepDbHelper upkeepDbHelper;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.EquipamentosTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.EquipamentosTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_CODIGO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_MODELO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_TIPO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_FABRICANTE + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_DESCRICAO + TEXT_TYPE+
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_DEFEITO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_STATUS + TEXT_TYPE + " )";


    public EquipamentoDAO(Context context){
        this.upkeepDbHelper = new UpkeepDbHelper(context);
        this.dbWriter = upkeepDbHelper.getWritableDatabase();
        this.dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    //metodo de inserção ou atualização:
    public long salva(Equipamento equipamento){
        long id = equipamento.getId();
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("nome", equipamento.getNome());
            values.put("codigo", equipamento.getCodigo());
            values.put("modelo", equipamento.getModelo());
            values.put("unidade", equipamento.getFabricante());
            values.put("defeito", equipamento.getDefeito());
            values.put("tipo", equipamento.getTipo());
            values.put("status", equipamento.getStatus());
            values.put("descricao", equipamento.getDescricao());
            if (id!=0){
                String _id = String.valueOf(id);
                String[] whereArgs = new String[]{_id};
                return (long) dbWriter.update("equipamentos", values, "_id=?", whereArgs);
            }else{
                return dbWriter.insert("equipamentos","",values);
            }
        }finally {
            dbWriter.close();
        }
    }

    public int delete(Equipamento equipamento){
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            return dbWriter.delete("equipamentos", "_id=?", new String[]{String.valueOf(equipamento.getId())});
        }finally {
            dbWriter.close();
        }
    }

    public List<Equipamento> findAll(){
        dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor cursor = dbReader.query("equipamentos",null,null,null,null,null,null);
            return toList(cursor);
        }finally {
            dbReader.close();
        }
    }

    private List<Equipamento> toList(Cursor c){
        List<Equipamento> equipamentos = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                Equipamento equipamento = new Equipamento();
                equipamentos.add(equipamento);
                equipamento.setId(c.getLong(c.getColumnIndex("_id")));
                equipamento.setNome(c.getString(c.getColumnIndex("nome")));
                equipamento.setCodigo(c.getString(c.getColumnIndex("codigo")));
                equipamento.setModelo(c.getString(c.getColumnIndex("modelo")));
                equipamento.setFabricante(c.getString(c.getColumnIndex("unidade")));
                equipamento.setDefeito(c.getString(c.getColumnIndex("defeito")));
                equipamento.setTipo(c.getString(c.getColumnIndex("tipo")));
                equipamento.setStatus(c.getString(c.getColumnIndex("status")));
                equipamento.setDescricao(c.getString(c.getColumnIndex("descricao")));
            }while(c.moveToNext());
        }
        return equipamentos;
    }


    public boolean exists(String nome){
        Cursor c = null;
        dbReader = upkeepDbHelper.getReadableDatabase();
        try{
             c = dbReader.query("equipamentos", null, "nome=?", new String[]{nome}, null, null, null, null);
            return c.getCount()>0;
        }finally {
            assert c != null; //foi colocado pelo AS - verificar sobre asserts
            c.close();
        }
    }

    //Executa SQL
    public void execSQL(String sql){
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            dbWriter.execSQL(sql);
        }finally {
            dbWriter.close();
        }
    }

    //Executa SQL
    public void execSQL(String sql, Object[] args){
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            dbWriter.execSQL(sql, args);
        }finally {
            dbWriter.close();
        }
    }
}
