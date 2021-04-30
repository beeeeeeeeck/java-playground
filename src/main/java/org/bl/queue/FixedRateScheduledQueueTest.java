package org.bl.queue;

import cn.hutool.core.date.DateTime;

import java.time.Duration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

public class FixedRateScheduledQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Long> queue = new FixedRateScheduledQueue<>(Duration.ofSeconds(5), list -> System.out.println(DateTime.now().toTimeStr() + " : " + list.toString()));

        LongStream.rangeClosed(1, 20).forEach(l -> {
            try {
                queue.put(l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (l % 10 == 9) {
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (1000 * ThreadLocalRandom.current().nextDouble(0.5, 1.2)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
