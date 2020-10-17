package practicas.simarro.bancojesus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AccesoActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usuario;
    private EditText contrasenya;
    private ImageButton inicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        usuario = findViewById(R.id.editTextDni);
        contrasenya = findViewById(R.id.editTextNumberPassword);
        inicioSesion = findViewById(R.id.btIniciar);

        inicioSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String user = "admin";
        String pass = "123456";

        if (usuario.getText().toString().equalsIgnoreCase(user)){
            if (contrasenya.getText().toString().equalsIgnoreCase(pass)){
                Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
                startActivity(intent);
            }
            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
    }
}