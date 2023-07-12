package com.learnconcurrent.concurrentcollection;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class QueueConsumer implements Runnable {
    private BlockingQueue<Integer> queue;

    public QueueConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer number = queue.take();
                if (number == -1) {
                    System.out.println("Consumer: STOPPED");
                    break;
                }
                consume(number);
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void consume(Integer number) {
        // fake consuming time
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Consumer: processed " + number);
    }
}
