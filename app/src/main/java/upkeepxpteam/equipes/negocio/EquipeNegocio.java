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

    public void salvarEquipe(Equipe equipe) {
        if (existeEquipe(equipe.getNome()) == false) {
            equipeDAO.equipeSave(equipe);
        }
    }

    public boolean existeEquipe(String nomeEquipe){
        if (equipeDAO.getIdEquipe(nomeEquipe) == -1){
            return false;
        }
        return true;
    }
}
