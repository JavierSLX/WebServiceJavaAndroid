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
import android.widget.Toast;

import com.javiersl.ejerciciowebserviceescuela.Clases.Alumno;
import com.javiersl.ejerciciowebserviceescuela.DAO.DAOAlumno;
import com.javiersl.ejerciciowebserviceescuela.R;

/**
 * Created by JavierSL on 10/04/2018.
 */

public class AlumnoInsertarFragment extends Fragment implements View.OnClickListener
{
    private EditText edtNombre, edtApellido;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_insertar_alumno, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        edtNombre = (EditText)view.findViewById(R.id.edtNombre);
        edtApellido = (EditText)view.findViewById(R.id.edtApellido);
        Button btInsertar = (Button)view.findViewById(R.id.btInsertar);

        btInsertar.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v)
    {
        if (edtNombre.length() == 0 && edtApellido.length() == 0)
            Snackbar.make(v, "Llene todos los campos por favor", Snackbar.LENGTH_LONG).show();
        else
        {
            Alumno alumno = new Alumno(edtNombre.getText().toString(), edtApellido.getText().toString(), true);

            DAOAlumno.getInstance().insertar(getContext(), alumno, new DAOAlumno.OnEstadoListener()
            {
                @Override
                public void procesoCorrecto(Boolean estado)
                {
                    if(estado)
                        Snackbar.make(v, "Elemento insertado correctamente", Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void error(String mensaje)
                {
                    Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
