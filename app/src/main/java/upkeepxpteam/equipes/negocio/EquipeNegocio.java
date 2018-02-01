package upkeepxpteam.equipes.negocio;

import android.content.Context;
import java.util.List;
import upkeepxpteam.equipes.equipeDAO.EquipeDAO;
import upkeepxpteam.equipes.equipebase.Equipe;

public class EquipeNegocio {

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

    public void editarEquipe(Equipe equipe, int idEquipeAntiga){

        equipeDAO.equipeEditar(equipe,idEquipeAntiga);

    }

    public List<Equipe> buscarTodasEquipes(){

        return equipeDAO.buscarTodasEquipes();

    }
}
