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

     

    public List<String> listAllProducts() {
        List<String> productsInfo = new ArrayList<>();
        String selectQuery = "SELECT productId, productName, manufacturer, manufactureDate, model, purchasePrice, retailPrice, quantity FROM Products";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String info = "Product ID: " + resultSet.getInt("productId") +
                               ", Name: " + resultSet.getString("productName") +
                               ", Manufacturer: " + resultSet.getString("manufacturer") +
                               ", Manufacture Date: " + resultSet.getString("manufactureDate") +
                               ", Model: " + resultSet.getString("model") +
                               ", Purchase Price: " + resultSet.getDouble("purchasePrice") +
                               ", Retail Price: " + resultSet.getDouble("retailPrice") +
                               ", Quantity: " + resultSet.getInt("quantity");
                productsInfo.add(info);
            }
        } catch (SQLException e) {
            System.out.println("Failed to list products: " + e.getMessage());
        }
        return productsInfo;
    }
      
    
    public boolean deleteAllProducts() {
        String deleteQuery = "DELETE FROM Products";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to delete all products: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(int productId) {
        String deleteQuery = "DELETE FROM Products WHERE productId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, productId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to delete product with ID " + productId + ": " + e.getMessage());
            return false;
        }
    }
    public List<String> searchProductsByName(String name) {
        List<String> productsInfo = new ArrayList<>();
        String selectQuery = "SELECT * FROM Products WHERE productName LIKE ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String info = "Product ID: " + resultSet.getInt("productId") +
                                   ", Name: " + resultSet.getString("productName") +
                                   ", Manufacturer: " + resultSet.getString("manufacturer") +
                                   ", Manufacture Date: " + resultSet.getString("manufactureDate") +
                                   ", Model: " + resultSet.getString("model") +
                                   ", Purchase Price: " + resultSet.getDouble("purchasePrice") +
                                   ", Retail Price: " + resultSet.getDouble("retailPrice") +
                                   ", Quantity: " + resultSet.getInt("quantity");
                    productsInfo.add(info);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to search products by name: " + e.getMessage());
        }
        return productsInfo;
    }

    // 根据生产厂家查询
    public List<String> searchProductsByManufacturer(String manufacturer) {
        List<String> productsInfo = new ArrayList<>();
        String selectQuery = "SELECT * FROM Products WHERE manufacturer LIKE ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, "%" + manufacturer + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String info = "Product ID: " + resultSet.getInt("productId") +
                                   ", Name: " + resultSet.getString("productName") +
                                   ", Manufacturer: " + resultSet.getString("manufacturer") +
                                   ", Manufacture Date: " + resultSet.getString("manufactureDate") +
                                   ", Model: " + resultSet.getString("model") +
                                   ", Purchase Price: " + resultSet.getDouble("purchasePrice") +
                                   ", Retail Price: " + resultSet.getDouble("retailPrice") +
                                   ", Quantity: " + resultSet.getInt("quantity");
                    productsInfo.add(info);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to search products by manufacturer: " + e.getMessage());
        }
        return productsInfo;
    }

    // 根据零售价格查询
    public List<String> searchProductsByRetailPrice(double minPrice) {
        List<String> productsInfo = new ArrayList<>();
        String selectQuery = "SELECT * FROM Products WHERE retailPrice >= ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setDouble(1, minPrice);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String info = "Product ID: " + resultSet.getInt("productId") +
                                   ", Name: " + resultSet.getString("productName") +
                                   ", Manufacturer: " + resultSet.getString("manufacturer") +
                                   ", Manufacture Date: " + resultSet.getString("manufactureDate") +
                                   ", Model: " + resultSet.getString("model") +
                                   ", Purchase Price: " + resultSet.getDouble("purchasePrice") +
                                   ", Retail Price: " + resultSet.getDouble("retailPrice") +
                                   ", Quantity: " + resultSet.getInt("quantity");
                    productsInfo.add(info);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to search products by retail price: " + e.getMessage());
        }
        return productsInfo;
    }

    // 组合查询
    public List<String> searchProductsByConditions(String name, String manufacturer, double minPrice) {
        List<String> productsInfo = new ArrayList<>();
        String selectQuery = "SELECT * FROM Products WHERE productName LIKE ? AND manufacturer LIKE ? AND retailPrice >= ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + manufacturer + "%");
            statement.setDouble(3, minPrice);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String info = "Product ID: " + resultSet.getInt("productId") +
                                   ", Name: " + resultSet.getString("productName") +
                                   ", Manufacturer: " + resultSet.getString("manufacturer") +
                                   ", Manufacture Date: " + resultSet.getString("manufactureDate") +
                                   ", Model: " + resultSet.getString("model") +
                                   ", Purchase Price: " + resultSet.getDouble("purchasePrice") +
                                   ", Retail Price: " + resultSet.getDouble("retailPrice") +
                                   ", Quantity: " + resultSet.getInt("quantity");
                    productsInfo.add(info);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to search products by conditions: " + e.getMessage());
        }
        return productsInfo;
    }
}

