package lab06;

public interface PQueueInterface <T extends Comparable<? super T>>{

    public void add(T element);

    public T removeMin();

    public boolean isEmpty();

}
