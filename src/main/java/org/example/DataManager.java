package org.example;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {


private DataSource dataSource;


    public DataManager(BasicDataSource dataSource){
        this.dataSource = dataSource;

    }


    public List<String> getAllActors(String lastName){
        List<String> actors = new ArrayList<>();
        String sql = "SELECT first_name FROM actor WHERE last_name = ?";
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);


        ){
            preparedStatement.setString(1,lastName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String firstName = resultSet.getString("first_name");
                actors.add("your favorite actor's full name name is: " + firstName + " " + lastName);
            }
        } catch (
                SQLException e){
            e.printStackTrace();
        }
        return actors;
    }

    public List<String> getFilmsByActorID(String id){
        List<String> films = new ArrayList<>();
        String sql = "SELECT film.* FROM film JOIN film_actor ON film.film_id = film_actor.film_id WHERE film_actor.actor_id = ?";
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);


        ){
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String title = resultSet.getString("title");
                films.add("your favorite actor appears in: " + id + " " + title);
            }
        } catch (
                SQLException e){
            e.printStackTrace();
        }
        return films;


    }


}
