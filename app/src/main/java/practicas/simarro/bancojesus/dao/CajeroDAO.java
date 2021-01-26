package practicas.simarro.bancojesus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;

import practicas.simarro.bancojesus.bd.MiBD;
import practicas.simarro.bancojesus.pojo.Cliente;

public class CajeroDAO {
    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "cajeros" ;
    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID = "_id";
    public static final String C_COLUMNA_DIRECCION = "direccion";
    public static final String C_COLUMNA_LATITUD = "latitud";
    public static final String C_COLUMNA_LONGITUD = "longitud";
    public static final String C_COLUMNA_ZOOM = "zoom";
    private Context contexto;
    private MiBD miBD;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{ C_COLUMNA_ID, C_COLUMNA_DIRECCION, C_COLUMNA_LATITUD,
            C_COLUMNA_LONGITUD, C_COLUMNA_ZOOM} ;

    public CajeroDAO(Context context) {
        this.contexto = context;
    }

    public CajeroDAO abrir(){
        miBD = new MiBD(contexto);
        db = miBD.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        miBD.close();
    }

    /**
     * Devuelve un cursor con todas las filas y todas las columnas de la tabla hipotecas
     * SELECT * FROM hipotecas;
     */
    public Cursor getCursor() {
        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);
        return c;
    }

    /**
     * Devuelve un cursor con el resultado del select
     * @param id
     * @return
     */
    public Cursor getRegistro(long id){
        String condicion = C_COLUMNA_ID + "=" + id;
        Cursor c = db.query(true, C_TABLA, columnas, condicion, null, null, null, null,null);
        if (c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public long add(ContentValues contentValues) {
        return MiBD.getDB().insert("cajeros", null, contentValues);
    }

    public long update(ContentValues contentValues) {
        String condicion = C_COLUMNA_ID + "=" + contentValues.getAsString("_id");

        int resultado = MiBD.getDB().update("cajeros", contentValues, condicion, null);

        return resultado;
    }

    public void delete(long _id) {
        String condicion = C_COLUMNA_ID + "=" + _id;

        //Se borra el cliente indicado en el campo de texto
        MiBD.getDB().delete("cajeros", condicion, null);
    }

    public Cursor getAll() {
        Cursor cursor = MiBD.getDB().query("cajeros", columnas, null, null, null, null, null);
        return cursor;
    }

}
