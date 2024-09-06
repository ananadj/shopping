package org.example;

import java.util.List;
import java.util.Scanner;

public class MyAdminListUserAction implements MyUserAction {
    Scanner scanner = new Scanner(System.in);
    MyAdminManager adminManager = new MyAdminManager(); // 用于管理用户信息

    @Override
    public void action() {
        System.out.println("现在你在用户信息子菜单里.");
        System.out.print("请输入要查找的用户名（输入 'all' 查看所有用户）:");
        String input = scanner.nextLine();

        if ("all".equalsIgnoreCase(input)) {
            // 列出所有用户信息
            List<String> users = adminManager.listAllUsers();
            for (String user : users) {
                System.out.println(user);
            }
        } else {
            // 查找单个用户信息
            String user = adminManager.findUserByUsername(input);
            System.out.println(user);
        }
    }
}