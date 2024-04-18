/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.ArrayList;
import main.dataStructure.QuadTree;
import main.dataStructure.Rectangle;

public class Map2D implements IMap2D {
        private static final int MAX_WIDTH = 10000000;
        private static final int MAX_HEIGHT = 10000000;
        private static final int MAX_PLACES = 100000000;
        private static final int MAX_QUERIES = 50;
        private static final int MIN_BOX = 100;
        private static final int MAX_BOX = 100000;

        private ArrayList<Place> places;
        private Rectangle boundingBox;

        private QuadTree map;

        public Map2D() {
                Rectangle r = new Rectangle(MAX_WIDTH, MAX_HEIGHT);
                this.map = new QuadTree(r);
                this.places = new ArrayList<>();
                this.boundingBox = new Rectangle(MIN_BOX, MIN_BOX);
        }

        public Rectangle setBoundingBox(Rectangle r) {
                // if size fits within
                this.boundingBox = r;
                // else...
        }
        public void addPlace(Place place){
                places.add(place);
                map.insert(place.getLocation());
        }

        public void removePlace(Place place) {
                places.remove(place);
                // map.remove(place);
        }

        public Place[] searchRange(Rectangle range){
                return map.search(range);
        }

        public Place[] searchRange(Place place, int distance) {
                Rectangle range = new Rectangle(place.getLocation(), distance);
                return map.search(range);
        }

        public Place[] searchNear(Place place) {
                // set bounding box
                // map.search(boundingBox);
        }




}
