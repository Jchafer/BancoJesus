package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.pojo.Cliente;

public class AccesoActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private EditText usuario;
    private EditText contrasenya;
    private ImageButton inicioSesion;
    private MiBancoOperacional mbo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_acceso);
        mbo = MiBancoOperacional.getInstance(this);

        usuario = findViewById(R.id.editTextDni);
        contrasenya = findViewById(R.id.editTextNumberPassword);
        inicioSesion = findViewById(R.id.btAceptar);

        inicioSesion.setOnClickListener(this);

        usuario.setText("11111111A");
        contrasenya.setText("1234");
    }

    @Override
    public void onClick(View view) {

        Cliente cliente = new Cliente();
        cliente.setNif(usuario.getText().toString());
        cliente.setClaveSeguridad(contrasenya.getText().toString());

        cliente = mbo.login(cliente);

        cliente.setListaCuentas(mbo.getCuentas(cliente));

        if (cliente != null) {
            Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
        }
        /*String user = "1";
        String pass = "1";

        if (usuario.getText().toString().equalsIgnoreCase(user)){
            if (contrasenya.getText().toString().equalsIgnoreCase(pass)){
                Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
        }*/
    }
}