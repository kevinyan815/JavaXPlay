package com.threadlocal;

public class UseThreadLocalAppMain {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread workingThread = new Thread(() -> {
                System.out.println("开始处理......");
                PerformanceTracker.reset();
                // TODO 下面有三个处理步骤，但是都是用一个线程执行。
                // TODO 使用ThreadLocal可以不用传递统计数据
                InputHandler inputHandler = new InputHandler();
                String content = inputHandler.getInput();

                DBQuery query = new DBQuery();
                query.query();

                ContentProcess contentProcess = new ContentProcess();
                contentProcess.process(content);

                PerformanceTracker.finish();
                System.out.println("处理结束");
            }, "worker-" + i);
            workingThread.start();
        }
    }
}
