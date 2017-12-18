package upkeepxpteam.usuario.usuarioactivity;


import android.support.v4.app.Fragment;

import upkeepxpteam.infraestrutura.SingleFragmentActivity;

public class CadastraUsuarioActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CadastraUsuarioFragment();
    }
}
