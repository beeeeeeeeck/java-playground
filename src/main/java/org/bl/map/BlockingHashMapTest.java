package org.bl.map;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class BlockingHashMapTest {
    public static void main(String[] args) {
        BlockingMap<String, String> map = new BlockingHashMap<>(20);

        ExecutorService producerService = Executors.newFixedThreadPool(20);
        IntStream.rangeClosed(1, 1000).forEach(l -> {
            producerService.execute(() -> {
                try {
                    Thread.sleep((long) (1000 * ThreadLocalRandom.current().nextDouble(0.1, 3)));
                    map.put("foo" + l, "bar");
                    System.out.println(l + " put");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            if (l == 1000) {
                producerService.shutdown();
            }
        });

        System.out.println("start");

        ExecutorService consumerService = Executors.newFixedThreadPool(10);
        IntStream.rangeClosed(1, 1000).forEach(l -> {
            consumerService.execute(() -> {
                try {
                    System.out.println(l + " take - " + map.take("foo" + l));
                    // Thread.sleep((long) (1000 * ThreadLocalRandom.current().nextDouble(0.5, 1.2)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            if (l == 1000) {
                consumerService.shutdown();
            }
        });
    }
}
