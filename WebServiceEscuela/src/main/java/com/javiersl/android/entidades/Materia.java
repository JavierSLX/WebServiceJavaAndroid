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
public class Materia
{
    private Integer id;
    private String nombre;
    private boolean estado;

    public Materia()
    {
    }

    public Materia(String nombre, boolean estado)
    {
        this.nombre = nombre;
        this.estado = estado;
    }

    public Materia(Integer id, String nombre, boolean estado)
    {
        this.id = id;
        this.nombre = nombre;
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
        return "Materia{" + "id: " + id + ", nombre: " + nombre + ", estado: " + estado + '}';
    }
    
    
}
