package com.learnconcurrent.concurrentcollection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 在并发情况下写 ArrayList 造成数据不一致错误
 */
public class CollectionConcurrentErrorDemo {

    private List<Integer> listNoSync;

    public CollectionConcurrentErrorDemo() {
        this.listNoSync = new ArrayList<>();
    }

    public void addNoSync(int temp){
        listNoSync.add(temp);
    }

    public List<Integer> getListNoSync() {
        return this.listNoSync;
    }

    public static void main(String[] args) throws InterruptedException {
        CollectionConcurrentErrorDemo demo = new CollectionConcurrentErrorDemo();
        // 创建一个核心线程数、最大线程数都是10 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 给线程池提交10个任务
        for (int i = 0; i < 10; i++) {
            // 每个线程执行1000次添加操作
            executorService.submit(()->{
                        for (int j = 0; j < 1000; j++) {
                            demo.addNoSync(j);
                        }});
        }
        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        // 任务执行完后, 打印一下 ArrayList 的长度, 大概率会小于 10000
        // Java 11 里能执行，Java 8 里会直接数组越界异常
        System.out.println(demo.getListNoSync().size());
    }
}
