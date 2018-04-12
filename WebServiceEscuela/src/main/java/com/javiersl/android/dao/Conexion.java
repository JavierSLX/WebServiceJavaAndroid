/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JavierSL
 */
public class Conexion
{
    private Connection connection;
    private static Conexion conexion;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "normal";
    private static final String PASS = "normal";
    private static final String URL = "jdbc:mysql://localhost:3300/escuela?serverTimezone=UTC&amp;useUnicode=true&amp;characterEncoding=utf8";

    private Conexion()
    {
    }
    
    public static Conexion getInstance() throws SQLException
    {
        if(conexion == null)
            conexion = new Conexion();
        
        conexion.conectar();
        return conexion;
    }
    
    private void conectar() throws SQLException
    {
        if(connection == null || connection.isClosed())
        {
            try
            {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
    }
    
    public boolean isConexion() throws SQLException
    {
        return connection != null && !connection.isClosed();
    }

    public Connection getConnection()
    {
        return connection;
    }
}
