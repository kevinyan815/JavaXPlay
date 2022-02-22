package com.learnconcurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        Task task = new Task();
        for(int i = 1; i <= 3; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    task.execute();
                }
            }, "Thread-" + i);

            t.start();
        }
    }

    static class Task {

        private ReentrantLock lock = new ReentrantLock();

        public void execute() {
            try {
                lock.lock();
                for (int i = 0; i < 3; i++) {
                    System.out.println(lock.toString());

                    // 故意再获取一次锁，查询当前线程 hold 住此锁的次数
                    // 可重入锁会对锁持有数就行累加
                    getHoldCount();

                    // 查询正等待获取此锁的线程数
                    System.out.println("\t queuedLength: " + lock.getQueueLength());

                    // 是否为公平锁
                    System.out.println("\t isFair: " + lock.isFair());

                    // 是否被锁住
                    System.out.println("\t isLocked: " + lock.isLocked());

                    // 是否被当前线程持有锁
                    System.out.println("\t isHeldByCurrentThread: " + lock.isHeldByCurrentThread());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        private void getHoldCount() {
            try {
                lock.lock();
                // 查询当前线程 hold 住此锁的次数
                System.out.println("\t holdCount: " + lock.getHoldCount());
            } finally {
                lock.unlock();
            }

        }

    }

}