package org.ravikant.cache.policies;


import org.ravikant.algoritms.DoublyLinkedList;
import org.ravikant.algoritms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 * An implementation of the {@link EvictionPolicy} interface using a Least Recently Used (LRU) eviction strategy.
 * <p>
 * This class uses a combination of a doubly linked list and a hash map to efficiently manage cache entries
 * based on their access order. The doubly linked list maintains the order of key access, while the hash map
 * provides quick access to the nodes in the linked list.
 *
 * @param <Key> The type of keys used in the cache.
 */
public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> dll;
    private final Map<Key, DoublyLinkedListNode<Key>> mapper;

    /**
     * Constructs a new {@link LRUEvictionPolicy} instance with an empty list and map.
     */
    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.mapper = new HashMap<>();
    }

    /**
     * Updates the eviction policy based on key access.
     * <p>
     * If the key is already present in the cache, it is moved to the end of the list to mark it as recently used.
     * If the key is not present, it is added to the end of the list and stored in the map.
     *
     * @param key The key that was accessed. This key is used to update its position in the list.
     */
    @Override
    public void keyAccessed(Key key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            DoublyLinkedListNode<Key> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }

    /**
     * Determines and returns the least recently used key that should be evicted from the cache.
     * <p>
     * This method retrieves the first node from the doubly linked list, which represents the least recently used key,
     * detaches it from the list, and returns its associated key. If the list is empty, it returns null.
     *
     * @return The key that should be evicted or null if no key can be evicted.
     */
    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key> first;
        try {
            first = dll.getFirstNode();
        } catch (NoSuchElementException noSuchElementException) {
            return null;
        }
        dll.detachNode(first);
        return first.getElement();
    }
}

