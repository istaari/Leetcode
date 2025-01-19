package leetcode.graph.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame_3 {

    public static boolean canReach(int[] arr, int start) {

        int n = arr.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.add(start);
        while (!queue.isEmpty()) {

            int currIndex = queue.poll();
            visited[currIndex] = true;

            if (arr[currIndex] == 0) return true;

            int leftIndex = currIndex + arr[currIndex];
            int rightIndex = currIndex - arr[currIndex];

            if (leftIndex >= 0 && leftIndex < n && !visited[leftIndex]) {
                queue.add(leftIndex);
            }

            if (rightIndex >= 0 && rightIndex < n && !visited[rightIndex]) {
                queue.add(rightIndex);
            }

        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;

        arr = new int[]{3, 0, 2, 1, 2};
        start = 2;
        System.out.println(canReach(arr, start));
    }
}
