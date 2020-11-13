package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.fragment.Fragment_Cuentas;
import practicas.simarro.bancojesus.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textoBienvenida;
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

        textoBienvenida = findViewById(R.id.textBienvenida);
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

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        textoBienvenida.setText("Bienvenido/a " + cliente.getNombre() + " " + cliente.getApellidos());
        //Toast.makeText(this, "Cliente"+cliente.getNombre()+" "+cliente.getApellidos(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        String botonTag = ((ImageButton) view).getTag().toString();
        Intent intent;
        switch (botonTag){
            case "Volver":
                intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
                break;
            case "Posición Global":
                intent = new Intent(view.getContext(), PosicionGlobal.class);
                intent.putExtra("Cliente", cliente);
                startActivity(intent);
                break;
            case "Ingresos":
                /*intent = new Intent(view.getContext(), *.class);
                startActivity(intent);*/
                break;
            case "Transferencias":
                intent = new Intent(view.getContext(), Transferencias.class);
                intent.putExtra("Cliente", cliente);
                startActivity(intent);
                break;
            case "Cambiar Clave":
                intent = new Intent(view.getContext(), CambioContra.class);
                intent.putExtra("Cliente", cliente);
                startActivity(intent);
                break;
            case "Promociones":
                /*intent = new Intent(view.getContext(), *.class);
                startActivity(intent);*/
                break;
            case "Cajeros Cercanos":
                /*intent = new Intent(view.getContext(), *.class);
                startActivity(intent);*/
                break;
        }
    }
}