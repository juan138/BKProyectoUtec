/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.UsuarioDto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JSerrano
 * Val435 = validacion de login
 */
public class Val435 {
      Conexion conn;

    //metodo constructor con parametro
    public Val435(Conexion conn) {
        this.conn = conn;
    }
    
    /**
     * valida si El usuario Existe y esta activo
     * @param user
     * @param contra
     * @return 
     */
    public Integer valUserAct(String user, String contra){
        String sql = "select pk_user,user_name from person.usuarios where user_name=? and user_pass=? and status_user=true";
        Integer resp = null;
        try {
            //sentencia preparada
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, contra);
            ResultSet rs = ps.executeQuery(); // ejecuta query
            while (rs.next()) {
                resp=rs.getInt("pk_user");
            }
            return resp!=null?resp:0;
        } catch (Exception e) {
            System.out.println("Error de Logueo : "+e.toString());
            return null;
        }
    }
    
    /**
     * metodo insertar Usuario
     * @param user
     * @param contra
     * @return 
     */
    public boolean insertar(String user, String contra){
        
        //query de insertar
        String sql = "insert into person.usuarios (user_name,user_pass,status_user) values (?,?,true)";
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, contra);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error de Insertar Usuario "+e.toString());
            return false;
        }
        
    }
    
    /**
     * metodo insertar Usuario
     * @param user
     * @param contra
     * @return 
     */
    public boolean insertPerson(String userName, String apellido, Integer keyUser){
        
        //query de insertar
        String sql = "insert into person.datos_personales (first_name_person,last_name_person,fk_user) values (?,?,?)";
        try {
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, apellido);
            ps.setInt(3, keyUser);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error de Insertar Usuario "+e.toString());
            return false;
        }
        
    }
    /**
     * consulta todos los usuarios
     * @return List<UsuarioDto>
     */
    public List<UsuarioDto> userList(){
        String sql = "select user_pass,user_name from person.usuarios where status_user=true";
        List<UsuarioDto> lst = new ArrayList<>();
        
        try {
            //sentencia preparada
            PreparedStatement ps = conn.conectar().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery(); // ejecuta query
            while (rs.next()) {
                UsuarioDto obj = new UsuarioDto();
                obj.setUserName(rs.getString("user_name"));
                obj.setUserPass(rs.getString("user_pass"));
                lst.add(obj);
            }
            return lst;
        } catch (Exception e) {
            System.out.println("Error de Logueo : "+e.toString());
            return null;
        }
    }
}
