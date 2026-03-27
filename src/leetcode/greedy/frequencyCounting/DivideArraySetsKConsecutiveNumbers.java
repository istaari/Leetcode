package leetcode.greedy.frequencyCounting;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1296. Divide Array in Sets of K Consecutive Numbers
 * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 *
 * Given an array of integers nums and a positive integer k, check whether it is
 * possible to divide this array into sets of k consecutive numbers.
 *
 * Example 1: nums = [1,2,3,3,4,4,5,6], k = 4 -> true ([1,2,3,4] and [3,4,5,6])
 * Example 2: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3 -> true
 * Example 3: nums = [1,2,3,4], k = 3 -> false
 *
 * Constraints:
 *   1 <= k <= nums.length <= 10^5
 *   1 <= nums[i] <= 10^9
 *
 * ---
 * Approach: Greedy with min-heap
 *
 * Always start a new group from the smallest available number.
 * Use a min-heap to always access the smallest element.
 * For each group, greedily take k consecutive numbers starting from the minimum.
 *
 * (Can also be solved with TreeMap for O(n log n) instead of O(n * k).)
 *
 * Time:  O(n * k) with heap removal
 * Space: O(n)
 */
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
