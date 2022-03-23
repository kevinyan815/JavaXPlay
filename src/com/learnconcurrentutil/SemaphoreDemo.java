package com.learnconcurrentutil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + ": getting db connection");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + ": save data");
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + ": release db connection");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}