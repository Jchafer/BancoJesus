package practicas.simarro.bancojesus.principal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.bd.Constantes;
import practicas.simarro.bancojesus.dao.CajeroDAO;
import practicas.simarro.bancojesus.pojo.Cliente;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GestionCajeroActivity extends AppCompatActivity {

    private CajeroDAO cajeroDAO;
    private Cursor cursor;
    private int modo;
    private long id;
    private Cliente cliente;

    private EditText direccion;
    private EditText latitud;
    private EditText longitud;
    private EditText zoom;

    private TextView txtInfCajero;

    private Button boton_guardar;
    private Button boton_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cajero);

        crearToolbar();

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if (extra == null) return;

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        //
        // Obtenemos los elementos de la vista
        //
        direccion = (EditText) findViewById(R.id.direccion);
        latitud = (EditText) findViewById(R.id.latitud);
        longitud = (EditText) findViewById(R.id.longitud);
        zoom = (EditText) findViewById(R.id.zoom);

        txtInfCajero = (TextView) findViewById(R.id.txtInfCajero);

        boton_guardar = (Button) findViewById(R.id.boton_guardar);
        boton_cancelar = (Button) findViewById(R.id.boton_cancelar);

        //
        // Creamos el DAO
        //
        cajeroDAO = new CajeroDAO(this);
        try {
            cajeroDAO.abrir();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Obtenemos el identificador del registro
        if (extra.containsKey(cajeroDAO.C_COLUMNA_ID)){
            id = extra.getLong(cajeroDAO.C_COLUMNA_ID);
            consultar(id);
        }

        // Establecemos el modo del formulario
        establecerModo(extra.getInt(Constantes.C_MODO));

        //
        // Definimos las acciones para los dos botones
        //
        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        boton_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelar();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        if (cliente.isAdmin()) {
            if (modo == Constantes.C_VISUALIZAR) {
                getMenuInflater().inflate(R.menu.menu_modificar_borrar_cajero, menu);
                boton_guardar.setVisibility(View.GONE);
                boton_cancelar.setVisibility(View.GONE);
            } else {
                getMenuInflater().inflate(R.menu.menu_guardar_cancelar_cajero, menu);
                boton_guardar.setVisibility(View.VISIBLE);
                boton_cancelar.setVisibility(View.VISIBLE);
            }
        }else{
            boton_guardar.setVisibility(View.GONE);
            boton_cancelar.setVisibility(View.GONE);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_modificarCajero:
                establecerModo(Constantes.C_EDITAR);
                boton_guardar.setVisibility(View.VISIBLE);
                boton_cancelar.setVisibility(View.VISIBLE);
                return true;
            case R.id.menu_eliminarCajero:
                borrar(id);
                return true;
            case R.id.menu_cancelar:
                cancelar();
                return true;
            case R.id.menu_guardar:
                guardar();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Crear Toolbar
    private void crearToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        TextView nombreCliente = (TextView) findViewById(R.id.txtAbSubTitulo);
        nombreCliente.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    // Consultamos por el id
    private void consultar(long id){
        cursor = cajeroDAO.getRegistro(id);

        direccion.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_DIRECCION)));
        latitud.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_LATITUD)));
        longitud.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_LONGITUD)));
        zoom.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_ZOOM)));
    }

    private void setEdicion(boolean opcion) {
        direccion.setEnabled(opcion);
        latitud.setEnabled(opcion);
        longitud.setEnabled(opcion);
        zoom.setEnabled(opcion);
    }

    private void establecerModo(int m) {
        this.modo = m;

        // Si estamos solamente visualizando establecemos el modo edicion desactivado a todo el formulario
        if (modo == Constantes.C_VISUALIZAR) {
            this.setEdicion(false);
        } else if (modo == Constantes.C_CREAR) {
            txtInfCajero.setText(R.string.cajero_crear_titulo);
            this.setEdicion(true);
        } else if (modo == Constantes.C_EDITAR) {
            txtInfCajero.setText(R.string.cajero_editar_titulo);
            this.setEdicion(true);
        }
    }

    private void guardar() {
        //
        // Obtenemos los datos del formulario
        //
        ContentValues reg = new ContentValues();

        if (modo == Constantes.C_EDITAR) {
            reg.put(CajeroDAO.C_COLUMNA_ID, id);
        }

        reg.put(CajeroDAO.C_COLUMNA_DIRECCION, direccion.getText().toString());
        reg.put(CajeroDAO.C_COLUMNA_LATITUD, latitud.getText().toString());
        reg.put(CajeroDAO.C_COLUMNA_LONGITUD, longitud.getText().toString());
        reg.put(CajeroDAO.C_COLUMNA_ZOOM, zoom.getText().toString());
        if (modo == Constantes.C_CREAR) {
            cajeroDAO.add(reg);
            Toast.makeText(GestionCajeroActivity.this, R.string.cajero_crear_confirmacion,
                    Toast.LENGTH_SHORT).show();
        }else if (modo == Constantes.C_EDITAR) {
            Toast.makeText(GestionCajeroActivity.this, R.string.cajero_editar_confirmacion,
                    Toast.LENGTH_SHORT).show();
            cajeroDAO.update(reg);
        }
        //
        // Devolvemos el control
        //
        setResult(RESULT_OK);
        finish();
    }

    private void cancelar(){
        setResult(RESULT_CANCELED, null);
        finish();
    }

    private void borrar(final long id)
    {
        /**
         * Borramos el registro con confirmación
         */
        AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(this);
        dialogEliminar.setIcon(android.R.drawable.ic_dialog_alert);
        dialogEliminar.setTitle(getResources().getString(R.string.cajero_eliminar_titulo));
        dialogEliminar.setMessage(getResources().getString(R.string.cajero_eliminar_mensaje));
        dialogEliminar.setCancelable(false);
        dialogEliminar.setPositiveButton(getResources().getString(android.R.string.ok), new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int boton) {
                        cajeroDAO.delete(id);
                        Toast.makeText(GestionCajeroActivity.this, R.string.cajero_eliminar_confirmacion,
                                Toast.LENGTH_SHORT).show();
                        /**
                         * Devolvemos el control
                         */
                        setResult(RESULT_OK);
                        finish();
                    }
                });
        dialogEliminar.setNegativeButton(android.R.string.no, null);

        dialogEliminar.show();
    }
}