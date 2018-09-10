package com.abc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class compose {

    public static int save(String id,String sender,String receiver,String subject,String message){
        int status=0;

            try {
                Connection con = ConProvider.getConnection();
                PreparedStatement ps = con.prepareStatement("insert into mail_message(id,sender,receiver,subject,message,sendtrash,messagedate,rectrash) values(?,?,?,?,?,?,?,?)");
                ps.setString(1, id);
                ps.setString(2, sender);
                ps.setString(3, receiver);
                ps.setString(4, subject);
                ps.setString(5, message);
                ps.setString(6, "no");
                ps.setDate(7, Formatter.getCurrentDate());
                ps.setString(8, "no");
                status = ps.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }

        return status;
    }
}