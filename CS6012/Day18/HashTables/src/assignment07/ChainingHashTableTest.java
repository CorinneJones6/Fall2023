package assignment07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {

    @org.junit.jupiter.api.Test
    void add() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());

        assertTrue(hashTable.add("apple"));
        assertTrue(hashTable.add("banana"));
        assertTrue(hashTable.add("orange"));

        // Adding the same item again should return false
        assertFalse(hashTable.add("apple"));
        assertFalse(hashTable.add("banana"));

        // Check that the elements are present in the hash table
        assertTrue(hashTable.contains("apple"));
        assertTrue(hashTable.contains("banana"));
        assertTrue(hashTable.contains("orange"));

        // Check that other elements are not falsely reported as present
        assertFalse(hashTable.contains("grape"));
        assertFalse(hashTable.contains("kiwi"));
    }

    @org.junit.jupiter.api.Test
    void addAll() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());

        // Test adding a collection with unique items
        ArrayList<String> uniqueItems = new ArrayList<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(hashTable.addAll(uniqueItems));
        assertEquals(uniqueItems.size(), hashTable.size());

        // Test adding a collection with duplicate items
        ArrayList<String> duplicateItems = new ArrayList<>(Arrays.asList("apple", "banana", "orange"));
        assertFalse(hashTable.addAll(duplicateItems));
        assertEquals(uniqueItems.size(), hashTable.size()); // Size should remain the same

        // Test adding an empty collection
        ArrayList<String> emptyCollection = new ArrayList<>();
        assertFalse(hashTable.addAll(emptyCollection));
        assertEquals(uniqueItems.size(), hashTable.size()); // Size should remain the same

        // Test adding a collection with some new items
        ArrayList<String> newItems = new ArrayList<>(Arrays.asList("grape", "kiwi"));
        assertTrue(hashTable.addAll(newItems));
        assertEquals(uniqueItems.size() + newItems.size(), hashTable.size());

    }

    @org.junit.jupiter.api.Test
    void clear() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());

        // Add some items to the hash table
        hashTable.add("apple");
        hashTable.add("banana");
        hashTable.add("orange");

        // Verify that the hash table is not empty
        assertFalse(hashTable.isEmpty());

        // Clear the hash table
        hashTable.clear();

        // Verify that the hash table is now empty
        assertTrue(hashTable.isEmpty());
        assertEquals(0, hashTable.size());

        // Verify that adding items after clearing works correctly
        hashTable.add("grape");
        hashTable.add("kiwi");

        assertFalse(hashTable.isEmpty());
        assertEquals(2, hashTable.size());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());
        // Test with an empty hash table
        assertFalse(hashTable.contains("apple"));

        // Add some items to the hash table
        hashTable.add("apple");
        hashTable.add("banana");
        hashTable.add("orange");

        // Test with items that are present
        assertTrue(hashTable.contains("apple"));
        assertTrue(hashTable.contains("banana"));
        assertTrue(hashTable.contains("orange"));

        // Test with items that are not present
        assertFalse(hashTable.contains("grape"));
        assertFalse(hashTable.contains("kiwi"));

    }

    @org.junit.jupiter.api.Test
    void containsAll() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());
        // Test with an empty hash table and an empty collection
        assertTrue(hashTable.containsAll(Arrays.asList()));

        // Add some items to the hash table
        hashTable.add("apple");
        hashTable.add("banana");
        hashTable.add("orange");

        // Test with a collection that contains all items
        assertTrue(hashTable.containsAll(Arrays.asList("apple", "banana", "orange")));

        // Test with a collection that contains some items
        assertTrue(hashTable.containsAll(Arrays.asList("apple", "orange")));

        // Test with a collection that contains no items
        assertTrue(hashTable.containsAll(Arrays.asList()));

        // Test with a collection that contains an item not in the hash table
        assertFalse(hashTable.containsAll(Arrays.asList("apple", "kiwi")));
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());

        // Test with an initially empty hash table
        assertTrue(hashTable.isEmpty());

        // Add some items to the hash table
        hashTable.add("apple");
        hashTable.add("banana");

        // Test with a non-empty hash table
        assertFalse(hashTable.isEmpty());

        // Clear the hash table
        hashTable.clear();

        // Test with a cleared hash table
        assertTrue(hashTable.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());

        // Test removing from an initially empty hash table
        assertFalse(hashTable.remove("apple"));

        // Add some items to the hash table
        hashTable.add("apple");
        hashTable.add("banana");
        hashTable.add("orange");

        // Test removing an item that exists in the hash table
        assertTrue(hashTable.remove("apple"));
        assertFalse(hashTable.contains("apple"));
        assertEquals(2, hashTable.size());

        // Test removing an item that does not exist in the hash table
        assertFalse(hashTable.remove("grape"));
        assertEquals(2, hashTable.size()); // Size should remain the same

        // Test removing from an empty slot in the hash table
        hashTable.clear();
        assertFalse(hashTable.remove("banana"));
        assertEquals(0, hashTable.size());

        // Test removing from a non-empty slot in the hash table
        hashTable.add("kiwi");
        assertTrue(hashTable.remove("kiwi"));
        assertEquals(0, hashTable.size());
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());

        // Test removing all items from an initially empty hash table
        assertFalse(hashTable.removeAll(Arrays.asList("apple", "banana")));

        // Add some items to the hash table
        hashTable.add("apple");
        hashTable.add("banana");
        hashTable.add("orange");

        // Test removing a collection of items that exist in the hash table
        assertTrue(hashTable.removeAll(Arrays.asList("apple", "banana")));
        assertFalse(hashTable.contains("apple"));
        assertFalse(hashTable.contains("banana"));
        assertTrue(hashTable.contains("orange"));
        assertEquals(1, hashTable.size());

        // Test removing a collection of items where some do not exist in the hash table
        assertFalse(hashTable.removeAll(Arrays.asList("grape", "kiwi")));
        assertEquals(1, hashTable.size()); // Size should remain the same

        // Test removing all items from the hash table
        assertTrue(hashTable.removeAll(Arrays.asList("orange")));
        assertTrue(hashTable.isEmpty());
        assertEquals(0, hashTable.size());
    }

    @org.junit.jupiter.api.Test
    void size() {
        ChainingHashTable hashTable = new ChainingHashTable(10, new GoodHashFunctor());

        // Test with an initially empty hash table
        assertEquals(0, hashTable.size());

        // Add some items to the hash table
        hashTable.add("apple");
        hashTable.add("banana");
        hashTable.add("orange");

        // Test with a non-empty hash table
        assertEquals(3, hashTable.size());

        // Remove an item and test the updated size
        hashTable.remove("apple");
        assertEquals(2, hashTable.size());

        // Remove all items and test the size
        hashTable.removeAll(Arrays.asList("banana", "orange"));
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testGrowCapacity() {
        ChainingHashTable hashTable = new ChainingHashTable(5, new GoodHashFunctor());

        // Add items to the hash table until the load factor exceeds the threshold
        hashTable.add("apple");
        hashTable.add("banana");
        hashTable.add("orange");

        // Initial capacity is 5, so the load factor is 3/5 = 0.6, which is below the threshold (2)
        assertEquals(5, hashTable.getCapacity());

        // Add more items to trigger the capacity growth
        hashTable.add("grape");
        hashTable.add("kiwi");
        hashTable.add("melon");
        hashTable.add("clementine");
        hashTable.add("blueberry");
        hashTable.add("watermelon");
        hashTable.add("strawberry");

        // After adding these items, the load factor is =2 which exceeds threshold capacity
        assertEquals(10, hashTable.getCapacity());

        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("apple", "melon", "strawberry"));
        assertTrue(hashTable.containsAll(arrayList));
    }
}