package org.example;

import java.util.List;
import java.util.Scanner;

public class MyProductSearchAction implements MyUserAction {
    Scanner scanner = new Scanner(System.in);
    MyProductManager productManager = new MyProductManager(); // 用于管理商品信息

    @Override
    public void action() {
        System.out.println("现在你在商品查询子菜单里.");
        System.out.print("请输入商品名称（留空查询所有商品）: ");
        String name = scanner.nextLine();

        System.out.print("请输入生产厂家（留空查询所有商品）: ");
        String manufacturer = scanner.nextLine();

        System.out.print("请输入最小零售价格（留空查询所有商品）: ");
        String priceInput = scanner.nextLine();
        double minPrice = 0;
        try {
            minPrice = Double.parseDouble(priceInput);
        } catch (NumberFormatException e) {
            minPrice = 0; // 无效输入，查询所有商品
        }

        if (name.isEmpty() && manufacturer.isEmpty() && minPrice == 0) {
            // 查询所有商品
            List<String> products = productManager.listAllProducts();
            for (String product : products) {
                System.out.println(product);
            }
        } else {
            // 组合查询
            List<String> products = productManager.searchProductsByConditions(name, manufacturer, minPrice);
            for (String product : products) {
                System.out.println(product);
            }
        }
    }
}