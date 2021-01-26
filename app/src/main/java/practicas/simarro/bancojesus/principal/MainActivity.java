package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

import practicas.simarro.bancojesus.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton botonAcceder;
    SharedPreferences prefsManager;
    Locale localizacion;
    MediaPlayer mediaPlayer;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsManager = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        if (!prefsManager.getString("idioma", "").isEmpty()){
            String idioma = prefsManager.getString("idioma", "");

            if (idioma.equalsIgnoreCase("ESP"))
                localizacion = new Locale("es", "ES");
            else if (idioma.equalsIgnoreCase("ENG"))
                localizacion = new Locale("en", "US");

            Locale.setDefault(localizacion);
            Configuration config = new Configuration();
            config.locale = localizacion;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        }
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        if (pref.getBoolean("reproducirMusica", false)){
            mediaPlayer = MediaPlayer.create(this, R.raw.sound_relax);
            mediaPlayer.start();
        }


        /*prefs = getSharedPreferences("preferenciasbancarias", Context.MODE_PRIVATE);

        if (prefs.getBoolean("musica", false))
            mediaPlayer.start();
        else
            mediaPlayer.stop();*/



        setContentView(R.layout.layout_activity_main);

        botonAcceder = (ImageButton) findViewById(R.id.btAcceder);

        botonAcceder.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), AccesoActivity.class);
        startActivity(intent);
    }

}