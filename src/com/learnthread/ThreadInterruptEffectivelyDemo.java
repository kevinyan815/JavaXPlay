package com.learnthread;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptEffectivelyDemo {
    public static void main(String[] args) throws Exception {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "线程-A");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(50);
        thread.interrupt();
    }

    private static class MyTask implements Runnable {

        private volatile long count = 0L;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 线程启动");
            // 通过 Thread.interrupted 和 interrupt 配合来控制线程终止
            while (!Thread.interrupted()) {
                System.out.println(count++);
            }
            System.out.println(Thread.currentThread().getName() + " 线程终止");
        }
    }
}
