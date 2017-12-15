package upkeepxpteam.usuario.usuariopersistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by herma on 11/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "upkeepxp.db";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE Usuarios (Email TEXT PRIMARY KEY, Nome TEXT NOT NULL, Sobrenome TEXT NOT NULL, " +
            "Nascimento TEXT, Sexo TEXT, Fone TEXT, Especialidade TEXT, CEP TEXT, Numero TEXT, UF TEXT NOT NULL, Funcao TEXT);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}