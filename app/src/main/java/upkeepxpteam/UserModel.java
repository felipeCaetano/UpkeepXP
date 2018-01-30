package upkeepxpteam;

import upkeepxpteam.usuario.usuariobase.Usuario;

public class UserModel {

    boolean estaSelecionado;
    Usuario usuario;

    public UserModel(boolean estaSelecionado, Usuario usuario) {
        this.estaSelecionado = estaSelecionado;
        this.usuario = usuario;
    }

    public boolean isSelected() {

        return estaSelecionado;
    }

    public void setSelected(boolean selected) {

        estaSelecionado = selected;
    }

    public Usuario getUsuario() {

        return usuario;
    }

}
