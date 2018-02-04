package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio.Equipe;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.ui.CadastraEquipeActivity;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpKeepDataBaseContract;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpkeepDbHelper;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;


public class EquipeDAO {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.EquipesTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.EquipesTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.EquipesTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipesTable.COLUMN_NAME_USUARIOS + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.EquipesTable.TABLE_NAME;
    private final SQLiteDatabase dbWriter;
    private final SQLiteDatabase dbReader;

    public EquipeDAO(Context ctx) {
        UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(ctx);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public EquipeDAO(CadastraEquipeActivity cadastraEquipeActivity) {
        UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(cadastraEquipeActivity);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable() {
        return SQL_CREATE_ENTRIES;
    }

    public void equipeSave(Equipe equipe) {
        ContentValues cv = new ContentValues();
        cv.put("Nome", equipe.getNome());
        dbWriter.insert(UpKeepDataBaseContract.EquipesTable.TABLE_NAME, null, cv);
        for (Usuario usuario : equipe.getUsers()) {
            ContentValues contentValues = new ContentValues();
            int idEquipe = getIdEquipe(equipe.getNome());
            contentValues.put("idEquipe", idEquipe);
            contentValues.put("idUsuario", usuario.getId());
            dbWriter.insert(UpKeepDataBaseContract.EquipesTableID.TABLE_NAME, null, contentValues);
        }
    }

    public void equipeEditar(Equipe equipe, int idEquipeEdit){
        dbWriter.delete(UpKeepDataBaseContract.EquipesTableID.TABLE_NAME, "idEquipe = ?", new String[]{String.valueOf(idEquipeEdit)});
        ContentValues cv = new ContentValues();
        cv.put("Nome", equipe.getNome());
        dbWriter.update(UpKeepDataBaseContract.EquipesTable.TABLE_NAME,cv,"_id = ?", new String[]{String.valueOf(idEquipeEdit)});
        for (Usuario usuario : equipe.getUsers()) {
            ContentValues contentValues = new ContentValues();
            int idEquipe = getIdEquipe(equipe.getNome());
            contentValues.put("idEquipe", idEquipe);
            contentValues.put("idUsuario", usuario.getId());
            dbWriter.insert(UpKeepDataBaseContract.EquipesTableID.TABLE_NAME, null, contentValues);
        }
    }

    public List<Equipe> buscarTodasEquipes() {
        String sql = "SELECT * FROM equipe";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql, null);
        return listarEquipe(cursor);
    }

    public List<Equipe> listarEquipe(Cursor cursor) {
        List<Equipe> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            Equipe equipe = new Equipe();
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            equipe.setNome(nome);
            int idEquipe = getIdEquipe(nome);
            equipe.setId(idEquipe);
            result.add(equipe);
        }
        return result;
    }

    public void excluirEquipe(String nome) {

        SQLiteDatabase db = dbReader;
        int id = getIdEquipe(nome);
        db.delete(UpKeepDataBaseContract.EquipesTableID.TABLE_NAME, "idEquipe = ?", new String[]{String.valueOf(id)});
        db.delete(UpKeepDataBaseContract.EquipesTable.TABLE_NAME, "Nome = ?", new String[]{nome});

    }

    public int getIdEquipe(String nomeEquipe) {
        String sql = "SELECT * FROM equipe WHERE Nome = '" + nomeEquipe + "'";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql, null);
        return listarIdEquipe(cursor);
    }

    public int listarIdEquipe(Cursor cursor) {
        int id = -1;
        while (cursor.moveToNext()) {
            int idUsuario = cursor.getInt(cursor.getColumnIndex("_id"));
            id = idUsuario;
        }
        return id;
    }
}