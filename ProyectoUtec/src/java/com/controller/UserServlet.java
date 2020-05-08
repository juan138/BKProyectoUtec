/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.Conexion;
import com.dao.Val435;
import com.google.gson.Gson;
import com.model.UsuarioDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author master
 */
public class UserServlet extends HttpServlet {

    Conexion conn = new Conexion();
    Val435 valUser = new Val435(conn);
    RequestDispatcher rd;
    String msg = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "val435New":
                insertUser(request, response);
                break;
            case "test":
                testSession(request, response);
                break;
            case "val435All":
                getAllUsers(request, response);
                break;
        }
    }

    /**
     * Metodo para Insertar Usuario
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void insertUser(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String firstName = request.getParameter("nombres");
        String lastName = request.getParameter("apellidos");
        
        Integer pkUser = null;
        boolean rsp = false;
        rsp=valUser.insertar(user, pass);
        
         if (rsp) {
             pkUser = valUser.valUserAct(user, pass);
             rsp=valUser.insertPerson(firstName, lastName, pkUser);
            msg = rsp==true?"exito al insertar":"error al insertar";
        } else {
            msg = "error al insertar";
        }
         
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(rsp);
        out.flush();
    }
   
    /**
     * metodo para provar la session del usuario
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void testSession(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        Integer pkUser = null;
        HttpSession misession= (HttpSession) request.getSession();
        pkUser=(Integer)misession.getAttribute("keyUser");
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("usuario key "+pkUser!=null?pkUser:" NAN ");
        out.flush();
     }
    
    /**
     * metodo para consultar todos los usuarios
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void getAllUsers(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        List<UsuarioDto> lst = new ArrayList<>();
         lst=valUser.userList();
        String usersJson = new Gson().toJson(lst);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(usersJson);
        out.flush();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
