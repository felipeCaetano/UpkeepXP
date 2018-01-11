package upkeepxpteam.atividadediaria.atividadediariabase;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import upkeepxpteam.usuario.usuariobase.Usuario;

/**
 * Created by Felipe on 19/12/2017.
 */

public class AtividadeDiaria implements Serializable {


    private int id;
    private String nome;
    private String data;
    private List<Usuario> usuarios;
    private String local;
    private String descricao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

        String date = simpleDateFormat.format(data);
        return date;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public String getUsuariosNomes(){
        String retorno = "Nomes";
        if(usuarios != null){
            for (Usuario usuario:usuarios) {
                retorno += usuario.getNome()+" "+usuario.getSobrenome()+";";
            }
        }
        return retorno;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
