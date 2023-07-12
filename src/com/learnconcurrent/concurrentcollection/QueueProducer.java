package com.learnconcurrent.concurrentcollection;

import java.util.*;
import java.util.concurrent.*;

public class QueueProducer implements Runnable {
    private BlockingQueue<Integer> queue;

    public QueueProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i ++) {
                Integer number = produce();
                queue.put(number);
                System.out.println("PRODUCER: put " + number);
            }
            queue.put(-1);       // indicates end of producing
            System.out.println("PRODUCER: STOPPED");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private Integer produce() {
        Random random = new Random();

        Integer number = random.nextInt(1000);

        try {
            // fake producing time
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        return number;
    }
}
