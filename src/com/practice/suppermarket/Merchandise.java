package com.practice.suppermarket;

public class Merchandise {
    public String name;
    public String id;
    public int count = 999;
    public double soldPrice;
    public double purchasePrice;

    // >> TODO 静态变量使用 static 修饰符
    public static double DISCOUNT_FOR_VIP = 0.95;

    public static double getVIPDiscount() {
        // >> TODO 静态方法可以访问静态变量，包括自己类的静态变量和在访问控制符允许的别的类的静态变量
        return DISCOUNT_FOR_VIP;
    }

    // >> TODO 除了没有this，静态方法的定义和成员方法一样，也有方法名，返回值和参数
    // >> TODO 静态方法没有this自引用，它不属于某个实例，调用的时候也无需引用，直接用类名调用，所以它也不能直接访问成员变量
    // >> TODO 当然在静态方法里面，也可以自己创建对象，或者通过参数，获得对象的引用，进而调用方法和访问成员变量
    // >> TODO 静态方法只是没有this自引用的方法而已。
    public static double getDiscountOnDiscount(LittleSupperMarket littleSupperMarket) {
        return  littleSupperMarket.activityDiscount * DISCOUNT_FOR_VIP;
    }

    public Merchandise(String name, String id, int count,
                       double soldPrice, double purchasePrice) {
        this.name = name;
        this.id = id;
        this.count = count;
        this.soldPrice = soldPrice;
        this.purchasePrice = purchasePrice;
    }

    // >> TODO 在构造方法里才能调用重载的构造方法。语法为this(实参列表)
    // >> TODO 构造方法不能自己调用自己，这会是一个死循环
    // >> TODO 在调用重载的构造方法时，不可以使用成员变量。因为用语意上讲，这个对象还没有被初始化完成，处于中间状态。
    // >> TODO 在构造方法里才能调用重载的构造方法时，必须是方法的第一行。后面可以继续有代码
    public Merchandise(String name, String id, int count, double soldPrice) {
        this(name, id, count, soldPrice, soldPrice * 0.8);
    }

    public Merchandise() {
        this("无名", "000", 0, 1, 1.1);
    }
    public void describe() {
        System.out.println("商品的名字叫做" + name + ", id是" + id + "。商品售价是" + soldPrice
                + "。商品的进价是" + purchasePrice + "。商品的库存是" + count +
                "。销售一个的毛利率是" + (soldPrice - purchasePrice) + "。折扣为" + DISCOUNT_FOR_VIP);
    }

    public double calculateProfit() {
        double profit = soldPrice - purchasePrice;
        return profit;
    }

    public double buy() {
        return buy(1);
    }

    public double buy(int count) {
        return buy(count, false);
    }

    public double buy(int count, boolean isVip) {
        if (this.count < count) {
            return -1;
        }
        this.count -= count;
        double totalCost = count * soldPrice;
        if (isVip) {
            // >> TODO 静态方法的访问和静态变量一样，可以带上类名，当前类可以省略类名
            return totalCost * getVIPDiscount();
        } else {
            return totalCost;
        }
    }


    public boolean hasEnoughCountFor(int count) {
        return this.count >= count;
    }
}
