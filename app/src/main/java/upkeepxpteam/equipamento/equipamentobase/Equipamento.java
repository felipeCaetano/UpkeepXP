package upkeepxpteam.equipamento.equipamentobase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Felipe on 28/05/2017.
 */
public class Equipamento implements Parcelable {

    private static final long serialVersionUID = 6601006766832473959L;
    private long id;
    private String nome;
    private String codigo;
    private String modelo;
    private String fabricante;
    private String defeito;
    private String tipo;
    private String descricao;
    private String status;


    @Override
    public String toString(){

        return this.getNome()+ '\t' + '\t' + this.getCodigo()+ '\t' + this.getTipo();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        //escreve para serialização
        dest.writeLong(id);
        dest.writeString(this.nome);
        dest.writeString(this.codigo);
        dest.writeString(this.modelo);
        dest.writeString(this.fabricante);
        dest.writeString(this.defeito);
        dest.writeString(this.tipo);
        dest.writeString(this.status);
        dest.writeString(this.descricao);
    }
    private void readFromParcel(Parcel parcel){
        //le os dados na ordem que foram escritos
        this.id = parcel.readLong();
        this.nome = parcel.readString();
        this.codigo = parcel.readString();
        this.modelo = parcel.readString();
        this.fabricante = parcel.readString();
        this.defeito = parcel.readString();
        this.tipo = parcel.readString();
        this.status = parcel.readString();
        this.descricao = parcel.readString();
    }

    public static final Parcelable.Creator<Equipamento> CREATOR = new Parcelable.Creator<Equipamento>() {
        @Override
        public Equipamento createFromParcel(Parcel p) {
            Equipamento eq = new Equipamento();
            eq.readFromParcel(p);
            return eq;
        }
        @Override
        public Equipamento[] newArray(int size){
            return new Equipamento[size];
        }
    };

    //geters e setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
