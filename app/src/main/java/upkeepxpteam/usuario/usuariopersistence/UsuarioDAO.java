package upkeepxpteam.usuario.usuariopersistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.persistence.UpKeepDataBaseContract;
import upkeepxpteam.persistence.UpkeepDbHelper;
import upkeepxpteam.usuario.usuariobase.Usuario;


/**
 * Created by herma on 11/12/2017.
 */

public class UsuarioDAO {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private SQLiteDatabase dbWriter;
    private SQLiteDatabase dbReader;
    private UpkeepDbHelper upkeepDbHelper;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.UsuariosTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.UsuariosTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_SOBRENOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_NASCIMENTO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_SEXO + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_FONE + TEXT_TYPE +  COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_ESPECIALIDADE + TEXT_TYPE +  COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_CEP+ TEXT_TYPE +  COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_NUMERO + TEXT_TYPE +  COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_UF + TEXT_TYPE +  COMMA_SEP +
                    UpKeepDataBaseContract.UsuariosTable.COLUMN_NAME_FUNCAO + TEXT_TYPE + " )";


    public UsuarioDAO(Context ctx) {
        this.upkeepDbHelper = new UpkeepDbHelper(ctx);
        this.dbWriter = upkeepDbHelper.getWritableDatabase();
        this.dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable() {
        return SQL_CREATE_ENTRIES;
    }

    public boolean salva(Usuario usuario) {

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

    public List<Usuario> buscarTodosUsuarios() {
        String sql = "SELECT * FROM "+ UpKeepDataBaseContract.UsuariosTable.TABLE_NAME;
        dbReader = upkeepDbHelper.getReadableDatabase();
        try{
            Cursor cursor = dbReader.rawQuery(sql, null);
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
                String nome = cursor.getString(cursor.getColumnIndex("Nome"));
                usuario.setNome(nome);
                String email = cursor.getString(cursor.getColumnIndex("Email"));
                usuario.setEmail(email);
                String fone = cursor.getString(cursor.getColumnIndex("Fone"));
                usuario.setTelefone(fone);
                String snome = cursor.getString(cursor.getColumnIndex("Sobrenome"));
                usuario.setSobrenome(snome);
                String nascimento = cursor.getString(cursor.getColumnIndex("Nascimento"));
                usuario.setNascimento(nascimento);
                String sexo = cursor.getString(cursor.getColumnIndex("Sexo"));
                usuario.setSexo(sexo);
                String especialidade = cursor.getString(cursor.getColumnIndex("Especialidade"));
                usuario.setEspecialidade(especialidade);
                String cep = cursor.getString(cursor.getColumnIndex("CEP"));
                usuario.setCep(cep);
                String numero = cursor.getString(cursor.getColumnIndex("Numero"));
                usuario.setNumero(numero);
                String uf = cursor.getString(cursor.getColumnIndex("UF"));
                usuario.setUf(uf);
                String funcao = cursor.getString(cursor.getColumnIndex("Funcao"));
                usuario.setFuncao(funcao);
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