package leetcode.greedy.intervals.overlap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms2_253 {

    /*
     * Problem Statement
     * Given an array of meeting time intervals where each interval is represented
     * as an array of two integers [start, end], find the minimum number of conference rooms
     * required to hold all meetings.
     *
     * Example
     * Input: [[0, 30], [5, 10], [15, 20]]
     * Output: 2
     *
     * In the above example:
     * You need two rooms because [0, 30] overlaps with [5, 10] and [15, 20].
     */
    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort the intervals by their start times
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // Use a min-heap to track the end time of meetings
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Add the first meeting's end time to the heap
        minHeap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            // If the earliest ending meeting ends before the next meeting starts
            if (intervals[i][0] >= minHeap.peek()) {
                // Remove the earliest ending meeting
                minHeap.poll();
            }
            // Add the current meeting's end time to the heap
            minHeap.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum rooms required
        return minHeap.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(minMeetingRooms(intervals)); // Output: 2
    }


}
