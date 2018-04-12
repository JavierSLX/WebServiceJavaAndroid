/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.resource;

import com.javiersl.android.dao.DAOAlumno;
import com.javiersl.android.entidades.Alumno;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author JavierSL
 */

@Path("/alumno")
public class AlumnoWeb
{
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> listarAlumnos()
    {
        try
        {
            return DAOAlumno.getInstance().listar(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(AlumnoWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @GET
    @Path("/eliminados")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> listarEliminados()
    {
        try
        {
            return DAOAlumno.getInstance().listar(false);
        } catch (SQLException ex)
        {
            Logger.getLogger(AlumnoWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarAlumno(Alumno alumno)
    {
        try
        {
            DAOAlumno.getInstance().insertar(alumno);
        } catch (SQLException ex)
        {
            Logger.getLogger(AlumnoWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alumno obtenerAlumno(@PathParam("id")Integer id)
    {
        try
        {
            return DAOAlumno.getInstance().buscar(id);
        } catch (SQLException ex)
        {
            Logger.getLogger(AlumnoWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @DELETE
    @Path("/eliminar/{id}")
    public void eliminarAlumno(@PathParam("id")Integer id)
    {
        try
        {
            DAOAlumno.getInstance().cambiarEstado(id, false);
        } catch (SQLException ex)
        {
            Logger.getLogger(AlumnoWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/restaurar/{id}")
    public void restaurarAlumno(@PathParam("id")Integer id)
    {
        try
        {
            DAOAlumno.getInstance().cambiarEstado(id, true);
        } catch (SQLException ex)
        {
            Logger.getLogger(AlumnoWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
    
    @PUT
    @Path("/actualizar/{id}")
    public void actualizarAlumno(@PathParam("id")Integer id, Alumno alumno)
    {
        alumno.setId(id);
        try
        {
            DAOAlumno.getInstance().actualizar(id, alumno);
        } catch (SQLException ex)
        {
            Logger.getLogger(AlumnoWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
}
