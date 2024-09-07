package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MyUserShoppingAction implements MyUserAction {
    private Scanner scanner = new Scanner(System.in);
    private MyUserManager userManager = new MyUserManager(); // 用户管理
    private ShoppingCartManager cartManager = new ShoppingCartManager(); // 购物车管理
    private MyOrderManager orderManager = new MyOrderManager(); // 订单管理

    @Override
    public void action() {
        System.out.print("请输入用户名：");
        String username = scanner.nextLine();
        System.out.print("请输入密码：");
        String password = scanner.nextLine();

        if (userManager.loginUser(username, password)) {
            System.out.println("登录成功！欢迎使用购物系统！");
            shoppingSession();
        } else {
            System.out.println("登录失败，用户名或密码错误。");
        }
    }

    private void shoppingSession() {
        while (true) {
            System.out.println("请选择操作：1. 添加商品到购物车 2. 移除购物车中的商品 3. 修改购物车中的商品 4. 结账 5. 查看购物历史 6. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消费换行符

            switch (choice) {
                case 1:
                    addProductToCart();
                    break;
                case 2:
                    removeProductFromCart();
                    break;
                case 3:
                    updateProductInCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    displayHistory();
                    break;
                case 6:
                    System.out.println("感谢使用，再见！");
                    return;
                default:
                    System.out.println("无效的选择，请重试。");
            }
        }
    }

    private void addProductToCart() {
        System.out.print("请输入用户ID：");
        int userId = scanner.nextInt();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入商品名称：");
        String productName = scanner.nextLine();
        System.out.print("请输入数量：");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        // 根据商品名称查找商品ID
        int productId = findProductIdByName(productName);
        if (productId == -1) {
            System.out.println("未找到该商品。");
            return;
        }
        cartManager.addToCart(userId, productId, quantity);
        System.out.println("商品已添加到购物车。");
    }

    private void removeProductFromCart() {
        System.out.print("请输入用户ID：");
        int userId = scanner.nextInt();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入要移除的商品名称：");
        String productName = scanner.nextLine();

        // 根据商品名称查找商品ID
        int productId = findProductIdByName(productName);
        if (productId == -1) {
            System.out.println("未找到该商品。");
            return;
        }
        cartManager.removeFromCart(userId, productId);
        System.out.println("商品已从购物车移除。");
    }

    private void updateProductInCart() {
        System.out.print("请输入用户ID：");
        int userId = scanner.nextInt();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入要修改的商品名称：");
        String productName = scanner.nextLine();
        System.out.print("请输入新的数量（小于等于0则移除）：");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        // 根据商品名称查找商品ID
        int productId = findProductIdByName(productName);
        if (productId == -1) {
            System.out.println("未找到该商品。");
            return;
        }
        cartManager.updateCartQuantity(userId, productId, quantity);
        System.out.println("购物车已更新。");
    }

    private void checkout() {
        System.out.print("请输入用户ID：");
        int userId = scanner.nextInt();
        scanner.nextLine(); // 消费换行符
        System.out.print("选择支付方式（支付宝/微信/银行卡）：");
        String paymentMethod = scanner.nextLine();

        if (cartManager.checkout(userId, paymentMethod)) {
            System.out.println("结账成功，感谢您的购买！");
        } else {
            System.out.println("结账失败，请重试。");
        }
    }

    public void displayHistory() {
        System.out.print("请输入用户ID查看购物历史：");
        int userId = scanner.nextInt();
        scanner.nextLine(); // 消费换行符
        orderManager.viewHistory(userId);
    }


    public int findProductIdByName(String productName) {
        String selectQuery = "SELECT productId FROM Products WHERE productName = ?";
        try (Connection connection = DriverManager.getConnection( "jdbc:sqlite:users.db");
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, productName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // 如果找到了商品，返回对应的商品ID
                    return resultSet.getInt("productId");
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to find product ID by name: " + e.getMessage());
        }
        // 如果没有找到商品，返回-1
        return -1;
    }
    
}