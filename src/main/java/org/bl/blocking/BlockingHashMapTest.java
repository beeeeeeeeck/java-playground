package org.bl.blocking;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

public class BlockingHashMapTest {
    public static void main(String[] args) {
        BlockingMap<String, String> map = new BlockingHashMap<>(5);
        new Thread(() -> LongStream.rangeClosed(1, 20).forEach(l -> {
            System.out.println(l + " put");
            try {
                map.put("foo" + l, "bar");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
        System.out.println("start");
        LongStream.rangeClosed(1, 20).forEach(l -> {
            System.out.println(l + " take");
            try {
                System.out.println(map.take("foo" + l));
                Thread.sleep((long) (1000 * ThreadLocalRandom.current().nextDouble(0.5, 1.2)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
