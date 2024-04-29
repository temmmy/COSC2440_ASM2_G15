/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.Node;
import main.dataStructure.Point;
import main.dataStructure.QuadTree;
import main.dataStructure.Rectangle;

public class Map2D extends QuadTree<Place> {
        private static final int MAP_WIDTH = 10000000;
        private static final int MAP_HEIGHT = 10000000;
        private static final int MAX_PLACES = 100000000;
        private static final int MAX_QUERIES = 50;
        private static final int MIN_BOX = 100;
        private static final int MAX_BOX = 100000;

        private int numPlaces;
        private Rectangle boundingBox;
        private int distance;

        public Map2D() {
                super(new Rectangle(MAP_WIDTH, MAP_HEIGHT));
                this.boundingBox = new Rectangle(MAX_BOX, MAX_BOX);
                this.distance = MIN_BOX;
                this.numPlaces = 0;
        }



        public int getNumPlaces() { return numPlaces; }

        public Rectangle getBoundingBox() {
                return boundingBox;
        }

        public void setBoundingBox(Rectangle boundingBox) {
                this.boundingBox = boundingBox;
        }
        public void setBoundingBox(int x, int y, int distance) {
            Point center = new Point(x,y);
            Rectangle r = new Rectangle(center, distance, distance);
            setBoundingBox(r);
        }

        public void setDistance(DistanceType distanceType) {
            this.distance = switch (distanceType) {
                        case NEAR -> MIN_BOX;
                        case FAR -> MAX_BOX;
                        case WALKING -> 500;
                        case BIKE -> 1000;
                        case MOTORBIKE -> 5000;
                        case DRIVING -> 8000;
                };
        }

        public int getDistance() { return distance; }

        public enum DistanceType {
                NEAR, WALKING, BIKE, MOTORBIKE, DRIVING, FAR
        }

        public Map2D searchBy(Place placeToCompare) {
            Map2D results = new Map2D();
            searchBy(getRoot(), results, placeToCompare);
            return results;
        }

        private void searchBy(Node<Place> node, Map2D results, Place placeToCompare) {
            if (node == null) return;

            Rectangle boundary = node.getBoundary();

            if (boundingBox.intersects(boundary)) {
                if (node.isLeaf() && !node.isEmpty()) {
                    for (Place place : node.getData()) {
                        if (place == null) {
                            break;
                        }
                        if (boundingBox.contains(place.getLocation()) && place.partialEquals(placeToCompare)) {
                            results.insert(place);
                        }
                    }
                } else {
                    for (Node<Place> child : node.getChildren()) {
                       searchBy(child, results, placeToCompare);
                    }
                }
            }
        }






}
