package org.example;

import java.util.Scanner;

public class MyUserAddAction implements MyUserAction {
    Scanner scanner = new Scanner(System.in);
    MyUserManager userManager = new MyUserManager(); // 用于添加用户信息

    @Override
    public void action() {
        System.out.println("现在你在添加用户子菜单里.");
        System.out.print("请输入用户名:");
        String username = scanner.nextLine();
        System.out.print("请输入用户级别（如：金牌客户、银牌客户、铜牌客户）:");
        String user_level = scanner.nextLine();
        System.out.print("请输入手机号:");
        String phone = scanner.nextLine();
        System.out.print("请输入邮箱:");
        String email = scanner.nextLine();

        // 调用userManager的addUser方法添加用户
        boolean success = userManager.addUser(username, user_level, phone, email);
        if (success) {
            System.out.println("用户信息添加成功！");
        } else {
            System.out.println("用户信息添加失败，请重试.");
        }
    }
}