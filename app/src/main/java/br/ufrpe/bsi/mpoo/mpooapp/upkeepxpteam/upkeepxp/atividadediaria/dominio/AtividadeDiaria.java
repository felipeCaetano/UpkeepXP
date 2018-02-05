package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.atividadediaria.dominio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Felipe on 19/12/2017.
 * GUI das atividades diárias
 */

public class AtividadeDiaria implements Serializable {


    private int id;
    private String nome;
    private String data;
    private String equipeNome;
    private String local;
    private String descricao;
    private String situacao;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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

    public String getData() {

        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEquipeNome() {
        return equipeNome;
    }

    public void setEquipeNome(String nomeEquipe) {
        this.equipeNome = nomeEquipe;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
