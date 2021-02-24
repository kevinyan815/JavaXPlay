package com.practice2.suppermarket.impl;

import com.practice2.suppermarket.interfaces.*;

public class ThinkAndBuyCustomer extends AbsCustomer implements HasCard {

    private Card card = VipCard.Level0;
    public ThinkAndBuyCustomer(String customerId, Category shouldBuy) {
        super(customerId, shouldBuy, DEFAULT_GUANG_COUNT);
    }

    @Override
    public int buyMerchandise(Merchandise merchandise) {
        Category category = merchandise.getCategory();
        // 原定需要买的就买一个
        if (category == getShouldBuy()) {
            return 1;
        }

        double soldPrice = merchandise.getSoldPrice();

        double avgPrice = (category.getHigherPrice() + category.getLowerPrice()) / 2;
        if (soldPrice < avgPrice) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
