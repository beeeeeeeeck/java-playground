package org.bl.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author beckl
 */
public class LFUCache<K, V> implements ICache {
    private final Map<K, V> cache;
    private final Map<K, CacheStatistics> frequency;
    private final int capacity;

    public LFUCache(int capacity) {
        cache = new HashMap<>(capacity, 1.0F);
        frequency = new HashMap<>(capacity, 1.0F);
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

        CacheStatistics statistics = frequency.getOrDefault(key, new CacheStatistics());
        statistics.count += 1;
        frequency.put(key, statistics);
    }

    /**
     * Remove element at the head of the list (this will be the LFU element).
     */
    private void evictCache() {
        Map.Entry<K, CacheStatistics> minEntry = Collections.min(frequency.entrySet(), (e1, e2) -> {
            CacheStatistics s1 = e1.getValue();
            CacheStatistics s2 = e2.getValue();
            return s1.count == s2.count ? Long.compare(s2.addedTime, s1.addedTime) : Integer.compare(s1.count, s2.count);
        });
        cache.remove(minEntry.getKey());
        frequency.remove(minEntry.getKey());
    }

    @Override
    public int size() {
        return cache.size();
    }

    private static class CacheStatistics {
        int count;
        long addedTime;

        CacheStatistics() {
            count = 0;
            addedTime = System.currentTimeMillis();
        }
    }
}