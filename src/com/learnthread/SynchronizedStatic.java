package com.learnthread;

public class SynchronizedStatic implements Runnable {

    private static final int MAX = 100000;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedStatic instance = new SynchronizedStatic();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        // 等待工作线程执行结束
        t1.join();
        t2.join();
        System.out.println(count);
    }

    @Override
    public void run() {
        for (int i = 0; i < MAX; i++) {
            increase();
        }
    }

    /**
     * synchronized 修饰静态方法
     */
    public synchronized static void increase() {
        count++;
    }

}
