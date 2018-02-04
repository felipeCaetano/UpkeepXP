package upkeepxpteam.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import upkeepxpteam.atividadediaria.atividadediariaDAO.AtividadeDiariaDAO;
import upkeepxpteam.equipamento.equipamentodao.EquipamentoDAO;
import upkeepxpteam.equipes.equipeDAO.EquipeDAO;
import upkeepxpteam.usuario.usuariopersistence.UsuarioDAO;

/**
 * Created by herma on 11/12/2017.
 * Cria conexao com o banco de dados
 */

public class UpkeepDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "upkeepxp.db";
    private static final int DATABASE_VERSION = 2;
    private String createTable;
    private String createTableEquipe;


    public UpkeepDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable = AtividadeDiariaDAO.createMyTable();
        db.execSQL(createTable);
        createTable = UsuarioDAO.createMyTable();
        db.execSQL(createTable);
        createTableEquipe = EquipeDAO.createMyTable();
        db.execSQL(createTableEquipe);
        getCreateTableEquipeId = EquipeIdDAO.createMyTable();
        db.execSQL(getCreateTableEquipeId);
        createTable = EquipamentoDAO.createMyTable();
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}