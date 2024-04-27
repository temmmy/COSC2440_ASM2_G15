package main;

import main.dataStructure.Point;
import main.model.Map2D;
import main.model.Place;
import main.model.ServiceType;
import main.view.Menu;

public class Application {
    public static void main(String[] args) {
        Map2D map = new Map2D();

        int mapSize = 10000000;
        int placeSize = 9;
        int serviceCount = ServiceType.values().length;

        for (int i = 0; i < placeSize; i++) {
            int x = (int) (Math.random() * mapSize); // Assuming the map size is 1000x1000
            int y = (int) (Math.random() * mapSize);
            String name = "Name " + i;

            Place place = new Place();
            place.setName(name);
            place.setLocation(x,y);

            // Add random services
            int numServicesToAdd = (int) (Math.random() * serviceCount); // Random number of services to add
            for (int j = 0; j < numServicesToAdd; j++) {
                int randomIndex = (int) (Math.random() * serviceCount); // Random index to select a service
                ServiceType service = ServiceType.values()[randomIndex];
                place.addService(service);
            }
            map.insert(place);
        }
        map.display();

        Menu app = new Menu();
        app.mainMenu();


    }

}
