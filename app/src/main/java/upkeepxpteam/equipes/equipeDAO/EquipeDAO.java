package upkeepxpteam.equipes.equipeDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.equipes.equipebase.Equipe;
import upkeepxpteam.persistence.UpKeepDataBaseContract;
import upkeepxpteam.persistence.UpkeepDbHelper;
import upkeepxpteam.usuario.usuariobase.Usuario;


public class EquipeDAO {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.EquipesTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.EquipesTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.EquipesTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipesTable.COLUMN_NAME_USUARIOS + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME;
    private final SQLiteDatabase dbWriter;
    private final SQLiteDatabase dbReader;

    public EquipeDAO(Context ctx){
        UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(ctx);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public Boolean equipeSave(Equipe equipe){
        ContentValues cv = new ContentValues();

        cv.put("Nome", equipe.getNome());
        cv.put("Operario", equipe.getUsuariosNomes());

        return dbWriter.insert(UpKeepDataBaseContract.EquipesTable.TABLE_NAME,null,cv)>0;
    }

    public List<Equipe> buscarTodasEquipes() {
        String sql = "SELECT * FROM equipe";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql, null);
        return listarequipe(cursor);
    }

    public List<Equipe> listarequipe(Cursor cursor){
        List<Equipe> result = new ArrayList<>();
        while (cursor.moveToNext()){
            Equipe equipe = new Equipe();
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            equipe.setNome(nome);
            String operarios = cursor.getString(cursor.getColumnIndex("Operario"));
            equipe.setUsuario(operarios);
            result.add(equipe);
        }
        return result;
    }

    public void excluirEquipe(String nome){

        SQLiteDatabase db = dbReader;
        db.delete(UpKeepDataBaseContract.EquipesTable.TABLE_NAME,"Nome = ?", new String[]{nome});

    }

}
