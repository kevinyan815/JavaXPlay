package com.learnconcurrent.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {

    public ExecutorService executorService;

    public static void main(String[] args) {
        ThreadPoolExecutorDemo threadPoolDemo = new ThreadPoolExecutorDemo();
//        threadPoolDemo.executeDemo();
//        threadPoolDemo.submitCallableDemo();
//        threadPoolDemo.invokeAnyDemo();
        threadPoolDemo.invokeAllDemo();
        // 关闭线程池
        threadPoolDemo.executorService.shutdown();
    }

    public ThreadPoolExecutorDemo() {
        // 用 ThreadPoolExecutor 创建一个线程池
        executorService = new ThreadPoolExecutor(2, 10,
                1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5, true),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public void executeDemo() {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                String pattern = "yyyy-MM-dd HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(simpleDateFormat.format(new Date()) + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void submitCallableDemo() {
        Future future = executorService.submit(new Callable(){
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName() + " 线程开始执行任务。");
                return "Callable Result";
            }
        });

        try {
            System.out.println("执行结果是：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void invokeAnyDemo() {
        Set<Callable<String>> callables = new HashSet<>();
        callables.add(() -> "Task 1");
        callables.add(() -> "Task 2");
        callables.add(() -> "Task 3");
        try {
            String result = executorService.invokeAny(callables);
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void invokeAllDemo() {
        Set<Callable<String>> callables = new HashSet<>();

        callables.add(() -> "Task 1");
        callables.add(() -> "Task 2");
        callables.add(() -> "Task 3");

        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for(Future<String> future : futures){
                System.out.println("future.get = " + future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
