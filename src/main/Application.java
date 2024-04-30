package main;

import main.model.Map2D;
import main.model.Place;
import main.model.ServiceType;
import main.view.Menu;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("1. Start application");
        System.out.println("2. Start load testing");
        System.out.println("3. Start remove testing");
        System.out.print(">> Enter your choice: ");
        try (Scanner sc = new Scanner(System.in)) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    start();
                    break;
                    case 2:
                        loadTesting();
                        break;
                case 3:
                    removeTesting();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void loadTesting() {
        Map2D map = new Map2D();
        System.out.print("Number of places: ");
        Scanner sc = new Scanner(System.in);
        int numPlaces = sc.nextInt();
        int failureCount = 0;
        System.out.println("Loading testing data...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numPlaces; i++) {
            int x = i;
            int y = i;
            String name = "Place " + i;
            ServiceType[] services = ServiceType.values();
            Place place = new Place(x, y, name, services);
            if (map.insert(place)) {
                System.out.println(i + ". Inserted " + place);
            } else {
                failureCount++;
                System.out.println(i + ". Insertion failed");
            };
        }
        sc.close();

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("*".repeat(60));
        System.out.println("Loading testing data with " + numPlaces + " places finished in " + elapsedTime + " ms");
        double failureRate = (double) failureCount / (double) numPlaces;
        System.out.println("Failures: " + failureCount + ", Failure Rate: " + failureRate*100 + "%");
        System.out.println("*".repeat(60));
    }

    private static void removeTesting() {

        Map2D map = new Map2D();
        System.out.print("Number of places: ");
        Scanner sc = new Scanner(System.in);
        int numPlaces = sc.nextInt();
        int failureCount = 0;
        System.out.println("Loading testing data...");
        for (int i = 0; i < numPlaces; i++) {
            int x = i;
            int y = i;
            String name = "Place " + i;
            ServiceType[] services = ServiceType.values();
            Place place = new Place(x, y, name, services);
            map.insert(place);
        }
        sc.close();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numPlaces; i++) {
            int x = i;
            int y = i;
            String name = "Place " + i;
            ServiceType[] services = ServiceType.values();
            Place place = new Place(x, y, name, services);
            if (map.remove(place)) {
                System.out.println("Removed " + place);
            } else {
                failureCount++;
                System.out.println(i + ". Removal failed");
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("*".repeat(60));
        System.out.println("Removing testing data with " + numPlaces + " places finished in " + elapsedTime + " ms");
        double failureRate = (double) failureCount / (double) numPlaces;
        System.out.println("Failures: " + failureCount + ", Failure Rate: " + failureRate*100 + "%");
        System.out.println("*".repeat(60));
    }

    private static void start() {
        Menu app = new Menu();
        app.mainMenu();
    }
}
