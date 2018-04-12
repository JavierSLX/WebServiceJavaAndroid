/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.entidades;

import com.javiersl.android.dao.DAORegistro;
import java.sql.SQLException;

/**
 *
 * @author JavierSL
 */
public class Boleta
{
    private Integer id;
    private Double calificacion;
    private Integer registro_id;
    private Registro registro;

    public Boleta()
    {
    }

    public Boleta(Double calificacion, Integer registro_id)
    {
        this.calificacion = calificacion;
        this.registro_id = registro_id;
    }

    public Boleta(Integer id, Double calificacion, Integer registro_id)
    {
        this.id = id;
        this.calificacion = calificacion;
        this.registro_id = registro_id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Double getCalificacion()
    {
        return calificacion;
    }

    public void setCalificacion(Double calificacion)
    {
        this.calificacion = calificacion;
    }

    public Integer getRegistro_id()
    {
        return registro_id;
    }

    public void setRegistro_id(Integer registro_id)
    {
        this.registro_id = registro_id;
    }

    public Registro getRegistro() throws SQLException
    {
        if(registro_id != null)
            return DAORegistro.getInstance().buscar(registro_id);
        
        return null;
    }
}
