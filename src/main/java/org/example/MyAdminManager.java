package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// 管理员管理类
public class MyAdminManager {
    private static final String DB_URL = "jdbc:sqlite:admin.db";
    private static  String ADMIN_USERNAME = "admin";
    private static  String ADMIN_PASSWORD = "ynuinfo#777";

    public boolean adminLogin(String username, String password) {
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("管理员登录成功！");
            return true;
        } else {
            System.out.println("管理员登录失败，用户名或密码错误。");
            return false;
        }
    }

    public boolean changeAdminPassword(String oldPassword, String newPassword) {
        if (ADMIN_PASSWORD.equals(oldPassword)) {
            ADMIN_PASSWORD = newPassword;
            System.out.println("管理员密码修改成功！");
            return true;
        } else {
            System.out.println("密码修改失败，原密码错误。");
            return false;
        }
    }
    
    public boolean adminResetUserPassword(String username, String newPassword, MyUserManager userManager) {
        return userManager.resetUserPassword(username, newPassword);
    }


    public List<String> listAllUsers() {
        List<String> usersInfo = new ArrayList<>();
        String selectQuery = "SELECT id, username, userLevel, phone, email FROM Users";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
             PreparedStatement statement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String info = "ID: " + resultSet.getInt("id") +
                               ", Username: " + resultSet.getString("username") +
                               ", User Level: " + resultSet.getString("userLevel") +
                               ", Phone: " + resultSet.getString("phone") +
                               ", Email: " + resultSet.getString("email");
                usersInfo.add(info);
            }
        } catch (SQLException e) {
            System.out.println("Failed to list customers: " + e.getMessage());
        }
        return usersInfo;
    }

    public String findUserByUsername(String username) {
        String selectQuery = "SELECT id, username, userLevel, phone, email FROM Users WHERE username = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:users.db");
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String info = "ID: " + resultSet.getInt("id") +
                                   ", Username: " + resultSet.getString("username") +
                                   ", User Level: " + resultSet.getString("userLevel") +
                                   ", Phone: " + resultSet.getString("phone") +
                                   ", Email: " + resultSet.getString("email");
                    return info;
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to find user: " + e.getMessage());
        }
        return "User not found";
    }

    
    
    }


    








