package leetcode.greedy.intervals.scheduling;

import java.util.Arrays;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * There are some spherical balloons taped onto a flat wall represented by the
 * XY-plane. Balloons are represented as a 2D integer array points where
 * points[i] = [x_start, x_end] denotes a balloon whose horizontal diameter
 * stretches between x_start and x_end. You do not know the exact y-coordinates.
 *
 * Arrows can be shot up vertically (in the positive y-direction) from different
 * points along the x-axis. A balloon with x_start <= arrow <= x_end bursts.
 *
 * Return the minimum number of arrows to burst all balloons.
 *
 * Example 1:
 *   Input: points = [[10,16],[2,8],[1,6],[7,12]]
 *   Output: 2
 *   Explanation: Shoot at x=6 (bursts [2,8] and [1,6]) and x=11 (bursts [10,16] and [7,12]).
 *
 * Example 2:
 *   Input: points = [[1,2],[3,4],[5,6],[7,8]]
 *   Output: 4 (no overlaps)
 *
 * Example 3:
 *   Input: points = [[1,2],[2,3],[3,4],[4,5]]
 *   Output: 2 (shoot at x=2 and x=4)
 *
 * Constraints:
 *   1 <= points.length <= 10^5
 *   points[i].length == 2
 *   -2^31 <= x_start <= x_end <= 2^31 - 1
 *
 * ---
 * Approach: Greedy — sort by end, count non-overlapping groups
 *
 * This is equivalent to the classic "Activity Selection" / "Interval Scheduling
 * Maximization" problem:
 *   - Sort balloons by their END coordinate.
 *   - Greedily shoot an arrow at the end of the first balloon.
 *   - Skip all balloons that this arrow also bursts (overlap with arrow position).
 *   - When a balloon starts AFTER the arrow, we need a new arrow.
 *
 * This is the complement of NonOverlappingIntervals (LC 435):
 *   - LC 435: count intervals to REMOVE = total - maxNonOverlapping
 *   - LC 452: count groups of overlapping intervals = arrows needed
 *
 * Time:  O(n log n)
 * Space: O(1)
 */
public class MinimumArrowsBurstBalloons {

    public static int findMinArrowShots(int[][] points) {
        // Sort by end coordinate
        // Use Integer.compare to avoid overflow with extreme int values
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int arrowPos = points[0][1]; // Shoot first arrow at end of first balloon

        for (int i = 1; i < points.length; i++) {
            // If this balloon starts after the current arrow position,
            // it wasn't burst -> need a new arrow
            if (points[i][0] > arrowPos) {
                arrows++;
                arrowPos = points[i][1]; // Shoot at end of this balloon
            }
            // Otherwise, this balloon overlaps and is already burst
        }

        return arrows;
    }

    public static void main(String[] args) {
        int[][] points1 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println("Example 1: " + findMinArrowShots(points1)); // 2

        int[][] points2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        System.out.println("Example 2: " + findMinArrowShots(points2)); // 4

        int[][] points3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println("Example 3: " + findMinArrowShots(points3)); // 2
    }
}
