package upkeepxpteam.usuario.usuariopersistence;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by herma on 11/12/2017.
 */

public class UsuarioDAO {

    private final String TABLE_USUARIOS = "Usuarios";
    private DbGateway gw;

    public UsuarioDAO(Context ctx) {
        gw = DbGateway.getInstance(ctx);
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

        return gw.getDatabase().insert(TABLE_USUARIOS, null, cv) > 0;

    }
}
