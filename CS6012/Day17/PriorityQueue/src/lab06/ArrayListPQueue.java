package lab06;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayListPQueue<T extends Comparable<? super T>> implements PQueueInterface<T> {

    ArrayList<T> data_;

    public ArrayListPQueue(ArrayList<T> elem) {
        data_ = elem;
        heapify();
    }

    @Override
    public void add(T element) {
        data_.add(element);
        percolateUp(data_.size() - 1);
    }

    public void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (data_.get(index).compareTo(data_.get(parentIndex)) < 0) {
                data_.set(index, data_.get(parentIndex));
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    @Override
    public T removeMin() {
        T ret = data_.get(0);
        data_.set(0, data_.get(data_.size() - 1));
        percolateDown(0);
        return ret;
    }

    public void percolateDown(int index) {
        while (index < data_.size()) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < data_.size() && data_.get(leftChild).compareTo(data_.get(smallest)) < 0) {
                smallest = leftChild;
            }

            if (rightChild < data_.size() && data_.get(rightChild).compareTo(data_.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {
                data_.set(index, data_.get(smallest));
                index = smallest;
            } else {
                break;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return data_.isEmpty();
    }

    void heapify() {
        for (int i = data_.size() / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }
}
