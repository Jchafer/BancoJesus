package practicas.simarro.bancojesus.adaptador;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.pojo.Cuenta;

public class AdaptadorCuentas<T> extends ArrayAdapter<T> {

    public AdaptadorCuentas(Context context, List<T> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.layout_cuenta_list,parent,false);
        }
        //Obteniendo instancias de los text views
        TextView cuenta = (TextView)listItemView.findViewById(R.id.cuenta);
        TextView saldo = (TextView)listItemView.findViewById(R.id.saldo);
        //Obteniendo instancia del ítem en la posición actual
        Cuenta item = (Cuenta) getItem(position);
        cuenta.setText(item.getNumeroCuenta());
        saldo.setText(Float.toString(item.getSaldoActual()));

        if (item.getSaldoActual()<0){
            saldo.setTextColor(Color.parseColor("#FFFF0009"));
        }else{
            saldo.setTextColor(Color.parseColor("#005004"));
        }

        //Devolver al ListView la fila creada
        return listItemView;
    }
}