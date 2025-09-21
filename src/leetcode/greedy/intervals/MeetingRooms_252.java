package leetcode.greedy.intervals;

import java.util.Arrays;

public class MeetingRooms_252 {

    /**
     * Given an array of meeting time intervals where intervals[i] = [start, end], determine if a person could attend all meetings.
     *
     * @param intervals array of meeting time intervals
     * @return true if a person could attend all meetings, false otherwise
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) return false;
            end = intervals[i][1];
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(canAttendMeetings(intervals));
        intervals = new int[][]{{7, 10}, {2, 4}};
        System.out.println(canAttendMeetings(intervals));
    }
}
