/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.dao;

import com.javiersl.android.entidades.Alumno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JavierSL
 */
public class DAOAlumno implements IDAO<Alumno>
{
    private static DAOAlumno dAOAlumno;

    private DAOAlumno()
    {
    }
    
    public static DAOAlumno getInstance()
    {
        if (dAOAlumno == null)
            dAOAlumno = new DAOAlumno();
        
        return dAOAlumno;
    }

    @Override
    public void insertar(Alumno objeto) throws SQLException
    {
        PreparedStatement insertar;
        insertar = Conexion.getInstance().getConnection().prepareStatement("INSERT INTO alumno (nombre, apellido, estado) VALUES (?, ?, ?)");
        
        insertar.setString(1, objeto.getNombre());
        insertar.setString(2, objeto.getApellido());
        insertar.setBoolean(3, objeto.isEstado());
        
        insertar.executeUpdate();
        insertar.close();
    }
    
    @Override
    public void cambiarEstado(Integer id, boolean estado) throws SQLException
    {
        PreparedStatement cambio;
        cambio = Conexion.getInstance().getConnection().prepareStatement("UPDATE alumno SET estado = ? WHERE id = ?");
        
        cambio.setBoolean(1, estado);
        cambio.setInt(2, id);
        
        cambio.executeUpdate();
        cambio.close();
    }
    
    @Override
    public List<Alumno> listar(boolean estado) throws SQLException
    {
        PreparedStatement listar;
        listar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM alumno WHERE estado = ?");
        
        listar.setBoolean(1, estado);
        ResultSet set = listar.executeQuery();
        
        if(set.first())
        {
            List<Alumno> lista = new ArrayList<>();
            do
            {                
                lista.add(new Alumno(set.getInt("id"), set.getString("nombre"), set.getString("apellido"), set.getBoolean("estado")));
            } while (set.next());
            
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
    public Alumno buscar(Integer id) throws SQLException
    {
        PreparedStatement buscar;
        buscar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM alumno WHERE id = ?");
        
        buscar.setInt(1, id);
        ResultSet set = buscar.executeQuery();
        
        if(set.first())
            return new Alumno(set.getInt("id"), set.getString("nombre"), set.getString("apellido"), set.getBoolean("estado"));
        else
            return null;
    }

    @Override
    public void actualizar(Integer id, Alumno objeto) throws SQLException
    {
        PreparedStatement actualizar;
        actualizar = Conexion.getInstance().getConnection().prepareStatement("UPDATE alumno SET nombre = ?, apellido = ?, estado = ? WHERE id = ?");
        
        actualizar.setString(1, objeto.getNombre());
        actualizar.setString(2, objeto.getApellido());
        actualizar.setBoolean(3, objeto.isEstado());
        actualizar.setInt(4, id);
        
        actualizar.executeUpdate();
        actualizar.close();
    }
}
