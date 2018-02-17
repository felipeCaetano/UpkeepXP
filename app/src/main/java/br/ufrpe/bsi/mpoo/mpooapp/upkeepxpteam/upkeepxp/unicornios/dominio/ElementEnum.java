package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio;

/**
 * Created by anton on 16/02/2018.
 * Enum para os elementos dos unicornios
 */

public enum ElementEnum {

    AR("Ar"), TERRA("Terra"), FOGO("Fogo"), AGUA("Água"), LUZ("Luz"), ESCURIDAO("Escuridão");
    private String elemento;

    ElementEnum(String elemento){
        this.elemento = elemento;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }


    @Override
    public String toString() {
        return elemento;
    }
}
