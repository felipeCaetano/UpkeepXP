package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.dominio.Usuario;

public class UserModel {

    boolean isSelected;
    Usuario usuario;

    /**
     * Construtor para classe
     * @param isSelected
     * @param usuario
     */
    public UserModel(boolean isSelected, Usuario usuario) {
        this.isSelected = isSelected;
        this.usuario = usuario;
    }

    /**
     * Verifica se está selecionado
     * @return
     */
    public boolean isSelected() {

        return isSelected;
    }

    /**
     * Seta o atributo como selecionado
     * @param selected
     */
    public void setSelected(boolean selected) {

        isSelected = selected;
    }

    /**
     * Retorna o objeto usuário
     * @return
     */
    public Usuario getUsuario() {

        return usuario;
    }

}
