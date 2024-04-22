/** 
    @author GROUP 15
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.Point;

import java.util.Arrays;

public class Place extends Point {
        private static final int MAX_SERVICES = 10;
        private String name;
        private ServiceType[] services;

        public Place() {
                super();
                this.services = (ServiceType[]) new Object[MAX_SERVICES];
                this.name = null;
        }

        public Place(Point point, String name, ServiceType[] services) {
                super(point);
                this.services = services;
                this.name = name;
        }

        public Place (int x, int y, String name, ServiceType[] services) {
                super(x, y);
                this.services = services;
                this.name = name;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public ServiceType[] getServices() {
                return services;
        }

        public boolean addService(ServiceType newService) {

                for (int i = 0; i < services.length; i++) {
                        if (services[i] == null) {
                                services[i] = newService;
                                return true;
                        } else if (services[i].name().equals(newService.name())) {
                                return false;
                        }
                }
                return false;

        }

        public boolean removeService(ServiceType serviceToRemove) {
                for (int i = 0; i < services.length; i++) {

                        if (services[i] == null) {
                                return false;
                        } else if (services[i].name().equals(serviceToRemove.name())) {
                                services[i] = null;
                                return true;
                        }
                }
                return false;
        }

        @Override
        public String toString() {
                return "Place{" +
                        "name='" + name + '\'' +
                        ", services=" + Arrays.toString(services) + '\'' +
                        ", location=" + this.getX() + ", " + this.getY() +
                        '}';
        }
}
