package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyShoppingHistory {
    private static final String DB_URL = "jdbc:sqlite:your_database_name.db";

    // 查看购物历史
    public List<String> viewHistory(int userId) {
        List<String> history = new ArrayList<>();
        String selectQuery = "SELECT * FROM Orders WHERE userId = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String orderInfo = "Order ID: " + resultSet.getInt("orderId") +
                                       ", Date: " + resultSet.getTimestamp("orderDate") +
                                       ", Products: " + resultSet.getString("products");
                    history.add(orderInfo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to view shopping history: " + e.getMessage());
        }
        return history;
    }
}