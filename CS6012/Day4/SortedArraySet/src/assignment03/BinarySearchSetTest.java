package assignment03;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {

    @Test
    public void testFirst() {
        //Tested  throughout
        BinarySearchSet<Integer> emptySet=new BinarySearchSet<>();
        assertThrows(NoSuchElementException.class, emptySet::first);
    }

    @Test
    public void testLast() {
        //Tested throughout
        BinarySearchSet<Integer> emptySet=new BinarySearchSet<>();
        assertThrows(NoSuchElementException.class, emptySet::last);
    }

    @Test
    public void testAdd() {
        // Tests the 'add' method in various scenarios

        BinarySearchSet<Integer> set = new BinarySearchSet<>();

        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        //Add elements individually.
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

        //Add multiple elements at once using 'addAll'
        List<Integer> elementsToAdd = Arrays.asList(2, 4, 6);
        assertTrue(set.addAll(elementsToAdd));

        assertEquals(6, set.size());
        assertTrue(set.containsAll(elementsToAdd));

    }

    @Test
    public void testAdd2() {
        // Tests the 'add' method for a set of integers, checking size and first/last elements
        BinarySearchSet<Integer> integerSet = new BinarySearchSet<>();
        integerSet.add(1);
        integerSet.add(5);
        integerSet.add(2);
        integerSet.add(8);
        integerSet.add(7);

        assertEquals(integerSet.size(), 5);

        assertEquals(integerSet.first(), 1);
        assertEquals(integerSet.last(), 8);
    }

    @Test
    public void testAdd3() {
        // Tests the 'add' method for a set of integers

        BinarySearchSet<Integer> integerSet = new BinarySearchSet<>();
        integerSet.add(1);
        integerSet.add(2);
        integerSet.add(4);
        integerSet.add(5);
        integerSet.add(6);
        integerSet.add(3);

        // Checks the element was placed at the specific index
        assertEquals(integerSet.get(2), 3);
        integerSet.add(0);
        assertEquals(integerSet.get(0), 0);
    }

    @Test
    public void testAddAll() {
        // Tests the 'addAll' method for adding a collection of elements to the set
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
    public void testClear() {
        // Tests the 'clear' method to clear the set
        BinarySearchSet<Integer> clearSetTest = new BinarySearchSet<>();
        assertTrue(clearSetTest.isEmpty());

        clearSetTest.add(2);
        clearSetTest.add(3);
        clearSetTest.add(5);
        clearSetTest.clear();

        assertTrue(clearSetTest.isEmpty());
    }

    @Test
    public void testContains() {
        // Tests the 'contains' method in various scenarios

        // Check contains for empty set
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        assertFalse(set.contains(1));

        set.add(3);
        set.add(1);
        set.add(5);

        assertFalse(set.isEmpty());
        assertEquals(3, set.size());

        //Check contains after adding elements
        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertTrue(set.contains(5));
        assertFalse(set.contains(2));
    }

    @Test
    public void testContainsAll() {
        // Tests the 'containsAll' method for checking if the set contains all elements from a collection
        BinarySearchSet<String> stringSetTest = new BinarySearchSet<>();
        stringSetTest.add("one");
        stringSetTest.add("two");
        stringSetTest.add("three");

        Set<String> elementsToCheck = new HashSet<>(Arrays.asList("one", "two", "three"));
        //one number present, one not, should come back false
        Set<String> elementsToCheck2=new HashSet<>(Arrays.asList("one", "four"));

        assertTrue(stringSetTest.containsAll(elementsToCheck));
        assertFalse(stringSetTest.containsAll(elementsToCheck2));
    }

    @Test
    public void testIsEmpty() {
        // Tests the 'isEmpty' method for an empty set and a non-empty set
        BinarySearchSet<Integer> emptySet = new BinarySearchSet<>();
        BinarySearchSet<Integer> fullSet = new BinarySearchSet<>();
        fullSet.add(1);
        fullSet.add(2);
        assertTrue(emptySet.isEmpty());
        assertFalse(fullSet.isEmpty());
    }

    @Test
    public void testRemove() {
        // Tests the 'remove' method in various scenarios, in the middle and the last

        BinarySearchSet<Integer> removeSetTest = new BinarySearchSet<>();
        assertTrue(removeSetTest.isEmpty());
        assertEquals(0, removeSetTest.size());

        removeSetTest.add(6);
        removeSetTest.add(7);
        removeSetTest.add(8);

        //Remove an element in the middle
        removeSetTest.remove(7);
        //Remove the last element
        assertEquals(removeSetTest.last(), 8);
        assertEquals(removeSetTest.size(), 2);
        assertFalse(removeSetTest.remove(9));
    }

    @Test
    public void testRemoveAll() {
        // Tests the 'removeAll' method for removing a collection of elements from the set
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
    public void testSize() {
        //Tested throughout
    }

    @Test
    public void testToArray() {
        BinarySearchSet<Integer> toArrayTest = new BinarySearchSet<>();

        toArrayTest.add(1);
        toArrayTest.add(3);
        toArrayTest.add(5);
        toArrayTest.add(7);
        toArrayTest.add(9);

        // Get the array representation using toArray
        Object[] toArrayResult = toArrayTest.toArray();

        // Expected array based on the elements added
        Object[] expectedResult = {1, 3, 5, 7, 9};

        // Asserting that the obtained array is equal to the expected array
        assertArrayEquals(expectedResult, toArrayResult);
    }

    @Test
    public void testGrowArray() {
        //Test grow array on a large amount of additions
        BinarySearchSet<Integer> growArrayTest = new BinarySearchSet<>();

        for (int i = 1; i <= 1000; i++) {
            growArrayTest.add(i);
        }
        assertEquals(growArrayTest.size(), 1000);
    }

    @Test
    public void testGet() {
        // Tests the 'get' method in various scenarios

        BinarySearchSet<Integer> testGetSet = new BinarySearchSet<>();
        testGetSet.add(1);
        testGetSet.add(5);
        testGetSet.add(2);
        testGetSet.add(7);
        testGetSet.add(8);

        //Get an element with a valid index
        int validIndex = 2;
        assertEquals(Integer.valueOf(5), testGetSet.get(validIndex));

        //Get an element with an invalid index, i.e., out of bounds
        int invalidIndex = testGetSet.size() + 1;
        try {
            testGetSet.get(invalidIndex);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // This exception is expected
        }
        // Get an element with a negative index
        int negativeIndex = -1;
        try {
            testGetSet.get(negativeIndex);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // This exception is expected
        }
    }

    //============START OF ITERATOR TESTS============//

    @Test
    public void testNextAndHasNext() {
        //Test next and hasNext by making an array, adding it to the BinarySearchSet, then comparing at same index
        var arrayList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));

        BinarySearchSet<Integer> hasNextTest = new BinarySearchSet<>();
        hasNextTest.addAll(arrayList);
        var iterator = hasNextTest.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), arrayList.get(0));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), arrayList.get(1));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), arrayList.get(2));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), arrayList.get(3));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), arrayList.get(4));

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveIterator() {
        // Tests the 'remove' method of the iterator, unable to remove at the "0" index w/o calling next() first
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

}