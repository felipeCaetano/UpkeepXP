package upkeepxpteam.persistence;

import android.provider.BaseColumns;

/**
 * Created by Felipe on 20/12/2017.
 */

public final class UpKeepDataBaseContract {

    private UpKeepDataBaseContract() {//esta clase não deve ser instanciada
    }

        public static class AtividadeDiariaTable implements BaseColumns{
            public static final String TABLE_NAME = "atividadiaria";
            public static final String COLUMN_NAME_NOME = "Nome";
            public static final String COLUMN_NAME_DATA = "Data";
            public static final String COLUMN_NAME_USUARIOS = "Equipe";
            public static final String COLUMN_NAME_LOCAL= "Local";
            public static final String COLUMN_NAME_DESCRICAO= "Descricao";
            public static final String COLUMN_NAME_SITUACAO= "Situacao";
        }

        public static class EquipesTable implements BaseColumns{
        public static final String TABLE_NAME = "equipe";
        public static final String COLUMN_NAME_NOME = "Nome";
        public static final String COLUMN_NAME_USUARIOS = "Operario";
        }

}
