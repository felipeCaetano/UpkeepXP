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



}
