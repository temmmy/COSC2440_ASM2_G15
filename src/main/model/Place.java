/** 
    @author GROUP 21
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.model;

import main.dataStructure.ArrayList;
import main.dataStructure.Point;

import java.util.Objects;

public class Place {
        public static final int MAX_SERVICES = 10;
        private String name;
        private ArrayList<ServiceType> services;
        private Point location;

        public Place() {
                this.location = new Point();
                this.services = new ArrayList<>(MAX_SERVICES);
                this.name = null;
        }

        public Place(Point location, String name, ServiceType[] services) {
                this.location = location;
                this.services = new ArrayList<>(services);
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

        public void setServices(ArrayList<ServiceType> services) {
                this.services = services;
        }

        public Point getLocation() {
                return location;
        }

        public void setLocation(Point location) {
                this.location = location;
        }

        public int distance(Place place) {
                Point p1 = getLocation();
                Point p2 = place.getLocation();

                return p1.distance(p2);
        }

        public boolean addService(ServiceType newService) {
                if (services.contains(newService))
                        return false;
                return services.add(newService);

        }

        public boolean removeService(ServiceType serviceToRemove) {
                return services.remove(serviceToRemove);
        }

        public boolean isFullService() {
                return services.size() == MAX_SERVICES;
        }

        public boolean partialEquals(Place p) {
                if (p == null)
                        return false;

                boolean nameMatch = (name != null && name.equals(p.getName()));
                boolean locationMatch = (location != null && location.equals(p.getLocation()));

                // Checks if there's any common service between this Place and Place p
                boolean serviceMatch = false;
                if (services != null && p.getServices() != null) {
                        serviceMatch = services.contains(p.getServices());
                }

                if (nameMatch) {
                        System.out.println("name: " + name);
                }
                if (locationMatch) {
                        System.out.println("location: " + location);
                }
                if (serviceMatch) {
                        System.out.println("service");
                }

                return nameMatch || locationMatch || serviceMatch;
        }

        @Override
        public String toString() {
                // Convert each ServiceType object to a string
                String[] serviceStrings = new String[services.size()];
                for (int i = 0; i < services.size(); i++) {
                        serviceStrings[i] = services.get(i).toString();
                }
                // Join the service strings with commas
                String serviceStr = String.join(", ", serviceStrings);
                return "Place{" +
                                "name='" + name + '\'' +
                                ", services=[" + serviceStr + "]" + '\'' +
                                ", location=" + location +
                                '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o)
                        return true;
                if (o == null || getClass() != o.getClass())
                        return false;
                Place place = (Place) o;
                return Objects.equals(getName(), place.getName()) && Objects.equals(getServices(), place.getServices())
                                && Objects.equals(getLocation(), place.getLocation());
        }

        @Override
        public int hashCode() {
                return Objects.hash(getName(), getServices(), getLocation());
        }
}
