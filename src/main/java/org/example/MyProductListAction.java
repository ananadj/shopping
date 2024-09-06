package org.example;

import java.util.List;
import java.util.Scanner;

public class MyProductListAction implements MyUserAction {
    Scanner scanner = new Scanner(System.in);
    MyProductManager productManager = new MyProductManager(); // 用于管理商品信息

    @Override
    public void action() {
        System.out.println("现在你在商品信息列表子菜单里.");
        // 列出所有商品信息
        List<String> products = productManager.listAllProducts();
        for (String product : products) {
            System.out.println(product);
        }
    }
}