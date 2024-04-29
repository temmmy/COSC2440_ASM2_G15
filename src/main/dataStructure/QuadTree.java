package main.dataStructure;

import main.model.Place;

public class QuadTree<T extends Place> {
    private static final int MAX_CAPACITY = 4;
    Node<T> root;
    private int count;

    public QuadTree() {

        this.root = null;
        count = 0;
    }

    public QuadTree(Rectangle boundary) {

        this.root = new Node<>(boundary);
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public Node<T> getRoot() {
        return root;
    }

    public boolean insert(T dataToInsert) {
        if (root == null) {
            System.out.println("Root is not set.");
            return false;
        }
        return insert(root, dataToInsert);
    }

    private boolean insert(Node<T> node, T dataToInsert) {
//        if (node == null) {
//            return false;
//        }

        if (!node.contains(dataToInsert)) {
            return false;
        }

        if (node.isLeaf()) {
                if (node.getData()[MAX_CAPACITY - 1] != null) {
                    // Split the leaf node if it's full
                    node.split();
                    // Recursively try to insert into children after splitting
                    node.distributeData();
                    return insert(node, dataToInsert);
                } else {
                    node.insert(dataToInsert);
                    count++;
                    return true;
            }
        } else {
            // If not a leaf, continue searching for appropriate leaf node
            for (Node<T> child : node.getChildren()) {
                if (insert(child, dataToInsert)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean remove(T dataToRemove) {
        return remove(null, root, dataToRemove);
    }

    private boolean remove(Node<T> parent, Node<T> node, T dataToRemove) {
        if (node == null) return false;

        if (node.contains(dataToRemove)) {
            if (node.isLeaf()) {
                boolean removed = node.remove(dataToRemove);
                if (removed) {
                    if (parent != null) {
                        parent.removeChild();
                    }
                }
                return removed;
            } else {
                for (Node<T> child : node.getChildren()) {
                    if (remove(node, child, dataToRemove)) {
                        count--;
                        return true; // Element found and remove in child node
                    }
                }
            }
        }

        return false;
    }

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            display(root, 0);
        }
    }

    private void display(Node<T> node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("Level " + i + ": ");
        }

        if (node.isLeaf()) {
            System.out.println("Leaf:");
            for (T dataPoint : node.getData()) {
                    System.out.println("  Data point = " + dataPoint);
            }

        } else {
            System.out.println("Node:" + node.getBoundary());
            for (Node<T> child : node.getChildren()) {
                display(child, level + 1);
            }
        }
    }

    public void displayData() {
        if (root == null) {
            System.out.println("Root is not set.");
        } else {
            displayData(root);
        }
    }

    private void displayData(Node<T> node) {
        if (node == null) return;

        // Check each child node for data if the current node is not a leaf
        if (!node.isLeaf()) {
            for (Node<T> child : node.getChildren()) {
                displayData(child);
            }
        } else {
            // This is a leaf node, display all non-null data points
            for (T dataPoint : node.getData()) {
                if (dataPoint != null) {
                    System.out.println(dataPoint);
                }
            }
        }
    }

}
