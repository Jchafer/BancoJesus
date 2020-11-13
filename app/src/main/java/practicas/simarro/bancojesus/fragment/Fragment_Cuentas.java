package practicas.simarro.bancojesus.fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterCuentas;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.pojo.Cliente;
import practicas.simarro.bancojesus.pojo.Cuenta;

public class Fragment_Cuentas extends Fragment {

    private Cliente cliente;
    private MiBancoOperacional mbo;
    private AdapterCuentas adaptador;
    private ListView lstCuentas;
    private CuentaListener listener;
    private ArrayList<Disco> datos = new ArrayList<Disco>();
    private ArrayList<Cancion> cancionesEstopa = new ArrayList<Cancion>();
    private ArrayList<Cancion> cancionesCanto = new ArrayList<Cancion>();
    private ArrayList<Cancion> cancionesOreja = new ArrayList<Cancion>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            cliente = (Cliente) getArguments().get("Cliente");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_fragment_cuentas, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        cliente = (Cliente) getArguments().get("Cliente");
        mbo = (MiBancoOperacional) getArguments().get("Banco");


        lstCuentas = (ListView) getView().findViewById(R.id.listViewCuentas);

        //mbo = MiBancoOperacional.getInstance(this);

        lstCuentas.setAdapter(new AdapterCuentas(this, mbo.getCuentas(cliente)));

        lstCuentas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                if (listener != null) {
                    listener.onCuentaSeleccionada(
                            (Cuenta) lstCuentas.getAdapter().getItem(pos));
                }
            }
        });
    }

    public void setCuentaListener(CuentaListener listener) {
        this.listener = listener;
    }

}