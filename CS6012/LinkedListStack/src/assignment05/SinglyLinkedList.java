package assignment05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
    Node head_;
    Node tail_;
    int size_;

    public class Node {
        E value_;
        Node next_;

        /**
         * first Node constructor if no params given
         */
        private Node() {
        }

        /**
         * second Node constructor
         * @param elem - makes the node value the elem if specified
         */
        public Node(E elem) {
            value_ = elem;
        }
    }

    /**
     * first SinglyLinkedList constructor
     */
    public SinglyLinkedList() {
        head_ = null;
        size_ = 0;
    }

    /**
     * second SinglyLinkedList constructor
     * @param arr - takes in array, turns into SinglyLinkedList
     */
    public SinglyLinkedList(ArrayList<E> arr) {
        for (var x : arr) {

            if (head_ == null) {
                head_ = new Node(x);
                tail_ = head_;
            }
            else {
                tail_.next_ = new Node(x);
                tail_ = tail_.next_;
            }
        }
        size_ = arr.size();
    }

    /**
     *
     * @param element - the element to add in the first position of the SinglyLinkedList
     */
    @Override
    public void insertFirst(E element) {
        Node newNode = new Node();
        newNode.value_ = element;
        newNode.next_ = head_;
        head_ = newNode;
        size_++;
    }

    /**
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException - if attempt to insert out of bounds
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        //todo what if it is the last element?
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index is beyond list range");
        }

        if (index == 0) {
            insertFirst(element);
        } else {

            size_++;

            Node newNode = new Node();
            newNode.value_ = element;

            Node currentNode = head_;

            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next_;
            }

            newNode.next_ = currentNode.next_;
            currentNode.next_ = newNode;
        }

    }

    /**
     *
     * @return - the value of the head node
     * @throws NoSuchElementException - if there is no head node
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (head_ == null) {
            throw new NoSuchElementException("The list is empty");
        }
        return head_.value_;
    }

    /**
     *
     * @param index - the specified position
     * @return - the node value at the specified index
     * @throws IndexOutOfBoundsException - if attempt to insert out of bounds
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node currentNode = head_;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next_;
        }
        return currentNode.value_;
    }

    /**
     *
     * @return - the deleted node (first node) value
     * @throws NoSuchElementException - if there is no head node
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {

        if (head_ == null) {
            throw new NoSuchElementException("List is empty");
        }

        Node oldHead = head_;
        head_ = head_.next_;
        size_--;

        return oldHead.value_;
    }

    /**
     *
     * @param index - the specified position
     * @return - the deleted nodes value
     * @throws IndexOutOfBoundsException - if attempt to insert out of bounds
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        if (index == 0) {
            return deleteFirst();
        }

        Node currentNode = head_;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.next_;
        }

        Node oldNode = currentNode.next_;
        currentNode.next_ = oldNode.next_;

        size_--;

        return oldNode.value_;
    }

    /**
     *
     * @param element - the element to search for
     * @return - the index of a specified element, -1 if not found
     */
    @Override
    public int indexOf(E element) {
        int count = 0;
        Node currentNode = head_;

        while (currentNode != null) {
            if (currentNode.value_.equals(element)) {
                return count;
            }
            currentNode = currentNode.next_;
            count++;
        }

        return -1;
    }

    /**
     *
     * @return the int size member variable
     */
    @Override
    public int size() {
        return size_;
    }

    /**
     *
     * @return true/false if the boolean is empty
     */
    @Override
    public boolean isEmpty() {
        return size_ == 0;
    }

    /**
     * sets the head and tail to null and returns the size to 0
     */
    @Override
    public void clear() {
        head_ = null;
        tail_ = null;
        size_ = 0;
    }

    /**
     *
     * @return the SinglyLinkedList as an array
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size_];
        Node currentNode = head_;
        int index = 0;

        while (currentNode != null) {
            array[index] = currentNode.value_;
            currentNode = currentNode.next_;
            index++;
        }

        return array;
    }

    /**
     *
     * @return - the iterator implemented below
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<E> {

        Node currentNode = head_;
        boolean canDelete = false;

        /**
         *
         * @return - true/false depending on if there is another node
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         *
         * @return - the value of the next node
         */
        @Override
        public E next() {

            if (!hasNext()) {
                throw new NoSuchElementException("No more elements exist in the set");
            }

            E data = currentNode.value_;
            currentNode = currentNode.next_;
            canDelete = true;
            return data;
        }

        /**
         * Removes the "current" item
         */
        @Override
        public void remove() {

            if (!canDelete) {
                throw new IllegalStateException("No more elements to iterate over");
            }
            if (currentNode == head_) {
                head_ = head_.next_;
                currentNode = head_;
            }
            else {
                int currentIndex = indexOf(currentNode.value_) - 1;

                delete(currentIndex);

                canDelete = false;
            }
        }

    }
}
