package org.ravikant.cache;

import org.ravikant.cache.exceptions.NotFoundException;
import org.ravikant.cache.exceptions.StorageFullException;
import org.ravikant.cache.policies.EvictionPolicy;
import org.ravikant.cache.storage.Storage;

import java.util.Objects;

public class Cache<K, V> {
    private final Storage<K, V> storage;
    private EvictionPolicy<K> evictionPolicy;

    public Cache(EvictionPolicy<K> evictionPolicy, Storage<K, V> storage) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public Cache(Storage<K, V> storage) {
        this.storage = storage;
    }

    public void put(K key, V value) {

        try {
            this.storage.add(key, value);
            if (Objects.nonNull(evictionPolicy)) {
                this.evictionPolicy.keyAccessed(key);
            }
        } catch (StorageFullException exception) {
            System.out.println("Storage full. Attempting to evict.");
            K keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State. Storage full and no key to evict.");
            }
            this.storage.remove(keyToRemove);
            System.out.println("Creating space by evicting item..." + keyToRemove);
            put(key, value);
        }
    }

    public V get(K key) {
        try {
            V value = this.storage.get(key);
            if (Objects.nonNull(this.evictionPolicy)) {
                this.evictionPolicy.keyAccessed(key);
            }
            return value;
        } catch (NotFoundException notFoundException) {
            System.out.println("Tried to access non-existing key.");
            return null;
        }
    }

}

