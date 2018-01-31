package upkeepxpteam;

import upkeepxpteam.usuario.usuariobase.Usuario;

public class UserModel {

<<<<<<< Updated upstream
    private boolean isSelected;
    private Usuario usuario;
=======
<<<<<<< HEAD
    boolean estaSelecionado;
    Usuario usuario;
=======
    private boolean isSelected;
    private Usuario usuario;
>>>>>>> Dev
>>>>>>> Stashed changes

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
