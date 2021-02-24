package com.practice2.suppermarket.impl;

import com.practice2.suppermarket.interfaces.Category;
import com.practice2.suppermarket.interfaces.Customer;
import com.practice2.suppermarket.interfaces.Merchandise;
import com.practice2.suppermarket.interfaces.ShoppingCart;

import  static com.practice2.suppermarket.util.ShoppingUtil.getRandomCategory;

public abstract class AbsCustomer implements Customer {
    private Category shouldBuy;
    private String customerId;
    private double moneySpent;
    private int guangLeft = 0;
    private int guangCount = 0;

    public static final int DEFAULT_GUANG_COUNT = 5;

    public AbsCustomer (String customerId, Category shouldBuy, int guangCount) {
        this.shouldBuy = shouldBuy;
        this.guangCount = guangCount;
        this.customerId = customerId;
    }

    public AbsCustomer(String customerId, Category shouldBuy) {
        this(customerId, shouldBuy, DEFAULT_GUANG_COUNT);
    }
    public Category getShouldBuy() {
        return shouldBuy;
    }

    @Override
    public String getCustomerId() {
        return customerId;
    }

    @Override
    public double getMoneySpent() {
        return moneySpent;
    }

    @Override
    public void startShopping() {
        guangLeft = guangCount;
    }

    @Override
    public Category chooseCategory() {
        if (guangLeft + 1 > guangCount) {
            return  shouldBuy;
        } else {
            return getRandomCategory();
        }
    }

    @Override
    public int buyMerchandise(Merchandise merchandise) {
        return 0;
    }

    @Override
    public boolean wantToCheckout() {
        guangLeft--;
        return  guangLeft < 0;
    }

    @Override
    public double payFor(ShoppingCart shoppingCart, double totalCost) {
        moneySpent += totalCost;
        return moneySpent;
    }

    public int getGuangLeft() {
        return guangLeft;
    }

    public int getGuangCount() {
        return guangCount;
    }

    public void setGuangCount(int guangCount) {
        this.guangCount = guangCount;
    }


}
