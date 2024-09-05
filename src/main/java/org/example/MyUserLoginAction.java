package org.example;

import java.util.Scanner;

public class MyUserLoginAction implements MyUserAction {
    String username ,password;
    Scanner scanner = new Scanner(System.in);

    MyUserManager userManager=new MyUserManager();

    public MyUserLoginAction()
    {}

    public void action() {
        System.out.println("现在你在用户登录子菜单里.");
        while (true) {
            System.out.print("请输入用户名:");
            String username = scanner.nextLine();
            System.out.print("请输入密码:");
            String password = scanner.nextLine();
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
