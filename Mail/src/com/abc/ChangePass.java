package com.abc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Mansi on 26-10-2016.
 */
@WebServlet(name = "ChangePass")
public class ChangePass extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("header.html").include(request, response);
        request.getRequestDispatcher("link.html").include(request, response);

        HttpSession session=request.getSession(false);
        if(session==null){
            response.sendRedirect("index.html");
        }else{
            String email=(String)session.getAttribute("email");
            out.print("<span style='float:right'>Hi, "+email+"</span>");

           String msg=(String)request.getAttribute("msg");
            if(msg!=null){
                out.print("<p>"+msg+"</p>");}

            request.getRequestDispatcher("change.html").include(request, response);
        }

        out.close();


    }

}