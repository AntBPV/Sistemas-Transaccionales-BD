package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection myConnection = null;
        Statement myStatement = null;
        ResultSet myResultSet = null;

        
        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            myStatement = myConnection.createStatement();
            myStatement.executeUpdate("UPDATE sakila.actor set first_name = 'antonio' where actor_id=20");
            myResultSet=myStatement.executeQuery("Select * from sakila.actor");
            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name") + " " + myResultSet.getString("last_name"));
            }
            System.out.println("Chevere");
        } catch (Exception e) {
            System.out.println("Cagaste");
        }
    }
}