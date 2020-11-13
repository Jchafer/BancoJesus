package practicas.simarro.bancojesus.fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterMovimientos;
import practicas.simarro.bancojesus.pojo.Cuenta;

public class Fragment_Movimientos extends Fragment {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_movimientos);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_movimientos, container, false);
    }

    public void mostrarMovimiento(Cuenta cuenta) {
        ListView listadoMovimientos = (ListView) getView().findViewById(R.id.listViewCuentas);
        listadoMovimientos.setAdapter(new AdapterMovimientos(this, cuenta.getListaMovimientos()));

    }

}