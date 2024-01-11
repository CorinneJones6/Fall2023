package lab06;

import java.util.TreeSet;

public class TreeSetPQueue<T extends Comparable<? super T>> implements PQueueInterface<T> {
    TreeSet<T> data_;

    @Override
    public void add(T element) {
        data_.add(element);
    }

    @Override
    public T removeMin() {
        return data_.pollFirst();
    }

    @Override
    public boolean isEmpty() {
        return data_.isEmpty();
    }
}
