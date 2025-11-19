package leetcode.graph.traversal;

import java.util.LinkedList;
import java.util.Queue;


public class JumpGame_3 {

    /**
     * --- LeetCode 1306. Jump Game III ---
     *
     * Given an array of non-negative integers arr, you are initially positioned at
     * start index of the array. When you are at index i, you can jump to
     * i + arr[i] or i - arr[i], check if you can reach ANY index with value 0.
     *
     * Notice that you can not jump outside of the array at any time.
     *
     * Example 1:
     * Input: arr = [4,2,3,0,3,1,2], start = 5
     * Output: true
     * Explanation:
     * All possible ways to reach at index 3 with value 0 are:
     * index 5 -> index 4 -> index 1 -> index 3
     * index 5 -> index 6 -> index 4 -> index 1 -> index 3
     *
     * Example 2:
     * Input: arr = [4,2,3,0,3,1,2], start = 0
     * Output: true
     * Explanation:
     * One possible way to reach at index 3 with value 0 is:
     * index 0 -> index 4 -> index 1 -> index 3
     *
     * Example 3:
     * Input: arr = [3,0,2,1,2], start = 2
     * Output: false
     * Explanation: There is no way to reach at index 1 with value 0.
     */
    public static boolean canReach(int[] arr, int start) {

        int n = arr.length;
        // The queue stores the indices (nodes) we need to visit.
        // This is the standard data structure for BFS.
        Queue<Integer> queue = new LinkedList<>();

        // This `visited` array is CRUCIAL. It prevents us from getting
        // stuck in infinite loops. For example, if arr[1]=2 and arr[3]=2,
        // we could jump 1 -> 3 -> 1 -> 3 ... forever.
        boolean[] visited = new boolean[n];

        // We start our search at the given `start` index.
        queue.add(start);

        // The main BFS loop. It continues as long as there are still
        // indices in the queue to explore.
        while (!queue.isEmpty()) {

            // Get the next index from the front of the queue to visit.
            int currIndex = queue.poll();

            // Mark this index as visited *after polling it*.
            // This ensures we process it exactly once.
            visited[currIndex] = true;

            // --- GOAL CHECK ---
            // If the value at our current index is 0, we found it!
            if (arr[currIndex] == 0) {
                return true;
            }

            // --- EXPLORE NEIGHBORS (THE JUMPS) ---

            // 1. Calculate the "forward" jump (i + arr[i])
            int forwardIndex = currIndex + arr[currIndex];
            // 2. Calculate the "backward" jump (i - arr[i])
            int backwardIndex = currIndex - arr[currIndex];

            // --- Check and add the forward jump ---
            // 1. Is it in bounds? (>= 0 and < n)
            // 2. Have we *not* visited it already? (prevents cycles)
            if (forwardIndex >= 0 && forwardIndex < n && !visited[forwardIndex]) {
                // If it's valid and unvisited, add it to the queue
                // to be explored later.
                queue.add(forwardIndex);
            }

            // --- Check and add the backward jump ---
            // (Same exact logic as the forward jump)
            if (backwardIndex >= 0 && backwardIndex < n && !visited[backwardIndex]) {
                queue.add(backwardIndex);
            }
        }

        return false;
    }


    static void main(String[] args) {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        System.out.println("Test Case 1 (Expect true): " + canReach(arr, start));

        arr = new int[]{3, 0, 2, 1, 2};
        start = 2;
        System.out.println("Test Case 2 (Expect false): " + canReach(arr, start));
    }

}
