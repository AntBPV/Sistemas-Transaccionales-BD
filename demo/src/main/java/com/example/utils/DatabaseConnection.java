package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static String url = "jdbc:mysql://localhost:3306";
    private static String user = "root";
    private static String password = "root";
    
    private static Connection myConnection;

    public static Connection getInstance()throws Exception{
        if(myConnection == null){
            myConnection = DriverManager.getConnection(url, user, password);
        }
        return myConnection;
    }
}
