package upkeepxpteam.equipes.equipeDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.equipes.equipebase.Equipe;
import upkeepxpteam.persistence.UpKeepDataBaseContract;
import upkeepxpteam.persistence.UpkeepDbHelper;
import upkeepxpteam.usuario.usuariobase.Usuario;


public class EquipeDAO {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.EquipesTable.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.EquipesTable._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.EquipesTable.COLUMN_NAME_NOME + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipesTable.COLUMN_NAME_USUARIOS + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.AtividadeDiariaTable.TABLE_NAME;
    private final SQLiteDatabase dbWriter;
    private final SQLiteDatabase dbReader;

    public EquipeDAO(Context ctx){
        UpkeepDbHelper upkeepDbHelper = new UpkeepDbHelper(ctx);
        dbWriter = upkeepDbHelper.getWritableDatabase();
        dbReader = upkeepDbHelper.getReadableDatabase();
    }

    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    public Boolean equipeSave(Equipe equipe){
        ContentValues cv = new ContentValues();

        cv.put("Nome", equipe.getNome());
        cv.put("Operario", equipe.getUsuariosNomes());

        return dbWriter.insert(UpKeepDataBaseContract.EquipesTable.TABLE_NAME,null,cv)>0;
    }

    public List<Equipe> listarequipe(){
        List<Equipe> result = new ArrayList<>();
        String sql = "SELECT * FROM equipe";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Equipe equipe = new Equipe();
            String nome = cursor.getString(cursor.getColumnIndex("Nome"));
            equipe.setNome(nome);
            String operarios = cursor.getString(cursor.getColumnIndex("Operario"));
            equipe.setUsuario(operarios);
            result.add(equipe);

        } return result;
    }

    public List<Usuario> listarusuarios(){
        List<Usuario> result = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        SQLiteDatabase db = dbReader;
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
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
        } return result;
    }

}
