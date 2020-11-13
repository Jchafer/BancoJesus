package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterMovimientos;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.fragment.Fragment_Movimientos;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

public class Movimientos extends AppCompatActivity {

    private Cuenta cuenta;
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private AdapterMovimientos adaptador;
    //private ArrayAdapter<Movimiento> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_movimientos);

        Cuenta cuenta = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        Fragment_Movimientos detalle =
                (Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgListadoMovimientos);
        detalle.mostrarMovimiento(cuenta);

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