package org.example;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Integer, Integer> cart; // 商品ID和数量的映射

    public ShoppingCart() {
        cart = new HashMap<>();
    }

    // 将商品加入购物车
    public void addProduct(int productId, int quantity) {
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
    }

    // 移除购物车中的商品
    public void removeProduct(int productId) {
        cart.remove(productId);
    }

    // 修改购物车中的商品数量
    public void updateProductQuantity(int productId, int quantity) {
        if (quantity <= 0) {
            removeProduct(productId);
        } else {
            cart.put(productId, quantity);
        }
    }

    // 检查购物车是否为空
    public boolean isEmpty() {
        return cart.isEmpty();
    }

    // 获取购物车中的所有商品
    public Map<Integer, Integer> getProducts() {
        return new HashMap<>(cart);
    }
}