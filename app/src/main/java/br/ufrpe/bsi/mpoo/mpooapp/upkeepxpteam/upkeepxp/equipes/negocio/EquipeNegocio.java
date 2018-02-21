package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.negocio;

import android.content.Context;

import java.util.List;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.dominio.Equipe;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipes.persistencia.EquipeDAO;

public class EquipeNegocio {

    EquipeDAO equipeDAO;

    /**
     * Cria um novo objeto equipeDAO
     * @param context
     */
    public EquipeNegocio(Context context){
        equipeDAO = new EquipeDAO(context);
    }

    /**
     * Salva equipe no banco de daodos
     * @param equipe
     */
    public void salvarEquipe(Equipe equipe) {
        if (existeEquipe(equipe.getNome()) == false) {
            equipeDAO.equipeSave(equipe);
        }
    }

    /**
     * Verifica se exsite uma equipe com nome 'nomeEquipe'
     * @param nomeEquipe
     * @return
     */

    public boolean existeEquipe(String nomeEquipe){
        return equipeDAO.getIdEquipe(nomeEquipe) != -1;
    }

    /**
     * Modifica uma equipe salva no banco de dados
     * @param equipe
     * @param idEquipeAntiga
     */
    public void editarEquipe(Equipe equipe, int idEquipeAntiga){

        equipeDAO.equipeEditar(equipe,idEquipeAntiga);

    }

    /**
     * Retorna uma lista com todas as equipes cadastradas no banco de dados.
     * @return
     */
    public List<Equipe> buscarTodasEquipes(){

        return equipeDAO.buscarTodasEquipes();

    }
}
