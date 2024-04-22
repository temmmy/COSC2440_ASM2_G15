/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.QuadTree;
import main.dataStructure.Rectangle;

public class Map2D extends QuadTree<Place> implements IMap2D {
        private static final int MAP_WIDTH = 10000000;
        private static final int MAP_HEIGHT = 10000000;
        private static final int MAX_PLACES = 100000000;
        private static final int MAX_QUERIES = 50;
        private static final int MIN_BOX = 100;
        private static final int MAX_BOX = 100000;

        private Rectangle boundingBox;

        public Map2D() {
                super(new Rectangle(MAP_WIDTH, MAP_HEIGHT));
                this.boundingBox = new Rectangle(MIN_BOX, MIN_BOX);
        }

        public Rectangle getBoundingBox() {
                return boundingBox;
        }

        public void setBoundingBox(Rectangle boundingBox) {
                this.boundingBox = boundingBox;
        }

        public Place[] searchPlaces() {
                return searchElements(boundingBox, MAX_QUERIES);
        }

        public enum DistanceType {
                NEAR, WALKING, BIKE, MOTORBIKE, DRIVING, FAR
        }
        public Place[] searchPlaces(Place center, DistanceType distanceType) {
                int distance = switch (distanceType) {
                    case NEAR -> MIN_BOX;
                    case FAR -> MAX_BOX;
                    case WALKING -> 500;
                    case BIKE -> 1000;
                    case MOTORBIKE -> 5000;
                    case DRIVING -> 8000;
                };
                Rectangle range = new Rectangle(center, distance, distance);
                setBoundingBox(range);
                return searchPlaces();
        }



}
