package org.bl.concurrent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IsConcurrentHashMapPerfect {

    public static final int ITEM_COUNT = 1000;
    public static final int THREAD_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        System.out.println(String.format("Map initial size: %s", concurrentHashMap.size()));

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        // This is proving ConcurrentHashMap is NOT perfect for concurrent programming sometimes, Its methods is NOT atomic ensured
        // forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
        //     int gapCount = ITEM_COUNT - concurrentHashMap.size();
        //     System.out.println(String.format("Gap size found: %s", gapCount));
        //     concurrentHashMap.putAll(getData(gapCount));
        // }));
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            synchronized (concurrentHashMap) {
                int gapCount = ITEM_COUNT - concurrentHashMap.size();
                System.out.println(String.format("Gap size found: %s", gapCount));
                concurrentHashMap.putAll(getData(gapCount));
            }
        }));

        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(String.format("Total size: %s", concurrentHashMap.size()));
    }

    private static ConcurrentHashMap<String, Long> getData(int size) {
        ConcurrentHashMap<String, Long> dataMap = new ConcurrentHashMap<>(size);
        for (int i = 0; i < size; i++) {
            dataMap.put(UUID.randomUUID().toString(), i * 1031L);
        }
        return dataMap;
    }
}
