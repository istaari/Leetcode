package leetcode.greedy.frequencyCounting;

import java.util.PriorityQueue;
import java.util.Queue;

public class DivideArraySetsKConsecutiveNumbers {

    // Can be solved using tree map
    public boolean isPossibleDivide(int[] nums, int k) {
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
        }

        while (!minHeap.isEmpty()) {
            int start = minHeap.poll(); // Remove first element
            for (int i = 1; i < k; i++) {
                if (minHeap.remove(start + i)) {
                    continue;
                }
                return false;
            }
        }

        return true;
    }
}
