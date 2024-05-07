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
        this.boundary = boundary;
        this.children = new ArrayList<>(MAX_CAPACITY);
        this.data = new ArrayList<>(MAX_CAPACITY);
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

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public boolean addData(Place place) {
        return data.add(place);
    }

    public boolean removeData(Place place) {
        return data.remove(place);
    }

    @Override
    public String toString() {
        String[] childrenStrArr = new String[children.size()];
        String childrenStr = "";
        if (!children.isEmpty()) {
            for (int i = 0; i < children.size(); i++) {
                if (children.get(i).isLeaf()) {
                    childrenStrArr[i] = "Leaf";
                } else {
                    childrenStrArr[i] = "Node";
                }
            }
            childrenStr = String.join(", ", childrenStrArr);
        }

        String[] dataStrArr = new String[data.size()];
        String dataStr = "";
        if (!data.isEmpty()) {
            for (int i = 0; i < data.size(); i++) {
                dataStrArr[i] = data.get(i).toString();
            }
            dataStr = String.join(",", dataStrArr);
        }

        return "Node{" +
                "boundary=" + boundary +
                ", children=[" + childrenStr +
                "], data=[" + dataStr +
                "]}";
    }

}
