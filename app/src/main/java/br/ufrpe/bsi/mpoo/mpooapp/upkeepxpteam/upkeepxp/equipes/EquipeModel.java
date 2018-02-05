package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio.Equipe;

public class EquipeModel {

    Equipe equipe;

    public EquipeModel(Equipe equipe){

        this.equipe = equipe;
    }

    public Equipe getEquipe() {

        return equipe;
    }

    public void setEquipe(Equipe equipe) {

        this.equipe = equipe;
    }
}
