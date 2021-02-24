package com.practice2.suppermarket;

import com.practice2.suppermarket.impl.SimpleShopMan;
import com.practice2.suppermarket.interfaces.Customer;
import com.practice2.suppermarket.interfaces.ShopMan;
import com.practice2.suppermarket.interfaces.SuperMarket;
import static com.practice2.suppermarket.util.ShoppingUtil.*;

public class ShoppingAppMain {
    public static void main(String[] args) {
        SuperMarket superMarket = createSuperMarket();

        ShopMan shopMan = new SimpleShopMan(superMarket);

        boolean open = true;
        while (open) {
            new ShoppingTask(shopMan).executeTask();
            output("是否继续营业？(Yes)");
            open = ! input().next().trim().equalsIgnoreCase("no");
        }

        superMarket.dailyReport();
    }
}


class ShoppingTask {

    private ShopMan shopMan;

    public ShoppingTask(ShopMan shopman) {
        this.shopMan = shopman;
    }

    public void executeTask() {
        Customer customer = createCustomer(true);

        shopMan.serveCustomer(customer);

    }

}