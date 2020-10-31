package practicas.simarro.bancojesus;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.pojo.Cliente;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

public class Movimientos extends AppCompatActivity {

    private Cuenta cuenta;
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private ArrayAdapterMovimientos<Movimiento> adaptador;
    //private ArrayAdapter<Movimiento> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);
        mbo = MiBancoOperacional.getInstance(this);

        cuenta = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        listaMovimientos = (ListView) findViewById(R.id.listViewMovimientos);

        adaptador = new ArrayAdapterMovimientos<Movimiento>(this, mbo.getMovimientos(cuenta));
        //adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);
    }
}