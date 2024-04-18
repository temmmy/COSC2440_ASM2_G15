package main.dataStructure;

public class ArrayList<T> implements Collection<T> {
    T[] array;
    int size;

    public ArrayList() {
        this.size = 0;
    }


    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
