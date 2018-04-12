/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.entidades;

import com.javiersl.android.dao.DAOAlumno;
import com.javiersl.android.dao.DAOMateria;
import java.sql.SQLException;

/**
 *
 * @author JavierSL
 */
public class Registro
{
    private Integer id;
    private Integer alumno_id;
    private Integer materia_id;

    public Registro()
    {
    }

    public Registro(Integer alumno_id, Integer materia_id)
    {
        this.alumno_id = alumno_id;
        this.materia_id = materia_id;
    }
    
    public Registro(Integer id, Integer alumno_id, Integer materia_id)
    {
        this.id = id;
        this.alumno_id = alumno_id;
        this.materia_id = materia_id;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getAlumno_id()
    {
        return alumno_id;
    }

    public void setAlumno_id(Integer alumno_id)
    {
        this.alumno_id = alumno_id;
    }

    public Integer getMateria_id()
    {
        return materia_id;
    }

    public void setMateria_id(Integer materia_id)
    {
        this.materia_id = materia_id;
    }
}
