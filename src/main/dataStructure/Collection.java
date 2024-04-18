package main.dataStructure;

public interface Collection<E> {
    boolean add(E e);
    boolean addAll(Collection<? extends E> c);
    void clear();
    boolean contains(Object o);
    boolean containsAll(Collection<?> c);
    boolean isEmpty();
    boolean remove(Object o);
    boolean removeAll(Collection<?> c);
    int size();
}
