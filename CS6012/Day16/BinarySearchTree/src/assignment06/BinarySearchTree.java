package assignment06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
    Node root_;
    int size_;

    public class Node {
        T data_;
        Node left_, right_;

        /**
         * Node constructor
         *
         * @param elem - makes the node value the elem if specified
         */
        public Node(T elem) {
            data_ = elem;
            right_ = null;
            left_ = null;
        }
    }

    /**
     * BST empty constructor
     */
    public BinarySearchTree() {
        root_ = null;
        size_ = 0;
    }

    /**
     * Constructor for testing
     *
     * @param list - to convert ArrayList to BST
     */
    public BinarySearchTree(ArrayList<T> list) {
        if (list == null) {
            throw new NullPointerException("ArrayList cannot be null");
        }

        for (T item : list) {
            add(item);
        }
    }

    /**
     * Calls the addRecursive method
     *
     * @param item - the item whose presence is ensured in this set
     * @return if the item was/wasn't added
     * @throws NullPointerException - if the item is null
     */
    @Override
    public boolean add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }

        if (contains(item)) {
            return false;
        }

        if (isEmpty()) {
            root_ = new Node(item);
            size_++;
            return true;
        }

        return addRecursive(root_, item);
    }

    /**
     * Recursively iterates through the BST
     *
     * @param current - this is the current node being examined
     * @param item    - this is the item being added to the tree
     * @return - true or false depending on if item is added
     */
    public boolean addRecursive(Node current, T item) {
        if (item.compareTo(current.data_) < 0) {
            if (current.left_ == null) {
                current.left_ = new Node(item);
                size_++;
                return true;
            } else {
                return addRecursive(current.left_, item);
            }
        } else if (item.compareTo(current.data_) > 0) {
            if (current.right_ == null) {
                current.right_ = new Node(item);
                size_++;
                return true;
            } else {
                return addRecursive(current.right_, item);
            }
        } else {
            return false;
        }
    }

    /**
     * @param items - the collection of items whose presence is ensured in this set
     * @return - if the size has changed by an addition to the BST
     * @throws NullPointerException - if the item is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) throws NullPointerException {
        int oldSize = size();

        for (T item : items) {
            if (item == null) {
                throw new NullPointerException("Item cannot be null");
            }
            add(item);
        }

        int newSize = size();

        return newSize > oldSize;
    }

    @Override
    public void clear() {
        root_ = null;
        size_ = 0;
    }

    /**
     * calls the containsRecursive method
     *
     * @param item - the item sought in this set
     * @return - true/false based on if the item exists
     * @throws NullPointerException - if the item is null
     */
    @Override
    public boolean contains(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }
        return containsRecursive(root_, item);
    }

    /**
     * @param current - the current node being called/evaluated
     * @param item    - the item being compared against
     * @return - true/false depending on if the item is present, passed up to contains
     */
    private boolean containsRecursive(Node current, T item) {
        if (current == null) {
            return false;
        }
        if (item.compareTo(current.data_) == 0) {
            return true;
        } else if (item.compareTo(current.data_) < 0) {
            return containsRecursive(current.left_, item);
        } else {
            return containsRecursive(current.right_, item);
        }
    }

    /**
     * @param items - the collection of items sought in this set
     * @return - boolean true if any of the items are present
     */

    @Override
    public boolean containsAll(Collection<? extends T> items) throws NullPointerException {
        for (T item : items) {
            if (item == null) {
                throw new NullPointerException("Item cannot be null");
            }
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return - the root data
     * @throws NoSuchElementException - if the root is null
     */
    @Override
    public T first() throws NoSuchElementException {
        if (size() == 0) {
            throw new NoSuchElementException("No first item");
        }

        return findMin(root_).data_;
    }

    /**
     * Helper function, goes down farthest left, is the "min"
     *
     * @param node - the node to begin traversing
     * @return - the minimum node
     */

    private Node findMin(Node node) {
        while (node.left_ != null) {
            node = node.left_;
        }
        return node;
    }

    /**
     * @return - boolean if the size is 0
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return - the "largest" items data
     * @throws NoSuchElementException - if is empty, returns no item
     */
    @Override
    public T last() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("No first item");
        }

        return findMax(root_).data_;
    }

    /**
     * helper function for last
     *
     * @param node - the root node to traverse
     * @return - the right most node
     */
    private Node findMax(Node node) {
        while (node.right_ != null) {
            node = node.right_;
        }
        return node;
    }

    /**
     * @param item - the item whose absence is ensured in this set
     * @return - boolean true/false if the size get smaller
     * @throws NullPointerException - if the item is null
     */
    @Override
    public boolean remove(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException("Cannot remove a null item");
        }

        if (isEmpty()) {
            return false;
        }
        int oldSize = size();

        root_ = removeRecursive(root_, item);

        int newSize = size();

        return newSize < oldSize;
    }

    /**
     * @param current - current node being compared
     * @param item    - item the current node is being compared to
     * @return - current node
     */
    private Node removeRecursive(Node current, T item) {
        if (current == null) {
            return null;
        }

        if (item.compareTo(current.data_) == 0) {

            if (current.left_ == null && current.right_ == null) {
                size_--;
                return null;
            }
            if (current.right_ == null) {
                size_--;
                return current.left_;
            }
            if (current.left_ == null) {
                size_--;
                return current.right_;
            }
            T smallestValue = findSmallestValue(current.right_);
            current.data_ = smallestValue;
            current.right_ = removeRecursive(current.right_, smallestValue);
            return current;
        }

        if (item.compareTo(current.data_) < 0) {
            current.left_ = removeRecursive(current.left_, item);
            return current;
        }

        current.right_ = removeRecursive(current.right_, item);
        return current;
    }

    /**
     * Helper method in the third case scenario of removeRecursive where the node has two children
     *
     * @param node - node that has the two children
     * @return - the smallest of the two
     */
    private T findSmallestValue(Node node) {
        return node.left_ == null ? node.data_ : findSmallestValue(node.left_);
    }

    /**
     * @param items - the collection of items whose absence is ensured in this set
     * @return - boolean true if the new size is smaller than the old size
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) throws NullPointerException {
        int oldSize = size();

        for (T item : items) {
            if (item == null) {
                throw new NullPointerException("Item cannot be null");
            }
            remove(item);
        }

        int newSize = size();

        return newSize < oldSize;
    }

    /**
     * @return - the member variable size_
     */
    @Override
    public int size() {
        return size_;
    }

    /**
     * @return - the BST as an array list
     */
    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        inOrderTraversal(root_, arrayList);
        return arrayList;
    }

    /**
     * helper method for toArrayList
     *
     * @param current - the current node to traverse from
     * @param list    - the array list to add too
     */
    private void inOrderTraversal(Node current, ArrayList<T> list) {
        if (current != null) {
            inOrderTraversal(current.left_, list);
            list.add(current.data_);
            inOrderTraversal(current.right_, list);
        }
    }
}
