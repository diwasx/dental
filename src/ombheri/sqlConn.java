/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ombheri;
import java.sql.*;


/**
 *
 * @author Lanaya
 */
public class sqlConn {
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    String b=null;
    
    public sqlConn(){
        try{  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dental","root","test10");  
            stmt=con.createStatement();  
//            System.out.println(stmt);
//            rs=stmt.executeQuery(b);  
//            while(rs.next())  
//            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//            con.close();  
        }catch(Exception e){
            System.out.println(e);
        }  
    }
    public void getsql(String sql){
        b=sql;
    }
}
