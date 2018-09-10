package com.abc;

import java.sql.*;

/**
 * Created by Mansi on 30-09-2016.
 */

public class ConProvider {

    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            try {
                con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "system", "mansi");
                System.out.print("connection success");
            } catch (SQLException var2) {
                var2.printStackTrace();
            }

        }catch(Exception e){
            System.out.println("its me");
            System.out.println(e);}
        return con;
    }
}
