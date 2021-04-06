package com.learnthread;

public class DataHolder {
    private long number = 0;
    private static long numberStatic = 0;

    // 加在示例方法上，同一时间只能有一个线程能访问同一实例的这个方法
    public synchronized void change(long delta) {
        number += delta;
    }

    public static synchronized void changeStatic(long delta) {
        numberStatic += delta;
    }

    public void changeSyncBlock(long delta) {
        int abc = 99;
        synchronized (this) {
            // 还可以synchronized DataHolder.Class
            // 修饰代码块的时候可以根据锁住的参数类比给实例方法和静态类方法加锁
            number += delta;
        }
        int cde= 987;
    }

    public void print() {
        System.out.println("Number=" + number);
    }

    public static void printStatic() {
        System.out.println("Number=" + numberStatic);
    }
}
