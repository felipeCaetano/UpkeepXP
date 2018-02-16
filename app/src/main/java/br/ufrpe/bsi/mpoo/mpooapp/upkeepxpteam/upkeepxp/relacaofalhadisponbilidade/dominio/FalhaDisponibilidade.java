package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.relacaofalhadisponbilidade.dominio;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;

/**
 * Created by anton on 16/02/2018.
 */

public class FalhaDisponibilidade {

    private long id;
    private Equipamento atual;
    private Equipamento proximo;
    private Enum<AssociacaoEnum> associacaoEnum;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Equipamento getAtual() {
        return atual;
    }

    public void setAtual(Equipamento atual) {
        this.atual = atual;
    }

    public Equipamento getProximo() {
        return proximo;
    }

    public void setProximo(Equipamento proximo) {
        this.proximo = proximo;
    }

    public Enum<AssociacaoEnum> getAssociacaoEnum() {
        return associacaoEnum;
    }

    public void setAssociacaoEnum(Enum<AssociacaoEnum> associacaoEnum) {
        this.associacaoEnum = associacaoEnum;
    }
}
