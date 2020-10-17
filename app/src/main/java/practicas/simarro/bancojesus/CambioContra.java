package practicas.simarro.bancojesus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class CambioContra extends AppCompatActivity implements View.OnClickListener {

    private EditText claveActual;
    private EditText claveNueva;
    private ImageButton aplicarCambio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contra);

        claveActual = findViewById(R.id.editTextDni);
        claveNueva = findViewById(R.id.editTextNumberPassword);
        aplicarCambio = findViewById(R.id.btAplicarCambio);

        aplicarCambio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String clave = "123456"; // Dato que estará en una base de datos
        String claveCambiada; // Dato que estará en una base de datos
        if (claveActual.getText().toString().equalsIgnoreCase(clave)){
            claveCambiada = claveNueva.getText().toString();
            Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
            startActivity(intent);
        }

    }
}