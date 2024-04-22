package main.dataStructure;

public class Node<T extends Point> {
    private static final int MAX_CAPACITY = 4;
    private final Rectangle boundary;
    private final ArrayList<T> data;
    private final ArrayList<Node<T>> children;

    public Node(Rectangle boundary) {
        this.boundary = boundary;
        this.data = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    public Rectangle getBoundary() {
        return boundary;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public ArrayList<Node<T>> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
    public boolean isEmpty() { return data.isEmpty(); }
    public boolean contains(T dataPoint) {
        return this.boundary.contains(dataPoint.getX(), dataPoint.getY());
    }

    public boolean insert(T dataToInsert) {
        if (contains(dataToInsert) && isLeaf()) {
            this.data.add(dataToInsert);
            if (data.size() >= MAX_CAPACITY) {
                System.out.println("Splitting node...");
                split();
                distributeData();
            }
            return true;
        }
        return false;
    }

    /**
     * boundary:
     * (tLX,tLy)r0,r1
     *          r2,r3
     */
    private void split() {

        if (!isLeaf()) { return; } // Do not split if not a leaf node

        int subWidth = boundary.getWidth() / 2;
        int subHeight = boundary.getHeight() / 2;
        int x = boundary.getTopLeftX();
        int y = boundary.getTopLeftY();

        Rectangle r0 = new Rectangle(x, y, subWidth, subHeight);
        Rectangle r1 = new Rectangle(x + subWidth, y, subWidth, subHeight);
        Rectangle r2 = new Rectangle(x, y - subHeight, subWidth, subHeight);
        Rectangle r3 = new Rectangle(x + subWidth, y - subHeight, subWidth, subHeight);

        children.add(new Node<>(r0));
        children.add(new Node<>(r1));
        children.add(new Node<>(r2));
        children.add(new Node<>(r3));

    }

    private void distributeData() {
        // Distribute List<T> data into 4 children
        for (int i = 0; i < data.size(); i++) {
            T dataPoint = data.get(i);
            for (int j = 0; j < MAX_CAPACITY; j++){
                Node<T> child = children.get(j);
                if (child.insert(dataPoint)) break;
            }
        }
        data.clear(); // Clear data because only children or data can exist
    }

}
