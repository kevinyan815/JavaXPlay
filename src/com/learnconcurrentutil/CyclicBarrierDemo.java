package com.learnconcurrentutil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {

        // 1. 创建一个循环栅栏，给定等待线程数10和栅栏动作
        CyclicBarrier barrier = new CyclicBarrier(10, () -> {
            // 栅栏动作，等到所有线程都await，就会触发
            System.out.println("=== 人齐了，开始团吧");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("=== 准备第一波团战 ===");
        // 2. 创建10个线程，模拟10个玩家
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 玩家到场
                    System.out.println(Thread.currentThread().getName() + "=>第一波团，我准备好了");
                    // 等待其他人，等人齐就可以团了（人齐了会执行栅栏动作，此时这边也会恢复执行）
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // 3. 查询当前等待都线程数量，如果不为0，则主线程继续等待
        while (barrier.getNumberWaiting() != 0) {
            Thread.sleep(1000);
        }
        System.out.println("=== 第一波团战结束 ===");

        // 4. 此时还可以进行第二波第三波团战。。。（循环栅栏可循环触发，倒计数器锁只能触发一次）
        // barrier.reset();
    }
}
