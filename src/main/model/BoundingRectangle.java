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
import main.dataStructure.Rectangle;

public class BoundingRectangle {
    private static final int MAX_QUERIES = 50;
    private static final int MIN_DISTANCE = 100;
    private static final int MAX_DISTANCE = 100000;

    private Rectangle boundary;
    private Place placeCenter;
    private ArrayList<Place> places;
    private SortOption sortOption;
    private DistanceType distanceType;
    private double distance;

    public enum SortOption {
        DISTANCE_ASC, DISTANCE_DESC, NAME_ASC, NAME_DESC
    }

    public enum DistanceType {
        NEAR, WALKING, DRIVING, FAR, MAP
    }

    private BoundingRectangle() {
        this.boundary = new Rectangle();
        this.places = new ArrayList<>(Map2D.MAX_PLACES);
        this.placeCenter = new Place();
        this.sortOption = SortOption.DISTANCE_DESC;
        this.distanceType = DistanceType.NEAR;
        setDistance(-1);
    }

    private static class SingletonHelper {
        private static final BoundingRectangle INSTANCE = new BoundingRectangle();
    }

    public static BoundingRectangle getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private boolean compareForSort(Place place, Place other) {
        return switch (sortOption) {
            case DISTANCE_ASC -> place.distance(placeCenter) < other.distance(placeCenter);
            case DISTANCE_DESC -> place.distance(placeCenter) > other.distance(placeCenter);
            case NAME_ASC -> place.getName().compareTo(other.getName()) < 0;
            case NAME_DESC -> place.getName().compareTo(other.getName()) > 0;
        };
    }

    public void setDistance(int distance) {
        if (distance > MIN_DISTANCE && distance < MAX_DISTANCE) {
            this.distance = distance;
            return;
        }

        if (distance == -1) {
            this.distance = switch (distanceType) {
                case NEAR -> MIN_DISTANCE;
                case WALKING -> MIN_DISTANCE * 10;
                case DRIVING -> MIN_DISTANCE * 100;
                case FAR -> MAX_DISTANCE;
                case MAP -> Map2D.MAP_SIDE / 2.0;
            };
        }
    }

    public void adjust(Place center, int distance) {
        this.placeCenter = center;
        setDistance(distance);
        this.boundary = new Rectangle(center.getLocation(), (int) this.distance, (int) this.distance);
        places.clear();
    }

    public void clear() {
        int side = Map2D.MAP_SIDE;
        Rectangle boundary = new Rectangle(0, side, side, side);
        Point center = boundary.getCenter();
        Place placeCenter = new Place();
        placeCenter.setLocation(center);

        this.distance = center.distance(boundary.getLocation());
        setPlaceCenter(placeCenter);
        setBoundary(boundary);
        this.places.clear();
    }

    public Rectangle getBoundary() {
        return boundary;
    }

    public void setBoundary(Rectangle boundary) {
        this.boundary = boundary;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    public Place getPlaceCenter() {
        return placeCenter;
    }

    public void setPlaceCenter(Place center) {
        this.placeCenter = center;
    }

    public SortOption getSortOption() {
        return sortOption;
    }

    public void setSortOption(SortOption sortOption) {
        this.sortOption = sortOption;
    }

    public DistanceType getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(DistanceType distanceType) {
        this.distanceType = distanceType;
    }

    public double getDistance() {
        return distance;
    }

    public boolean addPlace(Place place) {
        if (!places.contains(place) && placeCenter.distance(place) <= distance) {
            return places.add(place);
        }
        return false;
    }

    public void addPlaces(ArrayList<Place> places) {
        for (int i = 0; i < places.size(); i++) {
            this.addPlace(places.get(i));
        }
    }

    public boolean removePlace(Place place) {
        return places.remove(place);
    }

    public void showPlaces() {
        if (places.isEmpty()) {
            System.out.println("No places to show.");
            return;
        }

        for (int i = 0; i < places.size(); i++) {
            if (i >= MAX_QUERIES)
                break;
            System.out.println(i + 1 + ". Distance: " + places.get(i).distance(placeCenter) + ", " + places.get(i));
        }
    }

    public void sortPlaces() {
        ArrayList<Place> sortedPlaces = mergeSort(places);
        setPlaces(sortedPlaces);
    }

    public ArrayList<Place> mergeSort(ArrayList<Place> places) {
        if (places.size() <= 1)
            return places;

        int mid = places.size() / 2;
        ArrayList<Place> leftHalf = new ArrayList<>(mid);
        ArrayList<Place> rightHalf = new ArrayList<>(mid);

        for (int i = 0; i < places.size(); i++) {
            if (i >= mid) {
                rightHalf.add(places.get(i));
            } else {
                leftHalf.add(places.get(i));
            }
        }

        ArrayList<Place> sortedLeft = mergeSort(leftHalf);
        ArrayList<Place> sortedRight = mergeSort(rightHalf);

        return merge(sortedLeft, sortedRight);
    }

    private ArrayList<Place> merge(ArrayList<Place> leftHalf, ArrayList<Place> rightHalf) {
        ArrayList<Place> merged = new ArrayList<>(leftHalf.size() + rightHalf.size());
        int i = 0;
        int j = 0;

        while (i < leftHalf.size() && j < rightHalf.size()) {
            if (compareForSort(leftHalf.get(i), rightHalf.get(j))) {
                merged.add(leftHalf.get(i));
                i++;
            } else {
                merged.add(rightHalf.get(j));
                j++;
            }
        }

        // Add remaining elements from leftHalf, if any
        while (i < leftHalf.size()) {
            merged.add(leftHalf.get(i));
            i++;
        }

        // Add remaining elements from rightHalf, if any
        while (j < rightHalf.size()) {
            merged.add(rightHalf.get(j));
            j++;
        }

        return merged;
    }

    @Override
    public String toString() {
        return "BoundingRectangle{" + "\n" +
                "boundary=" + boundary + "\n" +
                ", placeCenter=" + placeCenter + "\n" +
                ", total_places=" + places.size() +
                ", distance=" + distance + "\n" +
                '}';
    }
}