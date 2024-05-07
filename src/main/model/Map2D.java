/** 
    @author GROUP 21
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.ArrayList;
import main.dataStructure.QuadTree;
import main.dataStructure.Rectangle;

public class Map2D extends QuadTree<Place> {
    public static final int MAP_SIDE = 10000000;
    public static final int MAX_PLACES = 100000000;
    public int numPlaces;

    private Map2D() {
        Rectangle boundary = new Rectangle(0, MAP_SIDE, MAP_SIDE, MAP_SIDE);
        root = new Node(boundary);
        numPlaces = 0;
    }

    private static class SingletonHelper {
        private static final Map2D INSTANCE = new Map2D();
    }

    public static Map2D getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public int size() {
        return numPlaces;
    }

    @Override
    public boolean isFull() {
        return numPlaces == MAX_PLACES;
    }

    @Override
    public boolean isEmpty() {
        return numPlaces == 0;
    }

    // TimeO(log n) - O(n), Space O(1)
    @Override
    public boolean insert(Place place) {
        if (root == null || numPlaces == MAX_PLACES)
            return false;
        if (insert(root, place)) {
            numPlaces++;
            return true;
        }
        return false;
    }

    private boolean insert(Node node, Place place) {
        if (!node.getBoundary().contains(place.getLocation())) {
            // System.out.println("Not fit.");
            return false; // Does not fit
        }

        if (node.isLeaf()) {
            if (node.getData().contains(place)) {
                System.out.println("Place exists in this node or node is full");
                return false; // Place exists in this node or out of range
            }
            if (node.getData().size() < Node.MAX_CAPACITY) {
                node.addData(place);
                return true; // Place added successfully
            } else {
            // If node is full of data, split and distribute into 4 child nodes
                split(node);
                return insert(node, place);
            }
        }

        // Attempt to insert into child nodes
        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(i);
            if (insert(child, place)) {
                return true;
            }
        }
        return false; // Unable to insert into any child nodes
    }

    private boolean split(Node node) {
        if (!node.isLeaf() || node.getData().size() < Node.MAX_CAPACITY)
            return false;

        Rectangle boundary = node.getBoundary();
        ArrayList<Node> children = node.getChildren();

        int subWidth = boundary.getWidth() / 2;
        int subHeight = boundary.getHeight() / 2;
        int x = boundary.getLocation().getX();
        int y = boundary.getLocation().getY();

        Rectangle northWest = new Rectangle(x, y, subWidth, subHeight);
        Rectangle northEast = new Rectangle(x + subWidth, y, subWidth, subHeight);
        Rectangle southWest = new Rectangle(x, y - subHeight, subWidth, subHeight);
        Rectangle southEast = new Rectangle(x + subWidth, y - subHeight, subWidth, subHeight);

        children.add(new Node(northWest));
        children.add(new Node(northEast));
        children.add(new Node(southWest));
        children.add(new Node(southEast));
        node.setChildren(children);

        // System.out.println("Split the node is completed.");
        distributeData(node);
        return true;
    }

    private boolean distributeData(Node node) {
        ArrayList<Place> data = node.getData();
        ArrayList<Node> children = node.getChildren();

        for (int i = 0; i < data.size(); i++) {
            Place dataPoint = data.get(i);
            insert(node, dataPoint);
        }

        node.clearData();

        return true;
    }

    // Time O(log n) - O(n), Space: O(log n)
    @Override
    public boolean remove(Place place) {
        if (root == null || numPlaces == 0)
            return false;
        if (remove(null, root, place)) {
            numPlaces--;
            return true;
        }
        return false;
    }

    private boolean remove(Node parent, Node node, Place place) {
        if (node == null || place == null)
            return false; // Node does not exist
        if (!node.getBoundary().contains(place.getLocation()))
            return false; // Not found

        if (node.isLeaf()) {
            if (node.getData().isEmpty() || !node.getData().contains(place))
                return false;
            node.removeData(place);
            if (parent != null && canMerge(parent)) {
                merge(parent);
            }
            return true;
        }

        // Attempt to find and remove in child nodes
        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(i);
            if (remove(node, child, place)) {
                return true;
            }
        }

        return false;
    }

    // Check if the child nodes of a node have total less than 4 data.
    private boolean canMerge(Node node) {
        ArrayList<Node> children = node.getChildren();
        int count = 0;
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            count += child.getData().size();
            if (count > Node.MAX_CAPACITY) {
                return false;
            }
        }
        return true;
    }

    private boolean merge(Node node) {
        ArrayList<Node> children = node.getChildren();
        // Iterate in reverse order to avoid removal skipping issue
        ArrayList<Place> data = new ArrayList<>(Node.MAX_CAPACITY);
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            if (!child.isLeaf())
                return false;
            for (int j = 0; j < child.getData().size(); j++) {
                data.add(child.getData().get(j));
            }
        }
        node.setChildren(new ArrayList<>(Node.MAX_CAPACITY));
        node.setData(data);
        return true;
    }

    // Time O(n), Space O(log n)
    @Override
    public ArrayList<Place> search(BoundingRectangle box, Place partialData) {
        search(root, box, partialData);
        box.sortPlaces();
        return box.getPlaces();
    }

    private void search(Node node, BoundingRectangle box, Place partialData) {
        if (node == null)
            return;

        // Skip the branch if the bounding boxes do not intersect
        if (!node.getBoundary().intersects(box.getBoundary()))
            return;

        if (node.isLeaf()) {
            ArrayList<Place> dataPoints = node.getData();
            if (partialData == null) {
                box.addPlaces(dataPoints);
            } else {
                for (int i = 0; i < dataPoints.size(); i++) {
                    Place dataPoint = dataPoints.get(i);
                    if (dataPoint.partialEquals(partialData)) {
                        box.addPlace(dataPoint);
                    }
                }
            }
        }

        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(i);
            search(child, box, partialData);
        }
    }

    // Time O(n), Space O(log n)
    public Place searchPlace(BoundingRectangle box, Place place) {
        return searchPlace(root, box, place);

    }

    private Place searchPlace(Node node, BoundingRectangle box, Place partialData) {
        if (node == null)
            return null;

        if (!node.getBoundary().intersects(box.getBoundary()))
            return null;

        if (node.isLeaf()) {
            if (!node.getData().isEmpty()) {
                for (int i = 0; i < node.getData().size(); i++) {
                    Place dataPoint = node.getData().get(i);
                    if (dataPoint.equals(partialData)) {
                        return dataPoint;
                    }
                }
            }
        }

        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(i);
            return searchPlace(child, box, partialData);
        }

        return null;
    }

}
