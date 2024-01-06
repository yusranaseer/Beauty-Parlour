package com.example.pageone;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class mySqlConnect {

    Connection conn=null;
    public static Connection ConnectDb(){
        try{
            //Connection conn =  DriverManager.getConnection("jdbc:sqlite:D:/UWU/2.2/RAD/Practical/HomeService/homeService.db");
            //Class.forName("com.mysql.jdbc.Driver");


           try{
               Class.forName("com.mysql.jdbc.Driver");
               Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/beautypalor","root","");
               //JOptionPane.showMessageDialog(null,"Databased Connected ");
               return conn;
           }catch (ClassNotFoundException e){
               JOptionPane.showMessageDialog(null,e);
               return null;
           }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;

        }

    }

}
