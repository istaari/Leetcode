package leetcode.greedy.intervals.merging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
 * LeetCode 56: Merge Intervals
 *
 * Given an array of intervals where `intervals[i] = [start_i, end_i]`,
 * merge all overlapping intervals, and return an array of the
 * non-overlapping intervals that cover all the intervals in the input.
 *
 * ---
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * ---
 *
 * Constraints:
 * 1. 1 <= intervals.length <= 10^4
 * 2. intervals[i].length == 2
 * 3. 0 <= start_i <= end_i <= 10^4
 */
public class MergeIntervals_56 {

    public static int[][] merge(int[][] intervals) {
        // 1. Sort the intervals by start time.
        // Example: [[1,3],[2,6],[15,18],[8,10]] -> [[1,3],[2,6],[8,10],[15,18]]
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();
        // 2. Initialize the result list with the first interval.
        result.add(intervals[0]); // the result is now [[1,3]]

        // 3. Iterate through all intervals
        for (int[] currentInterval : intervals) {
            int[] lastInterval = result.getLast();

            // 4. Check for Overlap
            if (currentInterval[0] <= lastInterval[1]) {
                result.removeLast();
                int min = Math.min(lastInterval[0], currentInterval[0]);
                int max = Math.max(lastInterval[1], currentInterval[1]);
                result.add(new int[]{min, max});
            } else {
                result.add(currentInterval);
            }
        }

        // 6. Return the result
        return result.toArray(new int[result.size()][]);
    }

    static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {15, 18}, {8, 10}};
        int[][] result = merge(intervals);
        // After sorting: [[1, 3], [2, 6], [8, 10], [15, 18]]
        // After merging: [[1, 6], [8, 10], [15, 18]]
        System.out.println(Arrays.deepToString(result));
    }
}