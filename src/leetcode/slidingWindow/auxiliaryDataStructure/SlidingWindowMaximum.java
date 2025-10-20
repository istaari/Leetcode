package leetcode.slidingWindow.auxiliaryDataStructure;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode Problem: 239. Sliding Window Maximum
 * <p>
 * Question:
 * You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right.
 * You can only see the `k` numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 * <p>
 * Example 1:
 * Input: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
 * Output: [3, 3, 5, 5, 6, 7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * Constraints:
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

    @SuppressWarnings("all")
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // --- The Monotonic Deque Strategy ---
        // The core idea is to use a Deque (Double-Ended Queue) to store indices of elements in the current window.
        // We maintain a special property: the indices in the deque always point to numbers in decreasing order.
        // This makes the element at the front of the deque always the maximum in the current window.

        int n = nums.length;
        // The number of windows, and thus the size of the result array, is n - k + 1.
        int[] result = new int[n - k + 1];
        int index = 0; // Pointer for the result array.

        // The deque stores indices, not the numbers themselves.
        Deque<Integer> deque = new ArrayDeque<>();

        // Iterate through the array with the right pointer 'i' of the window.
        for (int i = 0; i < n; i++) {
            // Step 1: Clean the front of the deque.
            // Remove indices from the left that are no longer part of the current window.
            // The window is from [i-k+1, i], so any index <= i-k is out of bounds.
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }

            // Step 2: Maintain the monotonic decreasing property.
            // Before adding the current element's index, remove all indices from the back
            // of the deque that point to values smaller than or equal to the current value.
            // This is because these smaller elements can never be the maximum in any future window
            // that also includes the current, larger element.
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }

            // Step 3: Add the current element's index to the back of the deque.
            deque.addLast(i);

            // Step 4: Record the maximum for the current window.
            // The window is considered full once 'i' has reached k-1.
            // The index at the front of the deque always corresponds to the maximum element in the current window.
            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }


    static void main(String[] args) {
        int[] nums = {7, 2, 4};
        int k = 2;
        // Window 1: [7, 2], Max = 7
        // Window 2: [2, 4], Max = 4
        System.out.println(Arrays.toString(maxSlidingWindow(nums, k))); // Expected: [7, 4]

        int[] nums2 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k2 = 3;
        System.out.println(Arrays.toString(maxSlidingWindow(nums2, k2))); // Expected: [3, 3, 5, 5, 6, 7]
    }
}