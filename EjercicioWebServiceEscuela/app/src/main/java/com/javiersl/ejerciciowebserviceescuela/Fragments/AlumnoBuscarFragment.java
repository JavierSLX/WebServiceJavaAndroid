package com.javiersl.ejerciciowebserviceescuela.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.javiersl.ejerciciowebserviceescuela.Clases.Alumno;
import com.javiersl.ejerciciowebserviceescuela.DAO.DAOAlumno;
import com.javiersl.ejerciciowebserviceescuela.R;

import java.util.Locale;

/**
 * Created by JavierSL on 10/04/2018.
 */

public class AlumnoBuscarFragment extends Fragment implements View.OnClickListener, DAOAlumno.OnAlumnoListener
{
    private EditText edtID;
    private TextView txtAlumno;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_buscar_alumno, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        edtID = (EditText)view.findViewById(R.id.edtID);
        txtAlumno = (TextView)view.findViewById(R.id.txtAlumno);
        Button btBuscar = (Button)view.findViewById(R.id.btBuscar);

        btBuscar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(edtID.length() == 0)
            Snackbar.make(v, "Coloque el ID por favor", Snackbar.LENGTH_LONG).show();
        else
            DAOAlumno.getInstance().buscar(getContext(), Integer.parseInt(edtID.getText().toString()), this);
    }

    @Override
    public void obtenerAlumno(Alumno alumno)
    {
        String cadena = String.format(Locale.getDefault(), "ID: %d\nNombre: %s\nApellido: %s", alumno.getId(), alumno.getNombre(), alumno.getApellido());
        txtAlumno.setText(cadena);
    }

    @Override
    public void error(String mensaje)
    {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
