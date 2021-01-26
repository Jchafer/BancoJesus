package practicas.simarro.bancojesus.principal;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.fonts.Font;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.support.v4.media.RatingCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.fragment.Fragment_Cuentas;
import practicas.simarro.bancojesus.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener{

    public static ImageButton botonPosGlobal;
    public static ImageButton botonIngresos;
    public static ImageButton botonTransfer;
    public static ImageButton botonCambClave;
    public static ImageButton botonPromo;
    public static ImageButton botonCajCercanos;
    public static ImageButton botonVolver;
    public static ArrayList<ImageButton> botones;
    private Cliente cliente;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_principal);
        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        TextView nombreCliente = (TextView) findViewById(R.id.txtAbSubTitulo);
        nombreCliente.setText(nombreCliente.getText().toString() + " " + cliente.getNombre() + " " + cliente.getApellidos());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Obtener referencia a los botones

        botones = new ArrayList<>();
        botones.add(botonPosGlobal = findViewById(R.id.btPosGlobal));
        botones.add(botonIngresos = findViewById(R.id.btIngresos));
        botones.add(botonTransfer = findViewById(R.id.btTransfer));
        botones.add(botonCambClave = findViewById(R.id.btCambiarClave));
        botones.add(botonPromo = findViewById(R.id.btPromociones));
        botones.add(botonCajCercanos = findViewById(R.id.btCajerosCercanos));
        botones.add(botonVolver = findViewById(R.id.btVolver));


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        colorFondoBotones(pref);
        fuenteTextoBotones(pref);

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
                intent.setClass(PrincipalActivity.this, CajerosActivity.class);
                break;
            case R.id.action_configuracion:
                intent.setClass(PrincipalActivity.this, PreferenceActivity.class);
                break;
        }

        intent.putExtra("Cliente", cliente);
        startActivity(intent);
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btVolver:
                intent.setClass(view.getContext(), MainActivity.class);
                break;
            case R.id.btPosGlobal:
                intent.setClass(view.getContext(), PosicionGlobal.class);
                break;
            case R.id.btIngresos:
                //intent.setClass(view.getContext(), *.class);
                break;
            case R.id.btTransfer:
                intent.setClass(view.getContext(), Transferencias.class);
                break;
            case R.id.btCambiarClave:
                intent.setClass(view.getContext(), CambioContra.class);
                break;
            case R.id.btPromociones:
                //intent.setClass(view.getContext(), *.class);
                break;
            case R.id.btCajerosCercanos:
                intent.setClass(view.getContext(), CajerosActivity.class);
                break;
        }
        intent.putExtra("Cliente", cliente);
        startActivity(intent);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void colorFondoBotones(SharedPreferences pref){
        if (!pref.getString("color_fondo_botones", "").isEmpty()){
            for (ImageButton boton : botones){
                boton.setBackgroundColor(Color.parseColor(pref.getString("color_fondo_botones", "")));
            }
        }else
            for (ImageButton boton : botones){
                boton.setScrollBarStyle(R.style.BotonesNormal);
            }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void fuenteTextoBotones(SharedPreferences pref) {
        if (!pref.getString("fuentes_letras_botones", "").isEmpty()){
            Typeface typeface = fuentePreferencia(pref);

            TextView textPos = (TextView) findViewById(R.id.txtPosicionGlobal);
            textPos.setTypeface(typeface);
            TextView textIng = (TextView) findViewById(R.id.txtIngresos);
            textIng.setTypeface(typeface);
            TextView textTran = (TextView) findViewById(R.id.txtTransferencias);
            textTran.setTypeface(typeface);
            TextView textCamb = (TextView) findViewById(R.id.txtCambiarClave);
            textCamb.setTypeface(typeface);
            TextView textPro = (TextView) findViewById(R.id.txtPromociones);
            textPro.setTypeface(typeface);
            TextView textCaj = (TextView) findViewById(R.id.txtCajeros);
            textCaj.setTypeface(typeface);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Typeface fuentePreferencia(SharedPreferences pref) {
        switch (pref.getString("fuentes_letras_botones", "")) {
            case "open":
                return getResources().getFont(R.font.opensans);
            case "alexa":
                return getResources().getFont(R.font.alexandria);
            case "bodoni":
                return getResources().getFont(R.font.bodoni);
            case "playball":
                return getResources().getFont(R.font.playball);
            case "remachine":
                return getResources().getFont(R.font.remachine);
        }
        return getResources().getFont(R.font.opensans);
    }

}