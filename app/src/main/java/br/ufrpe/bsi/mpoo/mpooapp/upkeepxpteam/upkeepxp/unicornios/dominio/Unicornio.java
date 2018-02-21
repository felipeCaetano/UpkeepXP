package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.unicornios.dominio;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Felipe on 19/12/2017.
 * GUI das atividades diárias
 */

public class Unicornio implements Parcelable {


    private int id;
    private String nome;
    private double peso;
    private double altura;
    private String cor;
    private String elemento;
    private String genero;

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        //escreve para serialização
        dest.writeLong(id);
        dest.writeString(this.nome);
        dest.writeDouble(this.peso);
        dest.writeDouble(this.altura);
        dest.writeString(this.cor);
        dest.writeString(this.elemento);
        dest.writeString(this.genero);
    }

    /**
     * Lê os dados na ordem que foram escritos no objeto Parcel
     * @param parcel
     */
    private void readFromParcel(Parcel parcel){

        this.id = parcel.readInt();
        this.nome = parcel.readString();
        this.peso = parcel.readDouble();
        this.altura = parcel.readDouble();
        this.cor = parcel.readString();
        this.elemento = parcel.readString();
        this.genero = parcel.readString();
    }


    /**
     * Método necessário para implementação da interface Parcelable
     */
    public static final Parcelable.Creator<Unicornio> CREATOR = new Parcelable.Creator<Unicornio>() {

        /**
         * Retorna um objeto unicornio
         * passados pelo objeto Parcel
         * @param p
         * @return uni
         */
        @Override
        public Unicornio createFromParcel(Parcel p) {
            Unicornio uni = new Unicornio();
            uni.readFromParcel(p);
            return uni;
        }
        @Override
        public Unicornio[] newArray(int size){
            return new Unicornio[size];
        }
    };

    @Override
    public String toString(){

        return this.getNome()+ '\t' + this.getCor()+ '\t' + this.getElemento();
    }


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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {

        this.genero = genero;
    }

    //outros metodos dos unicornios...

    //relinchar, teletransportar...
}