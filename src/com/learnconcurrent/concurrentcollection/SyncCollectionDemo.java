package com.learnconcurrent.concurrentcollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用同步容器解决在并发情况下写 ArrayList 造成数据不一致错误
 * （参考 CollectionConcurrentErrorDemo）
 */
public class SyncCollectionDemo {

    private List<Integer> listSync;

    public SyncCollectionDemo() {
        // 这里包装一个空的 ArrayList 生成同步 List 容器
        this.listSync = Collections.synchronizedList(new ArrayList<>());
    }

    public void addSync(int temp){
        listSync.add(temp);
    }

    public List<Integer> getListSync() {
        return this.listSync;
    }

    public static void main(String[] args) throws InterruptedException {
        SyncCollectionDemo demo = new SyncCollectionDemo();
        // 创建一个核心线程数、最大线程数都是10 的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 给线程池提交10个任务
        for (int i = 0; i < 10; i++) {
            // 每个线程执行1000次添加操作
            executorService.submit(()->{
                for (int j = 0; j < 1000; j++) {
                    demo.addSync(j);
                }});
        }
        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        // 多线程并发写同步容器不会造成数据不一致
        System.out.println(demo.getListSync().size());
    }
}
