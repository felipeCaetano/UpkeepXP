package upkeepxpteam.equipes.negocio;


import android.content.Context;

import upkeepxpteam.equipes.equipeDAO.EquipeDAO;
import upkeepxpteam.equipes.equipebase.Equipe;

public class EquipeNegocio {

    Equipe equipe;
    EquipeDAO equipeDAO;

    public EquipeNegocio(Context context){
        equipeDAO = new EquipeDAO(context);
    }

    public void salvarEquipe(Equipe equipe){
        equipeDAO.equipeSave(equipe);
    }

    public void existeEquipe(String nomeEquipe){
        equipeDAO.getIdEquipe(nomeEquipe);
    }

}
