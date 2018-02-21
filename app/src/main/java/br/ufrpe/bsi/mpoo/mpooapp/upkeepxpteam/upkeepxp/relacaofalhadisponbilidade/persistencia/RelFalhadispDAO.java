package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.relacaofalhadisponbilidade.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpKeepDataBaseContract;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpkeepDbHelper;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.relacaofalhadisponbilidade.dominio.FalhaDisponibilidade;

/**
 * Created by anton on 16/02/2018.
 * Utilizado para resgatar falhas de disponibilidades
 */

public class RelFalhadispDAO
{
    private final UpkeepDbHelper upkeepDbHelper;

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.RelacaoEquipFalhasTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.RelacaoEquipFalhasTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.RelacaoEquipFalhasTable.COLUMN_NAME_ID_EQUIPAMENTO_ATUAL + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.RelacaoEquipFalhasTable.COLUMN_NAME_ID_EQUIPAMENTO_PROXIMO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.RelacaoEquipFalhasTable.COLUMN_NAME_TIPO_ASSOIACAO + TEXT_TYPE + " ) ";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.RelacaoEquipFalhasTable.TABLE_NAME;

    public RelFalhadispDAO(Context context) {
        this.upkeepDbHelper = new UpkeepDbHelper(context);
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public static String deleteMyTable() { return SQL_DELETE_ENTRIES; }

    public boolean salvaDisponibilidade(Equipamento atual, Equipamento prox, String ligar){
        SQLiteDatabase dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            ContentValues cv = new ContentValues();
            cv.put("idEquipamentoAtual", atual.getId());
            cv.put("idEquipamentoProximo", prox.getId());
            cv.put("tipoAssociacao", ligar); //entender esse parametro
            return dbWriter.insert(UpKeepDataBaseContract.RelacaoEquipFalhasTable.TABLE_NAME,null,cv)>0;
        }
        finally {
            dbWriter.close();
        }
    }

    public List<FalhaDisponibilidade> findAll(){
        SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor cursor = dbReader.query(UpKeepDataBaseContract.RelacaoEquipFalhasTable.TABLE_NAME,
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
    private List<FalhaDisponibilidade> toList(Cursor c){
        List<FalhaDisponibilidade> falhaDisponibilidades = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                FalhaDisponibilidade falhaDisponibilidade = new FalhaDisponibilidade();
                falhaDisponibilidades.add(falhaDisponibilidade);
                //falhaDisponibilidade.setAtual(c.getExtras("idEquipamentoAtual"));
                //result.add(cursor.getColumnIndex("idEquipamentoProximo"));
                //result.add(cursor.getString(cursor.getColumnIndex("tipoAssociacao")));
            }while(c.moveToNext());
        }
        return falhaDisponibilidades;
    }

}
