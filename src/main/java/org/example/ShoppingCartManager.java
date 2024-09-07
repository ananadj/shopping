package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    // 添加商品到购物车
    public void addToCart(int userId, int productId, int quantity) {
        String selectQuery = "SELECT quantity FROM ShoppingCart WHERE userId = ? AND productId = ?";
        String insertQuery = "INSERT INTO ShoppingCart (userId, productId, quantity) VALUES (?, ?, ?)";
        String updateQuery = "UPDATE ShoppingCart SET quantity = quantity + ? WHERE userId = ? AND productId = ?";
    
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, userId);
            selectStatement.setInt(2, productId);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    // 如果商品已存在，更新数量
                    int currentQuantity = resultSet.getInt("quantity");
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                        updateStatement.setInt(1, quantity);
                        updateStatement.setInt(2, userId);
                        updateStatement.setInt(3, productId);
                        updateStatement.executeUpdate();
                    }
                } else {
                    // 如果商品不存在，插入新记录
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                        insertStatement.setInt(1, userId);
                        insertStatement.setInt(2, productId);
                        insertStatement.setInt(3, quantity);
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to add product to cart: " + e.getMessage());
        }
    }

    //从购物车中移除商品
    public void removeFromCart(int userId, int productId) {
        String deleteQuery = "DELETE FROM ShoppingCart WHERE userId = ? AND productId = ?";
    
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
            deleteStatement.setInt(1, userId);
            deleteStatement.setInt(2, productId);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to remove product from cart: " + e.getMessage());
        }
    }

    //修改购物车中的商品数量
    public void updateCartQuantity(int userId, int productId, int quantity) {
        String updateQuery = "UPDATE ShoppingCart SET quantity = ? WHERE userId = ? AND productId = ?";
    
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, quantity);
            updateStatement.setInt(2, userId);
            updateStatement.setInt(3, productId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to update cart quantity: " + e.getMessage());
        }
    }

    //结账
    public boolean checkout(int userId, String paymentMethod) {
        // 计算总价
        double totalPrice = calculateTotalPrice(userId);
    
        // 创建订单
        String insertOrderQuery = "INSERT INTO Orders (userId, totalPrice, paymentMethod) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement insertStatement = connection.prepareStatement(insertOrderQuery)) {
            insertStatement.setInt(1, userId);
            insertStatement.setDouble(2, totalPrice);
            insertStatement.setString(3, paymentMethod);
            int affectedRows = insertStatement.executeUpdate();
    
            // 清空购物车
            String clearCartQuery = "DELETE FROM ShoppingCart WHERE userId = ?";
            try (PreparedStatement clearStatement = connection.prepareStatement(clearCartQuery)) {
                clearStatement.setInt(1, userId);
                clearStatement.executeUpdate();
            }
    
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to checkout: " + e.getMessage());
            return false;
        }
    }
    public double calculateTotalPrice(int userId) {
        // 查询购物车中所有商品的总价
        String selectQuery = "SELECT SUM(p.retailPrice * sc.quantity) AS total " +
                             "FROM Products p " +
                             "JOIN ShoppingCart sc ON p.productId = sc.productId " +
                             "WHERE sc.userId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
            selectStatement.setInt(1, userId);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("total");
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to calculate total price: " + e.getMessage());
        }
        return 0;
    }

 
}