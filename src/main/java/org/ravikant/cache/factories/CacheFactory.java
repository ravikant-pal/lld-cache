package org.ravikant.cache.factories;

import org.ravikant.cache.Cache;
import org.ravikant.cache.policies.EvictionPolicy;
import org.ravikant.cache.storage.LinkedHashMapBasedStorage;
import org.ravikant.cache.storage.Storage;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * A factory class for creating {@link Cache} instances with various configurations.
 * <p>
 * This class provides static methods to build {@link Cache} objects with different eviction policies
 * and storage mechanisms. It abstracts the instantiation process and allows for flexible cache configurations
 * to meet different requirements.
 *
 * @param <K> The type of keys used in the cache.
 * @param <V> The type of values stored in the cache.
 */
public class CacheFactory<K, V> {

    /**
     * Constructs a new {@link Cache} instance with the specified eviction policy and storage mechanism.
     *
     * @param evictionPolicy The policy used to determine when entries in the cache should be evicted.
     *                       This defines the criteria or strategy for removing old or unused cache entries.
     *                       Typically, an implementation of {@link EvictionPolicy}.
     * @param storage The storage mechanism where cache entries are stored. This could be an in-memory store,
     *                a file-based store, or any other type of storage defined by the {@link Storage} interface.
     *                Typically, an implementation of {@link Storage}.
     * @param <K> The type of keys used in the cache.
     * @param <V> The type of values stored in the cache.
     * @return A new {@link Cache} instance configured with the specified eviction policy and storage mechanism.
     */
    public static <K, V> Cache<K, V> buildCache(EvictionPolicy<K> evictionPolicy, Storage<K, V> storage) {
        return new Cache<>(evictionPolicy, storage);
    }


    /**
     * Creates a default {@link Cache} instance with a specified capacity. The cache will use an LRU (Least Recently Used)
     * eviction policy with a {@link LinkedHashMap} for tracking access order and a {@link HashMap} for storage.
     *
     * @param capacity The maximum number of entries the cache can hold before eviction occurs. Once this limit is reached,
     *                 the cache will evict the least recently used entries based on the LRU policy.
     * @return A new {@link Cache} instance configured with an LRU eviction policy and a hash map-based storage mechanism.
     */
    public static <K, V> Cache<K, V> getDefaultCache(final int capacity) {
        return new Cache<>(new LinkedHashMapBasedStorage<>(capacity));
    }
}

