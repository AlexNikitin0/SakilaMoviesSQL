package org.example;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import javax.sql.DataSource;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static String url = "jdbc:mysql://localhost:3306/sakila";
    public static String user = user = "root";
    public static String password = "123"; // not my real password



    public static Scanner keyboard = new Scanner(System.in);

    public static void main( String[] args )
    {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        DataManager dataManager = new DataManager(dataSource);
        System.out.println("1) Search by Last name of actor: ");
        System.out.println("2) Search Movies by Actor ID: ");
        String userChoice = keyboard.nextLine();
        String lastName;
        if(userChoice.equalsIgnoreCase("1")) {
            System.out.println("Enter the last name of an actor you like: ");

            lastName = keyboard.nextLine();
            List<String> actors = dataManager.getAllActors(lastName);
            //display all actors full names

            actors.forEach(System.out::println);
        }
        else if(userChoice.equalsIgnoreCase("2")){
            System.out.println("Enter the actor id: ");
            String id = keyboard.nextLine();
            List movies = dataManager.getFilmsByActorID(id);
            //display all movies
            movies.forEach(System.out::println);

        }
    }
}
