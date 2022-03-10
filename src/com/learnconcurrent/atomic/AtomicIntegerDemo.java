package com.learnconcurrent.atomic;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建一个核心线程数、最大线程数都是5 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            executorService.submit((Runnable) () -> {
                System.out.println(Thread.currentThread().getName() + " count=" + count.get());
                count.incrementAndGet();
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Final Count is : " + count.get());
    }
}