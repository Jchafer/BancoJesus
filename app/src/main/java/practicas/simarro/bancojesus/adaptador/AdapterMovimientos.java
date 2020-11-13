package practicas.simarro.bancojesus.adaptador;

import android.app.Activity;
import android.content.Context;
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
import practicas.simarro.bancojesus.pojo.Movimiento;

public class AdapterMovimientos extends ArrayAdapter<Movimiento> {
    Activity context;
    ArrayList<Movimiento> datos;

    public AdapterMovimientos(Fragment context, ArrayList<Movimiento> datos) {
        super(context.getActivity(), R.layout.layout_movimiento_list, datos);
        this.context = context.getActivity();
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_movimiento_list, null);
        TextView mov = (TextView) item.findViewById(R.id.movimiento);
        mov.setText(datos.get(position).toString());
        return (item);

        /*//Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
        //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.layout_movimiento_list,parent,false);
        }
        //Obteniendo instancias de los text views
        TextView movimiento = (TextView)listItemView.findViewById(R.id.movimiento);
        //Obteniendo instancia del ítem en la posición actual
        practicas.simarro.bancojesus.pojo.Movimiento item = (practicas.simarro.bancojesus.pojo.Movimiento) getItem(position);
        movimiento.setText("\n" + item.toString() + "\n");

        //Devolver al ListView la fila creada
        return listItemView;*/
    }
}
