package com.practice2.suppermarket.interfaces;

public interface Customer {
    String getCustomerId();

    /**
     * 开始购物前的准备
     */
    void startShopping();

    /**
     * @return 顾客想购买的商品种类
     */
    Category chooseCategory();

    /**
     * 顾客是否购买此商品
     * @param merchandise 判断是否购买的商品
     * @return 购买多少个
     */
    int buyMerchandise(Merchandise merchandise);

    /**
     * 顾客是否买够了，要结账
     *
     * @return true: 要结账，false: 继续买
     */
    boolean wantToCheckout();

    /**
     * 付款
     * @param shoppingCart 此次构建买的商品的购物车
     * @param totalCost 经过超市折扣策略折扣后的折后总价
     * @return 成功支付返回支付的钱， 否则返回 -1
     */
    double payFor(ShoppingCart shoppingCart, double totalCost);


    /**
     * @return 顾客花了多少钱
     */
    double getMoneySpent();
}
