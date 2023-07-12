package com.learnconcurrent.concurrentcollection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingQueueAppMain {

    public static void main(String[] args) throws InterruptedException {
        // TODO 默认是 Integer.MAX_VALUE 这么大
        // TODO 元素不允许为null
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(128);

        // TODO 查看队列头上的元素，但是不出队列
        linkedBlockingQueue.peek();

        // TODO 将元素放入队列，返回是否放入成功。一般在限制队列大小的情况下才会失败，毕竟到达Integer.MAX_VALUE程序可能就因为没有内存挂了
        boolean added = linkedBlockingQueue.offer("");
        // TODO 这个方法也有超时版本
        boolean addedInTime = linkedBlockingQueue.offer("", 1, TimeUnit.SECONDS);

        // TODO 队列里取出数据，没有就返回空，这个方法也有超时重载版本
        linkedBlockingQueue.poll();

        try {
            // TODO 将元素加入队列，如果队列满了，就等着
            linkedBlockingQueue.put("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO 一定要拿到一个，否则就无限等待
        try {
            linkedBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO put和take可以方便的实现生产者消费者模式
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        Thread producer = new Thread(new QueueProducer(queue));
        Thread consumer = new Thread(new QueueConsumer(queue));
        producer.start();
        consumer.start();

    }
}
