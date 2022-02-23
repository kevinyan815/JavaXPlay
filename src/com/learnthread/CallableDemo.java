package com.learnthread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<Integer> {
    @Override
    public Integer call() {
        int i = 0;
        for (i = 0; i < 20; i++) {
            if (i == 5) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

    public static void main(String[] args) {
        CallableDemo tt = new CallableDemo();
        FutureTask<Integer> ft = new FutureTask<>(tt);
        Thread t = new Thread(ft);
        t.start();
        try {
            System.out.println(Thread.currentThread().getName() + " " + ft.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}