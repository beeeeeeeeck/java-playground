package org.bl.retry;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RetryTest {

    @Test
    public void test_ensureRetryParams() {
        Retry<?> retry1 = new Retry<>(0);
        assertThrows(IllegalArgumentException.class, () -> retry1.retry(() -> null));
        assertThrows(IllegalArgumentException.class, () -> retry1.retry((i) -> null));

        Retry<?> retry2 = new Retry<>(0, Duration.ofSeconds(1));
        assertThrows(IllegalArgumentException.class, () -> retry2.retry(() -> null));
        assertThrows(IllegalArgumentException.class, () -> retry2.retry((i) -> null));
    }

    @Test
    public void testRetry_whenAllIsSuccessful() throws Exception {
        Retry<Integer> retry = new Retry<>(1);
        Integer callableResult = retry.retry(() -> 0);
        assertNotNull(callableResult);
        assertEquals(Integer.valueOf(0), callableResult);
        Integer functionResult = retry.retry((i) -> i);
        assertNotNull(functionResult);
        assertEquals(Integer.valueOf(0), functionResult);
    }

    @Test
    public void testRetry_whenAllHasFailure() {
        Retry<Integer> retry = new Retry<>(2);
        String exceptionMessage = "Mock Exception";
        RuntimeException exception1 = assertThrows(RuntimeException.class, () -> retry.retry(() -> {
            throw new RuntimeException(exceptionMessage);
        }));
        assertEquals(exceptionMessage, exception1.getMessage());
        RuntimeException exception2 = assertThrows(RuntimeException.class, () -> retry.retry((i) -> {
            throw new RuntimeException(exceptionMessage);
        }));
        assertEquals(exceptionMessage, exception2.getMessage());
    }

    @Test
    public void testRetry_withDuration_whenAllHasFailure() {
        Retry<Integer> retry = new Retry<>(3, Duration.ofMillis(100));
        String exceptionMessage = "Mock Exception";
        RuntimeException exception1 = assertThrows(RuntimeException.class, () -> retry.retry(() -> {
            throw new RuntimeException(exceptionMessage);
        }));
        assertEquals(exceptionMessage, exception1.getMessage());
        RuntimeException exception2 = assertThrows(RuntimeException.class, () -> retry.retry((i) -> {
            throw new RuntimeException(exceptionMessage);
        }));
        assertEquals(exceptionMessage, exception2.getMessage());
    }

    @Test
    public void testRetry_whenThe3rdAttemptIsSuccessful() throws Exception {
        Retry<Integer> retry = new Retry<>(5);
        Integer result = retry.retry((i) -> {
            if (i == 2) {
                return i;
            }

            throw new RuntimeException("Mock");
        });
        assertNotNull(result);
        assertEquals(Integer.valueOf(2), result);
    }

    @Test
    public void testRetryOnExceptions_whenMismatched() {
        Retry<Integer> retry = new Retry<>(5);
        Exception exception1 = assertThrows(Exception.class, () -> retry.retry(() -> {
            throw new FileNotFoundException("Mock");
        }, ClassNotFoundException.class));
        assertNotNull(exception1);
        assertSame(exception1.getClass(), FileNotFoundException.class);
        Exception exception2 = assertThrows(Exception.class, () -> retry.retry((i) -> {
            throw new FileNotFoundException("Mock");
        }, ClassNotFoundException.class));
        assertNotNull(exception2);
        assertSame(exception2.getClass(), FileNotFoundException.class);
    }

    @Test
    public void testRetryOnExceptions_whenMatched() {
        Retry<Integer> retry = new Retry<>(5);
        Exception exception1 = assertThrows(Exception.class, () -> retry.retry(() -> {
            throw new FileNotFoundException("Mock");
        }, FileNotFoundException.class));
        assertNotNull(exception1);
        assertSame(exception1.getClass(), FileNotFoundException.class);
        Exception exception2 = assertThrows(Exception.class, () -> retry.retry((i) -> {
            throw new FileNotFoundException("Mock");
        }, FileNotFoundException.class));
        assertNotNull(exception2);
        assertSame(exception2.getClass(), FileNotFoundException.class);
    }


    @Test
    public void testRetryOnExceptions_whenAllIsSuccessful() throws Exception {
        Retry<Integer> retry = new Retry<>(3);
        Integer callableResult = retry.retry(() -> 0, IllegalArgumentException.class);
        assertNotNull(callableResult);
        assertEquals(Integer.valueOf(0), callableResult);
        Integer functionResult = retry.retry((i) -> i, IllegalArgumentException.class);
        assertNotNull(functionResult);
        assertEquals(Integer.valueOf(0), functionResult);
    }

    @Test
    public void testRetryOnExceptions_whenMatched_andThe3rdAttemptIsSuccessful() throws Exception {
        Retry<Integer> retry = new Retry<>(5);
        Integer result = retry.retry((i) -> {
            if (i == 2) {
                return i;
            }

            throw new IllegalArgumentException("Mock");
        }, IllegalArgumentException.class);
        assertNotNull(result);
        assertEquals(Integer.valueOf(2), result);
    }
}