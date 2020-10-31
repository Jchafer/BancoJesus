package practicas.simarro.bancojesus;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ArrayAdapterMovimientos<T> extends ArrayAdapter<T> {
    public ArrayAdapterMovimientos(Context context, List<T> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
        //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.movimiento_list,parent,false);
        }
        //Obteniendo instancias de los text views
        TextView movimiento = (TextView)listItemView.findViewById(R.id.movimiento);
        //Obteniendo instancia del ítem en la posición actual
        practicas.simarro.bancojesus.pojo.Movimiento item = (practicas.simarro.bancojesus.pojo.Movimiento) getItem(position);
        movimiento.setText("\n" + item.toString() + "\n");

        //Devolver al ListView la fila creada
        return listItemView;
    }
}
