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

            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            statement.executeUpdate("DROP TABLE IF EXISTS Products");
            statement.executeUpdate("DROP TABLE IF EXISTS Orders");
            statement.executeUpdate("DROP TABLE IF EXISTS ShoppingCart");



            
            //用户列表
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Users ( username TEXT, password TEXT,id INTEGER PRIMARY KEY,userLevel TEXT,registrationDate DATETIME DEFAULT CURRENT_TIMESTAMP, totalSpent REAL,phone TEXT,email TEXT)";
            //商品列表
            String createProductsTableQuery = "CREATE TABLE IF NOT EXISTS Products ("
            + "productId INTEGER PRIMARY KEY, "
            + "productName TEXT, "
            + "manufacturer TEXT, "
            + "manufactureDate DATE, "
            + "model TEXT, "
            + "purchasePrice REAL, "
            + "retailPrice REAL, "
            + "quantity INTEGER)";

            String createOrdersTableQuery = "CREATE TABLE IF NOT EXISTS Orders ("
        + "orderId INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "userId INTEGER, "
        + "orderDate DATETIME DEFAULT CURRENT_TIMESTAMP, "
        + "products TEXT, "
        + "totalPrice REAL, "
        + "paymentMethod TEXT, "
        + " FOREIGN KEY (userId) REFERENCES Users(id) "
        + ")";

        String createShoppingCartTableQuery = "CREATE TABLE IF NOT EXISTS ShoppingCart ("
        + "userId INTEGER, "
        + "productId INTEGER, "
        + "quantity INTEGER, "
        + "PRIMARY KEY (userId, productId), "
        + "FOREIGN KEY (userId) REFERENCES Users(id), "
        + "FOREIGN KEY (productId) REFERENCES Products(productId)"
        + ")";

        

            statement.executeUpdate(createOrdersTableQuery);
            statement.executeUpdate(createTableQuery);
            statement.executeUpdate(createProductsTableQuery);
            statement.executeUpdate( createShoppingCartTableQuery);

            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to initialize database: " + e.getMessage());
        }
    }
}
