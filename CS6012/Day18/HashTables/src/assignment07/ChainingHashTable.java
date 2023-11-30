package assignment07;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {
    private LinkedList<String>[] storage_;

    private int size_;

    private int capacity_;

    private final HashFunctor functor_;

    /**
     * @param capacity - the size of the created array
     * @param functor  - to use with hashing
     */
    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor) {
        storage_ = (LinkedList<String>[]) new LinkedList[capacity];
        functor_ = functor;
        capacity_ = capacity;
        size_ = 0;
    }

    /**
     * @param item - the item whose presence is ensured in this set
     * @return - true/false if the item was added/not added
     */
    @Override
    public boolean add(String item) {
        if (contains(item)) {
            return false;
        }

        int hashCode = functor_.hash(item);
        int index = Math.abs(hashCode) % storage_.length;

        if (storage_[index] == null) {
            storage_[index] = new LinkedList<>();
        }

        storage_[index].add(item);
        size_++;

        if ((size_ / storage_.length) >= 2) {
            growCapacity();
        }
        return true;
    }

    /**
     * Helper method that grows the Capacity when the load factor gets too large
     */
    private void growCapacity() {
        capacity_ = 2 * capacity_;
        LinkedList<String>[] newStorage = (LinkedList<String>[]) new LinkedList[capacity_];

        for (LinkedList<String> list : storage_) {
            if (list != null) {
                for (String item : list) {
                    int index = Math.abs(functor_.hash(item)) % capacity_;
                    if (newStorage[index] == null) {
                        newStorage[index] = new LinkedList<>();
                    }
                    newStorage[index].add(item);
                }
            }
        }
        storage_ = newStorage;
    }

    /**
     * @param items - the collection of items whose presence is ensured in this set
     * @return - true if the size increases
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        int origSize = size();
        for (String item : items) {
            add(item);
        }
        int oldSize = size();

        return oldSize > origSize;
    }

    /**
     *
     */
    @Override
    public void clear() {

        Arrays.fill(storage_, null);

        size_ = 0;
    }

    /**
     * @param item - the item sought in this set
     * @return - true/false based on if the item exists
     */
    @Override
    public boolean contains(String item) {
        int hashCode = functor_.hash(item);
        int index = Math.abs(hashCode) % storage_.length;

        if (storage_[index] != null) {
            for (String s : storage_[index]) {
                if (s.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param items - the collection of items sought in this set
     * @return - true if all the items are in the list, false if not
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for (String s : items) {
            if (!contains(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return - true/false if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param item - the item whose absence is ensured in this set
     * @return - true/false if the item is removed
     */
    @Override
    public boolean remove(String item) {
        int hashCode = functor_.hash(item);
        int index = Math.abs(hashCode) % storage_.length;

        if (storage_[index] != null) {
            LinkedList<String> list = storage_[index];
            for (String s : list) {
                if (s.equals(item)) {
                    list.remove(s);
                    size_--;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param items - the collection of items whose absence is ensured in this set
     * @return - true if the new size is smaller than the original size
     */
    @Override
    public boolean removeAll(Collection<? extends String> items) {
        int origSize = size();
        for (String s : items) {
            remove(s);
        }
        int newSize = size();
        return newSize < origSize;
    }

    /**
     * @return - the member variable size
     */
    @Override
    public int size() {
        return size_;
    }

    /**
     * helper method used in testing
     *
     * @return - capacity
     */
    public int getCapacity() {
        return capacity_;
    }

    public LinkedList<String>[] getStorage_() {
        return storage_;
    }
}
