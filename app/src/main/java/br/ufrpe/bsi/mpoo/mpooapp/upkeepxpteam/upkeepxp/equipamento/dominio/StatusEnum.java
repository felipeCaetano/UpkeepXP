package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio;

/**
 * Enum com opções de status
 */

public enum StatusEnum {

    EM_ANALISE("Em Análise"),RESOLVIDO("Resolvido"),NAO_RESOLVIDO("Não Resolvido");

    private String status;

    StatusEnum(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return status;
    }
}
