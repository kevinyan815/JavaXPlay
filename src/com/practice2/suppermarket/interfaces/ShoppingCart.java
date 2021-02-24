package com.practice2.suppermarket.interfaces;

import java.util.Date;

public class ShoppingCart {

    // 购物车里放的商品
    private Merchandise[] buy;
    private int[] count;
    // 购物车接下来要放商品的购物格（虚拟的，假设购物车最多放max个商品，每个商品占一个格子）
    private int curr;
    // 购物车最多放多少种商品
    private int max;

    public ShoppingCart(int maxToBuy) {
        buy = new Merchandise[maxToBuy];
        count = new int[maxToBuy];
        max = maxToBuy;
        curr =0;
    }

    public boolean canHold() {
        return curr < max;
    }

    public boolean add(Merchandise m, int countToBuy) {
        if (!canHold()) {
            return false;
        }
        buy[curr] = m;
        count[curr] = countToBuy;
        curr++;
        m.buy(countToBuy);
        return true;
    }

    /**
     * @return 按原价购买购物车里的商品的成本价
     */
    public double calculateOriginCost() {
        double ret = 0;
        int pos = -1;
        for (Merchandise m : buy) {
            pos++;
            if (m == null) {
                continue;
            }
            ret += m.getPurchasedPrice() * count[pos];
        }

        return ret;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===========================\n");
        sb.append("购买时间：").append(new Date()).append("\n");
        int pos = -1;
        for (Merchandise m : buy) {
            pos++;
            if (m==null) {
                continue;
            }
            sb.append(m.getCategory().name()).append("\t").append(m.getName()).append("\t")
                    .append(count[pos]).append("\t").append(m.getPurchasedPrice() * count[pos]).append("\n");
        }

        sb.append("应付总额为：").append(calculateOriginCost()).append("\n");
        sb.append("===========================\n");
        return sb.toString();
    }
}
