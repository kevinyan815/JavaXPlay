package com.learnthread;

public class ThreadDaemonDemo {

    public static void main(String[] args) {
        Thread t = new Thread(new MyThread(), "线程");
        t.setDaemon(true); // 此线程在后台运行
        System.out.println("线程 t 是否是守护进程：" + t.isDaemon());
        t.start(); // 启动线程
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "在运行。");
            }
        }
    }
}