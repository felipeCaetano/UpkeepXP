package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio;


/**
 * Created by anton on 16/02/2018.
 * Enum de cores para o unicornio
 */

public enum  CorEnum {
    Black("Preta"), Blue("Azul"), Pink("Rosa"), White("Branca"),Yellow("Amarela"),
    Red("Vemelha"),Purple("Violeta"),Green("Verde");

    private String cor;

    CorEnum(String cor){
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }


    @Override
    public String toString() {
        return this.cor;
    }
}
