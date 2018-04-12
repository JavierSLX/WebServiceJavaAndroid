package com.javiersl.ejerciciowebserviceescuela.Clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JavierSL on 09/04/2018.
 */

public class Alumno
{
    private Integer id;
    private String nombre;
    private String apellido;
    private boolean estado;

    public Alumno(Integer id, String nombre, String apellido, boolean estado)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public Alumno(String nombre, String apellido, boolean estado)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public boolean isEstado()
    {
        return estado;
    }

    public void setEstado(boolean estado)
    {
        this.estado = estado;
    }

    @Override
    public String toString()
    {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", estado=" + estado +
                '}';
    }

    public static List<Alumno> obtenerLista(JSONArray array)
    {
        List<Alumno> lista = new ArrayList<>();

        try
        {
            if (array.getJSONObject(0).length() > 3)
            {
                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);
                    lista.add(new Alumno(jsonObject.getInt("id"), jsonObject.getString("nombre"), jsonObject.getString("apellido"),
                            jsonObject.getBoolean("estado")));
                }
            }
            else
            {
                for (int i = 0; i < array.length(); i++)
                {
                    JSONObject jsonObject = array.getJSONObject(i);
                    lista.add(new Alumno(jsonObject.getString("nombre"), jsonObject.getString("apellido"), jsonObject.getBoolean("estado")));
                }
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return lista;
    }
}
