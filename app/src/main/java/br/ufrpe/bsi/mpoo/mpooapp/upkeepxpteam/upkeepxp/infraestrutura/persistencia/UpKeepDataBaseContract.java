package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.persistencia;

import android.provider.BaseColumns;

/**
 * Created by Felipe on 20/12/2017.
 * Classe contendo as tabelas do banco de dados
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

        public static class UsuariosTable implements  BaseColumns{
        public static final String TABLE_NAME = "Usuarios";
        public static final String COLUMN_NAME_EMAIL = "Email";
        public static final String COLUMN_NAME_NOME = "Nome";
        public static final String COLUMN_NAME_SOBRENOME = "Sobrenome";
        public static final String COLUMN_NAME_NASCIMENTO = "Nascimento";
        public static final String COLUMN_NAME_SEXO = "Sexo";
        public static final String COLUMN_NAME_FONE = "Fone";
        public static final String COLUMN_NAME_ESPECIALIDADE = "Especialidade";
        public static final String COLUMN_NAME_CEP = "CEP";
        public static final String COLUMN_NAME_NUMERO = "Numero";
        public static final String COLUMN_NAME_UF = "UF";
        public static final String COLUMN_NAME_FUNCAO = "Funcao";
    }

    public static class EquipesTable implements BaseColumns{
        public static final String TABLE_NAME = "equipe";
        public static final String COLUMN_NAME_NOME = "Nome";
        public static final String COLUMN_NAME_USUARIOS = "Operario";
    }

    public static class EquipesTableID implements BaseColumns{
        public static final String TABLE_NAME = "equipeid";
        public static final String COLUMN_NAME_ID_EQUIPE = "idEquipe";
        public static final String COLUMN_NAME_ID_USUARIO = "idUsuario";
    }


    public static class EquipamentosTable implements BaseColumns{
        public static final String TABLE_NAME = "Equipamentos";
        public static final String COLUMN_NAME_NOME = "Equipamento";
        public static final String COLUMN_NAME_CODIGO = "Codigo";
        public static final String COLUMN_NAME_MODELO = "Modelo";
        public static final String COLUMN_NAME_TIPO = "Tipo";
        public static final String COLUMN_NAME_FABRICANTE = "Fabricante";
        public static final String COLUMN_NAME_DESCRICAO = "Descricao";
        public static final String COLUMN_NAME_DEFEITO = "Defeito";
        public static final String COLUMN_NAME_STATUS = "Status";
    }


        public static class EquipamentosTable implements BaseColumns{
            public static final String TABLE_NAME = "Equipamentos";
            public static final String COLUMN_NAME_NOME = "Equipamento";
            public static final String COLUMN_NAME_CODIGO = "Codigo";
            public static final String COLUMN_NAME_MODELO = "Modelo";
            public static final String COLUMN_NAME_TIPO = "Tipo";
            public static final String COLUMN_NAME_FABRICANTE = "Fabricante";
            public static final String COLUMN_NAME_DESCRICAO = "Descricao";
            public static final String COLUMN_NAME_DEFEITO = "Defeito";
            public static final String COLUMN_NAME_STATUS = "Status";
    }

}
