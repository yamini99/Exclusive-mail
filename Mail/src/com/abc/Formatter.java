package com.abc;

import java.text.SimpleDateFormat;

/**
 * Created by Mansi on 30-09-2016.
 */

public class Formatter {

    public static java.sql.Date getSqlDate(String strDate){
        java.sql.Date sqlDate=null;
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate=format.parse(strDate);
            sqlDate=new java.sql.Date(utilDate.getTime());
        }catch(Exception e){System.out.println(e);}
        return sqlDate;
    }

    public static java.sql.Date getCurrentDate(){
        java.sql.Date sqlCurrentDate=null;
        try{
            java.util.Date utilDate=java.util.Calendar.getInstance().getTime();
            sqlCurrentDate=new java.sql.Date(utilDate.getTime());

        }catch(Exception e){System.out.println(e);}
        return sqlCurrentDate;
    }
}
