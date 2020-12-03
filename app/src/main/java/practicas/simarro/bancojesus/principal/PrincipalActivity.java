package practicas.simarro.bancojesus.principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.fragment.Fragment_Cuentas;
import practicas.simarro.bancojesus.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton botonPosGlobal;
    private ImageButton botonIngresos;
    private ImageButton botonTransfer;
    private ImageButton botonCambClave;
    private ImageButton botonPromo;
    private ImageButton botonCajCercanos;
    private ImageButton botonVolver;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_principal);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        TextView nombreCliente = (TextView) findViewById(R.id.txtAbSubTitulo);
        nombreCliente.setText("Bienvenido/a " + cliente.getNombre() + " " + cliente.getApellidos());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        botonPosGlobal = findViewById(R.id.btPosGlobal);
        botonIngresos = findViewById(R.id.btIngresos);
        botonTransfer = findViewById(R.id.btTransfer);
        botonCambClave = findViewById(R.id.btCambiarClave);
        botonPromo = findViewById(R.id.btPromociones);
        botonCajCercanos = findViewById(R.id.btCajerosCercanos);
        botonVolver = findViewById(R.id.btVolver);

        botonPosGlobal.setOnClickListener(this);
        botonIngresos.setOnClickListener(this);
        botonTransfer.setOnClickListener(this);
        botonCambClave.setOnClickListener(this);
        botonPromo.setOnClickListener(this);
        botonCajCercanos.setOnClickListener(this);
        botonVolver.setOnClickListener(this);



        //textoBienvenida.setText("Bienvenido/a " + cliente.getNombre() + " " + cliente.getApellidos());
        //Toast.makeText(this, "Cliente"+cliente.getNombre()+" "+cliente.getApellidos(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.action_posicion_global:
                intent.setClass(PrincipalActivity.this, PosicionGlobal.class);
                break;
            case R.id.action_ingresos:
                //intent.setClass(PrincipalActivity.this, *.class);
                break;
            case R.id.action_transferencias:
                intent.setClass(PrincipalActivity.this, Transferencias.class);
                break;
            case R.id.action_cambiar_clave:
                intent.setClass(PrincipalActivity.this, CambioContra.class);
                break;
            case R.id.action_promociones:
                //intent.setClass(PrincipalActivity.this, *.class);
                break;
            case R.id.action_cajeros_cercanos:
                //intent.setClass(PrincipalActivity.this, *.class);
                break;
            case R.id.action_configuracion:
                //intent.setClass(PrincipalActivity.this, *.class);
                break;
        }

        intent.putExtra("Cliente", cliente);
        startActivity(intent);
        return true;
    }

    @Override
    public void onClick(View view) {
        String botonTag = ((ImageButton) view).getTag().toString();
        Intent intent = new Intent();
        switch (botonTag){
            case "Volver":
                intent.setClass(view.getContext(), MainActivity.class);
                break;
            case "Posición Global":
                intent.setClass(view.getContext(), PosicionGlobal.class);
                break;
            case "Ingresos":
                //intent.setClass(view.getContext(), *.class);
                break;
            case "Transferencias":
                intent.setClass(view.getContext(), Transferencias.class);
                break;
            case "Cambiar Clave":
                intent.setClass(view.getContext(), CambioContra.class);
                break;
            case "Promociones":
                //intent.setClass(view.getContext(), *.class);
                break;
            case "Cajeros Cercanos":
                //intent.setClass(view.getContext(), *.class);
                break;
        }
        intent.putExtra("Cliente", cliente);
        startActivity(intent);
    }
}