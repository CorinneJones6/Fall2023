package assignment05;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @org.junit.jupiter.api.Test
    void insertFirst() {
        SinglyLinkedList<String> stringLinkedList = new SinglyLinkedList<>();
        stringLinkedList.insertFirst("Third");
        assertEquals("Third", stringLinkedList.getFirst());
        assertEquals(1, stringLinkedList.size());

        stringLinkedList.insertFirst("Second");
        stringLinkedList.insertFirst("First");

        assertEquals("First", stringLinkedList.getFirst());
        assertEquals(3, stringLinkedList.size());
    }

    @org.junit.jupiter.api.Test
    void insert() {
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));

        SinglyLinkedList<Integer> insertList = new SinglyLinkedList<>(integerList);

        insertList.insert(2, 3);

        assertEquals(3, insertList.get(2));

        insertList.insert(0, 0);

        assertEquals(0, insertList.get(0));
    }

    @org.junit.jupiter.api.Test
    void getFirst() {
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList("apple", "banana", "cucumber", "dates"));
        SinglyLinkedList<String> stringLinkedList = new SinglyLinkedList<>(stringArrayList);

        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        SinglyLinkedList<Integer> integerLinkedList = new SinglyLinkedList<>(integerArrayList);

        assertEquals("apple", stringLinkedList.getFirst());
        assertEquals(1, integerLinkedList.getFirst());
    }

    @org.junit.jupiter.api.Test
    void get() {
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList("apple", "banana", "cucumber", "dates"));
        SinglyLinkedList<String> stringLinkedList = new SinglyLinkedList<>(stringArrayList);

        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        SinglyLinkedList<Integer> integerLinkedList = new SinglyLinkedList<>(integerArrayList);

        assertEquals("apple", stringLinkedList.get(0));
        assertEquals("dates", stringLinkedList.get(3));
        assertEquals("cucumber", stringLinkedList.get(2));
        assertEquals(1, integerLinkedList.get(0));
        assertEquals(6, integerLinkedList.get(5));
        assertEquals(4, integerLinkedList.get(3));
    }

    @org.junit.jupiter.api.Test
    void deleteFirst() {
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList("apple", "banana", "cucumber", "dates"));
        SinglyLinkedList<String> stringLinkedList = new SinglyLinkedList<>(stringArrayList);

        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        SinglyLinkedList<Integer> integerLinkedList = new SinglyLinkedList<>(integerArrayList);

        stringLinkedList.deleteFirst();
        integerLinkedList.deleteFirst();
        assertEquals("banana", stringLinkedList.getFirst());
        assertEquals(2, integerLinkedList.getFirst());
    }

    @org.junit.jupiter.api.Test
    void delete() {
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList("apple", "banana", "cucumber", "dates"));
        SinglyLinkedList<String> stringLinkedList = new SinglyLinkedList<>(stringArrayList);

        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        SinglyLinkedList<Integer> integerLinkedList = new SinglyLinkedList<>(integerArrayList);

        assertEquals(stringLinkedList.get(0), stringLinkedList.delete(0));
        assertEquals(3, stringLinkedList.size());

        assertEquals(stringLinkedList.get(2), stringLinkedList.delete(2));
        assertEquals(2, stringLinkedList.size());

        assertEquals(integerLinkedList.get(2), integerLinkedList.delete(2));
        assertEquals(5, integerLinkedList.size());

        assertEquals(integerLinkedList.get(2), integerLinkedList.delete(2));
        assertEquals(4, integerLinkedList.size());
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        ArrayList<String> stringArrayList = new ArrayList<>(Arrays.asList("apple", "banana", "cucumber", "dates"));
        SinglyLinkedList<String> stringLinkedList = new SinglyLinkedList<>(stringArrayList);

        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        SinglyLinkedList<Integer> integerLinkedList = new SinglyLinkedList<>(integerArrayList);

        assertEquals(0, stringLinkedList.indexOf("apple"));
        assertEquals(2, stringLinkedList.indexOf("cucumber"));
        assertEquals(3, stringLinkedList.indexOf("dates"));
        assertEquals(-1, stringLinkedList.indexOf("kiwi"));


        assertEquals(0, integerLinkedList.indexOf(1));
        assertEquals(4, integerLinkedList.indexOf(5));
        assertEquals(5, integerLinkedList.indexOf(6));
        assertEquals(-1, integerLinkedList.indexOf(0));

    }

    @org.junit.jupiter.api.Test
    void size() {
        //tested elsewhere
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        ArrayList<String> emptyArrayList = new ArrayList<>();
        SinglyLinkedList<String> emptyLinkedList = new SinglyLinkedList<>(emptyArrayList);

        assertTrue(emptyLinkedList.isEmpty());

        ArrayList<String> oneArrayList = new ArrayList<>();
        oneArrayList.add("one");
        SinglyLinkedList<String> oneLinkedList = new SinglyLinkedList<>(oneArrayList);

        assertFalse(oneLinkedList.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void clear() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertFalse(stack.isEmpty());
        assertEquals(3, stack.size());

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @org.junit.jupiter.api.Test
    void toArray() {
        ArrayList<Integer> oneArrayList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        SinglyLinkedList<Integer> oneLinkedList = new SinglyLinkedList<>(oneArrayList);

        Object[] toArrayResult = oneLinkedList.toArray();
        Object[] expectedResult = {1, 3, 5, 7, 9};

        for (int i = 0; i < toArrayResult.length; i++) {
            assertEquals(expectedResult[i], toArrayResult[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void iterator() {
    }
    //=======================START OF ITERATOR TESTS============================//

    @Test
    public void testNextAndHasNext() {
        //Test next and hasNext by making an array, adding it to the BinarySearchSet, then comparing at same index
        var arrayList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));

        SinglyLinkedList<Integer> hasNextTest = new SinglyLinkedList<>(arrayList);

        var iterator = hasNextTest.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), hasNextTest.get(0));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), hasNextTest.get(1));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), hasNextTest.get(2));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), hasNextTest.get(3));

        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), hasNextTest.get(4));

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemoveIterator() {
        // Tests the 'remove' method of the iterator, unable to remove at the "0" index w/o calling next() first
        SinglyLinkedList<String> testRemoveIterator = new SinglyLinkedList<>();

        testRemoveIterator.insert(0, "red");
        testRemoveIterator.insert(1, "orange");
        testRemoveIterator.insert(2, "yellow");
        testRemoveIterator.insert(3, "green");
        testRemoveIterator.insert(4, "blue");

        assertEquals(2, testRemoveIterator.indexOf("yellow"));

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
        System.out.println("NEW");
        for (int i = 0; i < testRemoveIterator.size(); i++) {
            System.out.println(testRemoveIterator.get(i));
        }
        assertEquals(-1, testRemoveIterator.indexOf("yellow"));
        assertEquals(4, testRemoveIterator.size());
    }

    @Test
    void iteratorRemove() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);

        Iterator<Integer> iterator = list.iterator();

        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println("Before next: " + list.get(i));
        }

        iterator.next();
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println("After next: " + list.get(i));
        }
        iterator.hasNext();
        iterator.remove();

        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println("After remove: " + list.get(i));
        }
        assertEquals(3, list.size());
        assertEquals(2, list.get(1));
    }

}