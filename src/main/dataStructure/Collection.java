package main.dataStructure;

public interface Collection<E> {
    void add(E e);
    boolean addAll(E[] c);
    void clear();
    boolean contains(E o);
    boolean containsAll(E[] c);
    boolean isEmpty();
    void remove(E o);
    boolean removeAll(E[] c);
    int size();
}
