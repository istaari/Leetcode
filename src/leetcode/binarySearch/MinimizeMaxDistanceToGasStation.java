package leetcode.binarySearch;

import java.util.Arrays;

/**
 * LeetCode Problem 774: Minimize Max Distance to Gas Station (Premium)
 *
 * You are given an integer array station that represents the positions of gas
 * stations on the x-axis. You are also given an integer k.
 *
 * You should add k new gas stations. You can add the stations anywhere on the
 * x-axis, and not necessarily on integer positions.
 *
 * Let penalty() be the maximum distance between adjacent gas stations after
 * adding k new stations.
 *
 * Return the smallest possible value of penalty(). Answers within 10^-6 of the
 * actual answer will be accepted.
 *
 * This is a classic "Binary Search on the Answer" problem, specifically on a
 * continuous domain (real numbers).
 *
 * Example 1:
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 9
 * Output: 0.50000
 *
 * Example 2:
 * Input: stations = [23, 24, 36, 39, 46, 56, 57, 65, 84, 98], k = 1
 * Output: 14.00000
 */
public class MinimizeMaxDistanceToGasStation {

    public static double minmaxGasDist(int[] stations, int k) {
        // Define the search space for the answer (the distance).
        // The smallest possible distance is 0.
        double low = 0;
        // The largest possible distance is the initial max gap between stations.
        double high = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            high = Math.max(high, stations[i + 1] - stations[i]);
        }
        
        // We use a precision-based termination condition because the answer is a double.
        double epsilon = 1e-6;

        // Binary search for the smallest possible distance `d` that is achievable.
        while ((high - low) > epsilon) {
            double mid = low + (high - low) / 2.0;

            if (isPossible(mid, stations, k)) {
                // A max distance of `mid` is possible. Let's see if we can achieve an
                // even smaller max distance. So, we make `mid` our new upper bound.
                high = mid;
            } else {
                // A max distance of `mid` is impossible with k stations. We must
                // allow for a larger distance.
                low = mid;
            }
        }

        // When the loop terminates, `low` and `high` are extremely close to the optimal answer.
        return high;
    }


    private static boolean isPossible(double maxDist, int[] stations, int k) {
        int stationsNeeded = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            double gap = stations[i + 1] - stations[i];
            
            // If a gap is larger than our allowed maxDist, we must add stations.
            // The number of new stations needed to break a gap into segments of
            // size at most `maxDist` is `ceil(gap / maxDist) - 1`.
            if (gap > maxDist) {
                stationsNeeded += (int) (Math.ceil(gap / maxDist) - 1);
            }
        }
        // If the total stations we need is within our budget `k`, it's possible.
        return stationsNeeded <= k;
    }

    public static void main(String[] args) {
        int[] stations1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k1 = 9;
        System.out.println("Input: stations = " + Arrays.toString(stations1) + ", k = " + k1);
        System.out.printf("Output: %.5f\n", minmaxGasDist(stations1, k1)); // Expected: 0.50000

        int[] stations2 = {23, 24, 36, 39, 46, 56, 57, 65, 84, 98};
        int k2 = 1;
        System.out.println("\nInput: stations = " + Arrays.toString(stations2) + ", k = " + k2);
        System.out.printf("Output: %.5f\n", minmaxGasDist(stations2, k2)); // Expected: 14.00000
    }
}
