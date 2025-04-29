package com.example;

import java.sql.Connection;

import com.example.models.Actor;
import com.example.repository.ActorRepository;
import com.example.repository.Repository;
import com.example.utils.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        try (Connection myConn = DatabaseConnection.getInstance()) {
            Repository<Actor> repository = new ActorRepository();
            Actor actor1 = repository.getById(1);
            System.out.println(actor1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}