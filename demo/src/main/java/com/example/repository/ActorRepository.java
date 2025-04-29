package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Actor;
import com.example.utils.DatabaseConnection;

public class ActorRepository implements Repository<Actor> {

    private Connection getConnection () throws SQLException {
        return DatabaseConnection.getInstance();
    }

    private Actor createActor(ResultSet myResultSet) throws SQLException {
        Actor a = new Actor();
        a.setActorId(myResultSet.getInt("actor_id"));
        a.setFirstName(myResultSet.getString("first_name"));
        a.setLastName(myResultSet.getString("last_name"));

        return a;
    }
    
    @Override
    public List<Actor> findAll() {
        List<Actor> actors = new ArrayList<>();
        String sql = "SELECT * FROM sakila.actor";

        try (Statement myStatement = getConnection().createStatement();
        ) {
            ResultSet myResultSet = myStatement.executeQuery(sql);
            while(myResultSet.next()) {
                Actor actor = createActor(myResultSet);
                actors.add(actor);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actors;
    }
    
    @Override
    public Actor getById(Integer id) throws SQLException {
        Actor actor = null;
        String sql = "SELECT * FROM sakila.actor WHERE actor_id = ?";

        try (PreparedStatement myPreparedStatement = getConnection().prepareStatement(sql)) {
            myPreparedStatement.setInt(1, id);
            try (ResultSet myResultSet = myPreparedStatement.executeQuery()) {
                if (myResultSet.next()) {
                    actor = createActor(myResultSet);
                }
            }
        }
        return actor;

    }
    
    @Override
    public void save(Actor actor) {
        String sqlInsert = "INSERT INTO sakila.actor (first_name, last_name) VALUES (?, ?)";
        String sqlUpdate = "UPDATE sakila.actor SET first_name = ?, last_name = ? WHERE actor_id = ?";

        try (Connection conn = getConnection()) {
            if (actor.getActorId() == null || getById(actor.getActorId()) == null) {
                try (PreparedStatement myStatement = conn.prepareStatement(sqlInsert)) {
                    myStatement.setString(1, actor.getFirstName());
                    myStatement.setString(2, actor.getLastName());
                    myStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement myStatement = conn.prepareStatement(sqlUpdate)) {
                    myStatement.setString(1, actor.getFirstName());
                    myStatement.setString(2, actor.getLastName());
                    myStatement.setInt(3, actor.getActorId());
                    myStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM sakila.actor WHERE actor_id = ?";
        try (PreparedStatement myStatement = getConnection().prepareStatement(sql)) {
            myStatement.setInt(1, id);
            myStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
