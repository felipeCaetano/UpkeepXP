package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.usuario.ui;


import android.support.v4.app.Fragment;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.SingleFragmentActivity;

public class CadastraUsuarioActivity extends SingleFragmentActivity {

    /**
     * Retorna um fragment que faz o cadastro do usuário
     * @return
     */
    @Override
    protected Fragment createFragment() {

        return new CadastraUsuarioFragment();
    }
}
