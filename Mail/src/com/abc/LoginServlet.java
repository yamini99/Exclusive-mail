package com.abc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Mansi on 30-09-2016.
 */

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String email=request.getParameter("email");
        String password=request.getParameter("password");
        if(Login.validate(email, password)){
            out.print("you are successfully logged in!");
            request.getSession().setAttribute("login", "true");
            request.getSession().setAttribute("email", email);
            response.sendRedirect("InboxServlet");

        }else{
            out.print("<p>Sorry, username or password error</p>");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.close();
    }

}
