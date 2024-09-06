package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyProductManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public boolean addProduct(int productId,String productName, String manufacturer, String manufactureDate, String model, double purchasePrice, double retailPrice, int quantity) {
        String insertQuery = "INSERT INTO Products (productId, productName, manufacturer, manufactureDate, model, purchasePrice, retailPrice, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, productId);
            statement.setString(2, productName);
            statement.setString(3, manufacturer);
            statement.setString(4, manufactureDate);
            statement.setString(5, model);
            statement.setDouble(6, purchasePrice);
            statement.setDouble(7, retailPrice);
            statement.setInt(8, quantity);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to add product: " + e.getMessage());
            return false;
        }
    }

    public boolean updateProduct(int productId,String productName,String manufacturer, String manufactureDate, String model, double purchasePrice, double retailPrice, int quantity) {
        String updateQuery = "UPDATE Products SET productId= ?,productName = ?, manufacturer = ?, manufactureDate = ?, model = ?, purchasePrice = ?, retailPrice = ?, quantity = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setInt(1, productId);
            statement.setString(2, productName);
            statement.setString(3, manufacturer);
            statement.setString(4, manufactureDate);
            statement.setString(5, model);
            statement.setDouble(6, purchasePrice);
            statement.setDouble(7, retailPrice);
            statement.setInt(8, quantity);
            
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to update product: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String deleteQuery = "DELETE FROM Products WHERE product_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, productId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to delete product: " + e.getMessage());
            return false;
        }
    }

    public List<String> listProducts() {
        List<String> products = new ArrayList<>();
        String selectQuery = "SELECT * FROM Products";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                products.add("Product ID: " + resultSet.getInt("product_id") + ", Name: " + resultSet.getString("product_name") + ", Manufacturer: " + resultSet.getString("manufacturer") + ", etc.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to list products: " + e.getMessage());
        }
        return products;
    }
      
    


}