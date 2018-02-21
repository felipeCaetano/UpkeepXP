package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio.EquipeId;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpKeepDataBaseContract;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia.UpkeepDbHelper;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.persistencia.UsuarioDAO;


public class EquipeIdDAO {

    private Context context;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UpKeepDataBaseContract.EquipesTableID.TABLE_NAME + " (" +
                    UpKeepDataBaseContract.EquipesTableID._ID + " INTEGER PRIMARY KEY," +
                    UpKeepDataBaseContract.EquipesTableID.COLUMN_NAME_ID_EQUIPE + TEXT_TYPE + COMMA_SEP +
                    UpKeepDataBaseContract.EquipesTableID.COLUMN_NAME_ID_USUARIO + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UpKeepDataBaseContract.EquipesTableID.TABLE_NAME;
    private final UpkeepDbHelper upkeepDbHelper;

    /**
     * Instancia os objetos necessários para realizar leitura e escrita no banco de dados.
     * @param ctx
     */
    public EquipeIdDAO(Context ctx){
        this.upkeepDbHelper = new UpkeepDbHelper(ctx);
        this.context = ctx;
    }

    /**
     * Cria a tabela conforme cláusulas da string SQL_CREATE_ENTRIES
     * @return
     */
    public static String createMyTable(){
        return SQL_CREATE_ENTRIES;
    }

    /**
     * Retorna um cursor que será tratado pos listarEquipeId.
     * @param idBusca
     * @return
     */
    public List<EquipeId> buscarTodasEquipesId(int idBusca) {
        String sql = "SELECT * FROM equipeid WHERE idEquipe = '" + idBusca + "'";
        SQLiteDatabase dbReader = upkeepDbHelper.getReadableDatabase();
        Cursor cursor = dbReader.rawQuery(sql, null);
        return listarEquipeId(cursor);
    }

    /**
     * Trata o cursor recebido de buscarTodasEquipesId, retornando a lista de id´s
     * @param cursor
     * @return
     */
    public List<EquipeId> listarEquipeId(Cursor cursor){
        List<EquipeId> result = new ArrayList<>();
        while (cursor.moveToNext()){
            EquipeId equipeId = new EquipeId();
            int idUsuario = cursor.getInt(cursor.getColumnIndex("idUsuario"));
            equipeId.setIdUsuario(idUsuario);
            int idEquipe = cursor.getInt(cursor.getColumnIndex("idEquipe"));
            equipeId.setIdEquipe(idEquipe);
            result.add(equipeId);
        }
        return result;
    }

    /**
     * Retorna uma lista dos usuários da equipe
     * @param id
     * @return
     */
    public String getStringOperarioEquipeId(int id){
        String operario = "";
        List<EquipeId> equipeIdList = buscarTodasEquipesId(id);
        for (EquipeId equipeId: equipeIdList){
            int userid = equipeId.getIdUsuario();
            UsuarioDAO usuarioDAO = new UsuarioDAO(context);
            List<Usuario> usuarioList = usuarioDAO.getUsuarioPorId(userid);
            operario += usuarioList.get(0).getNome()+"\n";
        }
        return operario;
    }
}
