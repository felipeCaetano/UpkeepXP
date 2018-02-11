package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpKeepDataBaseContract;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpkeepDbHelper;

/**
 * Created by Felipe on 27/05/2017.
 * DAO da classe Equipamento
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
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_DESCRICAO + TEXT_TYPE+ COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_DEFEITO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipamentosTable.COLUMN_NAME_STATUS + TEXT_TYPE + " )";

    /**
     * Construtor do objeto EquipamentoDAO que instancia objetos da Classe UpKeepDbHelper
     * utilizados para persistência
     * @param context
     */

    public EquipamentoDAO(Context context){
        this.upkeepDbHelper = new UpkeepDbHelper(context);
        this.dbWriter = upkeepDbHelper.getWritableDatabase();
        this.dbReader = upkeepDbHelper.getReadableDatabase();
    }

    /**
     * Classe de UpKeepDbHelper que cria a tabela de acordo com o atributo SQL_CREATE_ENTRIES da
     * linha 29
     * @return
     */

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }


    /**
     * Salva no banco os valores do registro
     * @param equipamento
     * @return
     */
    public Boolean salva(Equipamento equipamento){
        long id = equipamento.getId();
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put("Equipamento", equipamento.getNome());
            values.put("Codigo", equipamento.getCodigo());
            values.put("Modelo", equipamento.getModelo());
            values.put("Fabricante", equipamento.getFabricante());
            values.put("Defeito", equipamento.getDefeito());
            values.put("Tipo", equipamento.getTipo());
            values.put("Status", equipamento.getStatus());
            values.put("Descricao", equipamento.getDescricao());
            if (id!=0){
                String _id = String.valueOf(id);
                String[] whereArgs = new String[]{_id};
                return dbWriter.update(UpKeepDataBaseContract.EquipamentosTable.TABLE_NAME, values, "_id=?", whereArgs)>0;
            }else{
                Log.w("Banco", "Sucesso");
                return dbWriter.insert(UpKeepDataBaseContract.EquipamentosTable.TABLE_NAME,"",values)>0;
            }
        }finally {
            dbWriter.close();
        }
    }

    /**
     * Deleta do banco o um registro com id específico
     * @param equipamento
     * @return
     */
    public Boolean delete(Equipamento equipamento){
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            return dbWriter.delete(UpKeepDataBaseContract.EquipamentosTable.TABLE_NAME,
                    "_id=?", new String[]{String.valueOf(equipamento.getId())})>0;
        }finally {
            dbWriter.close();
        }
    }

    /**
     * Busca todos os equipamentos registrados
     * @return
     */

    public List<Equipamento> findAll(){
        dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor cursor = dbReader.query(UpKeepDataBaseContract.EquipamentosTable.TABLE_NAME,
                    null,null,null,null,null,null);
            return toList(cursor);
        }finally {
            dbReader.close();
        }
    }

    /**
     * Cria uma lista de equipamentos de acordo os dados dos registros
     * @param c
     * @return
     */
    private List<Equipamento> toList(Cursor c){
        List<Equipamento> equipamentos = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                Equipamento equipamento = new Equipamento();
                equipamentos.add(equipamento);
                equipamento.setId(c.getLong(c.getColumnIndex("_id")));
                equipamento.setNome(c.getString(c.getColumnIndex("Equipamento")));
                equipamento.setCodigo(c.getString(c.getColumnIndex("Codigo")));
                equipamento.setModelo(c.getString(c.getColumnIndex("Modelo")));
                equipamento.setFabricante(c.getString(c.getColumnIndex("Fabricante")));
                equipamento.setDefeito(c.getString(c.getColumnIndex("Defeito")));
                equipamento.setTipo(c.getString(c.getColumnIndex("Tipo")));
                equipamento.setStatus(c.getString(c.getColumnIndex("Status")));
                equipamento.setDescricao(c.getString(c.getColumnIndex("Descricao")));
            }while(c.moveToNext());
        }
        return equipamentos;
    }

    /**
     * Verifica um determnado equipamento pelo nome
     * @param nome
     * @return
     */
    public boolean exists(String nome){
        Cursor c = null;
        dbReader = upkeepDbHelper.getReadableDatabase();
        try{
             c = dbReader.query("equipamentos", null, "nome=?",
                     new String[]{nome}, null, null, null, null);
            return c.getCount()>0;
        }finally {
            assert c != null; //foi colocado pelo AS - verificar sobre asserts
            c.close();
        }
    }

    /**
     * Executa comando sql
     * @param sql
     */
    public void execSQL(String sql){
        dbWriter = upkeepDbHelper.getWritableDatabase();
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
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            dbWriter.execSQL(sql, args);
        }finally {
            dbWriter.close();
        }
    }
}
