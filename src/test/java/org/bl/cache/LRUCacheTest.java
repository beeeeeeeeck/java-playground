package org.bl.cache;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author beckl
 */
public class LRUCacheTest {
    @Test
    public void testBuilder() {
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.put(4, "D");
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(6, "C");

        assertEquals(3, cache.size());
        assertEquals("A", cache.get(1));
        assertEquals("B", cache.get(2));
        assertEquals("C", cache.get(6));
        cache.get(2);
        assertEquals("A,C,B", cache.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.joining(",")));
        cache.put(4, "D");
        assertEquals("C,B,D", cache.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.joining(",")));
        cache.get(1);
        assertEquals("C,B,D", cache.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.joining(",")));
    }
}
