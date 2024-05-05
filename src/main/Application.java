package main;

import main.dataStructure.ArrayList;
import main.dataStructure.Point;
import main.model.BoundingRectangle;
import main.model.Map2D;
import main.model.Place;
import main.model.ServiceType;
import main.view.Menu;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        mainMenu();
    }

    private static void start() {
        Menu app = new Menu();
        app.mainMenu();
    }

    private static void mainMenu() {
        System.out.println("1. Start application");
        System.out.println("2. Start Insert Testing");
        System.out.println("3. Start Remove Testing");
        System.out.println("4. Start Search Testing");
        System.out.print(">> Enter your choice: ");
        try (Scanner sc = new Scanner(System.in)) {
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> start();
                case 2 -> loadTesting();
                case 3 -> removeTesting();
                case 4 -> searchTesting();
                default -> System.exit(0);
            }
        }
    }

    private static void loadTesting() {
        Map2D map = Map2D.getInstance();
        System.out.print("Number of places: ");
        Scanner sc = new Scanner(System.in);
        int numPlaces = sc.nextInt();
        int failureCount = 0;
        System.out.println("Loading testing data...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numPlaces; i++) {
            int x = (int) (Math.random() * Map2D.MAP_SIDE);
            int y = (int) (Math.random() * Map2D.MAP_SIDE);
            String name = "Place " + i;
            ServiceType[] services = ServiceType.values();
            Place place = new Place(new Point(x,y), name, services);
            if (!map.insert(place)) {
                failureCount++;
                System.out.println(i + ". Failed to Insert: " + place);
            };
            progressBar(i - failureCount, numPlaces);
            System.out.print(" " + (System.currentTimeMillis() - startTime) + "ms");

        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        printResult(elapsedTime, failureCount, numPlaces);

        System.out.print("Retry? (y/n)");
        String retry = sc.next();

        if (retry.equalsIgnoreCase("y")) {
            loadTesting();
        } else {
            mainMenu();
        }
    }

    private static void progressBar(int current, int max) {
        int width = 50;
        double progress = (double) current/max;
        int numChars = (int) (progress * width);
        String progressBar = "[" + "#".repeat(numChars) + " ".repeat(width-numChars) + "]";
        System.out.print("\r" + progressBar + " " + progress*100 + "%");
    }

    private static ArrayList<Place> generateData(Map2D map) {
        System.out.print("Number of places: ");
        Scanner sc = new Scanner(System.in);
        int numPlaces = sc.nextInt();
        sc.close();
        System.out.println("Loading testing data...");
        ArrayList<Place> places = new ArrayList<>(numPlaces);
        for (int i = 0; i < numPlaces; i++) {
            int x = (int) (Math.random() * Map2D.MAP_SIDE);
            int y = (int) (Math.random() * Map2D.MAP_SIDE);
            String name = "Place " + i;
            ServiceType[] services = ServiceType.values();
            Place place = new Place(new Point(x,y), name, services);
            if (map.insert(place))
                places.add(place);
            progressBar(map.numPlaces, numPlaces);
        }


        System.out.println();
        return places;
    }

    private static void printResult(long elapsedTime, int failureCount, int size) {
        System.out.println();
        System.out.println("*".repeat(60));
        System.out.println("Testing data with " + size + " places finished in " + elapsedTime + " ms");
        double failureRate = (double) failureCount / (double) size;
        System.out.println("Failures: " + failureCount + ", Failure Rate: " + failureRate*100 + "%");
        System.out.println("*".repeat(60));
    }

    private static void removeTesting() {

        Map2D map = Map2D.getInstance();
        ArrayList<Place> places = generateData(map);
        int failureCount = 0;
        System.out.println();
        System.out.println("Removing test data...");
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < places.size(); i++) {
            if (!map.remove(places.get(i))) {
                failureCount++;
//                System.out.println(i + ". Failed to removed: " + places.get(i));
            }
            progressBar(i - failureCount, places.size());

        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        printResult(elapsedTime, failureCount, places.size());
    }

    private static void searchTesting() {
        Map2D map = Map2D.getInstance();
        BoundingRectangle box = BoundingRectangle.getInstance();
        box.clear();

        ArrayList<Place> places = generateData(map);
        System.out.println("Map size: " + map.numPlaces);
        int failureCount = 0;

        System.out.println("Searching testing data with exact match...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);
            box.clear();
            Place query = map.searchPlace(box, place);
            if (query == null || !query.equals(place)) {
                failureCount++;
            }
            progressBar(i, places.size());

        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        printResult(elapsedTime, failureCount, places.size());

        System.out.println("Searching testing data with same services...");
        Place place = new Place();
        ArrayList<ServiceType> services = new ArrayList<>(ServiceType.values());
        place.setServices(services);
        box.clear();

        startTime = System.currentTimeMillis();
        System.out.println(box);
        boolean running = true;
        map.search(box, place);
        System.out.println(box);
        box.showPlaces();
        endTime = System.currentTimeMillis();
        failureCount = (box.getPlaces().size() == map.numPlaces) ? 0 : 1;
        printResult(endTime - startTime, failureCount, 1);

    }


}
