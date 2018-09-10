package com.abc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Mansi on 26-10-2016.
 */
@WebServlet(name = "ChangePass1")
public class ChangePass1 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String email=request.getParameter("email");
        String password=request.getParameter("password");

        String password1=request.getParameter("password1");
        if(Login.validate(email, password)){

            try {
                Connection con = ConProvider.getConnection();
                PreparedStatement ps = con.prepareStatement("update mail_user set password=? where email= ?");
                ps.setString(1, password1);
                ps.setString(2, email);
                ResultSet rs=ps.executeQuery();
                con.close();

            }
            catch(Exception e){System.out.println(e);}

            out.print("Your Password Changed Successfully!");
            request.getRequestDispatcher("login.html").include(request, response);

        }else{
            request.setAttribute("msg","Wrong Password/Username");
            request.getRequestDispatcher("ChangePass").forward(request,response);


        }
        out.close();
    }
}
