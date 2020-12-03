package practicas.simarro.bancojesus.dialogos;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import practicas.simarro.bancojesus.R;
import practicas.simarro.bancojesus.pojo.Movimiento;

public class DialogoMovimiento extends DialogFragment {
    public String info;
    public TextView datos;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();


        View view = inflater.inflate(R.layout.layout_dialog_movimiento, null);
        datos = view.findViewById(R.id.tvDatos);
        datos.setText(info);
        //Log.i("///////", "Mov: " + importe.getText());

        builder.setView(inflater.inflate(R.layout.layout_dialog_movimiento, null)).
                setView(view).
                setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }
}
