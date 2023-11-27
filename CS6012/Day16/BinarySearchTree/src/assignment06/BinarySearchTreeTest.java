package assignment06;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @org.junit.jupiter.api.Test
    void add() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test throws Null Pointer Exception
        Integer item = null;
        assertThrows(NullPointerException.class, () -> bst.add(item));

        // Test adding elements to an empty BST
        assertTrue(bst.add(5));
        assertTrue(bst.contains(5));
        assertFalse(bst.isEmpty());
        assertEquals(1, bst.size());

        // Test adding an existing element (should return false)
        assertFalse(bst.add(5));
        assertEquals(1, bst.size());

        // Test adding elements in a sorted manner
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Check the size and presence of added elements
        assertEquals(7, bst.size());
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(8));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(9));
    }

    @org.junit.jupiter.api.Test
    void addAll() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test adding elements from an empty collection
        Collection<Integer> emptyCollection = Arrays.asList();
        assertFalse(bst.addAll(emptyCollection));
        assertTrue(bst.isEmpty());

        // Test adding elements from a non-empty collection
        Collection<Integer> collectionToAdd = Arrays.asList(5, 3, 8, 2, 4, 7, 9);

        assertTrue(bst.addAll(collectionToAdd));
        assertEquals(7, bst.size());
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(8));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(9));

        // Test adding elements where some already exist in the BST
        assertFalse(bst.addAll(collectionToAdd));
        assertEquals(7, bst.size());

        // Test adding elements from another non-empty collection
        Collection<Integer> anotherCollectionToAdd = Arrays.asList(1, 6, 10);

        assertTrue(bst.addAll(anotherCollectionToAdd));
        assertEquals(10, bst.size());
        assertTrue(bst.containsAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));

        //Test throws Null Pointer Exception
        Collection<Integer> items = Arrays.asList(1, 2, null, 4, 5);
        assertThrows(NullPointerException.class, () -> bst.addAll(items));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Add some elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));

        // Verify that the size is not 0 before clearing
        assertFalse(bst.isEmpty());
        assertEquals(3, bst.size());

        // Clear the BST
        bst.clear();

        // Verify that the size is 0 and the BST is empty after clearing
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());

        // Verify that the root is null after clearing
        assertNull(bst.root_);
    }

    @org.junit.jupiter.api.Test
    void contains() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test contains on an empty tree
        assertFalse(bst.contains(5));

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Test contains on existing elements
        assertTrue(bst.contains(5));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(8));
        assertTrue(bst.contains(2));
        assertTrue(bst.contains(4));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(9));

        // Test contains on non-existing elements
        assertFalse(bst.contains(1));
        assertFalse(bst.contains(6));
        assertFalse(bst.contains(10));

        //Test throws Null Pointer Exception
        Integer nullItem = null;
        assertThrows(NullPointerException.class, () -> bst.contains(nullItem));

        // Verify that the size remains the same
        assertEquals(7, bst.size());
    }

    @org.junit.jupiter.api.Test
    void containsAll() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test containsAll on an empty tree
        assertFalse(bst.containsAll(Arrays.asList(1, 2, 3)));

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Test containsAll with all existing elements
        assertTrue(bst.containsAll(Arrays.asList(5, 3, 8, 2, 4, 7, 9)));

        // Test containsAll with some existing and some non-existing elements
        assertFalse(bst.containsAll(Arrays.asList(1, 6, 10)));

        // Test containsAll with all non-existing elements
        assertFalse(bst.containsAll(Arrays.asList(11, 12, 13)));

        //Test throws Null Pointer Exception
        Collection<Integer> itemsWithNull = Arrays.asList(1, 6, null, 10);
        assertThrows(NullPointerException.class, () -> bst.containsAll(itemsWithNull));
    }

    @org.junit.jupiter.api.Test
    void first() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test first on an empty tree
        assertThrows(NoSuchElementException.class, bst::first);

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Test first with an existing element
        assertEquals(2, bst.first());

        // Remove the smallest element
        assertTrue(bst.remove(2));

        // Test first after removing the smallest element
        assertEquals(3, bst.first());

        // Remove all elements to make the tree empty
        assertTrue(bst.remove(3));
        assertTrue(bst.remove(4));
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(7));
        assertTrue(bst.remove(8));
        assertTrue(bst.remove(9));

        // Test first on an empty tree after removing all elements
        assertThrows(NoSuchElementException.class, bst::first);
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test isEmpty on an initially empty tree
        assertTrue(bst.isEmpty());

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));

        // Test isEmpty on a non-empty tree
        assertFalse(bst.isEmpty());

        // Remove all elements to make the tree empty
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(3));
        assertTrue(bst.remove(8));

        // Test isEmpty on an empty tree after removing all elements
        assertTrue(bst.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void last() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test last on an empty tree
        assertThrows(NoSuchElementException.class, bst::last);

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Test last with an existing element
        assertEquals(9, bst.last());

        // Remove the largest element
        assertTrue(bst.remove(9));

        // Test last after removing the largest element
        assertEquals(8, bst.last());

        // Remove all elements to make the tree empty
        assertTrue(bst.remove(2));
        assertTrue(bst.remove(3));
        assertTrue(bst.remove(4));
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(7));
        assertTrue(bst.remove(8));

        // Test last on an empty tree after removing all elements
        assertThrows(NoSuchElementException.class, bst::last);
    }

    @org.junit.jupiter.api.Test
    void remove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Add some elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Verify that the size is not 0 before removing
        assertFalse(bst.isEmpty());
        assertEquals(7, bst.size());

        // Remove an existing element
        assertTrue(bst.remove(7));

        // Verify that the size is smaller after removal
        assertEquals(6, bst.size());

        // Remove a non-existing element
        assertFalse(bst.remove(6));

        // Verify that the size remains the same
        assertEquals(6, bst.size());

        //Test throws Null Pointer Exception
        Integer nullItem = null;
        assertThrows(NullPointerException.class, () -> bst.remove(nullItem));

        // Remove all elements to make the BST empty
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(3));
        assertTrue(bst.remove(8));
        assertTrue(bst.remove(2));
        assertTrue(bst.remove(4));
        assertTrue(bst.remove(9));

        // Verify that the size is 0 and the BST is empty after removal
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertNull(bst.root_);
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test removeAll on an initially empty tree
        assertFalse(bst.removeAll(Arrays.asList(1, 2, 3)));

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Test removeAll with all existing elements
        assertTrue(bst.removeAll(Arrays.asList(5, 3, 8, 2, 4, 7, 9)));

        // Test removeAll with some existing and some non-existing elements
        assertFalse(bst.removeAll(Arrays.asList(1, 6, 10)));

        // Test removeAll with all non-existing elements
        assertFalse(bst.removeAll(Arrays.asList(11, 12, 13)));

        // Verify that the size is 0 and the BST is empty after removeAll
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertNull(bst.root_);

        //Test throws Null Pointer Exception
        Collection<Integer> itemsWithNull = Arrays.asList(1, 6, null, 10);
        assertThrows(NullPointerException.class, () -> bst.removeAll(itemsWithNull));

    }

    @org.junit.jupiter.api.Test
    void size() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test size on an initially empty tree
        assertEquals(0, bst.size());

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));

        // Test size on a non-empty tree
        assertEquals(3, bst.size());

        // Remove elements to decrease the size
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(3));

        // Test size after removing elements
        assertEquals(1, bst.size());

        // Clear the tree to make it empty
        bst.clear();

        // Test size on an empty tree after clearing
        assertEquals(0, bst.size());
    }

    @org.junit.jupiter.api.Test
    void toArrayList() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test toArrayList on an initially empty tree
        assertTrue(bst.toArrayList().isEmpty());

        // Add elements to the BST
        assertTrue(bst.add(5));
        assertTrue(bst.add(3));
        assertTrue(bst.add(8));
        assertTrue(bst.add(2));
        assertTrue(bst.add(4));
        assertTrue(bst.add(7));
        assertTrue(bst.add(9));

        // Test toArrayList on a non-empty tree
        List<Integer> expectedList = Arrays.asList(2, 3, 4, 5, 7, 8, 9);
        assertEquals(expectedList, bst.toArrayList());

        // Remove elements to change the order
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(3));
        assertTrue(bst.remove(8));

        // Test toArrayList after removing elements
        List<Integer> expectedListAfterRemove = Arrays.asList(2, 4, 7, 9);
        assertEquals(expectedListAfterRemove, bst.toArrayList());

        // Clear the tree to make it empty
        bst.clear();

        // Test toArrayList on an empty tree after clearing
        assertTrue(bst.toArrayList().isEmpty());
    }
}