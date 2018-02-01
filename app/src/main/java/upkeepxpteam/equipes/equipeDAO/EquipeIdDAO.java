package upkeepxpteam.equipes.equipeDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.equipes.equipebase.Equipe;
import upkeepxpteam.equipes.equipebase.EquipeId;
import upkeepxpteam.persistence.UpKeepDataBaseContract;
import upkeepxpteam.persistence.UpkeepDbHelper;


public class EquipeIdDAO {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.EquipesTableID.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.EquipesTableID._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.EquipesTableID.COLUMN_NAME_ID_EQUIPE + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipesTableID.COLUMN_NAME_ID_USUARIO + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.EquipesTableID.TABLE_NAME;
    private final SQLiteDatabase dbWriter;
    private final SQLiteDatabase dbReader;

    public EquipeIdDAO(Context ctx){
        UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(ctx);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public void equipeSave(EquipeId equipeId){
        ContentValues cv = new ContentValues();
        cv.put("Nome", equipeId.getIdEquipe());
        cv.put("Operario", equipeId.getIdUsuario());
        dbWriter.insert(UpKeepDataBaseContract.EquipesTable.TABLE_NAME,null,cv);
    }

    public List<EquipeId> buscarTodasEquipes() {
        String sql = "SELECT * FROM equipeid";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql, null);
        return listarEquipeId(cursor);
    }

    public List<EquipeId> listarEquipeId(Cursor cursor){
        List<EquipeId> result = new ArrayList<>();
        while (cursor.moveToNext()){
            EquipeId equipeId = new EquipeId();
            int idUsuario = cursor.getInt(cursor.getColumnIndex("idUsuario"));
            equipeId.setIdUsuario(idUsuario);
            int idEquipe = cursor.getInt(cursor.getColumnIndex("idEquipe"));
            equipeId.setIdEquipe(idEquipe);
            result.add(equipeId);
        }
        return result;
    }

}
