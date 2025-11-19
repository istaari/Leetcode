package leetcode.greedy.intervals.scheduling;

import java.util.Arrays;


/*
 * LeetCode 435: Non-overlapping Intervals
 *
 * Given an array of intervals `intervals` where `intervals[i] = [start_i, end_i]`,
 * return the minimum number of intervals you need to remove to make the rest
 * of the intervals non-overlapping.
 *
 * ---
 *
 * Example 1:
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are
 * non-overlapping.
 *
 * Example 2:
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the
 * intervals non-overlapping.
 *
 * Example 3:
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're
 * already non-overlapping.
 *
 * ---
 *
 * Constraints:
 * 1. 1 <= intervals.length <= 10^5
 * 2. intervals[i].length == 2
 * 3. -5 * 10^4 <= start_i < end_i <= 5 * 10^4
 */
public class NonOverlappingIntervals_435 {


    public static int eraseOverlapIntervals(int[][] intervals) {
        // Handle edge case
        if (intervals.length == 0) return 0;

        // 1. Sort by end time
        // Example: [[1,2], [2,3], [3,4], [1,3]]
        // Sorted:  [[1,2], [2,3], [1,3], [3,4]] - Ascending by end time

        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        // 2. Initialize: Keep the first interval
        int end = intervals[0][1]; // end = 2 (from [1,2])
        int count = 1;             // count = 1 (we kept [1,2])

        // 3. Iterate and Select
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }

        // 4. Return result
        // Total intervals = 4
        // Kept intervals (count) = 3
        // Removed = 4 - 3 = 1
        return intervals.length - count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(intervals)); // Output: 1
    }

}