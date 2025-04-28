package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Actor;
import com.example.utils.DatabaseConnection;

public class ActorRepository implements Repository<Actor> {
    
    @Override
    public List<Actor> findAll() {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT * FROM sakila.actor";

        try (Connection myConnection = DatabaseConnection.getInstance();
        PreparedStatement statement = myConnection.prepareStatement(sql);
        ResultSet myResultSet = statement.executeQuery()) {
            while(myResultSet.next()) {
                Actor actor = new Actor();
                actor.setActorId(myResultSet.getInt("id"));
                actor.setFirstName(myResultSet.getString("first_name"));
                actor.setLastName(myResultSet.getString("last_name"));
                
                actors.add(actor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actors;
    }
    
    @Override
    public Actor getById(Integer id) {
        Actor actor = new Actor();
        String sql = "Select * FROM sakila.actor WHERE id = ?";

        try (Connection myConnection = DatabaseConnection.getInstance();
            PreparedStatement statement = myConnection.prepareStatement(sql);
        ) {
            statement.setInt(1, id);
            try (ResultSet myResultSet = statement.executeQuery()) {
                if(myResultSet.next()) {
                    actor.setActorId(myResultSet.getInt("id"));
                    actor.setFirstName(myResultSet.getString("first_name"));
                    actor.setLastName(myResultSet.getString("last_name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return actor;
    }
    
    @Override
    public void save(Actor t) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        
    }
    
}
