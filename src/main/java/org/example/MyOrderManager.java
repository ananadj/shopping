package org.example;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class MyOrderManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    // 结账并更新库存
    public boolean checkout(ShoppingCart cart) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            connection.setAutoCommit(false);
            for (Map.Entry<Integer, Integer> entry : cart.getProducts().entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();
                // 模拟支付操作
                boolean paid = simulatePayment(productId, quantity);
                if (!paid) {
                    connection.rollback();
                    return false;
                }
                // 更新库存
                updateInventory(productId, quantity);
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to checkout: " + e.getMessage());
            return false;
        }
    }

    private boolean simulatePayment(int productId, int quantity) {
        // 模拟支付渠道
        System.out.println("模拟支付 " + quantity + " 件商品，商品ID: " + productId);
        return true; // 假设支付总是成功
    }

    private void updateInventory(int productId, int quantity) throws SQLException {
        String updateQuery = "UPDATE Products SET quantity = quantity - ? WHERE productId = ?";
        try (PreparedStatement statement = DriverManager.getConnection(DB_URL).prepareStatement(updateQuery)) {
            statement.setInt(1, quantity);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }

    public void viewHistory(int userId) {
        String selectQuery = "SELECT orderId, orderDate, totalPrice, paymentMethod FROM Orders WHERE userId = ? ORDER BY orderDate DESC";
    
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String record = "Order ID: " + resultSet.getInt("orderId") +
                                    ", Date: " + resultSet.getTimestamp("orderDate") +
                                    ", Total Price: " + resultSet.getDouble("totalPrice") +
                                    ", Payment Method: " + resultSet.getString("paymentMethod");
                    System.out.println(record);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to view order history: " + e.getMessage());
        }
    }
    
     
}