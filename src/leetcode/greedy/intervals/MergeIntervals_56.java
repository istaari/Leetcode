package leetcode.greedy.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals_56 {

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for (int[] currentInterval : intervals) {
            int[] lastInterval = result.get(result.size() - 1);
            if (currentInterval[0] <= lastInterval[1]) {
                result.remove(result.size() - 1);
                result.add(new int[]{lastInterval[0], Math.max(lastInterval[1], currentInterval[1])});
            } else {
                result.add(currentInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {15, 18}, {8, 10}};
        int[][] result = merge(intervals);
        System.out.println(Arrays.deepToString(result));
    }
}
