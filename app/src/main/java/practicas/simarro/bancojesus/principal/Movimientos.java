package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterMovimientos;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.fragment.Fragment_Movimientos;
import practicas.simarro.bancojesus.pojo.Cliente;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

public class Movimientos extends AppCompatActivity {

    private Cuenta cuenta;
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private AdapterMovimientos adaptador;
    private ArrayList<Movimiento> movimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_movimientos);
        //Log.w("entra","ENTRA EN MOVIMIENTOS");

        mbo = MiBancoOperacional.getInstance(this);
        cuenta = (Cuenta) getIntent().getSerializableExtra("Cuenta");

        //cuenta.setListaMovimientos(mbo.getMovimientos(cuenta));

        Fragment_Movimientos detalle =
                (Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgListadoMovimientos);
        detalle.mostrarMovimiento(cuenta);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Cuenta", cuenta);
        //bundle.putSerializable("Banco", mbo);
        detalle.setArguments(bundle);

        /*Bundle bundle = new Bundle();
        bundle.putSerializable("Cuenta", cuenta);

        detalle.setArguments(bundle);*/

        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_movimientos);
        mbo = MiBancoOperacional.getInstance(this);

        cuenta = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        listaMovimientos = (ListView) findViewById(R.id.listViewCuentas);

        adaptador = new AdapterMovimientos(this, mbo.getMovimientos(cuenta));
        //adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);*/
    }
}