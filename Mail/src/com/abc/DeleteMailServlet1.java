package com.abc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

/**
 * Created by Mansi on 30-09-2016.
 */

@WebServlet(name = "DeleteMailServlet1")
public class DeleteMailServlet1 extends HttpServlet {
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

            int id=Integer.parseInt(request.getParameter("id"));

            try{
                Connection con=ConProvider.getConnection();
                PreparedStatement ps=con.prepareStatement("update mail_message set sendtrash=? where id=?");
                ps.setString(1,"yes");
                ps.setInt(2,id);
                int i=ps.executeUpdate();
                if(i>0){
                    request.setAttribute("msg","Mail successfully deleted!");
                    request.getRequestDispatcher("InboxServlet").forward(request, response);
                }
                con.close();
            }catch(Exception e){out.print(e);}
        }



//        request.getRequestDispatcher("footer.html").include(request, response);
        out.close();
    }

}
