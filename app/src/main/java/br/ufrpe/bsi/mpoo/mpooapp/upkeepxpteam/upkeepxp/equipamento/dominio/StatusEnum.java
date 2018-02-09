package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio;

/**
 * Created by Felipe on 05/02/2018.
 */

public enum StatusEnum {

    EM_ANALISE("Em Análise"),RESOLVIDO("Resolvido"),NAO_RESOLVIDO("Não REsolvido");

    private final String status;

    StatusEnum(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return this.status;
    }

    public static String[] enumEstadosLista(){
        StatusEnum[] listastatus = StatusEnum.values();
        String[] lista = new String[listastatus.length];
        for (int i =0; i<listastatus.length;i++){
            lista[i] = listastatus[i].getStatus();
        }
        return  lista;
    }
}
