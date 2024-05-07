/** 
    @author GROUP 21
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.dataStructure;

import java.util.Objects;

public class Rectangle {
    private final int height;
    private final int width;
    private Point topLeft;
    private Point center;

    public Rectangle() {
        this.topLeft = new Point();
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(int topLeftX, int topLeftY, int width, int height) {
        this.topLeft = new Point(topLeftX, topLeftY);
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point center, int width, int height) {
        this.width = width;
        this.height = height;
        this.topLeft = new Point((int) (center.getX() - width / 2.0), (int) (center.getY() - height / 2.0));
    }

    public void setLocation(int x, int y) {
        this.topLeft.setLocation(x, y);
    }

    public void setLocation(Point p) {
        this.topLeft = p;
    }

    public Point getCenter() {
        return new Point((int) (topLeft.getX() + width / 2.0), (int) (topLeft.getY() - height / 2.0));
    }

    public boolean intersects(Rectangle r) {
        return this.topLeft.getX() < r.topLeft.getX() + r.width &&
                this.topLeft.getX() + this.width > r.topLeft.getX() &&
                this.topLeft.getY() > r.topLeft.getY() - r.height &&
                this.topLeft.getY() - this.height < r.topLeft.getY();
    }

    public boolean contains(int x, int y) {
        return x >= this.topLeft.getX() && x <= this.topLeft.getX() + this.width &&
                y <= this.topLeft.getY() && y >= this.topLeft.getY() - this.height;
    }

    public boolean contains(Point point) {
        int x = point.getX();
        int y = point.getY();
        return contains(x, y);
    }

    public Point getLocation() {
        return this.topLeft;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Rectangle rectangle = (Rectangle) o;
        return height == rectangle.height && width == rectangle.width && topLeft.equals(rectangle.topLeft);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width, topLeft);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                ", topLeft=" + topLeft +
                '}';
    }
}
