package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.ui;

/**
 * Created by Felipe on 05/02/2018.
 * Classe para controlar o comportamento e exibição do listView de equipamentos cadeastrados
 */

public class EquipamentoListView {
    private String equipamento;
    private String codigo;
    private String defeito;
    private String status;

    /**
     * Carrega a view com detalhes do equipamento. Ainda em desenvolvimento.
     * @param equipamento
     * @param codigo
     * @param defeito
     * @param status
     */
    public EquipamentoListView(String equipamento, String codigo, String defeito, String status) {
        this.equipamento = equipamento;
        this.codigo = codigo;
        this.defeito = defeito;
        this.status = status;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
