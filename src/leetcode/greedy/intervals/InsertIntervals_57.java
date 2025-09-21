package leetcode.greedy.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class InsertIntervals_57 {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for (int[] current : intervals) {

            // don't overlap
            if (newInterval == null || current[1] < newInterval[0]) {
                result.add(current);
            // adding new interval before the current item
            } else if (current[0] > newInterval[1]) {
                result.add(newInterval);
                result.add(current);
                newInterval = null;
            } else {
                newInterval[0] = Math.min(newInterval[0], current[0]);
                newInterval[1] = Math.max(newInterval[1], current[1]);
            }
        }

        if (newInterval != null) {
            result.add(newInterval);
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {15, 18}, {8, 10}};
        int[] newInterval = {2, 6};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));

        intervals = new int[][]{{1, 3}, {15, 18}};
        newInterval = new int[]{4, 6};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }
}
