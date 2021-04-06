package com.learnthread;

public class MultiThreadChaos {
    public static void main(String[] args) {
        // TODO 同样的运算，安排在两个线程里做，结果就不一样了
        // 用加synchronized修饰的实例方法
//        DataHolder dataHolder = new DataHolder();
//        Thread increaseThread = new Thread(new ChangeData(2, Integer.MAX_VALUE / 50, dataHolder));
//        Thread decreaseThread = new Thread(new ChangeData(-2, Integer.MAX_VALUE / 50, dataHolder));
        // 用synchronized修饰的静态方法
        Thread increaseThread = new Thread(new ChangeData(2, Integer.MAX_VALUE / 50, null));
        Thread decreaseThread = new Thread(new ChangeData(-2, Integer.MAX_VALUE / 50, null));

        System.out.println("执行开始");
        increaseThread.start();
        decreaseThread.start();
    }


}
