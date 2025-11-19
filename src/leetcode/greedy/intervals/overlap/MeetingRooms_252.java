package leetcode.greedy.intervals.overlap;

import java.util.Arrays;


/*
 * LeetCode 252: Meeting Rooms
 *
 * Given an array of meeting time intervals `intervals` where
 * `intervals[i] = [start_i, end_i]`, determine if a person could
 * attend all meetings.
 *
 * ---
 *
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Explanation: [0,30] overlaps with [5,10] and [15,20].
 *
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 * Explanation: [2,4] and [7,10] do not overlap.
 *
 * ---
 *
 * Constraints:
 * 1. 0 <= intervals.length <= 10^4
 * 2. intervals[i].length == 2
 * 3. 0 <= start_i <= end_i <= 10^6
 */
public class MeetingRooms_252 {

    public static boolean canAttendMeetings(int[][] intervals) {
        // 1. Handle edge case for 0 or 1 meetings.
        if (intervals == null || intervals.length <= 1) {
            return true;
        }

        // 2. Sort the intervals by their start time (a[0]).
        //    Example 1 becomes: [[0, 30], [5, 10], [15, 20]]
        //    Example 2 becomes: [[2, 4], [7, 10]]
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 3. Iterate from the second meeting (i=1).
        for (int i = 1; i < intervals.length; i++) {
            int currentMeetingStart = intervals[i][0];
            int previousMeetingEnd = intervals[i - 1][1];

            // 4. Check for overlap.
            // If the current meeting starts *before* the previous one ends,
            // they conflict.
            if (currentMeetingStart < previousMeetingEnd) {
                // Example 1, i=1:
                // currentStart = 5, previousEnd = 30
                // (5 < 30) is true. Return false.
                return false;
            }
            // Example 2, i=1:
            // currentStart = 7, previousEnd = 4
            // (7 < 4) is false. Loop continues.
        }

        // 5. If the loop completes, no overlaps were found.
        return true;
    }

    static void main(String[] args) {
        // Example 1: [[0, 30], {5, 10}, {15, 20}]
        // Sorted: [[0, 30], [5, 10], [15, 20]]
        // Check i=1: [5, 10] vs [0, 30]. Is 5 < 30? Yes. Return false.
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(canAttendMeetings(intervals)); // Output: false

        // Example 2: [[7, 10], [2, 4]]
        // Sorted: [[2, 4], [7, 10]]
        // Check i=1: [7, 10] vs [2, 4]. Is 7 < 4? No.
        // Loop finishes. Return true.
        intervals = new int[][]{{7, 10}, {2, 4}};
        System.out.println(canAttendMeetings(intervals)); // Output: true
    }
}