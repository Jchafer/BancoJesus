package practicas.simarro.bancojesus.principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.fragment.Fragment_Movimientos_Todos;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

public class Movimientos extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private Cuenta cuenta;
    private MiBancoOperacional mbo;
    //private ArrayList<Movimiento> movimientosTodos;
    //private ArrayList<Movimiento> movimientosTipo0;
    //private ArrayList<Movimiento> movimientosTipo1;
    //private ArrayList<Movimiento> movimientosTipo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_movimientos);

        navigationView = (BottomNavigationView) findViewById(R.id.menu_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        cuenta = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        mbo = MiBancoOperacional.getInstance(this);

        //movimientosTodos = mbo.getMovimientos(cuenta);
        //movimientosTipo0 = mbo.getMovimientosTipo(cuenta, 0);
        //movimientosTipo1 = mbo.getMovimientosTipo(cuenta, 1);
        //movimientosTipo2 = mbo.getMovimientosTipo(cuenta, 2);

        Fragment_Movimientos_Todos fr = new Fragment_Movimientos_Todos();
        Bundle args = new Bundle();
        args.putSerializable("Movimientos", mbo.getMovimientos(cuenta));
        args.putSerializable("txtTitulo", "Todos");
        fr.setArguments(args);

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor, fr).commit();

        //Log.i("Movimiento", "/////////////////////" + cuenta.getSaldoActual());
        /*Fragment_Movimientos_Todos fr = (Fragment_Movimientos_Todos) getSupportFragmentManager().findFragmentById(R.id.FrgListadoMovimientos);
        Log.i("Movimiento", "/////////////////////" + cuenta.getSaldoActual());
        //fr.mostrarMovimiento();
        Log.i("Movimiento", "/////////////////////");

        Bundle bundle = new Bundle();
        bundle.putSerializable("Cuenta", cuenta);
        bundle.putSerializable("Banco", mbo);
        fr.setArguments(bundle);*/


        //cuenta.setListaMovimientos(mbo.getMovimientos(cuenta));

        /*Fragment_Movimientos detalle =
                (Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgListadoMovimientos);
        detalle.mostrarMovimiento(cuenta);*/

        /*Fragment_Movimientos detalle =
                (Fragment_Movimientos) getSupportFragmentManager().findFragmentById(R.id.FrgListadoMovimientos);
        detalle.mostrarMovimiento(cuenta);

        Bundle bundle = new Bundle();
        bundle.putSerializable("Cuenta", cuenta);
        //bundle.putSerializable("Banco", mbo);
        detalle.setArguments(bundle);*/

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment f = null;
        Bundle args = new Bundle();

        switch (item.getItemId()){
            case R.id.navigation_todos:
                f = new Fragment_Movimientos_Todos();
                args.putSerializable("Movimientos", mbo.getMovimientos(cuenta));
                args.putSerializable("txtTitulo", "Todos");
                break;
            case R.id.navigation_descuento:
                f = new Fragment_Movimientos_Todos();
                args.putSerializable("Movimientos", mbo.getMovimientosTipo(cuenta, 0));
                args.putSerializable("txtTitulo", "Descuento");
                break;
            case R.id.navigation_ingreso:
                f = new Fragment_Movimientos_Todos();
                args.putSerializable("Movimientos",  mbo.getMovimientosTipo(cuenta, 1));
                args.putSerializable("txtTitulo", "Ingreso");
                break;
            case R.id.navigation_reintegro:
                f = new Fragment_Movimientos_Todos();
                args.putSerializable("Movimientos",  mbo.getMovimientosTipo(cuenta, 2));
                args.putSerializable("txtTitulo", "Reintegro");
                break;
        }

        if (f != null) {
            f.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, f).commit();
        }
        return true;
    }
}