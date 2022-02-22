package com.learnconcurrent.lock;

import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    static UnboundedCache<Integer, Integer> cache = new UnboundedCache<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    /** 线程任务每次向缓存中写入 3 个随机值，key 固定 */
                    Random random = new Random();
                    for (int i = 0; i < 3; i++) {
                        cache.put(i, random.nextInt(100));
                    }
                }
            }).start();

            new Thread(() -> cache.get(0)).start();
        }
    }

    /**
     * 简单的无界缓存实现
     * <p>
     * 使用 WeakHashMap 存储键值对。WeakHashMap 中存储的对象是弱引用，JVM GC 时会自动清除没有被引用的弱引用对象。
     */
    static class UnboundedCache<K, V> {

        private final Map<K, V> cacheMap = new WeakHashMap<>();

        private final ReadWriteLock cacheLock = new ReentrantReadWriteLock();

        public V get(K key) {
            V value;
            try {
                cacheLock.readLock().lock();
                value = cacheMap.get(key);
                String log = String.format("%s 读数据 key:%s, value:%s", Thread.currentThread().getName(), key, value);
                System.out.println(log);
            } finally {
                cacheLock.readLock().unlock();
            }
            return value;
        }

        public V put(K key, V value) {
            try {
                cacheLock.writeLock().lock();
                cacheMap.put(key, value);
                String log = String.format("%s 写入数据 key:%s, value:%s", Thread.currentThread().getName(), key, value);
                System.out.println(log);
            } finally {
                cacheLock.writeLock().unlock();
            }
            return value;
        }
    }
}