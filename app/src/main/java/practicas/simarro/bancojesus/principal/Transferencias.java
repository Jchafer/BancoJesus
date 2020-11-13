package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdaptadorCuentas;
import practicas.simarro.bancojesus.adaptador.AdapterCuentas;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.pojo.Cliente;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

import static android.widget.CompoundButton.*;

public class Transferencias extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener, OnCheckedChangeListener{

    private GridView grdOpciones;
    private RadioButton btPropia;
    private RadioButton btAjena;
    private Spinner cuentaPropia;
    private EditText cuentaAjena;
    private CheckBox enviarJustificante;
    private ImageButton btAceptar;
    private ImageButton btCancelar;
    private EditText importe;
    private EditText descripcion;
    private Spinner divisa;

    private Cliente cliente;
    private MiBancoOperacional mbo;
    private AdaptadorCuentas<Cuenta> adaptadorCuentas;

    private String[] divisas = new String[2];

    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Movimiento movimiento;
    private Double importeTrans;
    private String justificanteEnviado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_transferencias);
        mbo = MiBancoOperacional.getInstance(this);

        grdOpciones = (GridView) findViewById(R.id.gdCuentas);
        btPropia = (RadioButton) findViewById(R.id.btPropia);
        btAjena = (RadioButton) findViewById(R.id.btAjena);
        cuentaPropia = (Spinner) findViewById(R.id.spCuentas);
        cuentaAjena = (EditText) findViewById(R.id.editTextCuentaAjena);
        enviarJustificante = (CheckBox) findViewById(R.id.checkBoxJustificante);
        btAceptar = (ImageButton) findViewById(R.id.btAceptar);
        btCancelar = (ImageButton) findViewById(R.id.btCancelar);
        importe = (EditText) findViewById(R.id.editTextTextImporte);
        descripcion = (EditText) findViewById(R.id.editTextDescripcion);
        divisa = (Spinner) findViewById(R.id.spDivisa);

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        divisas [0] = "Euros";
        divisas [1] = "Dolares";


        //ArrayAdapter<String> adaptadorGrd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cuentas);
        adaptadorCuentas = new AdaptadorCuentas<>(this, mbo.getCuentas(cliente));
        grdOpciones.setAdapter(adaptadorCuentas);
        grdOpciones.setOnItemClickListener(this);

        btPropia.setOnCheckedChangeListener((OnCheckedChangeListener) this);

        btAjena.setOnCheckedChangeListener((OnCheckedChangeListener) this);

        //Toast.makeText(getApplicationContext(),"Entra"+ cliente.getListaCuentas(), Toast.LENGTH_SHORT).show();
        //ArrayAdapter<String> adaptadorSpCuentas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cuentas);
        ArrayAdapter<Cuenta> adaptadorSpCuentas = new ArrayAdapter<Cuenta>(this, android.R.layout.simple_spinner_dropdown_item, mbo.getCuentas(cliente));
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
        cuentaOrigen = (Cuenta) parent.getAdapter().getItem(position);
    }

    @Override
    public void onClick(View view) {
        if (view.getTag().equals("Aceptar")){
            if (comprobarDatos()){
                Toast.makeText(getApplicationContext(),"Cuenta origen -> "+cuentaOrigen.getNumeroCuenta()+
                                                            "\nCuenta destino -> "+cuentaDestino.getNumeroCuenta()+
                                                            "\nImporte -> "+importeTrans+
                                                            "\nJustificante enviado -> "+justificanteEnviado, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
                intent.putExtra("Cliente", cliente);
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
                descripcion.setVisibility(View.VISIBLE);
            }else{
                cuentaPropia.setVisibility(View.INVISIBLE);
            }
            if (buttonView.getTag().equals("Ajena")){
                cuentaAjena.setVisibility(View.VISIBLE);
                importe.setVisibility(View.VISIBLE);
                descripcion.setVisibility(View.VISIBLE);
                divisa.setVisibility(View.VISIBLE);
            }else{
                cuentaAjena.setVisibility(View.INVISIBLE);
                divisa.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void obtenerCuentaDestino(){
        if(btPropia.isChecked()){
            cuentaDestino = (Cuenta) cuentaPropia.getSelectedItem();
        }else if(btAjena.isChecked()){
            Cuenta cuentaDest = new Cuenta();
            cuentaDest.setNumeroCuenta(cuentaAjena.getText().toString());
            cuentaDestino = cuentaDest;
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
        /*for (int i = 0; i < mbo.getCuentas(cliente).size(); i++) {
            grdOpciones.getChildAt(i).setBackgroundColor(0);
        }*/
        btPropia.setChecked(false);
        btAjena.setChecked(false);
        cuentaPropia.setVisibility(View.GONE);
        cuentaAjena.setVisibility(View.GONE);
        cuentaAjena.setText("");
        importe.setVisibility(View.GONE);
        importe.setText("");
        descripcion.setVisibility(View.GONE);
        descripcion.setText("");
        divisa.setVisibility(View.GONE);
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