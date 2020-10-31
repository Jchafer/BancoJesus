package practicas.simarro.bancojesus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.pojo.Cliente;
import practicas.simarro.bancojesus.pojo.Cuenta;

public class PosicionGlobal extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Cliente cliente;
    private ListView listaCuentas;
    private MiBancoOperacional mbo;
    private ArrayAdapterCuentas<Cuenta> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posicion_global);
        mbo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        listaCuentas = (ListView) findViewById(R.id.listViewMovimientos);

        adaptador = new ArrayAdapterCuentas<>(this, mbo.getCuentas(cliente));
        listaCuentas.setAdapter(adaptador);
        listaCuentas.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(view.getContext(), Movimientos.class);
        intent.putExtra("Cuenta", mbo.getCuentas(cliente).get(i));
        startActivity(intent);
    }
}