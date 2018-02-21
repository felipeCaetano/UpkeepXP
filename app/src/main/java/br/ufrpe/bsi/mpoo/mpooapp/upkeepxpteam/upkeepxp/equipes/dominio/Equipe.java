package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio;

import java.io.Serializable;
import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;

public class Equipe implements Serializable {

    private int id;
    private String nome;
    private List<Usuario> users;
    private String usuarios;

    /**
     * Getters e setters do obejto equipe.
     * @return
     */
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsers() {
        return users;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    public void setUsuario(String usuario){this.usuarios = usuario;}

    public String getUsuario(){return usuarios;}

    public String toString(){
        return "" + this.getNome() +" "+ this.getUsuario();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
