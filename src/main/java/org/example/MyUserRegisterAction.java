package org.example;

import java.util.Scanner;

public class MyUserRegisterAction implements MyUserAction {
    String username ,password;
    Scanner scanner = new Scanner(System.in);
    MyUserManager userManager;

    public MyUserRegisterAction() {
        this.userManager = new MyUserManager(); // 初始化userManager
    }
     
    public void action() {
        System.out.println("现在你在用户注册子菜单里.");
        while (true) {
            System.out.print("请输入用户名:");
            this.username = scanner.nextLine();
            System.out.print("请输入密码:");
            this.password = scanner.nextLine();
            boolean success = userManager.registerUser(username, password);
            if (success) {
                System.out.println("用户注册成功！");
                break;
            } else {
                System.out.println("用户注册失败，请重试.");
            }
        }
    } 
}