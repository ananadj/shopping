package org.example;

import java.util.Scanner;

public class MyProductDeleteAction implements MyUserAction {
    Scanner scanner = new Scanner(System.in);
    MyProductManager productManager = new MyProductManager(); // 用于管理商品信息

    @Override
    public void action() {
        System.out.println("现在你在商品删除子菜单里.");
        System.out.print("请输入商品ID删除指定商品，或输入 'all' 删除所有商品: ");
        String input = scanner.nextLine();

        if ("all".equalsIgnoreCase(input)) {
            boolean success = productManager.deleteAllProducts();
            if (success) {
                System.out.println("所有商品已成功删除！");
            } else {
                System.out.println("删除所有商品失败，请重试.");
            }
        } else {
            try {
                int productId = Integer.parseInt(input);
                boolean success = productManager.deleteProduct(productId);
                if (success) {
                    System.out.println("商品已成功删除！");
                } else {
                    System.out.println("删除商品失败，或商品不存在，请重试.");
                }
            } catch (NumberFormatException e) {
                System.out.println("输入无效，请输入有效的商品ID或 'all'.");
            }
        }
    }
}