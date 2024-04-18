package main.dataStructure;

public class ArrayList<T> implements Collection<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;


    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(T[] a) {
        this.size = a.length;
        this.array = (T[]) new Object[size];
        addAll(a);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = Math.max(array.length * 2, minCapacity);
            T[] newArray =(T[]) new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }


    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public void add(T t) {
        ensureCapacity(size + 1);
        array[size++] = t;
    }

    @Override
    public boolean addAll(T[] c) {
        ensureCapacity(size + c.length);
        for (T element : c) {
            array[size++] = element;
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean contains(T o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o)) return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(T[] elements) {
        for (T e : elements){
            if (!contains(e)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void removeAtIndex(int index) {
        if (index >= 0 && index < size) {
            // Shift elements to the left to overwrite the element at the specified index
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            // Remove and return the decremented size
            array[--size] = null;
        }
    }

    @Override
    public void remove(T o) {
        for (int i = 0; i < size; i++){
            if (array[i].equals(o)){
                removeAtIndex(i);
                return;
            }
        }
    }

    @Override
    public boolean removeAll(T[] c) {
        boolean modified = false;
        ArrayList<T> newC = new ArrayList<>(c);
        for (int i = 0; i < size; i++) {
            if (newC.contains((T) array[i])) {
                removeAtIndex(i);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public int size() {
        return size;
    }
}
