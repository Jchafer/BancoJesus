package practicas.simarro.bancojesus.fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterCuentas;
import practicas.simarro.bancojesus.adaptador.AdapterMovimientos;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.pojo.Cliente;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

public class Fragment_Movimientos extends Fragment {

    MiBancoOperacional mbo;
    Cuenta cuenta;
    ListView lstMovimientos;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_movimientos);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_movimientos, container, false);
    }

    /*@Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        mbo = (MiBancoOperacional) getArguments().get("Banco");
        cuenta = (Cuenta) getArguments().get("Cuenta");

        lstMovimientos = (ListView) getView().findViewById(R.id.listViewMovimientos);

        lstMovimientos.setAdapter(new AdapterMovimientos(this, mbo.getMovimientos(cuenta)));

        lstMovimientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                System.out.println(mbo.getMovimientos(cuenta).get(pos).getImporte());
            }
        });
    }*/

    public void mostrarMovimiento(ArrayList<Movimiento> mov) {
        ListView listadoMovimientos = (ListView) getView().findViewById(R.id.listViewMovimientos);
        listadoMovimientos.setAdapter(new AdapterMovimientos(this, mov));

    }

    /*public void mostrarMovimiento(Cuenta cuenta) {
        ListView listadoMovimientos = (ListView) getView().findViewById(R.id.listViewMovimientos);
        listadoMovimientos.setAdapter(new AdapterMovimientos(this, mbo.getMovimientos(cuenta)));

    }*/

    /*public void mostrarMovimientoOtro() {
        ListView listadoMovimientos = (ListView) getView().findViewById(R.id.listViewMovimientos);
        listadoMovimientos.setAdapter(new AdapterMovimientos(this, cuenta.getListaMovimientos()));

    }*/

}