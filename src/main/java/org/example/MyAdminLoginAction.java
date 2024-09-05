package org.example;
import java.util.Scanner;

public class MyAdminLoginAction implements MyUserAction {
    MyAdminManager adminManager = new MyAdminManager();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void action() {
        System.out.println("现在你在管理员登录子菜单里.");
        while (true) {
            System.out.print("请输入管理员用户名:");
            String username = scanner.nextLine();
            System.out.print("请输入管理员密码:");
            String password = scanner.nextLine();
            boolean success = adminManager.adminLogin(username, password);
            if (success) {
                System.out.println("管理员登录成功！");
                break;
            } else {
                System.out.println("管理员登录失败，请重试.");
            }
        }
    }
}
