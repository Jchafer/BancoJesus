package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.pojo.Cliente;

public class Configuracion extends AppCompatActivity implements View.OnClickListener {

    CheckBox reproducirMusica;
    CheckBox reproducirVideo;
    Button btPreferencias;
    ImageButton btGuardar;
    SharedPreferences prefs;
    Cliente cliente;

    MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        reproducirMusica = findViewById(R.id.checkBoxReproducirMusica);
        reproducirVideo = findViewById(R.id.checkBoxReproducirVideo);
        btPreferencias = findViewById(R.id.btPreferencias);
        btGuardar = findViewById(R.id.btGuardar);

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        prefs = getSharedPreferences("preferenciasbancarias", Context.MODE_PRIVATE);

        File fichero = new File("/data/data/practicas.simarro.bancojesus/shared_prefs/preferenciasbancarias.xml");
        if (fichero.exists()){
            reproducirMusica.setChecked(prefs.getBoolean("musica", false));
            reproducirVideo.setChecked(prefs.getBoolean("video", false));
        }

        btGuardar.setOnClickListener(this);
        btPreferencias.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == btGuardar.getId()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("musica", reproducirMusica.isChecked());
            editor.putBoolean("video", reproducirVideo.isChecked());
            editor.commit();

            intent = new Intent(view.getContext(), PrincipalActivity.class);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);
        }else if (view.getId() == btPreferencias.getId()){
            intent = new Intent(view.getContext(), PreferenceActivity.class);
            startActivity(intent);
        }


    }

}