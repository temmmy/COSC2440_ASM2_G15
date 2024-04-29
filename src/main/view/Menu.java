package main.view;

import main.dataStructure.QuadTree;
import main.dataStructure.Rectangle;
import main.model.Map2D;
import main.model.Place;
import main.model.ServiceType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner;
    private Map2D map;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.map = new Map2D();
    }
    public void header(String name) {
        System.out.println("=".repeat(40));
        System.out.println("*** " + name + " ***");
        System.out.println("=".repeat(40));
    }

    public void mainMenu() {
        header("Welcome to Map Services");
        System.out.println("Please choose one option: ");
        System.out.println("1. Insert a Place");
        System.out.println("2. Remove a Place");
        System.out.println("3. Edit a Place");
        System.out.println("4. Display Places");
        System.out.println("5. Search Places");
        System.out.println("6. Exit");
        System.out.println("7. Load Places");
        System.out.print("> Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> insertMenu();
            case 2 -> removeMenu();
            case 3 -> editMenu();
            case 4 -> displayMenu();
            case 5 -> searchMenu();
            case 6 -> exit();
            case 7 -> loadPlaces();
            default -> System.out.println("Invalid choice");

        }
    }

    private void loadPlaces() {
        String fileName = "src/resources/places.txt";
        header("LOADING MAP...");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))  {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    String[] servicesStr = parts[1].split("\\|");
                    ServiceType[] services = new ServiceType[servicesStr.length];
                    for (int i = 0; i < servicesStr.length; i++) {
                        services[i] = ServiceType.valueOf(servicesStr[i]);
                    }
                    int x = Integer.parseInt(parts[2]);
                    int y = Integer.parseInt(parts[3]);
                    Place place = new Place(x,y,name, services);
                    if (map.insert(place)) {
                        System.out.println("Inserted " + place);
                    } else {
                        System.out.println("Error inserting " + place);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading places from file.");
            e.printStackTrace();
        } finally {
            returnToMain();
        }
    }

    private void returnToMain() {
        try {
            System.out.println("Returning to Main Menu...");
            Thread.sleep(2000);
            mainMenu();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
    }
    public void exit() {
        System.exit(0);
    }

    // Place(int id, String name, int x, int y, services)

    private String fieldBox(String field) {
        System.out.print(">> Enter " + field + ": ");
        return scanner.nextLine().trim();
    }

    private ServiceType pickAService() {
        System.out.println("Choose a service: ");
        ;
        for (int i = 0; i < ServiceType.values().length; i++) {
            System.out.println((i + 1) + ". " + ServiceType.values()[i]);
        }

        String choiceStr = fieldBox("your choice");
        if (choiceStr.isBlank()) { return null; };

        int choice = Integer.parseInt(choiceStr);

        if (choice < 1 || choice > ServiceType.values().length) {
            System.out.println("Invalid choice!");
        } else {
            return ServiceType.values()[choice - 1];
        }

        return null;
    }

    public void insertMenu() {
        header("INSERT A PLACE");
        System.out.println("Please enter place details:");
        String name = fieldBox("name of the place");
        int x = Integer.parseInt(fieldBox("x-coordinate"));
        int y = Integer.parseInt(fieldBox("y-coordinate"));

        Place newPlace = new Place();
        newPlace.setLocation(x,y);
        newPlace.setName(name);

        while (true) {
            ServiceType service = pickAService();
            if (service != null) {
                if (newPlace.addService(service)) {
                    System.out.println(newPlace);
                } else {
                    System.out.println("Duplicate service or Services is full for this place.");
                };
            } else {
                break;
            }
        }

        System.out.println("Inserting *" + newPlace.getName() + "* into the map...");
        map.insert(newPlace);
        System.out.println("Insert a Place successfully!");
        // Return to mainMenu

        returnToMain();

    }

    public Place createPartialPlace() {
        System.out.println("Attribute of the place: ");
        System.out.println("1. Name");
        System.out.println("2. Location");
        System.out.println("3. Service");
        int choice = Integer.parseInt(fieldBox("your choice: "));

        Place partialPlace = new Place();
        switch (choice) {
            case 1:
                String name = fieldBox("name of the place: ");
                partialPlace.setName(name);
                break;
            case 2:
                int x = Integer.parseInt(fieldBox("x-coordinate: "));
                int y = Integer.parseInt(fieldBox("y-coordinate: "));
                partialPlace.setLocation(x, y);
                break;
            case 3:
                ServiceType service = pickAService();
                partialPlace.addService(service);
                break;
            default:
                System.out.println("Wrong input.");
        }
        System.out.println("Partial Place created.");
        System.out.println(partialPlace);
        return partialPlace;
    }


    public void removeMenu() {
        header("REMOVE A PLACE");
        setBoundingBox();
        Place partialPlace = createPartialPlace();

        Place[] results = map.searchBy(partialPlace);
        Place placeToRemove = null;
        for (Place place : results) {
            if (place != null) {
                placeToRemove = place;
                break;
            }
        }

        System.out.println("Place to removed: " + placeToRemove);


        if (placeToRemove == null) {
            System.out.println("Place not found!");
            removeMenu();
        }
        boolean isRemoved = map.remove(placeToRemove);
        if (isRemoved) {
            System.out.println("Remove a Place successfully!");
        } else {
            System.out.println("Place not found");
        }

        returnToMain();
    }

    public void editMenu() {
        header("EDIT A PLACE");
        setBoundingBox();
        Place partialPlace = createPartialPlace();

        Place[] results = map.searchBy(partialPlace);
        Place placeToEdit = null;
        if (placeToEdit == null) {
            System.out.println("Place not found!");
            editMenu();
        }
        System.out.println("Found the place: " + placeToEdit);
        ServiceType[] services = placeToEdit.getServices();
        System.out.println("Do you want to add or remove a service?");
        String operation = fieldBox("ADD or REMOVE");
        if (operation.equalsIgnoreCase("ADD")) {
            if (services.length > 10) {
                System.out.println("Max number of services (10) is reached!");
                editMenu();
            } else {
                System.out.println(">>> Add a Service <<<");
                ServiceType pickedService = pickAService();
                for (int i = 0; i < services.length; i++) {
                    if (services[i] == null) {
                        placeToEdit.addService(pickedService);
                        services = placeToEdit.getServices();
                        System.out.println("Service " + pickedService + " is added.");
                        System.out.println("Place is updated: " + placeToEdit);
                    } else {
                        if (services[i].equals(pickedService)) {
                            System.out.println("This place already has the service.");
                            editMenu();
                        }
                    }
                }
            }
        } else if (operation.equalsIgnoreCase("REMOVE")) {
            System.out.println(">>> Remove a service <<<");
            if (services.length == 0 || services[0] == null) {
                System.out.println("This place has no services.");
                editMenu();
            }
            System.out.println("Choose a service to remove: ");
            for (int i = 0; i < services.length; i++) {
                if (services[i] != null)
                    System.out.println(i + ". " + services[i]);
            }
            int choice = Integer.parseInt(fieldBox("your choice"));
            ServiceType serviceToRemove = services[choice];
            placeToEdit.removeService(serviceToRemove);
            System.out.println("Service " + serviceToRemove  + " is removed!");
            System.out.println("Place is updated: " + placeToEdit);
        }

        returnToMain();
    }

    public void displayMenu() {
        header("Map Structure");
        map.displayData();
        returnToMain();
    }

    public void searchMenu() {
        header("FIND PLACES");
        setBoundingBox();
        Place placeToCompare = createPartialPlace();
        Place[] results = map.searchBy(placeToCompare);
        for (Place place : results) {
            if (place != null)
                System.out.println(place);
        }
        returnToMain();
    }

    private void setBoundingBox() {
        System.out.println("Do you want to change location? (Type YES or blank)");
        String choice = fieldBox("your choice: ");
        if (choice.equalsIgnoreCase("YES")) {
            int x = Integer.parseInt(fieldBox("x-coordinate: "));
            int y = Integer.parseInt(fieldBox("y-coordinate: "));
            int distance = Integer.parseInt(fieldBox("Distance: "));
            map.setBoundingBox(x,y,distance);
        }
    }

}
