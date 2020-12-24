package org.bl.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author beckl
 */
public class TestForkJoin {
    static class Fibonacci extends RecursiveTask<Integer> {
        private final int n;

        public Fibonacci(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            // assuming n >= 0
            if (n <= 1) {
                return n;
            } else {
                // f(n-1)
                Fibonacci f1 = new Fibonacci(n - 1);
                f1.fork();
                // f(n-2)
                Fibonacci f2 = new Fibonacci(n - 2);
                f2.fork();
                // f(n) = f(n-1) + f(n-2)
                return f1.join() + f2.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int target = 40;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU core number: " + Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(target);
        Future<Integer> future = forkJoinPool.submit(fibonacci);
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println(String.format("Time elapsed %d millis", end - start));

        start = System.currentTimeMillis();
        int result = computeFibonacci(target);
        end = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(String.format("Time elapsed %d millis", end - start));
    }

    private static int computeFibonacci(int n) {
        // assuming n >= 0
        if (n <= 1) {
            return n;
        } else {
            int first = 1;
            int second = 1;
            int third = 0;
            for (int i = 3; i <= n; i++) {
                third = first + second;
                first = second;
                second = third;
            }
            return third;
        }
    }
}
