package org.bl.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface BlockingMap<K, V> {
    void put(K key, V value) throws InterruptedException;

    boolean offer(K key, V value, long timeout, TimeUnit unit) throws InterruptedException;

    boolean offer(K key, V value);

    void add(K key, V value);

    V take(K key) throws InterruptedException;

    V poll(K key, long timeout, TimeUnit unit) throws InterruptedException;

    V poll(K key);

    V remove(K key);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    void clear();

    Set<K> keySet();

    Collection<V> values();

    Set<Map.Entry<K, V>> entrySet();
}
