package com.threadlocal;

public class ThreadLocalExample {

    private  ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

//   下面是为 ThreadLocal 实例提供初始值的两种方式，一般是推荐使用 withInitial
//    private ThreadLocal threadLocal = new ThreadLocal<Integer>() {
//        @Override protected Integer initialValue() {
//            return (int) System.currentTimeMillis();
//        }
//    };
//
//    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> (int) System.currentTimeMillis());


    private void setAndPrintThreadLocal() {
        threadLocal.set((int) (Math.random() * 100D) );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( Thread.currentThread().getName() + ": " + threadLocal.get() );

        if ( threadLocal.get() % 2 == 0) {
            // 测试删除 ThreadLocal
            System.out.println(Thread.currentThread().getName() + ": 删除ThreadLocal");
            threadLocal.remove();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalExample tlExample = new ThreadLocalExample();
        Thread thread1 = new Thread(() -> tlExample.setAndPrintThreadLocal(), "线程1");
        Thread thread2 = new Thread(() -> tlExample.setAndPrintThreadLocal(), "线程2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}