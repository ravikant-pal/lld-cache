# Cache System with Eviction Policy — LLD

## Overview

This project implements a simple in-memory cache system with support for **Least Recently Used (LRU)** eviction policy. The cache can store key-value pairs, with the ability to evict the least recently used items when the storage reaches capacity.

It provides flexibility to configure:
- **Eviction Policies**: Currently supports LRU via a doubly linked list.
- **Storage Mechanism**: Uses `HashMap` or `LinkedHashMap` as the underlying storage. The `LinkedHashMap` automatically handles eviction when the capacity is exceeded.

## Features

- **Cache Factory**: A factory class to easily create instances of the cache with various configurations.
- **Eviction Policies**: Interfaces and implementations to support different eviction strategies (LRU supported by default).
- **Storage**: Storage mechanisms to store the key-value pairs. Can be extended to support other storage backends.
- **Error Handling**: Exceptions like `StorageFullException`, `NotFoundException`, and `InvalidElementException` to ensure proper handling of invalid states.

## Class Structure

### Core Classes

- **`Cache<K, V>`**: The main class that stores key-value pairs. It manages the storage and eviction policy.

- **`EvictionPolicy<K>`**: Interface for eviction strategies, with methods for key access notification and eviction decision-making.

- **`LRUEvictionPolicy<K>`**: LRU eviction policy implementation using a doubly linked list and a map for efficient key management.

- **`Storage<K, V>`**: Interface for storage mechanisms with methods for adding, removing, and retrieving key-value pairs.

- **`HashMapBasedStorage<K, V>`**: Uses a `HashMap` as the underlying storage. It throws an exception when the storage is full.

- **`LinkedHashMapBasedStorage<K, V>`**: Uses a `LinkedHashMap` to automatically handle eviction based on LRU without needing explicit eviction policy logic.

### Utility Classes

- **`DoublyLinkedList<E>`**: A custom doubly linked list class to help manage nodes for LRU operations. Provides methods for adding, removing, and detaching nodes.

- **`DoublyLinkedListNode<E>`**: Represents a node in the doubly linked list.

- **`CacheFactory<K, V>`**: Factory class for creating `Cache` instances with default or custom eviction policies and storage mechanisms.

### Exception Classes

- **`InvalidElementException`**: Thrown when an invalid or null element is added to the doubly linked list.

- **`StorageFullException`**: Thrown when attempting to add to storage that is already full.

- **`NotFoundException`**: Thrown when a key that does not exist in storage is accessed.

## Getting Started

### Prerequisites

- **Java 8 or higher**.
- **Maven** for dependency management (if applicable).

### Running the Project

1. **Clone the repository**:
   ```bash
   git clone https://github.com/ravikant-pal/lld-cache.git
   cd lld-cache
   ```

2. **Build the project**:
   You can compile the project using your preferred IDE or Maven.
   ```bash
   mvn clean install
   ```

3. **Example Usage**:
   ```java
   public class Main {
        public static void main(String[] args) {
            // Create an LRU cache with a capacity of 3
            Cache<String, String> cache = CacheFactory.getDefaultCache(3);

            // Add items to the cache
            cache.put("A", "Item A");
            cache.put("B", "Item B");
            cache.put("C", "Item C");

            // Access the cache
            System.out.println(cache.get("A"));  // Accessing A to mark it as recently used

            // Add another item which causes the least recently used item (B) to be evicted
            cache.put("D", "Item D");

            // Now accessing B will return null
            System.out.println(cache.get("B"));  // Output: null
        }
   }
   ```

## Key Parts

### `Cache`

The `Cache<K, V>` class manages the key-value pairs with a selected storage mechanism and an eviction policy.

- **`put(K key, V value)`**: Adds a key-value pair to the cache. If the cache is full, it will evict the least recently used item.
- **`get(K key)`**: Retrieves the value for the given key. If the key does not exist, it returns `null`.

### `LRUEvictionPolicy<K>`

This class tracks key accesses using a doubly linked list, ensuring that the least recently used items are efficiently evicted when needed.

### `Storage`

The `Storage<K, V>` interface defines how data is stored in the cache. The project provides two implementations:
- **`HashMapBasedStorage<K, V>`**: Throws `StorageFullException` when full.
- **`LinkedHashMapBasedStorage<K, V>`**: Automatically evicts the least recently used item.

## Exception Handling

- **`StorageFullException`**: Handles cases when the cache reaches its storage limit and can't add more entries.
- **`NotFoundException`**: Handles cases when a non-existent key is accessed in the cache.

## Customization

- You can create your own eviction policies by implementing the `EvictionPolicy<K>` interface.
- New storage mechanisms can be added by implementing the `Storage<K, V>` interface, allowing integration with other storage types (e.g., database, file system).

## Tests

Unit tests can be added for:
- Cache insertion and eviction scenarios.
- LRU behavior with the eviction policy.
- Handling of storage full conditions and exception cases.

To run tests:
```bash
mvn test
```

## Contact

For questions, feedback, or contributions, please open an issue or submit a pull request.