package upkeepxpteam.usuario.usuariopersistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import upkeepxpteam.persistence.UpkeepDbHelper;

/**
 * Created by herma on 11/12/2017.
 * Classe deve ser deletada
 */

public class DbGateway {

    private static DbGateway gw;
    private SQLiteDatabase db;

    private DbGateway(Context ctx){
        UpkeepDbHelper helper = new UpkeepDbHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public static DbGateway getInstance(Context ctx){
        if(gw == null)
            gw = new DbGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }

}

