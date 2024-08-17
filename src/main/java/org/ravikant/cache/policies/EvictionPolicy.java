package org.ravikant.cache.policies;

/**
 * An interface for defining an eviction policy for cache entries.
 * <p>
 * Implementations of this interface define the strategy for evicting entries from a cache. The policy is responsible
 * for tracking key access and determining which key should be evicted when the cache reaches its capacity.
 *
 * @param <K> The type of keys used in the cache.
 */
public interface EvictionPolicy<K> {

    /**
     * Notifies the policy that a particular key has been accessed.
     * <p>
     * This method should be called whenever a key is accessed in the cache. The policy implementation can use this
     * information to update its internal state and make eviction decisions based on the access pattern.
     *
     * @param key The key that was accessed. This key is used to update the policy's state.
     */
    void keyAccessed(K key);

    /**
     * Determines and returns the key that should be evicted from the cache.
     * <p>
     * This method is called when the cache needs to remove an entry. The policy implementation should use its internal
     * state to decide which key to evict according to the eviction strategy (e.g., LRU, FIFO).
     *
     * @return The key that should be evicted. If no key can be evicted, the method may return null or throw an exception
     *         depending on the implementation.
     */
    K evictKey();
}

