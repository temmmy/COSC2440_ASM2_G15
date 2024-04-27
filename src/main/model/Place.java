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
                this.services = new ServiceType[MAX_SERVICES];
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

        public Place (int x, int y, String name) {
                super(x, y);
                this.serviceSize = 0;
                this.services = new ServiceType[MAX_SERVICES];
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
                if (serviceSize >= MAX_SERVICES) { return false; } // services is full

                for (int i = 0; i < serviceSize; i++) {
                        if (services[i].name().equals(newService.name())) {
                                return false; // service already exists
                        }
                }
                services[serviceSize] = newService;
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

        public boolean contain(ServiceType service) {
                for (ServiceType s : services) {
                        if (s.equals(service)) return true;
                }
                return false;
        }

        public boolean partialEquals(Place p){
            if (p == null) return false;
            return name.equals(p.getName()) ||
                    getLocation().equals(p.getLocation()) ||
                    contain(p.getServices()[0]);
        }
        @Override
        public String toString() {
                // Convert each ServiceType object to a string
                String[] serviceStrings = new String[serviceSize];
                for (int i = 0; i < serviceSize; i++) {
                        if (services[i] != null)
                                serviceStrings[i] = services[i].toString();
                }
                // Join the service strings with commas
                String serviceStr = String.join(", ", serviceStrings);
                return "Place{" +
                        "name='" + name + '\'' +
                        ", services=[" + serviceStr + "]" + '\'' +
                        ", location=(x=" + this.getX() + ", y=" + this.getY() + ")" +
                        '}';
        }
}
