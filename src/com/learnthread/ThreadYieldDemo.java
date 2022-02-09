package com.learnthread;

public class ThreadYieldDemo {

    public static void main(String[] args) {
        MyThread t = new MyThread();
        new Thread(t, "线程A").start();
        new Thread(t, "线程B").start();
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "运行，i = " + i);
                if (i == 2) {
                    System.out.print("线程礼让：");
                    Thread.yield();
                }
            }
        }
    }
}