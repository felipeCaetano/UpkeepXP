package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.atividadediaria.persistencia.AtividadeDiariaDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.persistencia.EquipeDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.persistencia.EquipeIdDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.persistencia.UsuarioDAO;

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
        createTable = EquipeIdDAO.createMyTable();
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}