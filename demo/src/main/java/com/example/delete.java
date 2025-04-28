package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class delete {
    public static void main(String[] args) {
        Connection myConnection = null;
        PreparedStatement myStatement = null;

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");

            String sql = "DELETE FROM sakila.actor WHERE actor_id = ?";
            myStatement = myConnection.prepareStatement(sql);

            myStatement.setInt(1, 999); 

            int rowsAffected = myStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsAffected);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
