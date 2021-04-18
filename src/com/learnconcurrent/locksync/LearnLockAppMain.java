package com.learnconcurrent.locksync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LearnLockAppMain {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true); // 公平可重入锁

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new WorkWithLock(lock), "worker-" + i);
            thread.start();
        }
    }
}
