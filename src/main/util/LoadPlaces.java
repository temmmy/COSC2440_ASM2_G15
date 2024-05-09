/** 
    @author GROUP 21
        - Nguyen Chi Nghia s3979170
        - Duong Viet Hoang s3962514
        - Nguyen Huy Anh   s3956092
        - Vu Tien Quang    s3981278
*/

package main.util;

import main.dataStructure.Point;
import main.model.Map2D;
import main.model.Place;
import main.model.ServiceType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is responsible for loading places from a file into a Map2D object.
 * It reads the file line by line, parses the data, and creates Place objects
 * with the extracted information. The created Place objects are then inserted
 * into the Map2D object.
 * 
 * The file format should be as follows:
 * - Each line represents a place.
 * - Each line should contain four comma-separated values: name, services,
 * x-coordinate, and y-coordinate.
 * - The services should be pipe-separated (|) values.
 * 
 * Example file content:
 * Place1,PARK|CAFE,100,200
 * Place2,RESTAURANT|HOTEL,300,400
 * 
 * The LoadPlaces class provides a static method 'loadPlacesFromFile' that takes
 * the filename and a Map2D object as parameters. It reads the file, creates
 * Place objects, and inserts them into the Map2D object.
 * 
 * The class also contains a main method for testing the loading functionality.
 * It creates a Map2D object, calls the 'loadPlacesFromFile' method, and
 * displays
 * the loaded data to verify the correctness.
 * 
 * Note: This class assumes that the file is in the correct format and does not
 * handle any exceptions related to incorrect file format or missing data.
 * 
 */
public class LoadPlaces {

    public static void loadPlacesFromFile(String filename, Map2D map) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0].trim();
                    String[] servicesStr = parts[1].split("\\|");
                    ServiceType[] services = new ServiceType[servicesStr.length];
                    for (int i = 0; i < servicesStr.length; i++) {
                        servicesStr[i] = servicesStr[i].replace(" ", "_");
                        services[i] = ServiceType.valueOf(servicesStr[i].trim());
                    }
                    int x = Integer.parseInt(parts[2].trim());
                    int y = Integer.parseInt(parts[3].trim());
                    Place place = new Place(new Point(x, y), name, services);
                    map.insert(place);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map2D map = Map2D.getInstance();
        loadPlacesFromFile("src/resources/places.txt", map);
        // Display loaded data to verify
        map.displayData();
    }
}
/*
 * public class Map2DTest {
 * 
 * public static void main(String[] args) {
 * // Create Map2D
 * Map2D map = new Map2D();
 * 
 * // Create places
 * Place place1 = new Place(3844974, 272300, "King River", new
 * ServiceType[]{ServiceType.PARK, ServiceType.CAFE});
 * Place place2 = new Place(6220976, 5716776, "Stony Hill", new
 * ServiceType[]{ServiceType.PARK, ServiceType.CAFE});
 * Place place3 = new Place(625583, 4020927, "Jimmy Newells Inlet", new
 * ServiceType[]{ServiceType.PARK, ServiceType.CAFE});
 * 
 * // Add places to map
 * System.out.println("Adding places...");
 * System.out.println("Insert King River: " + (map.insert(place1) ? "Success" :
 * "Failed"));
 * System.out.println("Insert Stony Hill: " + (map.insert(place2) ? "Success" :
 * "Failed"));
 * System.out.println("Insert Jimmy Newells Inlet: " + (map.insert(place3) ?
 * "Success" : "Failed"));
 * 
 * // Display data
 * System.out.println("\nCurrent data in the map:");
 * map.displayData();
 * 
 * // Remove places
 * System.out.println("\nRemoving Stony Hill...");
 * System.out.println("Remove Stony Hill: " + (map.remove(place2) ? "Success" :
 * "Failed"));
 * 
 * // Display data after removing
 * System.out.println("\nData in the map after removal:");
 * map.displayData();
 * 
 * // Searching places
 * System.out.println("\nSearching for Jimmy Newells Inlet...");
 * Place searchPlace = place3;
 * Place[] results = map.searchBy(searchPlace);
 * if (results.length > 0 && results[0] != null) {
 * System.out.println("Found: " + results[0]);
 * } else {
 * System.out.println("Jimmy Newells Inlet not found.");
 * }
 * 
 * 
 * // Add test places
 * Place place4 = new Place(100, 200, "Coffee Shop", new
 * ServiceType[]{ServiceType.CAFE});
 * map.insert(place4);
 * 
 * // Display initial state
 * System.out.println("Initial state:");
 * map.displayData();
 * 
 * // Update the place
 * ServiceType[] newServices = new ServiceType[]{ServiceType.ATM,
 * ServiceType.HOTEL};
 * System.out.println("\nUpdating Coffee Shop services...");
 * boolean updateResult = map.updateServices(new Place(100, 200, "Coffee Shop"),
 * newServices);
 * System.out.println("Update Test: " + (updateResult ? "Passed" : "Failed"));
 * 
 * // Display updated state
 * System.out.println("\nUpdated state:");
 * map.displayData();
 * }
 * }
 */
