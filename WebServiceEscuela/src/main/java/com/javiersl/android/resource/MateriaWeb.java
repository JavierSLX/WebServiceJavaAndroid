/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.resource;

import com.javiersl.android.dao.DAOMateria;
import com.javiersl.android.entidades.Materia;
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
@Path("/materia")
public class MateriaWeb
{
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> listarMaterias()
    {
        try
        {
            return DAOMateria.getInstance().listar(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(MateriaWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @GET
    @Path("/eliminados")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> listaEliminados()
    {
        try
        {
            return DAOMateria.getInstance().listar(false);
        } catch (SQLException ex)
        {
            Logger.getLogger(MateriaWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarMateria(Materia materia)
    {
        try
        {
            DAOMateria.getInstance().insertar(materia);
        } catch (SQLException ex)
        {
            Logger.getLogger(MateriaWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Materia obtenerMateria(@PathParam("id")Integer id)
    {
        try
        {
            return DAOMateria.getInstance().buscar(id);
        } catch (SQLException ex)
        {
            Logger.getLogger(MateriaWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @DELETE
    @Path("/eliminar/{id}")
    public void eliminarMateria(@PathParam("id")Integer id)
    {
        try
        {
            DAOMateria.getInstance().cambiarEstado(id, false);
        } catch (SQLException ex)
        {
            Logger.getLogger(MateriaWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/restaurar/{id}")
    public void restaurarMateria(@PathParam("id")Integer id)
    {
        try
        {
            DAOMateria.getInstance().cambiarEstado(id, true);
        } catch (SQLException ex)
        {
            Logger.getLogger(MateriaWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
    
    @PUT
    @Path("/actualizar/{id}")
    public void actualizarMateria(@PathParam("id")Integer id, Materia materia)
    {
        materia.setId(id);
        
        try
        {
            DAOMateria.getInstance().actualizar(id, materia);
        } catch (SQLException ex)
        {
            Logger.getLogger(MateriaWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
}
