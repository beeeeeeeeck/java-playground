package org.bl.map;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BlockingHashMapTest {
    public static void main(String[] args) {
        BlockingMap<String, String> map = new BlockingHashMap<>(10);
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().sorted(Comparator.comparingInt(i -> -1 * i)).collect(Collectors.toList());
        Collections.shuffle(list);
        new Thread(() -> list.forEach(l -> {
            System.out.println(l + " put");
            try {
                map.put("foo" + l, "bar");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
        System.out.println("start");
        IntStream.rangeClosed(1, 10).forEach(l -> {
            try {
                System.out.println(l + " take - " + map.take("foo" + l));
                Thread.sleep((long) (1000 * ThreadLocalRandom.current().nextDouble(0.5, 1.2)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
