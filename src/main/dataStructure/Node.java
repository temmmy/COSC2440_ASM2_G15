package main.dataStructure;

public class Node {
    private final Rectangle boundary;
    private final ArrayList<Point> points;
    private final Node[] children;

    public Node(Rectangle boundary) {
        this.boundary = boundary;
        this.points = new ArrayList<>();
        this.children = new Node[4];
    }

    public Rectangle getBoundary() {
        return boundary;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public Node[] getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children[0] == null;
    }

    public boolean insert(Point point) {
        boolean isAdded = false;
        if (!boundary.contains(point.getX(), point.getY())) {
            return false;
        }

        if (isLeaf()) {
            if (points.size() < 4) {
                points.add(point);
                isAdded = true;
            } else {
                split();
            }
        }

        for (Node child : children) {
            if (child.insert(point)) {
                isAdded = true;
                break;
            }
        }

        return isAdded;
    }

    /**
     * boundary:
     * (tLX,tLy)r0,r1
     *          r2,r3
     */
    public void split() {
        int subWidth = boundary.getWidth() / 2;
        int subHeight = boundary.getHeight() / 2;
        int x = boundary.getTopLeftX();
        int y = boundary.getTopLeftY();

        Rectangle r0 = new Rectangle(x, y, subWidth, subHeight);
        Rectangle r1 = new Rectangle(x + subWidth, y, subWidth, subHeight);
        Rectangle r2 = new Rectangle(x, y - subHeight, subWidth, subHeight);
        Rectangle r3 = new Rectangle(x + subWidth, y - subHeight, subWidth, subHeight);

        children[0] = new Node(r0);
        children[1] = new Node(r1);
        children[2] = new Node(r2);
        children[3] = new Node(r3);

        for (Point point : points) {
            for (Node child : children) {
                if (child.getBoundary().contains(point.getX(), point.getY())) {
                    child.insert(point);
                    break;
                }
            }
        }

        points.clear(); // Clear points from this node after redistribution
    }
}
