package org.bl.concurrent;

import org.apache.commons.lang3.time.StopWatch;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class DataOps {

    public static final int ITEM_COUNT = 1000;
    public static final int THREAD_COUNT = 5;

    static Map<String, Long> test1() throws InterruptedException {
        ConcurrentHashMap<String, Long> data = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, ITEM_COUNT).parallel().forEach(i -> {
            // String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            String key = "item" + i;
            synchronized (data) {
                if (data.containsKey(key)) {
                    data.put(key, data.get(key) + 1);
                } else {
                    data.put(key, 1L);
                }
            }
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);
        return data;
    }

    static Map<String, Long> test2() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> data = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, ITEM_COUNT).parallel().forEach(i -> {
            // String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            String key = "item" + i;
            data.computeIfAbsent(key, k -> new LongAdder()).increment();
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.MINUTES);
        return data.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
    }
}

public class ConcurrentHashMapPerf {
    public static final int ITEM_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Map<String, Long> testData1 = DataOps.test1();
        stopWatch.stop();
        System.out.println(testData1);
        System.out.println("Test 1 Time Elapsed: " + stopWatch.getTime());
        System.out.println(testData1.size());
        System.out.println(testData1.size() == ITEM_COUNT);
        System.out.println(testData1.values().stream().mapToLong(l -> l).reduce(0, Long::sum) == ITEM_COUNT);
        stopWatch.reset();
        stopWatch.start();
        Map<String, Long> testData2 = DataOps.test2();
        stopWatch.stop();
        System.out.println(testData2);
        System.out.println("Test 2 Time Elapsed: " + stopWatch.getTime());
        System.out.println(testData2.size());
        System.out.println(testData2.size() == ITEM_COUNT);
        System.out.println(testData2.values().stream().mapToLong(l -> l).reduce(0, Long::sum) == ITEM_COUNT);
    }
}
