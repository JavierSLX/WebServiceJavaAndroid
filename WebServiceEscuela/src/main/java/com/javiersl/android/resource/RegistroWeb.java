/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.resource;

import com.javiersl.android.dao.DAOAlumno;
import com.javiersl.android.dao.DAOMateria;
import com.javiersl.android.dao.DAORegistro;
import com.javiersl.android.entidades.Alumno;
import com.javiersl.android.entidades.Materia;
import com.javiersl.android.entidades.Registro;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
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
@Path("/registro")
public class RegistroWeb
{
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Registro> listarRegistros()
    {
        try
        {
            return DAORegistro.getInstance().listar(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(RegistroWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarRegistro(Registro registro)
    {
        try
        {
            DAORegistro.getInstance().insertar(registro);
        } catch (SQLException ex)
        {
            Logger.getLogger(RegistroWeb.class.getName() + ". Error en la BD").log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Registro obtenerRegistro(@PathParam("id")Integer id)
    {
        try
        {
            return DAORegistro.getInstance().buscar(id);
        } catch (SQLException ex)
        {
            Logger.getLogger(RegistroWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @PUT
    @Path("/actualizar/{id}")
    public void actualizarRegistro(@PathParam("id")Integer id, Registro registro)
    {
        registro.setId(id);
        
        try
        {
            DAORegistro.getInstance().actualizar(id, registro);
        } catch (SQLException ex)
        {
            Logger.getLogger(RegistroWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/obtener/alumno/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Alumno obtenerAlumno(@PathParam("id")Integer id)
    {
        try
        {
            return DAOAlumno.getInstance().buscar(id);
        } catch (SQLException ex)
        {
            Logger.getLogger(RegistroWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @GET
    @Path("/obtener/materia/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Materia obtenerMateria(@PathParam("id")Integer id)
    {
        try
        {
            return DAOMateria.getInstance().buscar(id);
        } catch (SQLException ex)
        {
            Logger.getLogger(RegistroWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
