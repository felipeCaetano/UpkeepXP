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
    private final UpkeepDbHelper upkeepDbHelper;

    /**
     * Instancia os objetos que serão utilizados para leitura e gravação no banco de dados, receben-
     * do um contexto como parâmetro
     * @param ctx
     */
    public EquipeDAO(Context ctx) {
        this.upkeepDbHelper = new UpkeepDbHelper(ctx);
    }

    /**
     * Retorna a constante com as cláusulas para criação da tabela
     * @return
     */
    public static String createMyTable() {
        return SQL_CREATE_ENTRIES;
    }

    /**
     * Salva no banco de dados a equipe pelo NOME e também relaciona na tabela EquipesTableID
     * os usuários a uma determinada equipe, passando o id da equipe e os ids dos usuários
     * @param equipe
     */

    public void equipeSave(Equipe equipe) {

        SQLiteDatabase dbWriter = upkeepDbHelper.getWritableDatabase();
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

    /**
     * Edita as equipes gravadas no banco de dados.  Exclui a equipe (idEquipeEdit) da tabela
     * EquipesTableId, atualiza a equipe (idEquipeEdit) e reconstrói a entrada na tabela
     * EquipesTableId com as novas informações.
     * @param equipe
     * @param idEquipeEdit
     */

    public void equipeEditar(Equipe equipe, int idEquipeEdit){
        SQLiteDatabase dbWriter = upkeepDbHelper.getWritableDatabase();
        dbWriter.delete(UpKeepDataBaseContract.EquipesTableID.TABLE_NAME,
                "idEquipe = ?", new String[]{String.valueOf(idEquipeEdit)});
        ContentValues cv = new ContentValues();
        cv.put("Nome", equipe.getNome());
        dbWriter.update(UpKeepDataBaseContract.EquipesTable.TABLE_NAME,cv,
                "_id = ?", new String[]{String.valueOf(idEquipeEdit)});
        for (Usuario usuario : equipe.getUsers()) {
            ContentValues contentValues = new ContentValues();
            int idEquipe = getIdEquipe(equipe.getNome());
            contentValues.put("idEquipe", idEquipe);
            contentValues.put("idUsuario", usuario.getId());
            dbWriter.insert(UpKeepDataBaseContract.EquipesTableID.TABLE_NAME, null, contentValues);
        }
    }

    /**
     * Faz consulta no banco de dados para retornar um cursor com dados das equipes cadastradas
     * @return
     */

    public List<Equipe> buscarTodasEquipes() {
        String sql = "SELECT * FROM equipe";
        SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
        Cursor cursor = dbReader.rawQuery(sql, null);
        return listarEquipe(cursor);
    }

    /**
     * Recebe o cursor obtido em buscarTodasEquipes e retorna uma lista de equipes
     * @param cursor
     * @return
     */

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

    /**
     * Exclui do banco uma equipe pelo nome
     * @param nome
     */
    public void excluirEquipe(String nome) {

        SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
        int id = getIdEquipe(nome);
        dbReader.delete(UpKeepDataBaseContract.EquipesTableID.TABLE_NAME, "idEquipe = ?", new String[]{String.valueOf(id)});
        dbReader.delete(UpKeepDataBaseContract.EquipesTable.TABLE_NAME, "Nome = ?", new String[]{nome});

    }

    /**
     * Executa uma consulta no banco pelo nome da equipe e retorna o id da respectiva equipe
     * @param nomeEquipe
     * @return
     */
    public int getIdEquipe(String nomeEquipe) {
        String sql = "SELECT * FROM equipe WHERE Nome = '" + nomeEquipe + "'";
        SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
        Cursor cursor = dbReader.rawQuery(sql, null);
        return listarIdEquipe(cursor);
    }

    /**
     * @param cursor
     * @return
     */
    public int listarIdEquipe(Cursor cursor) {
        int id = -1;
        while (cursor.moveToNext()) {
            int idUsuario = cursor.getInt(cursor.getColumnIndex("_id"));
            id = idUsuario;
        }
        return id;
    }
}