/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author master
 */
public class Conexion {
        //variables estaticas para la coneccion de la base
    static String bd = "proyectoUtec";
    static String user = "root";
    static String pass = "root";
    static String url = "jdbc:mysql://localhost/" + bd;
    static String driv = "com.mysql.jdbc.Driver";

    //variabla tipo connection
    Connection conn = null;

    //metodo contructor de conexion
     public Conexion() {
        try {
            Class.forName(driv);
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("EXITO DE CONEXION");
            }
        } catch (Exception e) {
            System.err.println("ERROR DE CONEXION");
        }
    }
     
    //metodo conectar tipo connection
    public Connection conectar() {
        return conn;
    }

    //metodo sin retorno para desconectar
    public void desconectar() throws Exception {
        conn.close();
    }
}
