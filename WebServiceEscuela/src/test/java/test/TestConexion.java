/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.javiersl.android.dao.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JavierSL
 */
public class TestConexion
{
    public static void main(String[] args)
    {
        try
        {
            if(Conexion.getInstance().isConexion())
                System.out.println("Conexión establecida");
        } catch (SQLException ex)
        {
            Logger.getLogger(TestConexion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al abrir la conexión");
        }
    }
}
