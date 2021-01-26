package practicas.simarro.bancojesus.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.adaptador.AdapterMovimientos;
import practicas.simarro.bancojesus.bd.MiBancoOperacional;
import practicas.simarro.bancojesus.dialogos.DialogoMovimiento;
import practicas.simarro.bancojesus.pojo.Cuenta;
import practicas.simarro.bancojesus.pojo.Movimiento;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Movimientos_Todos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Movimientos_Todos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<Movimiento> movimientos;
    private String txtTitulo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Movimientos_Todos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Movimientos_Todos.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Movimientos_Todos newInstance(String param1, String param2) {
        Fragment_Movimientos_Todos fragment = new Fragment_Movimientos_Todos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movimientos = (ArrayList<Movimiento>) getArguments().get("Movimientos");
        txtTitulo = (String) getArguments().get("txtTitulo");

        View myInflatedView = inflater.inflate(R.layout.fragment__movimientos__todos, container, false);

        ListView listadoMovimientos = (ListView) myInflatedView.findViewById(R.id.listViewMovimientosTodos);
        TextView titulo = (TextView) myInflatedView.findViewById(R.id.txtMovimientos);

        listadoMovimientos.setAdapter(new AdapterMovimientos(this, movimientos));
        titulo.setText(titulo.getText() + " (" + txtTitulo + ")");

        //getActivity().getLayoutInflater();

        listadoMovimientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogoMovimiento dialogo = new DialogoMovimiento();
                dialogo.info = ((Movimiento) list.getItemAtPosition(pos)).mostrarDatos();

                dialogo.show(fragmentManager, "tagMovimiento");
            }
        });
        return myInflatedView;
    }

}