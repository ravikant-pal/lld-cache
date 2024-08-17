package org.ravikant.cache.storage;

import org.ravikant.cache.exceptions.NotFoundException;
import org.ravikant.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of the {@link Storage} interface using a {@link HashMap} for storage.
 * <p>
 * This class provides a storage mechanism based on a hash map to store key-value pairs. It also includes capacity
 * management to ensure the storage does not exceed the specified limit. If the storage reaches its capacity, an
 * exception is thrown when attempting to add new entries.
 *
 * @param <K> The type of keys used in the storage.
 * @param <V> The type of values stored in the storage.
 */
public class HashMapBasedStorage<K, V> implements Storage<K, V> {

    private final Map<K, V> storage;
    private final int capacity;

    /**
     * Constructs a new {@link HashMapBasedStorage} instance with the specified capacity.
     * <p>
     * The capacity defines the maximum number of entries that can be stored. When the storage reaches this capacity,
     * attempting to add new entries will result in a {@link StorageFullException}.
     *
     * @param capacity The maximum number of entries that the storage can hold.
     */
    public HashMapBasedStorage(int capacity) {
        this.capacity = capacity;
        this.storage = new HashMap<>();
    }

    /**
     * Adds a key-value pair to the storage.
     * <p>
     * This method inserts or updates the entry with the specified key and value in the storage. If the storage is full,
     * a {@link StorageFullException} is thrown.
     *
     * @param key The key for the entry to be added.
     * @param value The value associated with the key to be stored.
     * @throws StorageFullException if the storage has reached its capacity.
     */
    @Override
    public void add(K key, V value) {
        if (isStorageFull()) {
            throw new StorageFullException("Capacity Full.....");
        }
        storage.put(key, value);
    }

    /**
     * Removes the entry with the specified key from the storage.
     * <p>
     * This method deletes the entry associated with the given key. If the key does not exist in the storage, a
     * {@link NotFoundException} is thrown.
     *
     * @param key The key of the entry to be removed.
     * @throws NotFoundException if the key does not exist in the storage.
     */
    @Override
    public void remove(K key) throws NotFoundException {
        if (!storage.containsKey(key)) {
            throw new NotFoundException(key + " doesn't exist in cache.");
        }
        storage.remove(key);
    }

    /**
     * Retrieves the value associated with the specified key from the storage.
     * <p>
     * This method returns the value for the given key. If the key does not exist in the storage, a
     * {@link NotFoundException} is thrown.
     *
     * @param key The key for which the associated value is to be retrieved.
     * @return The value associated with the key.
     * @throws NotFoundException if the key does not exist in the storage.
     */
    @Override
    public V get(K key) throws NotFoundException {
        if (!storage.containsKey(key)) {
            throw new NotFoundException(key + " doesn't exist in cache.");
        }
        return storage.get(key);
    }

    /**
     * Checks if the storage has reached its capacity.
     * <p>
     * This method determines whether the number of entries in the storage has reached the specified capacity.
     *
     * @return true if the storage is full, false otherwise.
     */
    private boolean isStorageFull() {
        return storage.size() >= capacity;
    }
}

