package practicas.simarro.bancojesus.principal;

import androidx.appcompat.app.AppCompatActivity;
import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.bd.Constantes;
import practicas.simarro.bancojesus.dao.CajeroDAO;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.EditText;

public class GestionCajeroActivity extends AppCompatActivity {

    private CajeroDAO cajeroDAO;
    private Cursor cursor;
    private int modo;
    private long id;

    private EditText direccion;
    private EditText latitud;
    private EditText longitud;
    private EditText zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_cajero);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        if (extra == null) return;
        //
        // Obtenemos los elementos de la vista
        //
        direccion = (EditText) findViewById(R.id.direccion);
        latitud = (EditText) findViewById(R.id.latitud);
        longitud = (EditText) findViewById(R.id.longitud);
        zoom = (EditText) findViewById(R.id.zoom);
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
    }

    // Consultamos por el id
    private void consultar(long id){
        cursor = cajeroDAO.getRegistro(id);

        direccion.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_DIRECCION)));
        latitud.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_LATITUD)));
        longitud.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_LONGITUD)));
        zoom.setText(cursor.getString(cursor.getColumnIndex(CajeroDAO.C_COLUMNA_ZOOM)));
    }

    private void establecerModo(int m) {
        this.modo = m;

        // Si estamos solamente visualizando establecemos el modo edicion desactivado a todo el formulario
        if (modo == Constantes.C_VISUALIZAR) {
            this.setTitle(direccion.getText().toString());
            this.setEdicion(false);
        }
    }

    private void setEdicion(boolean opcion) {
        direccion.setEnabled(opcion);
        latitud.setEnabled(opcion);
        longitud.setEnabled(opcion);
        zoom.setEnabled(opcion);
    }
}