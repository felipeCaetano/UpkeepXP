package upkeepxpteam.usuario.usuariopersistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.persistence.UpkeepDbHelper;
import upkeepxpteam.usuario.usuariobase.Usuario;

/**
 * Created by herma on 11/12/2017.
 */

public class UsuarioDAO {

    private final String TABLE_USUARIOS = "Usuarios";
    private final SQLiteDatabase dbWriter;
    private final SQLiteDatabase dbReader;


    public UsuarioDAO(Context ctx) {
        UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(ctx);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public boolean salvar(String email,  String nome, String sobrenome, String nascimento,
                          String sexo, String fone, String especialidade, String cep, String numero,
                          String uf, String funcao) {

        ContentValues cv = new ContentValues();
        cv.put("Email", email);
        cv.put("Nome", nome);
        cv.put("Sobrenome", sobrenome);
        cv.put("Nascimento", nascimento);
        cv.put("Sexo", sexo);
        cv.put("Fone", fone);
        cv.put("Especialidade", especialidade);
        cv.put("CEP", cep);
        cv.put("Numero", numero);
        cv.put("UF", uf);
        cv.put("Funcao", funcao);

        return dbWriter.insert(TABLE_USUARIOS, null, cv) > 0;

    }

    public List<Usuario> buscarTodosUsuarios() {
        String sql = "SELECT * FROM Usuarios";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql, null);
        return listarUsuarios(cursor);
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
}
