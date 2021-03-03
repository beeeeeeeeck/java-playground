package org.bl.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author beckl
 */
public class LRUCache<K, V> implements ICache {
    private final Map<K, V> cache;
    private final int capacity;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>(capacity, 1.0F, true);
        this.capacity = capacity;
    }

    /**
     * Get value by key
     *
     * @param key K
     * @return V
     */
    public V get(K key) {
        return cache.get(key);
    }

    /**
     * Put value with key
     *
     * @param key   K
     * @param value V
     */
    public void put(K key, V value) {
        boolean keyExists = cache.containsKey(key);
        if ((!keyExists) && (cache.size() + 1 > capacity)) {
            evictCache();
        }

        cache.put(key, value);
    }

    /**
     * Remove element at the head of the list (this will be the LRU element).
     */
    private void evictCache() {
        K key = cache.keySet().iterator().next();
        cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return cache.entrySet();
    }
}