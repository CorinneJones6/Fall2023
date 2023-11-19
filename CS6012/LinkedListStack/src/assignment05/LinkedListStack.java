package assignment05;

import java.util.NoSuchElementException;

public class LinkedListStack <E> implements Stack <E>{
    private SinglyLinkedList<E> list_;
    private int size_;

    public LinkedListStack() {
        list_ = new SinglyLinkedList<>();
        size_ = 0;
    }

    /**
     *
     */
    @Override
    public void clear() {
        list_.clear();
        size_=0;

    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return list_.isEmpty();
    }

    /**
     *
     * @return - the value of the first/top element
     * @throws NoSuchElementException - if there is no head node
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return list_.getFirst();
    }

    /**
     *
     * @return - the value of the removed element, i.e., the first/top element
     * @throws NoSuchElementException - if there is no head node
     */
    @Override
    public E pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        size_--;
        return list_.deleteFirst();
    }

    /**
     *
     * @param element - the element to be added to the first/top
     */
    @Override
    public void push(E element) {
        list_.insertFirst(element);
        size_++;
    }

    /**
     *
     * @return - the LinkedListStack size
     */
    @Override
    public int size() {
        return size_;
    }
}
