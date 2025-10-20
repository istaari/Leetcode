package leetcode.greedy.intervals;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarPooling {

    public static boolean carPooling(int[][] trips, int capacity) {
        List<int[]> location = new ArrayList<>();

        // Convert trips into pickup/drop-off events
        for (int[] trip : trips) {
            location.add(new int[]{trip[1], trip[0]});  // Pickup event (start location, numPassengers)
            location.add(new int[]{trip[2], -trip[0]}); // Drop-off event (end location, -numPassengers)
        }

        Comparator<int[]> comparator = (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1]; // Pickup before drop-off
            return a[0] - b[0]; // Sort by location
        };

        location.sort(comparator);
        int currentCapacity = 0;
        for (int[] trip : location) {
            currentCapacity += trip[1];

            if (currentCapacity > capacity) return false;
        }

        return true;
    }


    public static void main(String[] args) {
        int[][] trips = {{2, 1, 5}, {3, 3, 7}};
        int capacity = 5;
        System.out.println(carPooling(trips, capacity));
    }
}
