package org.bl.retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Retry<T> {

    private final int times; // total attempts is times + 1
    private Duration delay;

    public Retry(int times) {
        this.times = times;
    }

    public Retry(int times, Duration delay) {
        this.times = times;
        this.delay = delay;
    }

    private void ensureRetryParams() {
        if (times <= 0) {
            throw new IllegalArgumentException("Must retry at least one time");
        }
    }

    public T retry(RetryCallable<T> callable) throws Exception {
        return retry(new Task<>(callable), new ArrayList<>());
    }

    public T retry(RetryCallable<T> callable, Class<? extends Exception> retryOnException) throws Exception {
        return retry(new Task<>(callable), Collections.singletonList(retryOnException));
    }


    public T retry(RetryCallable<T> callable, List<Class<? extends Exception>> retryOnExceptions) throws Exception {
        return retry(new Task<>(callable), retryOnExceptions);
    }

    public T retry(RetryFunction<Integer, T> function) throws Exception {
        return retry(new Task<>(function), new ArrayList<>());
    }

    public T retry(RetryFunction<Integer, T> function, Class<? extends Exception> retryOnException) throws Exception {
        return retry(new Task<>(function), Collections.singletonList(retryOnException));
    }

    public T retry(RetryFunction<Integer, T> function, List<Class<? extends Exception>> retryOnExceptions) throws Exception {
        return retry(new Task<>(function), retryOnExceptions);
    }

    private T retry(Task<T> task, List<Class<? extends Exception>> retryOnExceptions) throws Exception {
        ensureRetryParams();

        final int maxTimes = times;
        int retryTime = -1;
        Exception throwable;
        do {
            try {
                if (task.isCallable()) {
                    return task.call();
                } else if (task.isFunction()) {
                    return task.apply(retryTime + 1);
                }

                throwable = new IllegalStateException("Nothing to retry");
            } catch (Exception ex) {
                throwable = ex;
                if (shouldNotRetry(ex, retryOnExceptions)) {
                    break;
                }

                retryTime++;
                // log.info(Strings.format("Retry {} of {} times as failure occurred", retryTime, maxTimes), ex);
                if (retryTime == 0) {
                    System.out.println("First attempt was failed");
                } else {
                    System.out.printf("Retry (%s/%s) was failed%n", retryTime, maxTimes);
                }
            }

            if (maxTimes - retryTime > 0 && delay != null) {
                try {
                    Thread.sleep(delay.toMillis());
                } catch (InterruptedException wakeAndAbort) {
                    // log.info(Strings.format("Retry {} of {} times has failure on delay", retryTime, maxTimes), wakeAndAbort);
                    System.out.printf("Retry (%s/%s) has failure on delay%n", retryTime, maxTimes);
                    break;
                }
            }
        } while (maxTimes - retryTime > 0);

        throw throwable;
    }

    private boolean shouldNotRetry(Exception ex, List<Class<? extends Exception>> retryOnExceptions) {
        if (retryOnExceptions == null || retryOnExceptions.isEmpty()) {
            return false; // retry if not any specified exceptions
        }

        return retryOnExceptions.stream().noneMatch(retryIfThisException -> ex.getClass() == retryIfThisException);
    }

    @FunctionalInterface
    public interface RetryCallable<V> {
        V call() throws Exception;
    }

    @FunctionalInterface
    public interface RetryFunction<T, R> {
        R apply(T t) throws Exception;
    }

    private enum TaskType {
        CALLABLE,
        FUNCTION
    }

    private static class Task<R> implements RetryCallable<R>, RetryFunction<Integer, R> {
        private RetryCallable<R> callable;
        private RetryFunction<Integer, R> function;
        private final TaskType type;

        Task(RetryCallable<R> callable) {
            this.callable = callable;
            this.type = TaskType.CALLABLE;
        }

        Task(RetryFunction<Integer, R> function) {
            this.function = function;
            this.type = TaskType.FUNCTION;
        }

        @Override
        public R call() throws Exception {
            if (callable == null) {
                return null;
            }

            return callable.call();
        }

        @Override
        public R apply(Integer i) throws Exception {
            if (function == null) {
                return null;
            }

            return function.apply(i);
        }

        boolean isCallable() {
            return TaskType.CALLABLE == this.type;
        }

        boolean isFunction() {
            return TaskType.FUNCTION == this.type;
        }
    }

}