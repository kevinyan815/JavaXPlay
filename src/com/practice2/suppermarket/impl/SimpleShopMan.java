package com.practice2.suppermarket.impl;

import com.practice2.suppermarket.interfaces.*;
import static com.practice2.suppermarket.util.ShoppingUtil.output;

public class SimpleShopMan implements ShopMan {
    private SuperMarket superMarket;

    public SimpleShopMan(SuperMarket superMarket) {
        this.superMarket =superMarket;
    }

    private static final int MAX_BUY_DEFAULT = 9;

    @Override
    public void serveCustomer(Customer customer) {
        int maxTypeToBuy = MAX_BUY_DEFAULT;
        if (customer instanceof AbsCustomer) {
            maxTypeToBuy = ((AbsCustomer) customer).getGuangCount();
        }
        ShoppingCart shoppingCart = new ShoppingCart(maxTypeToBuy);
        customer.startShopping();

        while (!customer.wantToCheckout() && shoppingCart.canHold()) {
            Category category = customer.chooseCategory();
            // 简单的导购员，顾客说不想买就算了，不做推荐
            if (category == null) {
                continue;
            }
            Merchandise[] toChoose = superMarket.getRandomMerchandiseOfCategory(category);
            // 简单的导购员，一个个推荐，不说从价格高到底推荐之类的小技巧。
            for (Merchandise m : toChoose) {
                if (m == null) {
                    continue;
                }
                int buyCount = customer.buyMerchandise(m);
                if (buyCount > 0) {
                    // 一个类别只买一个商品
                    shoppingCart.add(m, buyCount);
                    break;
                }
            }
        }

        double originCost = shoppingCart.calculateOriginCost();
        double finalCost = originCost;

        double savedMoney = originCost;
        if (customer instanceof HasCard) {
            Card card = ((HasCard) customer).getCard();
            savedMoney = card.processCardDiscount(originCost, finalCost,customer, shoppingCart);
            finalCost -= savedMoney;
        }

        double moneyEarned = customer.payFor(shoppingCart, finalCost);

        superMarket.addEarnedMoney(moneyEarned);

        output("顾客" + customer.getCustomerId() + "购物清单如下：");
        output(shoppingCart.toString());
        output("优惠金额为：" + savedMoney);
        output("实付金额为：" + moneyEarned);
    }
}
