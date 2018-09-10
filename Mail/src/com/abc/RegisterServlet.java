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

@WebServlet(name = "RegisterServlet")

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String gender=request.getParameter("gender");
        String dob=request.getParameter("dob");
        String addressLine=request.getParameter("addressLine");
        String city=request.getParameter("city");
        String state=request.getParameter("state");
        String country=request.getParameter("country");
        String contact=request.getParameter("contact");

        if(!Login.validate2(id)) {
            int status = Register.save(id, name, email + "@cspit.com", password, gender, dob, addressLine, city, state, country, contact);

            if (status > 0) {
                out.print("<p>You are successfully registered!</p>");
                request.getRequestDispatcher("login.html").include(request, response);

            } else {
                out.print("<p>You are not registered because of invalid email/invalid id, try another...</p>");

                request.getRequestDispatcher("register.html").include(request, response);

            }
        }
        else
        {
            out.print("<p>You are not registered because of invalid email/invalid id, try another...</p>");

            request.getRequestDispatcher("register.html").include(request, response);
        }
        out.close();
    }

}

