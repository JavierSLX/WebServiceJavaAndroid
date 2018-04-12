/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.resource;

import com.javiersl.android.dao.DAOBoleta;
import com.javiersl.android.dao.DAORegistro;
import com.javiersl.android.entidades.Boleta;
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
@Path("/boleta")
public class BoletaWeb
{
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Boleta> listarBoletas()
    {
        try
        {
            return DAOBoleta.getInstance().listar(true);
        } catch (SQLException ex)
        {
            Logger.getLogger(BoletaWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertarBoleta(Boleta boleta)
    {
        try
        {
            DAOBoleta.getInstance().insertar(boleta);
        } catch (SQLException ex)
        {
            Logger.getLogger(BoletaWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/buscar/{id}")
    public Boleta obtenerBoleta(@PathParam("id")Integer id)
    {
        try
        {
            return DAOBoleta.getInstance().buscar(id);
        } catch (SQLException ex)
        {
            Logger.getLogger(BoletaWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    @PUT
    @Path("/actualizar/{id}")
    public void actualizarBoleta(@PathParam("id")Integer id, Boleta boleta)
    {
        try
        {
            DAOBoleta.getInstance().actualizar(id, boleta);
        } catch (SQLException ex)
        {
            Logger.getLogger(BoletaWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/obtener/registro/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Registro obtenerRegistro(@PathParam("id")Integer id)
    {
        try
        {
            return DAORegistro.getInstance().buscar(id);
        } catch (SQLException ex)
        {
            Logger.getLogger(BoletaWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
