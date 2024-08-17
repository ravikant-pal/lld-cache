package org.ravikant.cache.storage;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A storage implementation based on {@link LinkedHashMap} that supports
 * automatic eviction of the least recently used (LRU) entries when a specified
 * capacity limit is reached.
 *
 * @param <K> the type of keys maintained by this storage
 * @param <V> the type of mapped values
 */
public class LinkedHashMapBasedStorage<K, V> implements Storage<K, V> {

    private final Map<K, V> storage;

    /**
     * Constructs a new storage with the specified capacity. When the capacity is
     * reached, the least recently used (LRU) entry is automatically removed from
     * the storage.
     *
     * @param capacity the maximum number of entries the storage can hold before
     *                 evicting the least recently used entry
     */
    public LinkedHashMapBasedStorage(int capacity) {
        this.storage = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    /**
     * Adds a key-value pair to the storage. If the storage exceeds its capacity,
     * the least recently used entry will be removed.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    @Override
    public void add(K key, V value) {
        storage.put(key, value);
    }

    /**
     * Removes the mapping for the specified key from this storage if present.
     *
     * @param key the key whose mapping is to be removed from the storage
     */
    @Override
    public void remove(K key) {
        storage.remove(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or {@code null} if
     * this storage contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or {@code null} if
     *         no mapping exists for the key
     */
    @Override
    public V get(K key) {
        return storage.get(key);
    }
}

