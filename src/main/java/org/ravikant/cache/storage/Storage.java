package org.ravikant.cache.storage;


/**
 * An interface for defining a storage mechanism for cache entries.
 * <p>
 * Implementations of this interface provide methods for adding, removing, and retrieving cache entries.
 * The storage can be in-memory, file-based, or any other form as defined by the implementing class.
 *
 * @param <K> The type of keys used in the storage.
 * @param <V> The type of values stored in the storage.
 */
public interface Storage<K, V> {

    /**
     * Adds a key-value pair to the storage.
     * <p>
     * This method inserts or updates the entry with the specified key and value in the storage. If the key already
     * exists, its associated value is updated. If the key does not exist, a new entry is created.
     *
     * @param key The key for the entry to be added.
     * @param value The value associated with the key to be stored.
     */
    void add(K key, V value);

    /**
     * Removes the entry with the specified key from the storage.
     * <p>
     * This method deletes the entry associated with the given key. If the key does not exist, no action is taken.
     *
     * @param key The key of the entry to be removed.
     */
    void remove(K key);

    /**
     * Retrieves the value associated with the specified key from the storage.
     * <p>
     * This method returns the value for the given key. If the key does not exist, it returns null or throws an exception
     * depending on the implementation.
     *
     * @param key The key for which the associated value is to be retrieved.
     * @return The value associated with the key, or null if the key does not exist.
     */
    V get(K key);
}

