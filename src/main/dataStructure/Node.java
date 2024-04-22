package main.dataStructure;

public class Node<T extends Point> {
    private static final int MAX_CAPACITY = 4;
    private Rectangle boundary;
    private T[] data;
    private Node<T>[] children;

    public Node(Rectangle boundary) {
        this.boundary = boundary;
        this.data = (T[]) new Point[MAX_CAPACITY];
        this.children = (Node<T>[]) new Node[MAX_CAPACITY];
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
            return false;
        }
        for (int i = 0; i < data.length; i++) {

            if (data[i] == null) {
                data[i] = dataToInsert;
                return true;
            }
            if (data[i].getLocation().equals(dataToInsert.getLocation())) return false;

        }
        return false;
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
        Rectangle r2 = new Rectangle(x, y - subHeight, subWidth, subHeight);
        Rectangle r3 = new Rectangle(x + subWidth, y - subHeight, subWidth, subHeight);

        children[0] = new Node<>(r0);
        children[1] = new Node<>(r1);
        children[2] = new Node<>(r2);
        children[3] = new Node<>(r3);

    }

}
