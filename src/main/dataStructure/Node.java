package main.dataStructure;

import main.model.Place;

public class Node<T extends Place> {
    private static final int MAX_CAPACITY = 4;
    private Rectangle boundary;
    private T[] data;
    private Node<T>[] children;

    @SuppressWarnings("unchecked")
    public Node(Rectangle boundary) {
        this.boundary = boundary;
        this.data = (T[]) new Place[MAX_CAPACITY];
        this.children = new Node[MAX_CAPACITY];
    }

    public Rectangle getBoundary() {
        return boundary;
    }

    public T[] getData() {
        return data;
    }

    public Node<T>[] getChildren() {
        return children;
    }

    public void setBoundary(Rectangle boundary) {
        this.boundary = boundary;
    }

    public void setData(T[] data) {
        this.data = data;
    }

    public void setChildren(Node<T>[] children) {
        this.children = children;
    }

    public void clear() {
        this.boundary = null;
        this.children = null;
        this.data = null;
    }

    public boolean isLeaf() {
        return children[0] == null;
    }

    public boolean isEmpty() {
        return data[0] == null;
    }

    public boolean contains(T dataPoint) {
        return boundary.contains(dataPoint);
    }

    public boolean insert(T dataToInsert) {
        if (!contains(dataToInsert)) {
            System.out.println("Data not in boundary.");
            return false;
        }

        if (isLeaf()) {
            for (int i = 0; i < MAX_CAPACITY; i++) {
                if (data[i] == null) {
                    data[i] = dataToInsert;
                    return true;
                }
                if (data[i].equals(dataToInsert)) {
                    System.out.println("Duplicate data: " + data[i]);
                    return false;
                }
            }
            split();
            distributeData();
            return insert(dataToInsert);
        } else {
            for (Node<T> child : children) {
                if (child.contains(dataToInsert) && child.insert(dataToInsert)){
                    return true;
                }
            }
            System.out.println("No child found for insertion.");
            return false;
        }

    }

    public void distributeData() {
        if (isLeaf()) {
            System.out.println("Node is a leaf and cannot distribute data/");
            return;
        }

        for (T dataPoint : data) {
            if (dataPoint == null) break;
            boolean inserted = false;
            for (Node<T> child : children) {
                if (child.contains(dataPoint)) {
                    child.insert(dataPoint);
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                System.out.println("Failed to insert " + dataPoint);
            }
        }

        for (int i = 0; i < data.length; i++){
            data[i] = null;
        }
    }
    public void split() {
        if (!isLeaf()) {
            return; // Do not split if not a leaf node
        }

        int subWidth = boundary.getWidth() / 2;
        int subHeight = boundary.getHeight() / 2;
        int x = boundary.getTopLeftX();
        int y = boundary.getTopLeftY();

        Rectangle r0 = new Rectangle(x, y, subWidth, subHeight);
        Rectangle r1 = new Rectangle(x + subWidth, y, subWidth, subHeight);
        Rectangle r2 = new Rectangle(x, y + subHeight, subWidth, subHeight);
        Rectangle r3 = new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight);

        children[0] = new Node<>(r0);
        children[1] = new Node<>(r1);
        children[2] = new Node<>(r2);
        children[3] = new Node<>(r3);

    }

    public boolean remove(T dataToRemove) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && data[i].equals(dataToRemove)) {
                data[i] = null;
                return true;
            }
        }
        return false;
    }

    public void removeChild() {
        for (int i = 0; i < children.length; i++) {
            children[i] = null;
        }
    }

}
