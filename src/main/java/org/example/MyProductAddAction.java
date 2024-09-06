package org.example;

import java.util.Scanner;

public class MyProductAddAction implements MyUserAction {
    Scanner scanner = new Scanner(System.in);
    MyProductManager productManager;

    public MyProductAddAction() {
        this.productManager = new MyProductManager(); // 初始化productManager
    }

    public void action() {
        System.out.println("现在你在添加商品子菜单里.");
        System.out.print("请输入商品编号:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入商品名称:");
        String productName = scanner.nextLine();
        System.out.print("请输入生产厂家:");
        String manufacturer = scanner.nextLine();
        System.out.print("请输入生产日期 (格式: yyyy-MM-dd):");
        String manufactureDate = scanner.nextLine();
        System.out.print("请输入型号:");
        String model = scanner.nextLine();
        System.out.print("请输入进货价:");
        double purchasePrice = scanner.nextDouble();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入零售价格:");
        double retailPrice = scanner.nextDouble();
        scanner.nextLine(); // 消费换行符
        System.out.print("请输入库存数量:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // 消费换行符

        boolean success = productManager.addProduct(productId,productName, manufacturer, manufactureDate, model, purchasePrice, retailPrice, quantity);
        if (success) {
            System.out.println("商品添加成功！");
        } else {
            System.out.println("商品添加失败，请重试.");
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public MyProductManager getProductManager() {
        return productManager;
    }

    public void setProductManager(MyProductManager productManager) {
        this.productManager = productManager;
    }
}