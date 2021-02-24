package com.practice.suppermarket;

public class LittleSupperMarket {
    public String getSuperMarketName() {
        return superMarketName;
    }

    public void setSuperMarketName(String superMarketName) {
        this.superMarketName = superMarketName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getParkingCount() {
        return parkingCount;
    }

    public void setParkingCount(int parkingCount) {
        this.parkingCount = parkingCount;
    }

    public double getIncomingSum() {
        return incomingSum;
    }

    public void setIncomingSum(double incomingSum) {
        this.incomingSum = incomingSum;
    }

    public Merchandise[] getMerchandises() {
        return merchandises;
    }

    public void setMerchandises(Merchandise[] merchandises) {
        this.merchandises = merchandises;
    }

    public int[] getMerchandiseSold() {
        return merchandiseSold;
    }

    public void setMerchandiseSold(int[] merchandiseSold) {
        this.merchandiseSold = merchandiseSold;
    }

    public String superMarketName;
    public String address;
    public int parkingCount;// 车位数
    public double incomingSum = 10000000; // 总收入
    public Merchandise[] merchandises;
    public int[] merchandiseSold;
    public double activityDiscount;

    /**
     * 构造方法初始化超市
     * @param superMarketName
     * @param address
     * @param parkingCount
     * @param merchandiseCount
     * @param count
     */
    public LittleSupperMarket(String superMarketName, String address, int parkingCount,
                             int merchandiseCount, int count) {
        this.superMarketName =superMarketName;
        this.address = address;
        this.parkingCount = parkingCount;

        merchandises = new Merchandise[merchandiseCount];
        for (int i = 0; i < merchandises.length; i++) {
            double purchasePrice = Math.random() * 200;
            // 创建并给商品的属性赋值
            Merchandise m = new Merchandise(
                    "商品" + i,
                    "ID" + i,
                    count,
                    purchasePrice * (1 + Math.random()),
                    purchasePrice

            );

            merchandises[i] = m;
        }
        merchandiseSold = new int[merchandises.length];
        activityDiscount = 0.8;
    }

    /**
     * 利润最高的商品
     *
     * @return
     */
    public Merchandise getBiggestProfitMerchandise() {
        Merchandise curr = null;
        for (int i = 0; i < merchandises.length; i++) {
            Merchandise m = merchandises[i];
            if (curr == null || curr.calculateProfit() < m.calculateProfit()) {
                curr = m;
            }
        }
        return curr;
    }

    /**
     * 获取索引对应的商品
     *
     * @param merchandiseIndex
     * @return
     */
    public Merchandise getMerchandiseOf(int merchandiseIndex) {
        if (merchandiseIndex < 0 || merchandiseIndex >= merchandises.length) {
            return null;
        }
        return merchandises[merchandiseIndex];
    }

    /**
     * 进账
     *
     * @param toBeAdded
     */
    public void addIncomingSum(double toBeAdded) {
        incomingSum += toBeAdded;
    }

    /**
     * 花钱
     *
     * @param toBeSpent
     */
    public void spendMoney(double toBeSpent) {
        incomingSum -= toBeSpent;
    }
}
