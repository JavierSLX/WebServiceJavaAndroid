/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.javiersl.android.dao.DAOAlumno;
import com.javiersl.android.entidades.Alumno;
import java.sql.SQLException;

/**
 *
 * @author JavierSL
 */
public class TestDAOAlumno
{
    public static void main(String[] args)
    {
        DAOAlumno dao = DAOAlumno.getInstance();
        
        try
        {
            //Inserta un alumno
            Alumno alumno = new Alumno("Charlie", "Serrano", true);
            //insertar(dao, alumno);
            
            //cambiarEstado(dao, 4, false);
            //buscar(dao, 1);
            listar(dao, true);
            //listar(dao, false);
            
        } catch (SQLException e)
        {
            e.getLocalizedMessage();
        }
        
    }
    
    private static void insertar(DAOAlumno dao, Alumno alumno) throws SQLException
    {
        dao.insertar(alumno);
        System.out.println("Se inserto el alumno " + alumno.getNombre() + " correctamente");
    }
    
    private static void cambiarEstado(DAOAlumno dao, int id, boolean estado) throws SQLException
    {
        dao.cambiarEstado(id, estado);
        System.out.println("Se cambio el estado del alumno");
    }
    
    private static void listar(DAOAlumno dao, boolean estado) throws SQLException
    {
        System.out.println(dao.listar(estado).toString());
    }
    
    private static void buscar(DAOAlumno dao, int id) throws SQLException
    {
        System.out.println(dao.buscar(id));
    }
    
    private static void actualizar(DAOAlumno dao, int id, Alumno alumno) throws SQLException
    {
        dao.actualizar(id, alumno);
        System.out.println("Se actualizo el alumno " + alumno.getNombre());
    }
}
