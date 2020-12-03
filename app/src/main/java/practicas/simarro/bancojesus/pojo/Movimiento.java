package practicas.simarro.bancojesus.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Movimiento implements Serializable {

    private int id;
    private int tipo;
    private Date fechaOperacion;
    private String descripcion;
    private float importe;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movimiento(int id, int tipo, Date fechaOperacion, String descripcion, float importe, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        this.id = id;
        this.tipo = tipo;
        this.fechaOperacion = fechaOperacion;
        this.descripcion = descripcion;
        this.importe = importe;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public Movimiento(int tipo, Date fechaOperacion, String descripcion, float importe, Cuenta cuentaOrigen, Cuenta cuentaDestino) {
        this.tipo = tipo;
        this.fechaOperacion = fechaOperacion;
        this.descripcion = descripcion;
        this.importe = importe;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public Movimiento(){
        super();
    }

    @Override
    public String toString(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "\nFecha operacion: " + formateador.format(this.fechaOperacion) + "\nDescripcion: " + this.descripcion + "\nImporte: " +
                this.importe + "\n";
    }

    public String mostrarDatos(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "\nFecha operacion: " + formateador.format(this.fechaOperacion) + "\nDescripcion: " + this.descripcion + "\nImporte: " +
                this.importe + "\n" + "\nCuenta origen: " + this.cuentaOrigen + "\nCuenta destino: " + this.cuentaDestino + "\n";
    }
    /*@Override
    public String toString(){
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        return "ID: " + this.id + "\nTipo: " + this.tipo + "\nFecha operacion: " + formateador.format(this.fechaOperacion) + "\nDescripcion: " + this.descripcion + "\nImporte: " +
                this.importe + "\nID cuenta origen: " + this.cuentaOrigen.getId() + "\nID cuenta destino: " + this.cuentaDestino.getId();
    }*/
}
