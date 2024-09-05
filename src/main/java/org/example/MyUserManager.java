package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;	
import java.sql.ResultSet;
import java.sql.SQLException; 

public class MyUserManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";	
 
    public boolean registerUser(String username, String password){
        try (
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Users(username, password) VALUES (?, ?)");
        ) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("User registered successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to register user: " + e.getMessage());
            return false;
        }
    }
 
    public boolean loginUser(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("User logged in successfully!");
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Failed to log in user: " + e.getMessage());
            return false;
        }
    }
}