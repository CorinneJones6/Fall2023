package assignment03;

import java.net.Inet4Address;
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
        // Check if the set is empty
        if (isEmpty()) {
            // If empty, throw a NoSuchElementException
            throw new NoSuchElementException("Set is empty");
        }
        // If not empty, return the element at index 0 in the set
        return set_[0];
    }

    @Override
    public E last() throws NoSuchElementException {
        // Check if the set is empty
        if (isEmpty()) {
            // If empty, throw a NoSuchElementException
            throw new NoSuchElementException("Set is empty");
        }
        // If not empty, return the last element in the set (at index size() - 1)
        return set_[size() - 1];
    }

    @Override
    public boolean add(E element) {

        if(element==null){
            throw new IllegalArgumentException("Can't add null element");
        }

        // Find the insertion point using binary search
        int insertionPoint = binarySearch(element);

        // If the element is not already present in the set
        if (insertionPoint >= 0) {
            return false;
        }
        // If the insertion point is negative, convert it to a positive index
        insertionPoint = -(insertionPoint + 1);

        // If the current size exceeds the capacity, increase the capacity of the set
        if (size_ >= capacity_) {
            growArray();
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
        // Iterate through the elements in the provided collection
        for (E obj : elements) {
            // Check if the set does not already contain the current element
            // MDJ TODO maybe remove this
            if (!contains(obj)) {
                // If not, add the element to the set
                add(obj);
            }
        }
        // Get the final size of the set after adding unique elements
        int finalSize = size();
        // Return true if the final size is greater than the original size, indicating that elements were added
        //Will return false if the final size is the same, indicating that elements weren't added
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
        // Iterate through each element in the provided collection
        for (E obj : elements) {
            // Check if the current element is not present in the set
            if (!contains(obj)) {
                // If not present, return false indicating that not all elements are contained in the set
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
        return new MyIterator(this);
    }

    class MyIterator implements Iterator<E> {
        //TODO why is this set grayed out but I can't delete it?
        private E[] set_; // Array to store the elements of the set

        private int position; // Position variable to keep track of the iterator's current position

        public MyIterator(BinarySearchSet<E> set) {
            // Initialize the array with the elements of the set
            set_ = set.getSet_();
        }

        @Override
        public boolean hasNext() {
            // Check if the iterators current position is less than the size of the set minus 1
            // This indicates there is another element in the iteration
            return position < size_ - 1;
        }

        @Override
        public E next() {
            // Get the element at the current position
            E obj = get(position);
            // Check if there is another element in the iteration
            if (hasNext()) {
                // If there is, move to the next position
                position++;
            }
            // Return the retrieved element
            return obj;
        }

        @Override
        public void remove() {
            // Remove the last element retrieved by the iterator
            // Get the element at the current position
            E obj = get(position);
            // Remove the element from the BinarySearchSet using the outer class's remove method
            BinarySearchSet.this.remove(obj);
        }

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
        // Iterate through each element in the provided collection
        for (E obj : elements) {
            // Remove the current element from the set using the remove method
            remove(obj);
        }
        // Check if any of the elements in the collection are still present in the set
        for (E obj : elements) {
            // If any element is still present, return false
            if (contains(obj)) {
                return false;
            }
        }
        // If all elements have been successfully removed, return true
        return true;
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
    public void growArray() {
        // Double the capacity of the set
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
                return mid; // Element found, returns a positive so we know it is present
            }
        }

        return -(left + 1); // Element not found, return the insertion point, add a +1 to account for first element creation
    }

    //Returns the set (for testing)
    public E[] getSet_() {
        return set_;
    }

    //Returns an element from the set (for testing)
    public E get(int i) {
        if (i < 0 || i >= size_) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for BinarySearchSet");
        }
        return set_[i];
    }

}
