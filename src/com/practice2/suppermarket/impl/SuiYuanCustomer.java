package com.practice2.suppermarket.impl;

import com.practice2.suppermarket.interfaces.Category;
import com.practice2.suppermarket.interfaces.Merchandise;

public class SuiYuanCustomer extends AbsCustomer {

    private static final double MUST_BUY_CHANCE = 0.8;
    private static final double GUANG_BUY_CHANCE = 0.1;

    public SuiYuanCustomer(String customerId, Category mustBuy) {
        super(customerId, mustBuy, DEFAULT_GUANG_COUNT);
    }

    @Override
    public int buyMerchandise(Merchandise merchandise) {
        // 买一个商品的概率
        double chanceToBuy = merchandise.getCategory() == getShouldBuy() ?
                MUST_BUY_CHANCE : GUANG_BUY_CHANCE;

        // 缘分不到，那就返回0
        if (chanceToBuy < Math.random()) {
            return 0;
        } else {
            // 否则就随机买1个或者多个
            return 1 + (int) (Math.random() * 3);
        }
    }
}
