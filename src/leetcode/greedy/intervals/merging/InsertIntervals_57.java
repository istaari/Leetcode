package leetcode.greedy.intervals.merging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*
 * LeetCode 57: Insert Interval
 *
 * You are given an array of non-overlapping intervals `intervals` where
 * `intervals[i] = [start_i, end_i]` represent the start and the end of the
 * i-th interval and `intervals` is sorted in ascending order by `start_i`.
 *
 * You are also given an interval `newInterval = [start, end]` that
 * represents the start and end of another interval.
 *
 * Insert `newInterval` into `intervals` such that `intervals` is still
 * sorted in ascending order by `start_i` and `intervals` still does not
 * have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return `intervals` after the insertion.
 *
 * ---
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Example 3 (Edge Case):
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 *
 * ---
 *
 * Constraints:
 * 1. 0 <= intervals.length <= 10^4
 * 2. intervals[i].length == 2
 * 3. 0 <= start_i <= end_i <= 10^5
 * 4. intervals is sorted by start_i in ascending order.
 * 5. newInterval.length == 2
 * 6. 0 <= start <= end <= 10^5
 */
@SuppressWarnings("all")
public class InsertIntervals_57 {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for (int[] current : intervals) {

            // Case 1: Current is "before" New (or New has already been placed)
            // `newInterval == null` handles all intervals *after* Case 2 has occurred.
            if (newInterval == null || current[1] < newInterval[0]) {
                result.add(current);
                // Case 2: Current is "after" New
            } else if (current[0] > newInterval[1]) {
                // This is the first time we've seen an interval *after* newInterval.
                // Add the (potentially merged) newInterval.
                result.add(newInterval);
                // Add the current interval that comes after it.
                result.add(current);
                // Mark newInterval as placed.
                newInterval = null;
                // Case 3: Overlap
            } else {
                // Merge current into newInterval by updating newInterval's bounds.
                newInterval[0] = Math.min(newInterval[0], current[0]);
                newInterval[1] = Math.max(newInterval[1], current[1]);
            }
        }

        // If newInterval was never placed (i.e., it belongs at the end,
        // or the list was empty), add it now.
        if (newInterval != null) {
            result.add(newInterval);
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // NOTE: The first example here is *unsorted* and does not follow
        // the problem's constraints. The algorithm assumes a sorted input array.
        // If it were sorted as {{1, 3}, {8, 10}, {15, 18}}, the output would be:
        // [[1, 6], [8, 10], [15, 18]]
        int[][] intervals = {{1, 3}, {15, 18}, {8, 10}};
        int[] newInterval = {2, 6};
        System.out.println("Unsorted example (violates constraints): " + Arrays.deepToString(insert(intervals, newInterval)));

        // This is a valid, sorted example.
        // Input: [[1, 3], [15, 18]], newInterval = [4, 6]
        // Output: [[1, 3], [4, 6], [15, 18]]
        intervals = new int[][]{{1, 3}, {15, 18}};
        newInterval = new int[]{4, 6};
        System.out.println("Valid sorted example: " + Arrays.deepToString(insert(intervals, newInterval)));

        // Official Example 1:
        // Input: [[1, 3], [6, 9]], newInterval = [2, 5]
        // Output: [[1, 5], [6, 9]]
        intervals = new int[][]{{1, 3}, {6, 9}};
        newInterval = new int[]{2, 5};
        System.out.println("Official Example 1: " + Arrays.deepToString(insert(intervals, newInterval)));

        // Official Example 2:
        // Input: [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]], newInterval = [4, 8]
        // Output: [[1, 2], [3, 10], [12, 16]]
        intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        newInterval = new int[]{4, 8};
        System.out.println("Official Example 2: " + Arrays.deepToString(insert(intervals, newInterval)));
    }
}