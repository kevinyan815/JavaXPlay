package com.practice;

import com.practice.suppermarket.LittleSupperMarket;
import com.practice.suppermarket.Merchandise;

import java.util.SortedMap;

public class RunSuperMarketApp {
    public static void main(String[] args) {
        LittleSupperMarket littleSupperMarket = new LittleSupperMarket("有家超市", "浦东新区世纪大道666号", 100, 200, 200);

        System.out.println("下面请利润最高的商品自我介绍：");
        littleSupperMarket.getBiggestProfitMerchandise().describe();

        System.out.println("VIP的折上折折扣最终为：" + Merchandise.getDiscountOnDiscount(littleSupperMarket));
    }
}
