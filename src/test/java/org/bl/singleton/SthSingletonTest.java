package org.bl.singleton;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author beckl
 */
public class SthSingletonTest {
    @Test
    public void test() {
        assertThrows(RuntimeException.class, SthSingleton.INSTANCE::doSth);
        int num = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        List<SthSingleton> list = Collections.synchronizedList(new ArrayList<>());
        IntStream.rangeClosed(1, num).forEach(i -> {
            Future<SthSingleton> result = executorService.submit(() -> SthSingleton.INSTANCE);
            try {
                list.add(result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        for (SthSingleton instance : list) {
            assertSame(instance, SthSingleton.INSTANCE);
        }
    }
}
