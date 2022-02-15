package com.learnthread;

public class DataRacingTest {

    public static void main(String[] args) throws InterruptedException {
        final DataRacingTest test = new DataRacingTest();
        // 创建两个线程，执行 add100000() 操作
        // 创建Thread 实例时的 Runnable 接口实现，这里直接使用了 Lambda
        Thread th1 = new Thread(()-> test.add100000());
        Thread th2 = new Thread(()-> test.add100000());
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        System.out.println(test.count);
    }

    private long count = 0;

    // 想复现 Data Racing，去掉这里的 synchronized
    private  void add100000() {
        int idx = 0;
        while(idx++ < 100000) {
            count += 1;
        }
    }
}
