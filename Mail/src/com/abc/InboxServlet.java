package com.abc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import java.sql.*;


/**
 * Created by Mansi on 30-09-2016.
 */

@WebServlet(name ="InboxServlet")
public class InboxServlet extends HttpServlet {
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
            out.print("<h1>Inbox</h1>");

            String msg=(String)request.getAttribute("msg");
            if(msg!=null){
                out.print("<p>"+msg+"</p>");
            }

            try{
                Connection con=ConProvider.getConnection();
                PreparedStatement ps=con.prepareStatement("select * from mail_message where receiver=? and rectrash='no' order by id desc");
                ps.setString(1,email);
                ResultSet rs=ps.executeQuery();
                out.print("<table border='1' style='width:700px'>");
                out.print("<tr style='background-color:grey;color:white'><td>Sender</td><td>Subject</td></tr>");
                while(rs.next()){
                    out.print("<tr><td>"+rs.getString("sender")+"</td><td><a href='ViewMailServlet?id="+rs.getString("id")+"'>"+rs.getString("subject")+"</td></tr>");



                }
                out.print("</table>");
                con.close();
            }catch(Exception e){out.print(e);}
        }
        out.close();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
