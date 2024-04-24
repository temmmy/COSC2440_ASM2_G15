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
        private int serviceSize;

        public Place() {
                super();
                this.serviceSize = 0;
                this.services = new ServiceType[serviceSize];
                this.name = null;
        }

        public Place(Point point, String name, ServiceType[] services) {
                super(point);
                this.serviceSize = services.length;
                this.services = services;
                this.name = name;
        }

        public Place (int x, int y, String name, ServiceType[] services) {
                super(x, y);
                this.serviceSize = services.length;
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
                if (serviceSize >= MAX_SERVICES) { return false; }

                for (int i = 0; i < serviceSize; i++) {
                        if (services[i].name().equals(newService.name())) {
                                return false;
                        }
                }
                services[serviceSize++] = newService;
                serviceSize++;
                return true;

        }

        public boolean removeService(ServiceType serviceToRemove) {
                if (serviceSize == 0) { return false; }

                int indexToRemove = -1;
                for (int i = 0; i < serviceSize; i++) {
                        if (services[i].name().equals(serviceToRemove.name())) {
                                indexToRemove = i;
                                break;
                        }
                }

                if (indexToRemove != -1) {
                        // Shift elements to the left to remove the service
                        for (int i = indexToRemove; i < serviceSize - 1; i++) {
                                services[i] = services[i + 1];
                        }
                        serviceSize--;
                        return true;
                } else {
                        return false;
                }
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
