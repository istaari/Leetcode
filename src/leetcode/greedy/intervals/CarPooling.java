package leetcode.greedy.intervals;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1094. Car Pooling
 * https://leetcode.com/problems/car-pooling/
 *
 * There is a car with 'capacity' empty seats. The vehicle only drives east
 * (i.e., it cannot turn around and drive west).
 *
 * You are given the integer capacity and an array trips where
 * trips[i] = [numPassengers_i, from_i, to_i] indicates that the i-th trip has
 * numPassengers_i passengers and the locations to pick them up and drop them off
 * are from_i and to_i respectively.
 *
 * Return true if it is possible to pick up and drop off all passengers for all
 * the given trips, or false otherwise.
 *
 * Example 1: trips = [[2,1,5],[3,3,7]], capacity = 4 -> false
 * Example 2: trips = [[2,1,5],[3,3,7]], capacity = 5 -> true
 *
 * Constraints:
 *   1 <= trips.length <= 1000
 *   1 <= capacity <= 10^5
 *   0 <= from_i < to_i <= 1000
 *
 * ---
 * Approach: Sweep Line (event-based)
 *
 * Convert each trip into two events:
 *   - Pickup at location from:  +numPassengers
 *   - Drop-off at location to:  -numPassengers
 *
 * Sort events by location (drop-offs before pickups at same location).
 * Sweep through events, tracking current passengers. If ever > capacity, return false.
 *
 * Time:  O(n log n)
 * Space: O(n)
 */
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
