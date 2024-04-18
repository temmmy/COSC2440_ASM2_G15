/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.ArrayList;
import main.dataStructure.Point;

public class Place {
        private int id;
        private Point location;
        private ArrayList<ServiceType> services;

        public Place() {
                this.services = new ArrayList<>();
                this.point = new Point();
        }

        public Place(int id, Point point, ArrayList<ServiceType> services) {
                this.id = id;
                this.location = point;
                this.services = services;
        }

        public int getId() {
                return id;
        }

        public Point getLocation() {
                return location;
        }

        public ArrayList<ServiceType> getServices() {
                return services;
        }

        public boolean addService(ServiceType s) {
                boolean isAdded = false;
                if (!services.contains(s)) {
                        services.add(s);
                        isAdded = true;
                }
                return isAdded;

        }

        public boolean removeService(ServiceType s) {
                boolean isRemoved = false;
                if (services.contains(s)) {
                        services.remove(s);
                        isRemoved = true;
                }
                return isRemoved;

        }


}
