package practicas.simarro.bancojesus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import static android.widget.CompoundButton.*;

public class Transferencias extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener, OnCheckedChangeListener{

    GridView grdOpciones;
    RadioButton btPropia;
    RadioButton btAjena;
    Spinner cuentaPropia;
    EditText cuentaAjena;
    CheckBox enviarJustificante;
    ImageButton btAceptar;
    ImageButton btCancelar;
    EditText importe;
    Spinner divisa;

    String[] cuentas = new String[4];
    String[] divisas = new String[2];

    String cuentaOrigen = null;
    String cuentaDestino = null;
    Double importeTrans = null;
    String justificanteEnviado = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);

        grdOpciones = (GridView) findViewById(R.id.gdCuentas);
        btPropia = (RadioButton) findViewById(R.id.btPropia);
        btAjena = (RadioButton) findViewById(R.id.btAjena);
        cuentaPropia = (Spinner) findViewById(R.id.spCuentas);
        cuentaAjena = (EditText) findViewById(R.id.editTextCuentaAjena);
        enviarJustificante = (CheckBox) findViewById(R.id.checkBoxJustificante);
        btAceptar = (ImageButton) findViewById(R.id.btAceptar);
        btCancelar = (ImageButton) findViewById(R.id.btCancelar);
        importe = (EditText) findViewById(R.id.editTextTextImporte);
        divisa = (Spinner) findViewById(R.id.spDivisa);

        cuentas [0] = "ES38.....0132";
        cuentas [1] = "ES38.....3671";
        cuentas [2] = "ES37.....4678";
        cuentas [3] = "ES39.....8273";

        divisas [0] = "Euros";
        divisas [1] = "Dolares";


        ArrayAdapter<String> adaptadorGrd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cuentas);
        grdOpciones.setAdapter(adaptadorGrd);
        grdOpciones.setOnItemClickListener(this);

        btPropia.setOnCheckedChangeListener((OnCheckedChangeListener) this);

        btAjena.setOnCheckedChangeListener((OnCheckedChangeListener) this);

        ArrayAdapter<String> adaptadorSpCuentas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cuentas);
        adaptadorSpCuentas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuentaPropia.setAdapter(adaptadorSpCuentas);

        ArrayAdapter<String> adaptadorSpDivisas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, divisas);
        adaptadorSpDivisas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        divisa.setAdapter(adaptadorSpDivisas);

        btAceptar.setOnClickListener(this);
        btCancelar.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, android.view.View v, int position, long id) {
        for (int i = 0; i < cuentas.length; i++) {
            if (i==position){
                cuentaOrigen = cuentas[i];
                v.setBackgroundColor(Color.parseColor("#FF018786"));

            }else{
                grdOpciones.getChildAt(i).setBackgroundColor(0);
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getTag().equals("Aceptar")){
            if (comprobarDatos()){
                Toast.makeText(getApplicationContext(),"Cuenta origen -> "+cuentaOrigen+
                                                            "\nCuenta destino -> "+cuentaDestino+
                                                            "\nImporte -> "+importeTrans+
                                                            "\nJustificante enviado -> "+justificanteEnviado, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"Transferencia no realizada", Toast.LENGTH_SHORT).show();
            }

        }else if(view.getTag().equals("Cancelar")){
            reiniciarDatos();
            Toast.makeText(getApplicationContext(),"Datos reiniciados ", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            if (buttonView.getTag().equals("Propia")){
                cuentaPropia.setVisibility(View.VISIBLE);
                importe.setVisibility(View.VISIBLE);
            }else{
                cuentaPropia.setVisibility(View.INVISIBLE);
            }
            if (buttonView.getTag().equals("Ajena")){
                cuentaAjena.setVisibility(View.VISIBLE);
                importe.setVisibility(View.VISIBLE);
                divisa.setVisibility(View.VISIBLE);
            }else{
                cuentaAjena.setVisibility(View.INVISIBLE);
                divisa.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void obtenerCuentaDestino(){
        if(btPropia.isChecked()){
            cuentaDestino = cuentaPropia.getSelectedItem().toString();
        }else if(btAjena.isChecked()){
            cuentaDestino = cuentaAjena.getText().toString();
        }
    }

    public void comprobarJustificante(){
        if(enviarJustificante.isChecked()){
            justificanteEnviado = "Si";
        }else{
            justificanteEnviado = "No";
        }
    }

    public boolean comprobarDatos(){
        if(cuentaOrigen!=null){
            if (btPropia.isChecked() || btAjena.isChecked()){
                obtenerCuentaDestino();
                if (!importe.getText().toString().isEmpty()){
                    importeTrans = Double.parseDouble(importe.getText().toString());
                    comprobarJustificante();
                    return true;
                }else{
                    Toast.makeText(getApplicationContext(),"No hay ningún importe introducido ", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"No hay ninguna cuenta destino seleccionada ", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),"No hay ninguna cuenta origen seleccionada ", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void reiniciarDatos(){
        /*for (int i = 0; i < cuentas.length; i++) {
            grdOpciones.getChildAt(i).setBackgroundColor(0);
        }*/
        btPropia.setChecked(false);
        btAjena.setChecked(false);
        cuentaPropia.setVisibility(View.INVISIBLE);
        cuentaAjena.setVisibility(View.INVISIBLE);
        cuentaAjena.setText("");
        importe.setVisibility(View.INVISIBLE);
        divisa.setVisibility(View.INVISIBLE);
        importe.setText("");
        enviarJustificante.setChecked(false);
        cuentaOrigen = null;
    }

    /*public void radioButtonChecked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.btPropia:
                if (checked)
                    Toast.makeText(getApplicationContext(),"Propia checked ", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.btAjena:
                if (checked)
                    Toast.makeText(getApplicationContext(),"Propia checked ", Toast.LENGTH_SHORT).show();
                    break;
        }
    }*/
}