package com.learnconcurrent.concurrentlist;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayListDemo {

    static class ReadTask implements Runnable {

        List<String> list;

        ReadTask(List<String> list) {
            this.list = list;
        }

        public void run() {
            for (String str : list) {
                System.out.println(str);
            }
        }
    }

    static class WriteTask implements Runnable {

        List<String> list;
        int index;

        WriteTask(List<String> list, int index) {
            this.list = list;
            this.index = index;
        }

        public void run() {
            list.remove(index);
            list.add(index, "write_" + index);
        }
    }

    public static void main(String[] args) {
        final int THREAD_NUM = 10;
        // ArrayList 在并发迭代访问时会抛出 ConcurrentModificationException 异常
        // List<String> list = new ArrayList<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < THREAD_NUM; i++) {
            list.add("main_" + i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            executorService.execute(new ReadTask(list));
            executorService.execute(new WriteTask(list, i));
        }
        executorService.shutdown();
    }
}