package assignment05;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {

    @Test
    void clear() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.clear();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(NoSuchElementException.class, stack::peek);
    }

    @Test
    void isEmpty() {
        LinkedListStack<String> stack = new LinkedListStack<>();
        assertTrue(stack.isEmpty());

        stack.push("Hello");
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void peek() {
        LinkedListStack<Character> stack = new LinkedListStack<>();
        stack.push('a');
        stack.push('b');
        stack.push('c');

        assertEquals('c', stack.peek());
        assertEquals(3, stack.size());
    }

    @Test
    void pop() {
        LinkedListStack<Double> stack = new LinkedListStack<>();
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);

        assertEquals(3.0, stack.pop());
        assertEquals(2.0, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void push() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.peek());
        assertEquals(3, stack.size());
    }

    @Test
    void size() {
        LinkedListStack<String> stack = new LinkedListStack<>();
        assertEquals(0, stack.size());

        stack.push("apple");
        stack.push("banana");
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());
    }
}