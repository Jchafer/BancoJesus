package practicas.simarro.bancojesus.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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
import practicas.simarro.bancojesus.dialogos.DialogoMovimiento;
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

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.layout_dialog_movimiento, null);
        TextView importe = v.findViewById(R.id.tvImporte);
       Log.i("///////", "Mov: " + importe.getText());
        importe.setText("Hola");
       Log.i("///////", "Mov: " + importe.getText());
        lstMovimientos = (ListView) getView().findViewById(R.id.listViewMovimientos);

        lstMovimientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                Log.i("Movimiento", "Mov: " + pos + id);
                FragmentManager fragmentManager = getFragmentManager();
                DialogoMovimiento dialogo = new DialogoMovimiento();
                dialogo.info = list.getItemAtPosition(pos).toString();


                dialogo.show(fragmentManager, "tagMovimiento");
            }
        });
    }*/

    public void mostrarMovimiento(Cuenta cuenta) {
        ListView listadoMovimientos = (ListView) getView().findViewById(R.id.listViewMovimientos);
        listadoMovimientos.setAdapter(new AdapterMovimientos(this, cuenta.getListaMovimientos()));

        /*LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.layout_dialog_movimiento, null);
        TextView importer = v.findViewById(R.id.tvImporte);
        importer.setText("Hola");
        Log.i("///////", "Mov: " + importer.getText());*/

        listadoMovimientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                //Log.i("Movimiento", "Mov: " + pos + id);
                FragmentManager fragmentManager = getFragmentManager();
                DialogoMovimiento dialogo = new DialogoMovimiento();
                dialogo.info = ((Movimiento) list.getItemAtPosition(pos)).mostrarDatos();

                dialogo.show(fragmentManager, "tagMovimiento");
            }
        });
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