package main.view;

import main.model.Map2D;
import main.model.Place;
import main.model.ServiceType;

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
        System.out.println("5. Search Places from a Place");
        System.out.println("6. Search Places from a Location");
        System.out.println("7. Exit");
        System.out.println("> Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> insertMenu();
            case 2 -> removeMenu();
            case 3 -> editMenu();
            case 4 -> displayMenu();
            case 5 -> searchFromAPlace();
            case 6 -> searchFromALocation();
            case 7 -> exit();
            default -> System.out.println("Invalid choice");

        }
    }

    public void exit() {
        System.exit(0);
    }

    // Place(int id, String name, int x, int y, services)

    private String fieldBox(String field) {
        System.out.println(">> Enter " + field + ": ");
        return scanner.nextLine();
    }

    private ServiceType pickAService() {
        System.out.println("Choose a service: ");
        ;
        for (int i = 0; i < ServiceType.values().length; i++) {
            System.out.println((i + 1) + ". " + ServiceType.values()[i]);
        }

        int choice = Integer.parseInt(fieldBox("your choice"));

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
        ServiceType[] services = new ServiceType[10];
        while (scanner.hasNext()) {
            int index = 0;
            services[index] = pickAService();
            index++;
        }
        Place newPlace = new Place(x,y,name, services);
        System.out.println("Inserting place " + newPlace.getName() + " into the map...");
        map.insert(newPlace);
        System.out.println("Insert a Place successfully!");

    }

    public void removeMenu() {
        header("REMOVE A PLACE");
        String id = fieldBox("ID of place you want to remove");
        Place placeToRemove = map.findByIdOrName(id, true);
        if (placeToRemove == null) {
            System.out.println("Place not found!");
            removeMenu();
            return;
        }
        boolean isRemoved = map.remove(placeToRemove);
        if (isRemoved) {
            System.out.println("Remove a Place successfully!");
        } else {
            System.out.println("Place not found");
        }
    }

    public void editMenu() {
        header("EDIT A PLACE");
        String id = fieldBox("ID of a place you want to edit");
        Place placeToEdit = map.findByIdOrName(id, true);
        if (placeToEdit == null) {
            System.out.println("Place not found!");
            editMenu();
            return;
        }
        System.out.println("Found the place: " + placeToEdit);
        ServiceType[] services = placeToEdit.getServices();
        System.out.println("Do you want to add or remove a service?");
        String operation = fieldBox("ADD or REMOVE");
        if (operation.equalsIgnoreCase("ADD")) {
            if (services.length > 10) {
                System.out.println("Max number of services (10) is reached!");
                return;
            } else {
                System.out.println(">>> Add a Service <<<");
                ServiceType pickedService = pickAService();
                for (int i = 0; i < services.length; i++) {
                    if (services[i] == null) {
                        placeToEdit.addService(pickedService);
                        services = placeToEdit.getServices();
                    } else {
                        if (services[i].equals(pickedService)) {
                            System.out.println("This place already has the service.");
                            return;
                        }
                    }
                }
            }
        } else if (operation.equalsIgnoreCase("REMOVE")) {
            System.out.println(">>> Remove a service <<<");
            if (services.length == 0 || services[0] == null) {
                System.out.println("This place has no services.");
                return;
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
        }
    }

    public void displayMenu() {
        header("Map Structure");
        map.display();
    }

    public void searchFromAPlace() {

    }

    public void searchFromALocation() {

    }

}
