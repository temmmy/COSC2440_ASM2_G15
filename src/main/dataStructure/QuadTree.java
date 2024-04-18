package main.dataStructure;

public class QuadTree {
    private static final int MAX_CAPACITY = 4;
    private final Node root;

    public QuadTree() {};
    public QuadTree(Rectangle boundary) {
        this.root = new Node(boundary);
    }

    public void insert(Point point) {
        insert(root, point);
    }

    private void insert(Node node, Point point) {
        Rectangle boundary = node.getBoundary();
        ArrayList<Point> points = node.getPoints();
        Node[] children = node.getChildren();

        if (!boundary.contains(point.getX(), point.getY())) {
            return;
        }

        if (points.size() < MAX_CAPACITY && node.isLeaf()) {
            points.add(point);
        } else {
            node.split();
        }

        for (Node child : children) {
            insert(child, point);
        }

    }

    public Point search(Rectangle range) {
        return search(root, range);
    }

    private Point search(Node node, Rectangle range){
        Rectangle boundary = node.getBoundary();
        ArrayList<Point> points = node.getPoints();
        Node[] children = node.getChildren();

        if (!boundary.intersects(range)) {
            return null;
        }

        for (int i = 0; i < points.size(); i++){
            Point point = points.get(i);
            if (range.contains(point.getX(), point.getY())){
                System.out.println("Found point: " + point);
                return point;
            }
        }

        if (!node.isLeaf()) {
            for (Node child : children) {
                return search(child, range);
            }
        }

    }


}
