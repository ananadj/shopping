package org.example;
import java.util.Scanner;

public class MyAdminResetPasswordAction implements MyUserAction {
    MyAdminManager adminManager = new MyAdminManager();
    Scanner scanner = new Scanner(System.in);
    MyUserManager userManager = new MyUserManager(); // 需要userManager来实际执行密码重置

    @Override
    public void action() {
        System.out.println("现在你在管理员重置用户密码子菜单里.");
        while (true) {
            System.out.print("请输入要重置密码的用户名:");
            String username = scanner.nextLine();
            System.out.print("请输入新密码:");
            String newPassword = scanner.nextLine();
            boolean success = adminManager.adminResetUserPassword(username, newPassword, userManager);
            if (success) {
                System.out.println("用户密码重置成功！");
            } else {
                System.out.println("用户密码重置失败，请重试.");
            }
            System.out.print("继续重置其他用户密码吗？(yes/no): ");
            String continueReset = scanner.nextLine();
            if (!"yes".equalsIgnoreCase(continueReset)) {
                break;
            }
        }
    }
}
