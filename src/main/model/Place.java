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

public class Place extends Point {
        private String name;
        private ArrayList<ServiceType> services;

        public Place() {
                super();
                this.services = new ArrayList<>();
                this.name = null;
        }

        public Place(Point point, String name, ArrayList<ServiceType> services) {
                super(point);
                this.services = services;
                this.name = name;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
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
