/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javiersl.android.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author JavierSL
 */
public interface IDAO<T>
{
    void insertar(T objeto) throws SQLException;
    void cambiarEstado(Integer id, boolean estado) throws SQLException;
    List<T> listar(boolean estado) throws SQLException;
    T buscar(Integer id) throws SQLException;
    void actualizar(Integer id, T objeto) throws SQLException;
}
