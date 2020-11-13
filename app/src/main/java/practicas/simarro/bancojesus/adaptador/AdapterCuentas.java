package practicas.simarro.bancojesus.adaptador;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.pojo.Cuenta;

public class AdapterCuentas extends ArrayAdapter<Cuenta> {

    Activity context;
    ArrayList<Cuenta> datos;

    public AdapterCuentas(Fragment context, ArrayList<Cuenta> datos) {
        super(context.getActivity(), R.layout.layout_cuenta_list, datos);
        this.context = context.getActivity();
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_cuenta_list, null);
        TextView lblCuenta = (TextView) item.findViewById(R.id.cuenta);
        lblCuenta.setText(datos.get(position).getNumeroCuenta());
        TextView lblSaldo = (TextView) item.findViewById(R.id.saldo);
        String saldo = Float.toString(datos.get(position).getSaldoActual());
        lblSaldo.setText(saldo);
        //lblSaldo.setText(Float.toString(datos.get(position).getSaldoActual()));
        return (item);

        /*//Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
        //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.layout_cuenta_list,parent,false);
        }
        //Obteniendo instancias de los text views
        TextView cuenta = (TextView)listItemView.findViewById(R.id.movimiento);
        TextView saldo = (TextView)listItemView.findViewById(R.id.saldo);
        //Obteniendo instancia del ítem en la posición actual
        practicas.simarro.bancojesus.pojo.Cuenta item = (practicas.simarro.bancojesus.pojo.Cuenta) getItem(position);
        cuenta.setText(item.getNumeroCuenta());
        saldo.setText(Float.toString(item.getSaldoActual()));

        if (item.getSaldoActual()<0){
            saldo.setTextColor(Color.parseColor("#FFFF0009"));
        }else{
            saldo.setTextColor(Color.parseColor("#005004"));
        }

        //Devolver al ListView la fila creada
        return listItemView;*/
    }
}
