package com.learnthread;

public class RunnableDemo {

    public static void main(String[] args) {
        // 实例化线程对象
        Runnable myRunnable = new Runnable() {
            private int ticket = 5;
            @Override
            public void run() {
                while (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖出了第 " + ticket + " 张票");
                    ticket--;
                }
            }
        };


        Thread threadA = new Thread(myRunnable, "Runnable 线程-A");
        Thread threadB = new Thread(new MyThread(), "Runnable 线程-B");
        // 启动线程
        threadA.start();
        threadB.start();
    }

    static class MyThread implements Runnable {

        private int ticket = 5;

        @Override
        public void run() {
            while (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出了第 " + ticket + " 张票");
                ticket--;
            }
        }

    }

}