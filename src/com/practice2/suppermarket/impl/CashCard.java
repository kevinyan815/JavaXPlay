package com.practice2.suppermarket.impl;

import com.practice2.suppermarket.interfaces.Card;
import com.practice2.suppermarket.interfaces.Customer;
import com.practice2.suppermarket.interfaces.ShoppingCart;

/**
 * 抵扣现金的卡
 */
public class CashCard implements Card {

    // 抵扣现金的点数
    private double point;

    public CashCard (double point) {
        this.point = point;
    }

    @Override
    public double processCardDiscount(double totalCost, double totalCostAfterDiscount,
                                      Customer customer, ShoppingCart shoppingCart) {
        if (totalCostAfterDiscount < point) {
            // 如果折扣下来剩下的钱比点数少，那么就抵扣掉需要付的剩下的钱
            point -= totalCostAfterDiscount;
            return totalCostAfterDiscount;
        } else {
            // 否则就抵扣掉所有的点
            point = 0;
            return point;
        }
    }
}
