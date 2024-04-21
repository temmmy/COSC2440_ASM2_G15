package main.dataStructure;

public class QuadTree<T extends Point> {
    private static final int MAX_CAPACITY = 4;
    private Node<T> root;

    public QuadTree() {
        this.root = null;
    };
    public QuadTree(Rectangle boundary) {
        this.root = new Node<>(boundary);
    }

    public void insert(T dataPoint) {
        insert(root, dataPoint);
    }

    private boolean insert(Node<T> node, T dataPoint) {
        if (node == null) { return false; }

        if (!node.getBoundary().contains(dataPoint.getX(), dataPoint.getY())) {
            return false;
        }

        if (node.getData().size() < MAX_CAPACITY || node.isLeaf()) {
            node.insert(dataPoint);
            return true;
        } else {
            node.split();
            return insert(node, dataPoint);
        }
    }

    public T search(Rectangle range) {
        return search(root, range);
    }

    private T search(Node<T> node, Rectangle range) {

    }

    public boolean remove(T dataPoint) {
        return remove(root, dataPoint);
    }

    private boolean remove(Node<T> node, T dataPoint) {
        if (node == null) { return false; }


    }



}
