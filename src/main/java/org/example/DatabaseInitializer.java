package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public void initializeDatabase() {
     try (Connection connection = DriverManager.getConnection(DB_URL);
               Statement statement = connection.createStatement()) {

            //statement.executeUpdate("DROP TABLE IF EXISTS Users");

            String createTableQuery = "CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,user_level TEXT,registration_date DATETIME DEFAULT CURRENT_TIMESTAMP, total_spent REAL,phone TEXT,email TEXT)";
            
            statement.executeUpdate(createTableQuery);
            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to initialize database: " + e.getMessage());
        }
    }
}
