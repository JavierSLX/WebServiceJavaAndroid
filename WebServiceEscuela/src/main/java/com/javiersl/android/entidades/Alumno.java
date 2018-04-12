/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.entidades;

/**
 *
 * @author JavierSL
 */
public class Alumno
{
    private Integer id;
    private String nombre;
    private String apellido;
    private boolean estado;

    public Alumno()
    {
    }

    public Alumno(String nombre, String apellido, boolean estado)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public Alumno(int id, String nombre, String apellido, boolean estado)
    {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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
        return "Alumno{" + "id: " + id + ", nombre: " + nombre + ", apellido: " + apellido + '}';
    }
    
    
}
