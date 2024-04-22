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

    public void insert(T dataToInsert) {
        insert(root, dataToInsert);
    }

    private boolean insert(Node<T> node, T dataToInsert) {
        if (node == null) { return false; }

        boolean inserted = node.insert(dataToInsert);

        if (!inserted) {
            for (int i = 0; i < MAX_CAPACITY; i++) {
                Node<T> child = node.getChildren().get(i);
                inserted = insert(child, dataToInsert);
            }
        }
        return inserted;
    }

    public Node<T> dfs(T dataPoint) {
        return dfs(root, dataPoint);
    }
    private Node<T> dfs(Node<T> node, T dataPoint) {
        if (node == null) { return null; }

        // Processing
        // Need to test this if it needs to be more conditional
        if (node.contains(dataPoint)) {
            if (!node.isLeaf()) {
                for (int i = 0; i < node.getChildren().size(); i++) {
                    Node<T> child = node.getChildren().get(i);
                    Node<T> result = dfs(child, dataPoint);
                    if (result != null) {
                        return result;
                    }
                }
            }
            ArrayList<T> dataPoints = node.getData();
            for (int i = 0; i < dataPoints.size(); i++) {
                T data = dataPoints.get(i);
                if (data.equals(dataPoint)) {
                    return node;
                }
            }
        }
        return null;
    }

    public boolean remove(T dataToRemove) {

        // if remain last leaf, merge node
        //      parent
        //     /  /  \  \
        //  node  n  n   n
        //   [dataToRemove,null, null, null]
        return remove(root, dataToRemove);
    }

    private boolean remove(Node<T> node, T dataToRemove) {
        /**
         * if node data is empty, return false
         * if node is a leaf:
         *    remove data
         *    if data size > 0: return true
         *    else ( = 0):
         *        if this node is the second child:
         *            mergeNode(parent, node)
         *        else: return true (should not have a case of only child ???)
         */

    }
    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            display(root, 0); // Start the display with root node and initial indentation level 0
        }
    }

    private void display(Node<T> node, int level) {
        // Print indentation for better visualization
        for (int i = 0; i < level; i++) {
            System.out.print("  "); // Adjust the number of spaces based on the desired indentation level
        }

        if (node.isLeaf()) {
            System.out.println("Leaf:");
            for (int i = 0; i < node.getData().size(); i++) {
                T dataPoint = node.getData().get(i);
                System.out.println("  Data point = " + dataPoint.getLocation());
            }
        } else {
            System.out.println("Node:");
            for (int i = 0; i < node.getChildren().size(); i++) {
                Node<T> child = node.getChildren().get(i);
                display(child, level + 1); // Recursive call with increased indentation level
            }
        }
    }




}
