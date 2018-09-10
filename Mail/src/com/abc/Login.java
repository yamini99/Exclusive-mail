package com.abc;

/**
 * Created by Mansi on 30-09-2016.
 */

import java.sql.*;

public class Login {

    public static boolean validate(String email,String password){
        boolean status=false;
        try{
            Connection con=ConProvider.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from mail_user where email=? and password=? and authorized=?");
            ps.setString(1,email);
            ps.setString(2,password);
            ps.setString(3,"yes");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                status=true;
            }


        }catch(Exception e){System.out.println(e);}

        return status;
    }

    public static boolean validate1(String email){
        boolean status=false;
        try{
            Connection con=ConProvider.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from mail_user where email=? and authorized=?");
            ps.setString(1,email);
            ps.setString(2,"yes");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                status=true;
            }


        }catch(Exception e){System.out.println(e);}

        return status;
    }

    public static boolean validate2(String id){
        boolean status=false;
        try{
            Connection con=ConProvider.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from mail_user where id=?");
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                status=true;
            }


        }catch(Exception e){System.out.println(e);}

        return status;
    }

    public static boolean validate3(String id){
        boolean status=false;
        try{
            Connection con=ConProvider.getConnection();
            PreparedStatement ps=con.prepareStatement("select * from mail_message where id=?");
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                status=true;
            }


        }catch(Exception e){System.out.println(e);}

        return status;
    }
}
