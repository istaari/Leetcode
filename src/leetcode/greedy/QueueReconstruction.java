package leetcode.greedy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QueueReconstruction {

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0]; // Sort by height descending
            return a[1] - b[1]; // Sort by k ascending
        });

        List<int[]> list = new LinkedList<>();
        for (int[] current : people) {
            list.add(current[1], new int[]{current[0], current[1]});
        }

        return list.toArray(new int[people.length][]);
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }

}
