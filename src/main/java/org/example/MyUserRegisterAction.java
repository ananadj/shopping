package org.example;

import java.util.Scanner;

public class MyUserRegisterAction {
    private Scanner scanner;
    private MyUserManager userManager;

    public MyUserRegisterAction(Scanner scanner, MyUserManager userManager) {
        this.scanner = scanner;
        this.userManager = userManager;
    }

    public void registerUser() {
        System.out.println("现在你在用户注册子菜单里.");
        while (true) {
            System.out.print("请输入用户名:");
            String username = this.scanner.nextLine();
            System.out.print("请输入密码:");
            String password = this.scanner.nextLine();
            boolean success = this.userManager.registerUser(username, password);
            if (success) {
                System.out.println("用户注册成功！");
                break;
            } else {
                System.out.println("用户注册失败，请重试.");
            }
        }
    }
}