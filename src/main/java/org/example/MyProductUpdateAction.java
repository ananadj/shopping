package org.example;

import java.util.Scanner;

public class MyProductUpdateAction implements MyUserAction {
    Scanner scanner = new Scanner(System.in);
    MyProductManager productManager;

    public MyProductUpdateAction() {
        this.productManager = new MyProductManager(); // 初始化productManager
    }

    public void action() {
        System.out.println("现在你在更新商品子菜单里.");
        System.out.print("请输入要更新的商品ID:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        System.out.print("请输入新的商品名称:");
        String productName = scanner.nextLine();
        System.out.print("请输入新的生产厂家:");
        String manufacturer = scanner.nextLine();
        System.out.print("请输入新的生产日期 (格式: yyyy-MM-dd):");
        String manufactureDate = scanner.nextLine();
        System.out.print("请输入新的型号:");
        String model = scanner.nextLine();
        System.out.print("请输入新的进货价:");
        double purchasePrice = scanner.nextDouble();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入新的零售价格:");
        double retailPrice = scanner.nextDouble();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入新的库存数量:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        boolean success = productManager.updateProduct(productId, productName, manufacturer, manufactureDate, model, purchasePrice, retailPrice, quantity);
        if (success) {
            System.out.println("商品更新成功！");
        } else {
            System.out.println("商品更新失败，请重试.");
        }
    }
}

    