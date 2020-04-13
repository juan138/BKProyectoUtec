/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.google.gson.Gson;
import com.model.ProductoDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ProductosServlet extends HttpServlet {

    RequestDispatcher rd;
    String msg = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");
        switch (action) {
            case "lista":
                consultar(request, response);
                break;
        }
    }

    // http://localhost:8080/ProyectoUtecProgra3/PrincipalServlet?action=login&user=juan&pass=s34
    //modificar
    protected void consultar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List  prod = new ArrayList<ProductoDto>();
        ProductoDto pr = new ProductoDto();
        pr.setId("2");
        pr.setCatidad("4");
        pr.setNombre("clavos");
        prod.add(pr);
        ProductoDto pr2 = new ProductoDto();
        pr2.setId("3");
        pr2.setCatidad("6");
        pr2.setNombre("martillo");
        prod.add(pr2);
        
         String productoJson = new Gson().toJson(prod);
        //request.setAttribute("lista", employeeJsonString);
          
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(productoJson);
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
