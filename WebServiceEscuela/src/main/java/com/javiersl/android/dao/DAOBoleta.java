/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.dao;

import com.javiersl.android.entidades.Boleta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JavierSL
 */
public class DAOBoleta implements IDAO<Boleta>
{
    private static DAOBoleta dAOBoleta;

    private DAOBoleta()
    {
    }
    
    public static DAOBoleta getInstance()
    {
        if(dAOBoleta == null)
            dAOBoleta = new DAOBoleta();
        
        return dAOBoleta;
    }

    @Override
    public void insertar(Boleta objeto) throws SQLException
    {
        PreparedStatement insertar;
        insertar = Conexion.getInstance().getConnection().prepareStatement("INSERT INTO boleta (calificacion, registro_id) VALUES (?, ?)");
        
        insertar.setDouble(1, objeto.getCalificacion());
        insertar.setInt(2, objeto.getRegistro_id());
        
        insertar.executeUpdate();
        insertar.close();
    }

    @Override
    public void cambiarEstado(Integer id, boolean estado) throws SQLException
    {
        System.err.println("Método no posible");
    }

    @Override
    public List<Boleta> listar(boolean estado) throws SQLException
    {
        if (!estado)
            return null;
        
        PreparedStatement listar;
        listar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM boleta");
        
        ResultSet set = listar.executeQuery();
        
        if(set.first())
        {
            List<Boleta> lista = new ArrayList<>();
            do
            {                
                lista.add(new Boleta(set.getInt("id"), set.getDouble("calificacion"), set.getInt("registro_id")));
                
            }while(set.next());
            
            return lista;
        }
        else
        {
            listar.close();
            return null;
        }
    }

    @Override
    public Boleta buscar(Integer id) throws SQLException
    {
        PreparedStatement buscar;
        buscar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM boleta WHERE id = ?");
        
        buscar.setInt(1, id);
        ResultSet set = buscar.executeQuery();
        
        if(set.first())
            return new Boleta(set.getInt("id"), set.getDouble("calificacion"), set.getInt("registro_id"));
        else
            return null;
    }

    @Override
    public void actualizar(Integer id, Boleta objeto) throws SQLException
    {
        PreparedStatement actualizar;
        actualizar = Conexion.getInstance().getConnection().prepareStatement("UPDATE boleta SET calificacion = ?, registro_id = ? WHERE id = ?");
        
        actualizar.setDouble(1, objeto.getCalificacion());
        actualizar.setInt(2, objeto.getRegistro_id());
        actualizar.setInt(3, id);
        
        actualizar.executeUpdate();
        actualizar.close();
    }
    
}
