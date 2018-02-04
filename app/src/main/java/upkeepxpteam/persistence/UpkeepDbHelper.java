package upkeepxpteam.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import upkeepxpteam.atividadediaria.atividadediariaDAO.AtividadeDiariaDAO;
import upkeepxpteam.equipes.equipeDAO.EquipeDAO;

/**
 * Created by herma on 11/12/2017.
 */

public class UpkeepDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "upkeepxp.db";
    private static final int DATABASE_VERSION = 2;
    private final String CREATE_TABLE = "CREATE TABLE Usuarios (Email TEXT PRIMARY KEY, Nome TEXT NOT NULL, Sobrenome TEXT NOT NULL, " +
            "Nascimento TEXT, Sexo TEXT, Fone TEXT, Especialidade TEXT, CEP TEXT, Numero TEXT, UF TEXT NOT NULL, Funcao TEXT);";
    String createTable;
    String createTableEquipe;

    public UpkeepDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable = AtividadeDiariaDAO.createMyTable();
        db.execSQL(createTable);
        db.execSQL(CREATE_TABLE);
        createTableEquipe = EquipeDAO.createMyTable();
        db.execSQL(createTableEquipe);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}