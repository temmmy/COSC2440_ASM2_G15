package main.dataStructure;

import main.model.BoundingRectangle;
import main.model.Place;
import main.model.Node;

public abstract class QuadTree<T> {
    protected Node root;


    public abstract int size();
    public abstract boolean isFull();
    public abstract boolean isEmpty();
    public abstract boolean insert(T dataToInsert);
    public abstract boolean remove(T dataToRemove);
    public abstract ArrayList<Place> search(BoundingRectangle box, T partialData);

    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            display(root, 0);
        }
    }

    private void display(Node node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("Level " + i + ": ");
        }

        if (node.isLeaf()) {
            System.out.println("Leaf:");
            for (int i = 0; i < node.getData().size(); i++) {
                Place dataPoint = node.getData().get(i);
                System.out.println("  Data point = " + dataPoint);
            }

        } else {
            System.out.println("Node:" + node.getBoundary());
            for (int i = 0; i < node.getChildren().size(); i++) {
                Node child = node.getChildren().get(i);
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

    private void displayData(Node node) {
        if (node == null) return;

        // Check each child node for data if the current node is not a leaf
        if (!node.isLeaf()) {
            for (int i = 0; i < node.getChildren().size(); i++) {
                Node child = node.getChildren().get(i);
                displayData(child);
            }
        } else {
            // This is a leaf node, display all non-null data points
            for (int i = 0; i < node.getData().size(); i++) {
                Place dataPoint = node.getData().get(i);
                if (dataPoint != null) {
                    System.out.println(dataPoint);
                }
            }
        }
    }
}
