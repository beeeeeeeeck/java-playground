package org.bl.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author beckl
 */
public class LFUCacheTest {
    @Test
    public void testBuilder() {
        // ["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
        // [[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]
        // [null,null,null,2,1,2,null,null,3,2,-1,4]
        // [null,null,null,2,1,2,null,null,-1,2,1,4]
        LFUCache<Integer, String> cache = new LFUCache<>(3);
        cache.put(2, "2");
        cache.put(1, "1");
        assertEquals("2", cache.get(2));
        assertEquals("1", cache.get(1));
        assertEquals("2", cache.get(2));
        cache.put(3, "3");
        cache.put(4, "4");
        assertNull(cache.get(3));
        assertEquals("2", cache.get(2));
        assertEquals("1", cache.get(1));
        assertEquals("4", cache.get(4));
    }
}
