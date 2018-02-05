package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpKeepDataBaseContract;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpkeepDbHelper;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;

/**
 * Created by herma on 11/12/2017.
 * DAO da classe usuário
 */

public class UsuarioDAO {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.UsuariosTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.UsuariosTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_SOBRENOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_NASCIMENTO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_SEXO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_FONE + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_ESPECIALIDADE + TEXT_TYPE+ COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_CEP + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_NUMERO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_UF + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_FUNCAO + TEXT_TYPE + " )";

    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;
    private final UpkeepDbHelper upkeepDbHelper;


    public UsuarioDAO(Context ctx) {
        upkeepDbHelper = new UpkeepDbHelper(ctx);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public boolean salvar(Usuario usuario) {

        ContentValues cv = new ContentValues();
        cv.put("Email", usuario.getEmail());
        cv.put("Nome", usuario.getNome());
        cv.put("Sobrenome", usuario.getSobrenome());
        cv.put("Nascimento", usuario.getNascimento());
        cv.put("Sexo", usuario.getSexo());
        cv.put("Fone", usuario.getTelefone());
        cv.put("Especialidade", usuario.getEspecialidade());
        cv.put("CEP", usuario.getCep());
        cv.put("Numero", usuario.getNumero());
        cv.put("UF", usuario.getUf());
        cv.put("Funcao", usuario.getFuncao());

        return dbWriter.insert(UpKeepDataBaseContract.UsuariosTable.TABLE_NAME, null, cv) > 0;

    }

    public Boolean delete(Usuario usuario){
        dbWriter = upkeepDbHelper.getWritableDatabase();
        try{
            return dbWriter.delete(
                    UpKeepDataBaseContract.UsuariosTable.TABLE_NAME,
                    "_id=?",new String[]{String.valueOf(usuario.getId())})>0;
        }finally {
            dbWriter.close();
        }
    }

    public List<Usuario> buscarTodosUsuarios() {
        dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor cursor = dbReader.query(UpKeepDataBaseContract.UsuariosTable.TABLE_NAME,
                    null,null,null,null,null,null);
            return listarUsuarios(cursor);
        }finally {
            dbReader.close();
        }
    }

    public List<Usuario> listarUsuarios(Cursor cursor){
        List<Usuario> result = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                Usuario usuario = new Usuario();
                usuario.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                usuario.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                usuario.setTelefone(cursor.getString(cursor.getColumnIndex("Fone")));
                usuario.setSobrenome(cursor.getString(cursor.getColumnIndex("Sobrenome")));
                usuario.setNascimento(cursor.getString(cursor.getColumnIndex("Nascimento")));
                usuario.setSexo(cursor.getString(cursor.getColumnIndex("Sexo")));
                usuario.setEspecialidade(cursor.getString(cursor.getColumnIndex("Especialidade")));
                usuario.setCep(cursor.getString(cursor.getColumnIndex("CEP")));
                usuario.setNumero(cursor.getString(cursor.getColumnIndex("Numero")));
                usuario.setUf(cursor.getString(cursor.getColumnIndex("UF")));
                usuario.setFuncao(cursor.getString(cursor.getColumnIndex("Funcao")));
                result.add(usuario);
            }while (cursor.moveToNext());
        }
        return result;
    }


    public List<Usuario> getUsuarioPorId(int idbusca) {
        String busca = String.valueOf(idbusca);
        String sql = "SELECT * FROM Usuarios WHERE _id = '" + busca + "'";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql, null);
        return listarUsuarios(cursor);
    }
}
