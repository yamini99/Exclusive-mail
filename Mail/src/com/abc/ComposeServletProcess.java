package com.abc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="ComposeServletProcess")
public class ComposeServletProcess extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getRequestDispatcher("header.html").include(request, response);


        String id = request.getParameter("id");
        String receiver = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        message = message.replaceAll("\n", "<br/>");
        String email = (String) request.getSession(false).getAttribute("email");

        System.out.println(email);

        if ((Login.validate1(receiver)) && (!Login.validate3(id)))
        {
            int i = compose.save(id, email, receiver, subject, message);

        if (i > 0) {
            request.setAttribute("msg", "Message Successfully Sent....");
            request.getRequestDispatcher("ComposeServlet").forward(request, response);
        }
    }

        else
        {
            request.setAttribute("msg", "Wrong Email Address of Receiver/Id, Try Again...!");
            request.getRequestDispatcher("ComposeServlet").forward(request, response);
        }
        out.close();

    }

}
