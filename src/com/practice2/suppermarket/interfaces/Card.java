package com.practice2.suppermarket.interfaces;

public interface Card {
    /**
     * 执行完毕超市自身的打折策略之后，确定了顾客需要付多少钱。
     * 然后在根据顾客是否有VIP卡打折，判断是否继续打折，看顾客是否有现金卡，抵扣现金
     *
     * @param totalCost 商品的原价总价
     * @param totalCostAfterDiscount 实现完超市活动后的总价
     * @param customer 购买的顾客
     * @param shoppingCart 购物车
     * @return 优惠额，注意不是优惠后的价格，是优惠了多少钱
     */
    double processCardDiscount(double totalCost, double totalCostAfterDiscount,
                               Customer customer, ShoppingCart shoppingCart);
}
