package com.javiersl.ejerciciowebserviceescuela.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.javiersl.ejerciciowebserviceescuela.Adapters.AlumnoAdapter;
import com.javiersl.ejerciciowebserviceescuela.Clases.Alumno;
import com.javiersl.ejerciciowebserviceescuela.DAO.DAOAlumno;
import com.javiersl.ejerciciowebserviceescuela.R;

import java.util.List;

/**
 * Created by JavierSL on 09/04/2018.
 */

public class AlumnoListarFragment extends Fragment implements View.OnClickListener, DAOAlumno.OnListaListener
{
    private RecyclerView recyclerView;
    private RadioButton rdActivo, rdInactivo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_listar_alumno, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        rdActivo = (RadioButton)view.findViewById(R.id.rdActivo);
        rdInactivo = (RadioButton)view.findViewById(R.id.rdInactivo);

        rdActivo.setOnClickListener(this);
        rdInactivo.setOnClickListener(this);

        listarAlumnos(true);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.rdActivo:
                listarAlumnos(true);
                break;

            case R.id.rdInactivo:
                listarAlumnos(false);
                break;
        }
    }

    private void listarAlumnos(boolean estado)
    {
        DAOAlumno.getInstance().listar(getContext(), estado, this);
    }

    @Override
    public void listaElementos(List<Alumno> lista)
    {
        AlumnoAdapter adapter = new AlumnoAdapter(lista);

        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void error(String mensaje)
    {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}
