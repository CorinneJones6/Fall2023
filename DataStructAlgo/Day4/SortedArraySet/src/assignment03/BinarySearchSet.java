package assignment03;

import java.util.*;

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

    private E[] set_;
    private int capacity_;
    private int size_;
    private Comparator<? super E> comparator_;

    public BinarySearchSet() {
        capacity_ = 10;
        set_ = (E[]) new Object[capacity_];
        size_ = 0;
        comparator_ = null;
    }

    public BinarySearchSet(Comparator<? super E> comparator) {
        comparator_ = comparator;
        capacity_ = 10;
        set_ = (E[]) new Object[capacity_];
        size_ = 0;
    }

    @Override
    public Comparator<? super E> comparator() {
        return comparator_;
    }

    @Override
    public E first() throws NoSuchElementException {

        if (isEmpty()) {
            // If empty, throw a NoSuchElementException
            throw new NoSuchElementException("Set is empty");
        }
        // If not empty, return the element at index 0 in the set
        return set_[0];
    }

    @Override
    public E last() throws NoSuchElementException {

        if (isEmpty()) {
            // If empty, throw a NoSuchElementException
            throw new NoSuchElementException("Set is empty");
        }
        // If not empty, return the last element in the set (at index size() - 1)
        return set_[size() - 1];
    }

    @Override
    public boolean add(E element) {
        //don't allow null items to be added
        if (element == null) {
            throw new IllegalArgumentException("Can't add null element");
        }

        // Find the insertion point using binary search
        int insertionPoint = binarySearch(element);

        // If the element is already present in the set, it will return positive, return false
        if (insertionPoint >= 0) {
            return false;
        }
        // If the insertion point is negative, this is a flag for the index, convert it to a positive
        insertionPoint = -(insertionPoint + 1);

        // If the current size exceeds the capacity, increase the capacity of the set
        if (size_ >= capacity_) {
            growCapacity();
        }

        // Shift elements to make space for the new element at the insertion point
        System.arraycopy(set_, insertionPoint, set_, insertionPoint + 1, size_ - insertionPoint);

        // Insert the new element at the calculated insertion point
        set_[insertionPoint] = element;

        // Increment the size of the set
        size_++;

        return true; //Return element was added
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        // Store the original size of the set
        int origSize = size();

        for (E obj : elements) {
            // add() checks for duplicates, won't add if it already exists
            add(obj);
        }

        int finalSize = size();

        //Will return "true" if elements added, "false" if not based on comparing sizes
        return finalSize > origSize;
    }

    @Override
    public void clear() {
        // Set the size of the set to 0
        size_ = 0;
    }

    @Override
    public boolean contains(E element) {
        // If the element is not null, use binary search to check for its presence in the set
        return binarySearch(element) >= 0;
    }

    @Override
    public boolean containsAll(Collection<? extends E> elements) {

        for (E obj : elements) {
            if (!contains(obj)) {
                // If current element not present, return false indicating that it's not in the set
                return false;
            }
        }
        // If all elements are found in the set, return true
        return true;
    }

    @Override
    public boolean isEmpty() {
        // Check if the size of the set is 0
        return size_ == 0;
    }

    @Override
    public Iterator iterator() {
        // Create and return a new instance of the custom iterator (MyIterator) for this set
        return new MyIterator();
    }

    @Override
    public boolean remove(E element) {
        // Use binary search to find the index of the element in the set
        int index = binarySearch(element);

        if (index >= 0) {
            // If the element is found in the set
            // Shift elements to fill the gap left by the removed element
            System.arraycopy(set_, index + 1, set_, index, size_ - index - 1);
            // Clear the last element to avoid duplicates
            set_[--size_] = null;
            // Return true to indicate successful removal
            return true;
        }
        // If the element is not found in the set, return false
        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        int origSize = size();

        for (E obj : elements) {
            // Remove the current element from the set using the remove method
            remove(obj);
        }
        int finalSize = size();

        // Returns true if the set has been changed
        return finalSize < origSize;
    }

    @Override
    public int size() {
        // Return the current size of the set
        return size_;
    }

    @Override
    public E[] toArray() {
        // Create a new array to avoid modifying the internal set_
        E[] arrayCopy = Arrays.copyOf(set_, size_);

        // Return the copied array
        return arrayCopy;
    }

    // Helper function to increase the capacity of the set's internal array
    public void growCapacity() {

        capacity_ = 2 * capacity_;

        // Create a new array with the updated capacity and copy the original array contents into it
        set_ = Arrays.copyOf(set_, capacity_);
    }

    // Helper function to perform binary search on the set
    private int binarySearch(E element) {
        // Initialize low and high indices for the binary search
        int left = 0;
        int right = size_ - 1;

        // Perform binary search until low index is less than or equal to high index
        while (left <= right) {
            // Calculate the middle index
            int mid = (left + right) / 2;
            int cmp;
            // Compare elements using either the provided comparator or natural ordering
            if (comparator_ != null) {
                // Use the provided comparator for comparison
                cmp = comparator_.compare(set_[mid], element);
            } else {
                try {
                    //Use natural ordering for comparison
                    Comparable<? super E> midVal = (Comparable<? super E>) set_[mid];
                    cmp = midVal.compareTo(element);
                } catch (ClassCastException e) {
                    //If the natural ordering doesn't work
                    throw new ClassCastException(e.getMessage());
                }
            }

            // Adjust low and high indices based on the comparison result
            if (cmp < 0) {
                left = mid + 1;
            } else if (cmp > 0) {
                right = mid - 1;
            } else {
                return mid; // Element found, returns a positive if present
            }
        }

        return -(left + 1); // Element not found, return the insertion point, add a +1 to account for first element creation
    }

    //Returns an element from the set (for testing)
    public E get(int i) {
        if (i < 0 || i >= size_) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for BinarySearchSet");
        }
        return set_[i];
    }

    class MyIterator implements Iterator<E> {

        private int position; // Position variable to keep track of the iterators current position
        boolean canRemove=false;

        @Override
        public boolean hasNext() {

            // This indicates there is another element in the iteration
            return position < size_;
        }

        @Override
        public E next() {

            // If not another element, throw an exception
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate over");
            }

            var val=set_[position];
            position++;
            canRemove=true;
            // Get the element at the current position, then increase the position
            return val;
        }

        @Override
        public void remove() {
            // Remove the last element retrieved by the iterator
            if (!canRemove) {
                throw new IllegalStateException("No more elements to iterate over");
            }
            // Get the element at the current position minus one, since next automatically increments
            E obj = get(position - 1);
            position--;
            size_--;
            canRemove=false;
            for (int i=position; i<size_; i++){
                set_[i]=set_[i+1];
            }
        }

    }

}
