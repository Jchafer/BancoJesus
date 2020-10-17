package practicas.simarro.bancojesus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton botonPosGlobal;
    private ImageButton botonIngresos;
    private ImageButton botonTransfer;
    private ImageButton botonCambClave;
    private ImageButton botonPromo;
    private ImageButton botonCajCercanos;
    private ImageButton botonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

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
            case "Posición global":
                /*intent = new Intent(view.getContext(), *.class);
                startActivity(intent);*/
                break;
            case "Ingresos":
                /*intent = new Intent(view.getContext(), *.class);
                startActivity(intent);*/
                break;
            case "Transferencias":
                /*intent = new Intent(view.getContext(), *.class);
                startActivity(intent);*/
                break;
            case "Cambiar Clave":
                intent = new Intent(view.getContext(), CambioContra.class);
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