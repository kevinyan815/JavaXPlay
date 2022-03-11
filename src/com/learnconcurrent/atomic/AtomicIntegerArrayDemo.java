package com.learnconcurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {

    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

//    static { // 还可以以下面这种提供一个 int[] 给构造方法的方式创建 AtomicIntegerArray
//        int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//        atomicIntegerArray = new AtomicIntegerArray(nums);
//    }

    public static void main(final String[] arguments) throws InterruptedException {
        System.out.println("Init Values: ");
        // 把原子数组初始化成：{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9}
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.set(i, i);
            System.out.print(atomicIntegerArray.get(i) + " ");
        }
        System.out.println();

        Thread t1 = new Thread(() -> {
            // 这个线程给 atomicIntegerArray 每个位置的值自增 1
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                int value = atomicIntegerArray.incrementAndGet(i);
                System.out.println(Thread.currentThread().getName() + ", index = " + i + ", value = " + value);
            }
        });
        Thread t2 = new Thread(() -> {
            // 这个线程执行 CAS 操作，遍历每个位置，如果位置上的值是 2 则设置成新值 3
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                boolean swapped = atomicIntegerArray.compareAndSet(i, 2, 3);
                if (swapped) {
                    System.out.println(Thread.currentThread().getName() + " swapped, index = " + i + ", value = 3");
                }
            }
        });
        t1.start();
        t2.start();
        // 等待 t1 t2 执行完毕
        t1.join();
        t2.join();
        System.out.println("Final Values: ");
        // 输出 atomicIntegerArray 各个位置上的最终值
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.print(atomicIntegerArray.get(i) + " ");
        }
        System.out.println();
    }
}