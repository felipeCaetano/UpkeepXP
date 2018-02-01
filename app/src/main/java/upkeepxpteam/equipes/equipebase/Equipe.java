package upkeepxpteam.equipes.equipebase;

import java.io.Serializable;
import java.util.List;

import upkeepxpteam.usuario.usuariobase.Usuario;

public class Equipe implements Serializable {

    private int id;
    private String nome;
    private List<Usuario> users;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
