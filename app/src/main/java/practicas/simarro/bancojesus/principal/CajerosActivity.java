package practicas.simarro.bancojesus.principal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterCajero;
import practicas.simarro.bancojesus.bd.Constantes;
import practicas.simarro.bancojesus.dao.CajeroDAO;
import practicas.simarro.bancojesus.pojo.Cliente;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CajerosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView lista;
    private CajeroDAO cajeroDAO;
    private AdapterCajero adapterCajero;
    private Cursor cursor;
    private TextView v_txtSinDatos;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cajeros);

        crearToolbar();

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        lista = (ListView) findViewById(R.id.lista);

        // Creamos la clase que nos permitira acceder a las operaciones de la db
        cajeroDAO = new CajeroDAO(this);

        try {
            // Abrimos la base de datos
            cajeroDAO.abrir();
            // Obtenemos el cursor
            cursor = cajeroDAO.getCursor();
            // Se indica que a la Actividad principal que controle los recursos
            // cursor. Es decir, si se termina la Actividad, se elimina este cursor de la memoria
            startManagingCursor(cursor);
            // Creamos el adaptador
            adapterCajero = new AdapterCajero(this, cursor);
            // Asignamos el adaptador a la lista
            lista.setAdapter(adapterCajero);
            lista.setOnItemClickListener(this);
            // Si hay datos no se muestra la etiqueta de Sin Datos
            if(cursor.getCount()>0){
                v_txtSinDatos = (TextView) findViewById(R.id.txtSinDatos);
                v_txtSinDatos.setVisibility(View.INVISIBLE);
                v_txtSinDatos.invalidate();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Se ha producido un error al abrir la base de datos.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (cliente.isAdmin()) getMenuInflater().inflate(R.menu.menu_crear_cajero, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        // Creamos el intent para abrir el formulario de hipotecas
        Intent i = new Intent(CajerosActivity.this, GestionCajeroActivity.class);
        // Le pasamos que el modo en que lo vamos a abrir es solo de visualizacion
        i.putExtra(Constantes.C_MODO, Constantes.C_VISUALIZAR);
        // Le pasamos el valor del identificador de la hipoteca
        i.putExtra(CajeroDAO.C_COLUMNA_ID, id);
        // Pasamos el cliente
        i.putExtra("Cliente", cliente);
        // Iniciamos la actividad esperando un resultado, que en este caso no nos importa cual sea
        startActivityForResult(i, Constantes.C_VISUALIZAR);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent i;
        switch (item.getItemId()) {
            case R.id.menu_crearCajero:
                i = new Intent(CajerosActivity.this, GestionCajeroActivity.class);
                i.putExtra(Constantes.C_MODO, Constantes.C_CREAR);
                i.putExtra("Cliente", cliente);
                startActivityForResult(i, Constantes.C_CREAR);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //
        // Nos aseguramos que es la petición que hemos realizado
        //
        switch (requestCode) {
            case Constantes.C_CREAR:
                if (resultCode == RESULT_OK)
                    recargar_lista();
            case Constantes.C_VISUALIZAR:
                if (resultCode == RESULT_OK)
                    recargar_lista();
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void recargar_lista() {
        CajeroDAO cajeroDAO = new CajeroDAO(getBaseContext());
        cajeroDAO.abrir();
        AdapterCajero adapterCajero = new AdapterCajero(this, cajeroDAO.getCursor());
        lista.setAdapter(adapterCajero);
    }

    private void crearToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        TextView nombreCliente = (TextView) findViewById(R.id.txtAbSubTitulo);
        nombreCliente.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}