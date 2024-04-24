package main.dataStructure;

public class QuadTree<T extends Point> {
    private static final int MAX_CAPACITY = 4;
    private Node<T> root;

    public QuadTree() {
        this.root = null;
    }

    public QuadTree(Rectangle boundary) {
        this.root = new Node<>(boundary);
    }

    public void insert(T dataToInsert) {
        insert(root, dataToInsert);
    }

    private boolean insert(Node<T> node, T dataToInsert) {
        if (node == null) {
            return false;
        }

        if (!node.contains(dataToInsert)) {
            return false;
        }

        if (node.isLeaf()) {
                if (node.getData()[MAX_CAPACITY - 1] != null) {
                    // Split the leaf node if it's full
                    node.split();
                    // Recursively try to insert into children after splitting
                    for (Node<T> child : node.getChildren()) {
                        if (insert(child, dataToInsert)) {
                            return true;
                        }
                    }
                } else {
                    node.insert(dataToInsert);
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


    public Node<T> dfs(T dataPoint) {
        return dfs(root, dataPoint);
    }

    private Node<T> dfs(Node<T> node, T dataPoint) {
        if (node == null) {
            return null;
        }

        if (node.contains(dataPoint)) {
            if (!node.isLeaf()) {
                for (Node<T> child : node.getChildren()) {
                    Node<T> result = dfs(child, dataPoint);
                    if (result != null) {
                        return result;
                    }
                }
            }

            for (T data : node.getData()) {
                if (data.equals(dataPoint)) {
                    return node;
                }
            }
        }
        return null;
    }

    public T[] searchElements(Rectangle boundingBox, int maxResults) {
        T[] results = (T[]) new Object[maxResults];
        searchElements(root, boundingBox, maxResults, results, 0);

        return results;
    }

    private int searchElements(Node<T> node, Rectangle boundingBox, int maxResults, T[] results, int index) {
        if (node == null || index >= maxResults) {
            return index;
        }

        if (boundingBox.intersects(node.getBoundary())) {
            if (node.isLeaf() && !node.isEmpty()) {
                for (T dataPoint : node.getData()) {
                    if (boundingBox.contains(dataPoint)) {
                        results[index++] = dataPoint;
                        if (index >= maxResults) {
                            break;
                        }
                    }
                }
            } else {
                for (Node<T> child : node.getChildren()) {
                    index = searchElements(child, boundingBox, maxResults, results, index);
                }
            }
        }
        return index;
    }

    public boolean remove(T dataToRemove) {
        if (root == null) {
            return false;
        }
        return remove(null, root, dataToRemove);
    }

    private boolean remove(Node<T> parent, Node<T> node, T dataToRemove) {
        return false;
    }

    private void mergeNode(Node<T> parent, Node<T> node) {
        parent.setData(node.getData());
        parent.setChildren(null);
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
}
