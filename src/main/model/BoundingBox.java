/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.Rectangle;

public class BoundingBox {
    private static final int MAX_QUERIES = 50;
    private Rectangle boundary;
    private Place[] places;

    public BoundingBox(Rectangle boundary, Place[] places) {
        this.boundary = boundary;
        this.places = places;
    }

    public Rectangle getBoundary() {
        return boundary;
    }

    public void setBoundary(Rectangle boundary) {
        this.boundary = boundary;
    }

    public Place[] getPlaces() {
        return places;
    }

    public void setPlaces(Place[] places) {
        this.places = places;
    }

}