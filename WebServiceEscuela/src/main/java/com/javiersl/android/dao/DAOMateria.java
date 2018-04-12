/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.dao;

import com.javiersl.android.entidades.Materia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JavierSL
 */
public class DAOMateria implements IDAO<Materia>
{
    private static DAOMateria dAOMateria;

    private DAOMateria()
    {
    }
    
    public static DAOMateria getInstance()
    {
        if (dAOMateria == null)
            dAOMateria = new DAOMateria();
        
        return dAOMateria;
    }

    @Override
    public void insertar(Materia objeto) throws SQLException
    {
        PreparedStatement insertar;
        insertar = Conexion.getInstance().getConnection().prepareStatement("INSERT INTO materia (nombre, estado) VALUES (?, ?)");
        
        insertar.setString(1, objeto.getNombre());
        insertar.setBoolean(2, objeto.isEstado());
        
        insertar.executeUpdate();
        insertar.close();
    }

    @Override
    public void cambiarEstado(Integer id, boolean estado) throws SQLException
    {
        PreparedStatement cambio;
        cambio = Conexion.getInstance().getConnection().prepareStatement("UPDATE materia SET estado = ? WHERE id = ?");
        
        cambio.setBoolean(1, estado);
        cambio.setInt(2, id);
        
        cambio.executeUpdate();
        cambio.close();
    }

    @Override
    public List<Materia> listar(boolean estado) throws SQLException
    {
        PreparedStatement listar;
        listar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM materia WHERE estado = ?");
        
        listar.setBoolean(1, estado);
        ResultSet set = listar.executeQuery();
        
        if(set.first())
        {
            List<Materia> lista = new ArrayList<>();
            do
            {                
                lista.add(new Materia(set.getInt("id"), set.getString("nombre"), set.getBoolean("estado")));
                
            }while(set.next());
            
            listar.close();
            return lista;
        }
        else
        {
            listar.close();
            return null;
        }
    }

    @Override
    public Materia buscar(Integer id) throws SQLException
    {
        PreparedStatement buscar;
        buscar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM materia WHERE id = ?");
        
        buscar.setInt(1, id);
        ResultSet set = buscar.executeQuery();
        
        if(set.first())
            return new Materia(set.getInt("id"), set.getString("nombre"), set.getBoolean("estado"));
        else
            return null;
    }

    @Override
    public void actualizar(Integer id, Materia objeto) throws SQLException
    {
        PreparedStatement actualizar;
        actualizar = Conexion.getInstance().getConnection().prepareStatement("UPDATE materia SET nombre = ?, estado = ? WHERE id = ?");
        
        actualizar.setString(1, objeto.getNombre());
        actualizar.setBoolean(2, objeto.isEstado());
        actualizar.setInt(3, id);
        
        actualizar.executeUpdate();
        actualizar.close();
    }
}
