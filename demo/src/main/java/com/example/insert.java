package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class insert {
    public static void main(String[] args) {
        Connection myConnection = null;
        PreparedStatement myStatement = null;
        ResultSet myResultSet = null;

        try {
            myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            String sql = "INSERT INTO sakila.actor (actor_id,first_name, last_name) values (?,?,?) ";
            myStatement = myConnection.prepareStatement(sql);

            myStatement.setInt(1,999);
            myStatement.setString(2, "Antonio");
            myStatement.setString(3, "Cagaste");
            myStatement.executeUpdate();
            int rowsAffected = myStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
