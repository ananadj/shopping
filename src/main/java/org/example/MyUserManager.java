package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;	
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

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

    public boolean resetUserPassword(String username, String newPassword) {
        try (
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = connection.prepareStatement("UPDATE Users SET password = ? WHERE username = ?");
        ) {
            statement.setString(1, newPassword);
            statement.setString(2, username);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Failed to reset user password: " + e.getMessage());
            return false;
        }
    }

    public List<CustomerInfo> listAllCustomers() {
        List<CustomerInfo> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
             PreparedStatement statement = connection.prepareStatement("SELECT id, username, user_level, registration_date, total_spent, phone, email FROM Customers");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                CustomerInfo customer = new CustomerInfo(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("user_level"),
                        resultSet.getTimestamp("registration_date").toString(),
                        resultSet.getDouble("total_spent"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Failed to list customers: " + e.getMessage());
        }
        return customers;
    }
    
    public boolean addUser(int id,String username, String userLevel, String phone, String email) {
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            // 检查用户是否存在
            try (PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
                checkStatement.setString(1, username);
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next()) {
                    // 用户存在，更新用户信息
                    try (PreparedStatement updateStatement = connection.prepareStatement(
                            "UPDATE Users SET id = ?, userLevel = ?, phone = ?, email = ? WHERE username = ?")) {
                        updateStatement.setInt(1, id);
                        updateStatement.setString(2, userLevel);
                        updateStatement.setString(3, phone);
                        updateStatement.setString(4, email);
                        updateStatement.setString(5, username);
                        int affectedRows = updateStatement.executeUpdate();
                        return affectedRows > 0;
                    }
                } else {
                    // 用户不存在，需要注册
                    System.out.println("未注册用户");
                     return false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to add or update user: " + e.getMessage());
            return false;
        }
    }
    }


