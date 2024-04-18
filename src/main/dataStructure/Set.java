package main.dataStructure;


public class Set<E> implements Collection<E> {
    E[] array;
    int size;

    public Set() {
        this.size = 0;
    }

    public Set(E[] a) {
        this.array = a;
        this.size = a.length;
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            ensureCapacity(size + 1);
            array[size++] = e;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                removeAtIndex(i);
                return true;
            }
        }
        return false;
    }

    private void removeAtIndex(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object e : c) {
            if (remove(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public int size() {
        return size;
    }

    // Other methods from Collection interface can be implemented similarly.

    // Private method to ensure capacity of array
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int oldCapacity = array.length;
            int newCapacity = oldCapacity * 2;
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            E[] newArray = (E[]) new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
