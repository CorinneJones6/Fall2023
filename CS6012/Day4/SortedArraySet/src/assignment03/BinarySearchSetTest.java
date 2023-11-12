package assignment03;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {

    @Test
    public void testFirst(){

    }

    @Test
    public void testLast(){

    }

    @Test
    public void testAdd() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();

        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        assertTrue(set.add(3));
        assertTrue(set.add(1));
        assertTrue(set.add(5));

        assertFalse(set.isEmpty());
        assertEquals(3, set.size());

        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertTrue(set.contains(5));
        assertFalse(set.contains(2));

        // Add duplicates, expect false return
        assertFalse(set.add(3));
        assertFalse(set.add(1));
        assertFalse(set.add(5));

        assertEquals(3, set.size());

        // Add multiple elements at once
        List<Integer> elementsToAdd = Arrays.asList(2, 4, 6);
        assertTrue(set.addAll(elementsToAdd));

        assertEquals(6, set.size());
        assertTrue(set.containsAll(elementsToAdd));
    }

    @Test
    public void testAdd2(){
        BinarySearchSet<Integer> integerSet = new BinarySearchSet<>();
        System.out.println(integerSet.size());
        integerSet.add(1);
        System.out.println(integerSet.size());
        integerSet.add(5);
        integerSet.add(2);
        integerSet.add(7);
        integerSet.add(8);
        assertEquals(integerSet.size(), 5);

        assertEquals(integerSet.first(), 1);
        assertEquals(integerSet.last(), 8);
    }

    @Test
    public void testAddAll(){
        BinarySearchSet<String> addAllTest = new BinarySearchSet<>();
        addAllTest.add("blue");
        addAllTest.add("red");
        addAllTest.add("green");

        Set<String> elementsToAdd = new HashSet<>(Arrays.asList("purple", "pink", "yellow"));
        addAllTest.addAll(elementsToAdd);

        assertTrue(addAllTest.contains("purple"));
        assertTrue(addAllTest.contains("pink"));
        assertTrue(addAllTest.contains("yellow"));
        assertTrue(addAllTest.contains("blue"));
        assertTrue(addAllTest.contains("red"));
        assertTrue(addAllTest.contains("green"));
    }

    @Test
    public void testClear(){
        BinarySearchSet<Integer> clearSetTest = new BinarySearchSet<>();
        assertTrue(clearSetTest.isEmpty());
        //Say "set is already clear?"
        clearSetTest.add(2);
        clearSetTest.add(3);
        clearSetTest.add(5);
    }

    @Test
    public void testContains() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        assertFalse(set.contains(1));

        set.add(3);
        set.add(1);
        set.add(5);

        assertFalse(set.isEmpty());
        assertEquals(3, set.size());

        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertTrue(set.contains(5));
        assertFalse(set.contains(2));
    }

    @Test
    public void testContainsAll(){
        BinarySearchSet<String> stringSet=new BinarySearchSet<>();
        stringSet.add("one");
        stringSet.add("two");
        stringSet.add("three");

        Set<String> elementsToCheck = new HashSet<>(Arrays.asList("one", "two", "three"));

        assertTrue(stringSet.containsAll(elementsToCheck));
    }

    @Test
    public void testIsEmpty() {
        BinarySearchSet<Integer> emptySet = new BinarySearchSet<>();
        BinarySearchSet<Integer> fullSet = new BinarySearchSet<>();
        fullSet.add(1);
        fullSet.add(2);
        assertTrue(emptySet.isEmpty());
        assertFalse(fullSet.isEmpty());
    }

        //============START OF ITERATOR TESTS============//
    @Test
    public void testHasNext(){

    }

    @Test
    public void testNext(){

    }

    @Test
    public void testRemoveIterator(){
        BinarySearchSet<String> testRemoveIterator = new BinarySearchSet<>();
        testRemoveIterator.add("red");
        testRemoveIterator.add("orange");
        testRemoveIterator.add("yellow");
        testRemoveIterator.add("green");
        testRemoveIterator.add("blue");

        assertTrue(testRemoveIterator.contains("yellow"));

        // Get an iterator and advance it to the position of "yellow"
        Iterator<String> iterator = testRemoveIterator.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next().toString();
            System.out.println(str);
            if (str.equals("yellow")) {
                break;
            }
        }
        // Call the remove method using the iterator
        iterator.remove();
        // Now "yellow" should be removed from the set
        assertFalse(testRemoveIterator.contains("yellow"));
    }

    //=================END OF ITERATOR TESTS===================//

    @Test
    public void testRemove(){
        BinarySearchSet<Integer> removeSetTest = new BinarySearchSet<>();
        assertTrue(removeSetTest.isEmpty());
        assertEquals(0, removeSetTest.size());

        removeSetTest.add(6);
        removeSetTest.add(7);
        removeSetTest.add(8);

        removeSetTest.remove(7);
        assertEquals(removeSetTest.last(), 8);
        assertEquals(removeSetTest.size(), 2);
    }

    @Test
    public void testRemoveAll() {
        BinarySearchSet<Integer> testSet = new BinarySearchSet<>();
        testSet.add(1);
        testSet.add(5);
        testSet.add(2);
        testSet.add(7);
        testSet.add(8);

        Collection<Integer> elementsToRemove = new ArrayList<>();
        elementsToRemove.add(5);
        elementsToRemove.add(7);

        assertTrue(testSet.removeAll(elementsToRemove));

        assertFalse(testSet.contains(5));
        assertFalse(testSet.contains(7));
        assertTrue(testSet.contains(1));
        assertTrue(testSet.contains(2));
        assertTrue(testSet.contains(8));
    }

    @Test
    public void testSize(){

    }

    @Test
    public void testToArray(){

    }

    @Test
    public void testGrowArray(){

    }

    @Test
    public void testGet() {
        BinarySearchSet<Integer> testGetSet = new BinarySearchSet<>();
        testGetSet.add(1);
        testGetSet.add(5);
        testGetSet.add(2);
        testGetSet.add(7);
        testGetSet.add(8);

        // Test with a valid index
        int validIndex = 2;
        assertEquals(Integer.valueOf(5), testGetSet.get(validIndex));

        // Test with an invalid index (out of bounds)
        int invalidIndex = (testGetSet.getSet_()).length + 1;
        try {
            testGetSet.get(invalidIndex);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // This exception is expected
        }
        // Test with negative index
        int negativeIndex = -1;
        try {
            testGetSet.get(negativeIndex);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // This exception is expected
        }
    }

}