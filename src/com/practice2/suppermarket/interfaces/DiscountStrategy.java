package com.practice2.suppermarket.interfaces;

/**
 * 超市的折扣策略
 */
public interface DiscountStrategy {
    /**
     * @param shoppingCart 本次购物的购物车
     * @return 应用此折扣策略折扣了多少钱
     */
    double discount(ShoppingCart shoppingCart);
}
