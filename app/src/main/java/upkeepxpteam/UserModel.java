package upkeepxpteam;

import upkeepxpteam.usuario.usuariobase.Usuario;

public class UserModel {


    private boolean isSelected;
    private Usuario usuario;

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
