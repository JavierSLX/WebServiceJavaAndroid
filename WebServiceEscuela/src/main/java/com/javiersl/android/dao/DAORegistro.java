/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.dao;

import com.javiersl.android.entidades.Alumno;
import com.javiersl.android.entidades.Registro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JavierSL
 */
public class DAORegistro implements IDAO<Registro>
{
    private static DAORegistro dAORegistro;

    private DAORegistro()
    {
    }
    
    public static DAORegistro getInstance()
    {
        if (dAORegistro == null)
            dAORegistro = new DAORegistro();
        
        return dAORegistro;
    }

    @Override
    public void insertar(Registro objeto) throws SQLException
    {
        PreparedStatement insertar;
        insertar = Conexion.getInstance().getConnection().prepareStatement("INSERT INTO registro (alumno_id, materia_id) VALUES (?, ?)");
        
        insertar.setInt(1, objeto.getAlumno_id());
        insertar.setInt(2, objeto.getMateria_id());
        
        insertar.executeUpdate();
        insertar.close();
    }

    @Override
    public void cambiarEstado(Integer id, boolean estado) throws SQLException
    {
        System.err.println("Método no posible");
    }

    @Override
    public List<Registro> listar(boolean estado) throws SQLException
    {
        if (!estado)
            return null;
    
        PreparedStatement listar;
        listar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM registro");
        
        ResultSet set = listar.executeQuery();
        
        if(set.first())
        {
            List<Registro> lista = new ArrayList<>();
            do
            {                
                lista.add(new Registro(set.getInt("id"), set.getInt("alumno_id"), set.getInt("materia_id")));
                
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
    public Registro buscar(Integer id) throws SQLException
    {
        PreparedStatement buscar;
        buscar = Conexion.getInstance().getConnection().prepareStatement("SELECT * FROM registro WHERE id = ?");
        
        buscar.setInt(1, id);
        ResultSet set = buscar.executeQuery();
        
        if(set.first())
            return new Registro(set.getInt("id"), set.getInt("alumno_id"), set.getInt("materia_id"));
        else
            return null;
    }

    @Override
    public void actualizar(Integer id, Registro objeto) throws SQLException
    {
        PreparedStatement actualizar;
        actualizar = Conexion.getInstance().getConnection().prepareStatement("UPDATE registro SET alumno_id = ?, materia_id = ? WHERE id = ?");
        
        actualizar.setInt(1, objeto.getAlumno_id());
        actualizar.setInt(2, objeto.getMateria_id());
        actualizar.setInt(3, id);
        
        actualizar.executeUpdate();
        actualizar.close();
    }    
}
