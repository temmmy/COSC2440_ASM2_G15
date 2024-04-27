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
    // Inherits topLeftX, topLeftY, w, h
    private Place center;

    public BoundingBox(Place center) {
        super(center);
        this.center = center;
    }

    public BoundingBox(String name) {

    }
}