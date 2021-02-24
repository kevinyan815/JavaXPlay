package com.practice2.suppermarket.interfaces;

/**
 * 商品接口
 */
public interface Merchandise {
    String getName();

    double getSoldPrice();

    double getPurchasedPrice();

    int buy(int count);

    void putBack(int count);

    Category getCategory();

    int getCount();
}
