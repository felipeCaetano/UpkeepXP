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
 */

public class UpkeepDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "upkeepxp.db";
    private static final int DATABASE_VERSION = 2;
<<<<<<< Updated upstream
    private String createTable;
    private String createTableEquipe;
=======
<<<<<<< HEAD
    private static final String CREATE_TABLE = "CREATE TABLE Usuarios (Email TEXT PRIMARY KEY, Nome TEXT NOT NULL, Sobrenome TEXT NOT NULL, " +
            "Nascimento TEXT, Sexo TEXT, Fone TEXT, Especialidade TEXT, CEP TEXT, Numero TEXT, UF TEXT NOT NULL, Funcao TEXT);";
    String createTable;
    String createTableEquipe;
=======
    private String createTable;
    private String createTableEquipe;
>>>>>>> Dev
>>>>>>> Stashed changes

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
        createTable = EquipamentoDAO.createMyTable();
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}