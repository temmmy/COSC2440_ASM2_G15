package main.dataStructure;

import java.util.Arrays;
import java.util.Objects;

public class ArrayList<T> {
    private T[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        arr = (T[]) new Object[capacity];
        size = 0;
    }

    public ArrayList(T[] arr) {
        this.arr = arr;
        size = arr.length;
    }

    public boolean add(T element) {
        if (size >= arr.length) {
            try {
                arr = Arrays.copyOf(arr, arr.length * 2);
            } catch (OutOfMemoryError e) {
                return false;
            }
        }
        arr[size++] = element;
        return true;
    }

    public boolean add(int index, T element) {
        if (index > size || index < 0) {
            return false;
        }

        if (index == size) return add(element);

        arr[index] = element;
        return true;
    }

    public boolean remove(T element) {
        if (size == 0) return false;

        boolean removed = false;
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(element)) {
                removed = true;
            }
            if (removed && i < size - 1)
                arr[i] = arr[i+1]; // Shift elements to the left
        }
        if (removed) size--;
        return removed;
    }

    public T pop(int index) {
        if (index > size || index < 0) {
            return null;
        }
         if (!remove(arr[index])) return null;
         return arr[index];
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T[] elements) {
        for (T element : elements) {
            if (!contains(element)) {
                return false; // Exit the method early if any element is not found
            }
        }
        return true; // All elements are found in the list
    }

    public boolean contains(ArrayList<T> elements){
        if (isEmpty() || elements.isEmpty()) return false;
        for (int i = 0; i < elements.size(); i++) {
            T element = elements.get(i);
            if (!contains(element)) {
                return false; // Exit the method early if any element is not found
            }
        }
        return true; // All elements are found in the list
    }


    public int size() { return size; }
    public T[] toArray() {
        return arr;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T element) {
        arr[index] = element;
    }

    public void clear() {
        if (size == 0) return;
        for (int i = 0; i < size; i++){
            arr[i] = null;
        }
        size = 0;
    }

    public void fill(T element) {
        for (int i = 0; i < size; i++) {
            arr[i] = element;
        }
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.println(i+1 + ". " + arr[i]);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayList<?> arrayList = (ArrayList<?>) o;
        return size == arrayList.size && Objects.deepEquals(arr, arrayList.arr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(arr), size);
    }
}
