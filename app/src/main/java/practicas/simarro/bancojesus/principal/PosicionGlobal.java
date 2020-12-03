package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterCuentas;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.fragment.CuentaListener;
import practicas.simarro.bancojesus.fragment.Fragment_Cuentas;
import practicas.simarro.bancojesus.fragment.Fragment_Movimientos;
import practicas.simarro.bancojesus.pojo.Cliente;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

public class PosicionGlobal extends AppCompatActivity implements CuentaListener {

    private Cliente cliente;
    private MiBancoOperacional mbo;

    /*private ListView listaCuentas;
    private AdapterCuentas<Cuenta> adaptador;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_posicion_global);

        Fragment_Cuentas frgListadoCuentas =
                (Fragment_Cuentas) getSupportFragmentManager().findFragmentById(R.id.FrgListadoCuentas);
        frgListadoCuentas.setCuentaListener(this);

        mbo = MiBancoOperacional.getInstance(this);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        Bundle bundle = new Bundle();
        bundle.putSerializable("Cliente", cliente);
        bundle.putSerializable("Banco", mbo);

        frgListadoCuentas.setArguments(bundle);

        /*mbo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");*/
        /*listaCuentas = (ListView) findViewById(R.id.listViewMovimientos);

        adaptador = new ArrayAdapterCuentas<>(this, mbo.getCuentas(cliente));
        listaCuentas.setAdapter(adaptador);
        listaCuentas.setOnItemClickListener(this);*/
    }


    /*@Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(view.getContext(), Movimientos.class);
        intent.putExtra("Cuenta", mbo.getCuentas(cliente).get(i));
        startActivity(intent);
    }*/

    @Override
    public void onCuentaSeleccionada(Cuenta cuenta) {
        boolean hayDetalle = (getSupportFragmentManager().findFragmentById(R.id.FrgListadoMovimientos) != null);
        //ArrayList<Movimiento> mov = mbo.getMovimientos(cuenta);

        cuenta.setListaMovimientos(mbo.getMovimientos(cuenta));

        //System.out.println("////////////////////Lista Movimientos" + cuenta.getListaMovimientos());

        if (hayDetalle) {
            ((Fragment_Movimientos) getSupportFragmentManager()
                    .findFragmentById(R.id.FrgListadoMovimientos)).mostrarMovimiento(cuenta);
        } else {
            Intent i = new Intent(this, Movimientos.class);

            //Bundle bundle = new Bundle();
            //bundle.putSerializable("Cuenta", cuenta);

            i.putExtra("Cuenta", cuenta);

            startActivity(i);
        }
    }
}