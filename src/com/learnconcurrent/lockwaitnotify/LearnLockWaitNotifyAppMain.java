package com.learnconcurrent.lockwaitnotify;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LearnLockWaitNotifyAppMain {

    public static void main(String[] args) {
        Lock locker = new ReentrantLock();
        Condition condition = locker.newCondition();
        int workingSec = 2;
        int threadCount = 3;
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                System.out.println(getName() + ":线程开始工作......");
                try {
                    locker.lock();
                    sleepSec(workingSec);
                    System.out.println(getName() + ":进入等待");
                    // >> TODO await 方法必须在当前线程获取锁之后才能调用
                    // >> TODO await 方法调用后自动失去锁
                    condition.await();
                    System.out.println(getName() + ":线程继续......");
                    sleepSec(workingSec);
                    System.out.println(getName() + ":结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    locker.unlock();
                }
            }, "工作线程" + i).start();
        }

        // >> TODO await / signal 一样也有 lost notification 的问题
        System.out.println("------------- 主线程作为唤醒线程，先sleep -------------");
        sleepSec(workingSec + 1);
        System.out.println("------------- 唤醒线程sleep结束 -------------");
        try {
            locker.lock();
            // >> TODO signal / signalAll 方法必须在当前线程获取锁之后才能调用
            System.out.println("------------- 开始唤醒所有 -------------");
            condition.signalAll();

        } finally {
            locker.unlock();
        }
    }

    private static void sleepSec(int sec) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(sec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getName() {
        return Thread.currentThread().getName();
    }
}
