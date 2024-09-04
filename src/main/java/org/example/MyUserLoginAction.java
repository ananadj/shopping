package org.example;

import java.util.Scanner;

public class MyUserLoginAction {
    private Scanner scanner;
    private MyUserManager userManager;

    public MyUserLoginAction(Scanner scanner, MyUserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }

    public void loginUser() {
        System.out.println("现在你在用户登录子菜单里.");
        while (true) {
            System.out.print("请输入用户名:");
            String username = this.scanner.nextLine();
            System.out.print("请输入密码:");
            String password = this.scanner.nextLine();
            boolean success = this.userManager.loginUser(username, password);
            if (success) {
                System.out.println("用户登录成功！");
                break;
            } else {
                System.out.println("用户名或密码错误，请重试.");
            }
        }
    }
}
