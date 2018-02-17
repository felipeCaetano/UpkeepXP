package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio;

import android.graphics.Color;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Felipe on 19/12/2017.
 * GUI das atividades diárias
 */

public class Unicornio implements Serializable {


    private int id;
    private String nome;
    private double peso;
    private double altura;
    private Color cor;
    private Enum<ElementEnum> elemento;
    private String genero;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public Enum<ElementEnum> getElemento() {
        return elemento;
    }

    public void setElemento(Enum<ElementEnum> elemento) {
        this.elemento = elemento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}