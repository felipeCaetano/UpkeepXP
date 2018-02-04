package upkeepxpteam.equipes.equipebase;

import java.io.Serializable;
import java.util.List;

import upkeepxpteam.usuario.usuariobase.Usuario;

public class Equipe implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String nome;
    private List<Usuario> users;
    private String usuarios;

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

    public String getUsuariosNomes(){
        String retorno = "Operários: ";
        if(users!= null){
            for (Usuario usuario:users) {
                retorno += usuario.getNome()+" "+usuario.getSobrenome()+"\n";
            }
        }
        return retorno;
    }

    public String toString(){
        return "" + this.getNome() +" "+ this.getUsuario();
    }

    public String toString2(){ return "" + this.getNome(); }
}
