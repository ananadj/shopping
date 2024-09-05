package org.example;
import java.util.Scanner;

public class MyAdminPasswordAction implements MyUserAction {
    MyAdminManager adminManager = new MyAdminManager();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void action() {
        System.out.println("现在你在管理员密码管理子菜单里.");
        while (true) {
            System.out.print("请输入原密码:");
            String oldPassword = scanner.nextLine();
            System.out.print("请输入新密码:");
            String newPassword = scanner.nextLine();
            boolean success = adminManager.changeAdminPassword(oldPassword, newPassword);
            if (success) {
                break;
            } else {
                System.out.println("密码修改失败，请重试.");
            }
        }
    }
}