/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.dao.Conexion;
import com.dao.Val435;
import java.io.IOException;
import java.io.PrintWriter;
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
public class LoginServlet extends HttpServlet {

    Conexion conn = new Conexion();
    Val435 valUser = new Val435(conn);
    RequestDispatcher rd;
    String msg = "";
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                login(request, response);
                break;
            case "sigInOut":
                closedSession(request, response);
                break;
        }
        
    }
    
        
    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        
        //declaracion de variables
        boolean rsp = false;
        Integer pkUser = null;
        HttpSession misession= request.getSession(true);
        
        pkUser = valUser.valUserAct(user, pass);
        
        rsp=pkUser!=0;
        misession.setAttribute("keyUser",pkUser);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(rsp);
        out.flush();
    }
    
    protected void closedSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(false);
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(true);
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
