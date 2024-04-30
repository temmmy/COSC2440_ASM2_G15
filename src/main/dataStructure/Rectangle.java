package main.dataStructure;

import java.util.Objects;

public class Rectangle {
    private int height;
    private int width;
    private int topLeftX;
    private int topLeftY;

    public Rectangle() {
        this.topLeftX = 0;
        this.topLeftY = 0;
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(int width, int height){
        this.topLeftX = this.topLeftY = 0;
        this.width = width;
        this.height = height;
    }

    public Rectangle(int x, int y, int width, int height) {
        this.topLeftX = x;
        this.topLeftY = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point p) {
        this.topLeftX = p.getX();
        this.topLeftY = p.getY();
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(Point center, int width, int height) {
        this.width = width;
        this.height = height;
        this.topLeftX = center.getX() - width / 2;
        this.topLeftY = center.getY() - height / 2;
    }

    public void setLocation(int x, int y) {
        this.topLeftX = x;
        this.topLeftY = y;
    }

    public void setLocation(Point p) {
        this.topLeftX = p.getX();
        this.topLeftY = p.getY();
    }

    public Point getCenter() {
        return new Point(topLeftX + width / 2, topLeftY + height / 2);
    }

    public boolean intersects(Rectangle r){
        return this.topLeftX < r.topLeftX + r.width &&
                this.topLeftX + this.width > r.topLeftX &&
                this.topLeftY < r.topLeftY + r.height &&
                this.topLeftY + this.height > r.topLeftY;
    }

    public boolean contains(int x, int y) {
        return x >= this.topLeftX && x <= this.topLeftX + this.width &&
                y >= this.topLeftY && y <= this.topLeftY + this.height;
    }

    public boolean contains(Point point) {
        int x = point.getX();
        int y = point.getY();
        return contains(x, y);
    }


    public Point getLocation() {
        return new Point(this.topLeftX, this.topLeftY);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return height == rectangle.height && width == rectangle.width && topLeftX == rectangle.topLeftX && topLeftY == rectangle.topLeftY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, topLeftX, topLeftY);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                ", topLeftX=" + topLeftX +
                ", topLeftY=" + topLeftY +
                '}';
    }
}
