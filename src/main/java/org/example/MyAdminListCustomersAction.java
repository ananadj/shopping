package org.example;
import java.util.List;

public class MyAdminListCustomersAction implements MyUserAction {
    MyAdminManager adminManager = new MyAdminManager();
    MyUserManager userManager = new MyUserManager(); // 需要userManager来实际执行查询

    @Override
    public void action() {
        System.out.println("现在你在管理员列出用户信息子菜单里.");
        List<CustomerInfo> customers = userManager.listAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("没有找到用户信息。");
        } else {
            for (CustomerInfo customer : customers) {
                System.out.println(customer);
            }
        }
    }
}

