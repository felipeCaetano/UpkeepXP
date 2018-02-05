package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;

public class UserModel {

    boolean isSelected;
    Usuario usuario;

    public UserModel(boolean isSelected, Usuario usuario) {
        this.isSelected = isSelected;
        this.usuario = usuario;
    }

    public boolean isSelected() {

        return isSelected;
    }

    public void setSelected(boolean selected) {

        isSelected = selected;
    }

    public Usuario getUsuario() {

        return usuario;
    }

}
