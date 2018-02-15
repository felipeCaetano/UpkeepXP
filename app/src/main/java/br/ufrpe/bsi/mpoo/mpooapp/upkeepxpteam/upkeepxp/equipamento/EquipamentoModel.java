package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;


public class EquipamentoModel {

    Equipamento equipamento;

    /**
     * Construtor para classe
     * @param equipamento
     */
    public EquipamentoModel(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    /**
     * Retorna um equipamento
     * @return equipamento
     */
    public Equipamento getEquipamento() {
        return equipamento;
    }

    /**
     * Seta um equipamento no objeto EquipamentoModel
     * @param equipamento
     */
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
