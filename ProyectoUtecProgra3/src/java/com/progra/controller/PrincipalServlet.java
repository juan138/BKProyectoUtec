/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progra.controller;

import com.google.gson.Gson;
import com.progra.model.UsuarioBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author master
 */
public class PrincipalServlet extends HttpServlet {

   
    RequestDispatcher rd;
    String msg = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
        }
    }

     protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        boolean rsp = false;
        
        if(user.equals("jserrano")  && pass.equals("12345") )
            rsp=true;
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(rsp);
        out.flush();
    }
     
    // http://localhost:8080/ProyectoUtecProgra3/PrincipalServlet?action=login&user=juan&pass=s34
    //modificar
    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
            UsuarioBean ub = new UsuarioBean();
            ub.setPass(pass);
            ub.setUser(user);
         String employeeJsonString = new Gson().toJson(ub);
        //request.setAttribute("lista", employeeJsonString);
          
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
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
