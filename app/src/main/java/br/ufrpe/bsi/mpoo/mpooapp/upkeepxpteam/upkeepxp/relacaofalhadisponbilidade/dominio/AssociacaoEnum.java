package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.relacaofalhadisponbilidade.dominio;

/**
 * Created by Felipe on 16/02/2018.
 * para associação
 */

enum AssociacaoEnum {
    SERIE("Em série"),PARALELO("Em Paralelo");

    private String associacao;

    AssociacaoEnum(String associacao) {
        this.associacao = associacao;
    }

    public String getAssociacao() {
        return associacao;
    }

    public void setAssociacao(String associacao) {
        this.associacao = associacao;
    }

    @Override
    public String toString() {
        return associacao;
    }
}
