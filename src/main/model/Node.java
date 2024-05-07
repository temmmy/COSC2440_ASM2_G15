/** 
    @author GROUP 21
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.ArrayList;
import main.dataStructure.Rectangle;

public class Node {
    public static final int MAX_CAPACITY = 4;
    private Rectangle boundary;
    private ArrayList<Node> children;
    private ArrayList<Place> data;

    public Node() {
        this.boundary = new Rectangle();
        this.children = new ArrayList<>(MAX_CAPACITY);
        this.data = new ArrayList<>(MAX_CAPACITY);
    }

    public Node(Rectangle boundary, ArrayList<Node> children, ArrayList<Place> data) {
        this.boundary = boundary;
        this.children = children;
        this.data = data;
    }

    public Node(Rectangle boundary) {
        this(boundary, new ArrayList<>(MAX_CAPACITY), new ArrayList<>(MAX_CAPACITY));

    }

    public Rectangle getBoundary() {
        return boundary;
    }

    public void setBoundary(Rectangle boundary) {
        this.boundary = boundary;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<Place> getData() {
        return data;
    }

    public void setData(ArrayList<Place> data) {
        this.data = data;
    }

    public void clearData() {
        data.clear();
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean addData(Place place) {
        if (place == null && data.size() >= MAX_CAPACITY) return false;
        return data.add(place);
    }

    public boolean removeData(Place place) {
        if (place == null) return false;
        return data.remove(place);
    }

    @Override
    public String toString() {
        StringBuilder childrenStr = new StringBuilder();
        if (!children.isEmpty()) {
            String[] childrenStrArr = new String[children.size()];
            for (int i = 0; i < children.size(); i++) {
                childrenStrArr[i] = children.get(i).isLeaf() ? "Leaf" : "Node";
            }
            childrenStr.append(String.join(", ", childrenStrArr));
        }

        StringBuilder dataStr = new StringBuilder();
        if (!data.isEmpty()) {
            String[] dataStrArr = new String[data.size()];
            for (int i = 0; i < data.size(); i++) {
                dataStrArr[i] = data.get(i).getLocation().toString();
            }
            dataStr.append(String.join(", ", dataStrArr));
        }

        return "Node{" +
                "boundary=" + boundary +
                ", children=[" + childrenStr +
                "], data=[" + dataStr +
                "]}";
    }


}
