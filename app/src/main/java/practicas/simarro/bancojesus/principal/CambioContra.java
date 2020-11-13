package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.pojo.Cliente;

public class CambioContra extends AppCompatActivity implements View.OnClickListener {

    private EditText claveActual;
    private EditText claveNueva;
    private ImageButton aplicarCambio;
    private Cliente cliente;
    private MiBancoOperacional mbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_cambio_contra);
        mbo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        claveActual = findViewById(R.id.editTextClaveActual);
        claveNueva = findViewById(R.id.editTextClaveNueva);
        aplicarCambio = findViewById(R.id.btAceptar);

        aplicarCambio.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (claveActual.getText().toString().equalsIgnoreCase(cliente.getClaveSeguridad())){
            cliente.setClaveSeguridad(claveNueva.getText().toString());

            if (mbo.changePassword(cliente) == 1) {
                Toast.makeText(this, "La contraseña se ha cambiado correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "La contraseña no se ha cambiado correctamente", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);

        }else{
            Toast.makeText(this, "La contraseña actual no coincide", Toast.LENGTH_SHORT).show();
        }

    }
}