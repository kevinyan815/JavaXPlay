package com.learnconcurrentutil;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 1. 创建一个内部计数器初始为 10 的 CountDownLatch 对象
        CountDownLatch latch = new CountDownLatch(10);
        System.out.println(Thread.currentThread().getName() + "：准备加载");
        // 这里我们创建10个线程，来执行子任务
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                // 这里我们给点延时，模拟处理和网络延时
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "：加载100%");
                // 2. 这里的countDown就是用来对内部倒计数器进行减1
                // 该方法不会阻塞调用线程
                latch.countDown();
            }).start();
        }
        // 3. 这里阻塞等待状态的完成，即10变为0;
        latch.await();
        System.out.println(Thread.currentThread().getName() + "：所有任务加载完成，开始游戏");
    }
}
