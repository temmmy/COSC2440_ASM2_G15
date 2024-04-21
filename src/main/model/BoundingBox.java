/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.Rectangle;

public class BoundingBox extends Rectangle {
    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;

    public BoundingBox(int topLeftX, int topLeftY, int width, int height) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
    }

    public boolean contain(int x, int y) {
        return x >= topLeftX && x <= topLeftX + width && y >= topLeftY && y <= topLeftY + height;
    }

    public boolean intersect(BoundingBox other) {
        return topLeftX < other.topLeftX + other.width && topLeftX + width > other.topLeftX
                && topLeftY < other.topLeftY + other.height && topLeftY + height > other.topLeftY;
    }

    public int getTopLeftX() {
        return this.topLeftX;
    }

    public void setTopLeftX(int topLeftX) {
        this.topLeftX = topLeftX;
    }

    public int getTopLeftY() {
        return this.topLeftY;
    }

    public void setTopLeftY(int topLeftY) {
        this.topLeftY = topLeftY;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "{" +
                " topLeftX='" + getTopLeftX() + "'" +
                ", topLeftY='" + getTopLeftY() + "'" +
                ", width='" + getWidth() + "'" +
                ", height='" + getHeight() + "'" +
                "}";
    }

}