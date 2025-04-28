package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.utils.DatabaseConnection;

public class Select {
    public static void main(String[] args) {
        Statement myStatement = null;
        ResultSet myResultSet = null;

        try {
            Connection myConnection = DatabaseConnection.getInstance();

            myStatement = myConnection.createStatement();
            myResultSet = myStatement.executeQuery("Select * from sakila.actor");

            while (myResultSet.next()) {
                System.out.println(myResultSet.getString("first_name") + " " + myResultSet.getString("last_name"));
            }
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
