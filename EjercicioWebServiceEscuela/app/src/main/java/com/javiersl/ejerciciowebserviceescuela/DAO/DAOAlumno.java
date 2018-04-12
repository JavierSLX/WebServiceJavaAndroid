package com.javiersl.ejerciciowebserviceescuela.DAO;

import android.content.Context;
import android.widget.Toast;

import com.javiersl.ejerciciowebserviceescuela.Clases.Alumno;
import com.javiersl.ejerciciowebserviceescuela.Hilos.HiloVolleyArrayGET;
import com.javiersl.ejerciciowebserviceescuela.Hilos.HiloVolleyJsonGET;
import com.javiersl.ejerciciowebserviceescuela.Hilos.HiloVolleyJsonPUT;
import com.javiersl.ejerciciowebserviceescuela.Hilos.HiloVolleyStringDELETE;
import com.javiersl.ejerciciowebserviceescuela.Hilos.HiloVolleyStringGET;
import com.javiersl.ejerciciowebserviceescuela.Hilos.HiloVolleyStringPOST;
import com.javiersl.ejerciciowebserviceescuela.Hilos.OnConsultaListener;
import com.javiersl.ejerciciowebserviceescuela.Recursos.Basic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by JavierSL on 10/04/2018.
 */

//Clase DAO de Alumno que conecta con la informacion
public class DAOAlumno
{
    private static DAOAlumno dao;

    public interface OnListaListener
    {
        void listaElementos(List<Alumno> lista);
        void error(String mensaje);
    }

    public interface OnEstadoListener
    {
        void procesoCorrecto(Boolean estado);
        void error(String mensaje);
    }

    public interface OnAlumnoListener
    {
        void obtenerAlumno(Alumno alumno);
        void error(String mensaje);
    }

    private DAOAlumno()
    {
    }

    public static DAOAlumno getInstance()
    {
        if(dao == null)
            dao = new DAOAlumno();

        return dao;
    }

    //Método que permite obtener la lista de alumnos (por medio de Callback)
    public void listar(Context context, boolean estado, final OnListaListener listener)
    {
        String url;
        if (estado)
            url = Basic.HTTP + "alumno/listar";
        else
            url = Basic.HTTP + "alumno/eliminados";

        HiloVolleyArrayGET.getInstance().getResponse(context, url, new OnConsultaListener<JSONArray>()
        {
            @Override
            public void onSuccess(JSONArray respuesta)
            {
                listener.listaElementos(Alumno.obtenerLista(respuesta));
            }

            @Override
            public void onFailed(String error)
            {
                listener.error(error);
            }
        });
    }

    //Método que permite insertar un alumno
    public void insertar(Context context, Alumno alumno, final OnEstadoListener listener)
    {
        String url;
        JSONObject json = new JSONObject();

        try
        {
            json.put("nombre", alumno.getNombre());
            json.put("apellido", alumno.getApellido());
            json.put("estado", alumno.isEstado());

        } catch (JSONException e)
        {
            Toast.makeText(context, "Error al crear JSON", Toast.LENGTH_SHORT).show();
        }

        url = Basic.HTTP + "alumno/insertar";
        HiloVolleyStringPOST.getInstance().getResponse(context, url, json, new OnConsultaListener<Boolean>()
        {
            @Override
            public void onSuccess(Boolean elemento)
            {
                listener.procesoCorrecto(elemento);
            }

            @Override
            public void onFailed(String mensaje)
            {
                listener.error(mensaje);
            }
        });
    }

    //Método que permite obtener un alumno al buscar por su id
    public void buscar(final Context context, int id, final OnAlumnoListener listener)
    {
        String url = Basic.HTTP + "alumno/buscar/" + String.valueOf(id);
        HiloVolleyJsonGET.getInstance().getResponse(context, url, new OnConsultaListener<JSONObject>()
        {
            @Override
            public void onSuccess(JSONObject elemento)
            {
                Alumno alumno = null;
                try
                {
                    alumno = new Alumno(elemento.getInt("id"), elemento.getString("nombre"), elemento.getString("apellido"), elemento.getBoolean("estado"));
                } catch (JSONException e)
                {
                    e.printStackTrace();
                    Toast.makeText(context, "Sin elementos que analizar", Toast.LENGTH_SHORT).show();
                }

                listener.obtenerAlumno(alumno);
            }

            @Override
            public void onFailed(String mensaje)
            {
                listener.error(mensaje);
            }
        });
    }

    //Método que permite eliminar un alumno por su id
    public void eliminar(Context context, int id, final OnEstadoListener listener)
    {
        String url = Basic.HTTP + "alumno/eliminar/" + String.valueOf(id);
        HiloVolleyStringDELETE.getInstance().getResponse(context, url, new OnConsultaListener<Boolean>()
        {
            @Override
            public void onSuccess(Boolean estado)
            {
                listener.procesoCorrecto(estado);
            }

            @Override
            public void onFailed(String mensaje)
            {
                listener.error(mensaje);
            }
        });
    }

    //Método que permite restaurar un alumno por su id
    public void restaurar(Context context, int id, final OnEstadoListener listener)
    {
        String url = Basic.HTTP + "alumno/restaurar/" + String.valueOf(id);
        HiloVolleyStringGET.getInstance().getResponse(context, url, new OnConsultaListener<Boolean>()
        {
            @Override
            public void onSuccess(Boolean elemento)
            {
                listener.procesoCorrecto(elemento);
            }

            @Override
            public void onFailed(String mensaje)
            {
                listener.error(mensaje);
            }
        });
    }

    //Método que permite actualizar un alumno por su id
    public void actualizar(Context context, Alumno alumno, final OnEstadoListener listener)
    {
        String url = Basic.HTTP + "alumno/actualizar/" + String.valueOf(alumno.getId());
        JSONObject json = new JSONObject();

        try
        {
            json.put("id", alumno.getId());
            json.put("nombre", alumno.getNombre());
            json.put("apellido", alumno.getApellido());
            json.put("estado", alumno.isEstado());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        HiloVolleyJsonPUT.getInstance().getResponse(context, url, json, new OnConsultaListener<Boolean>()
        {
            @Override
            public void onSuccess(Boolean elemento)
            {
                listener.procesoCorrecto(elemento);
            }

            @Override
            public void onFailed(String mensaje)
            {
                listener.error(mensaje);
            }
        });
    }
}
