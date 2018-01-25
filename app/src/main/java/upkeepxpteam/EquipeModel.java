package upkeepxpteam;

import upkeepxpteam.equipes.equipebase.Equipe;

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
