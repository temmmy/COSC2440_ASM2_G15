/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.ArrayList;
import main.dataStructure.Set;

public class Place {
        private int id;
        private int x;
        private int y;
        private ArrayList<ServiceType> services;

        public Place() {
                this.services = new ArrayList<>();
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
