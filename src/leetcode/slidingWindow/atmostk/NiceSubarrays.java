package leetcode.slidingWindow.atmostk;

import java.util.PriorityQueue;

/**
 * LeetCode Problem: 1248. Count Number of Nice Subarrays
 * <p>
 * Question:
 * Given an array of integers `nums` and an integer `k`, a "nice" subarray is a subarray that has exactly `k` odd numbers in it.
 * Return the number of "nice" subarrays.
 * <p>
 * Example:
 * Input: nums = [1, 1, 2, 1, 1], k = 3
 * Output: 2
 * Explanation: The subarrays with exactly 3 odd numbers are [1, 1, 2, 1] and [1, 2, 1, 1].
 * <p>
 * Constraints:
 * - 1 <= nums.length <= 50000
 * - 1 <= nums[i] <= 10^5
 * - 1 <= k <= nums.length
 */
@SuppressWarnings("all")
public class NiceSubarrays {

    /**
     * A helper function that calculates the number of subarrays with AT MOST K odd numbers.
     * This is a standard sliding window template for "at most K" problems.
     *
     * @param nums The input array.
     * @param K    The maximum number of odd numbers allowed in a subarray.
     * @return The total count of subarrays with at most K odd numbers.
     */
    public static int subarrayAtMostK(int[] nums, int K) {
        int result = 0;
        int left = 0; // Left pointer of the sliding window
        int count = 0; // Counter for odd numbers within the current window

        // The right pointer expands the window
        for (int right = 0; right < nums.length; right++) {
            // If the number entering the window is odd, increment our count.
            if (nums[right] % 2 == 1) {
                count++;
            }

            // If the count of odd numbers exceeds K, the window is invalid.
            // We must shrink the window from the left until it becomes valid again.
            while (count > K) {
                // If the number leaving the window is odd, decrement our count.
                if (nums[left] % 2 == 1) {
                    count--;
                }
                left++; // Shrink the window
            }

            // --- The Core Insight ---
            // At this point, the window [left, right] is valid (has at most K odd numbers).
            // Any subarray ending at `right` that starts from `left` or later is also valid.
            // The number of such new subarrays is `right - left + 1`.
            // For example, if window is [A, B, C], the new subarrays are [C], [B, C], and [A, B, C].
            result += right - left + 1;
        }

        return result;
    }


    /**
     * Calculates the number of "nice" subarrays (exactly k odd numbers).
     *
     * @param nums The input array.
     * @param k    The exact number of odd numbers required.
     * @return The number of nice subarrays.
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        // --- The "At Most K" Principle ---
        // It's hard to directly count subarrays with EXACTLY k odd numbers using a single sliding window.
        // However, it's easy to count subarrays with AT MOST k odd numbers.
        // The trick is to use this relationship:
        // (Number of subarrays with EXACTLY k) = (Number of subarrays with AT MOST k) - (Number of subarrays with AT MOST k-1)
        // This works because the first term includes subarrays with 0, 1, ..., k-1, k odd numbers.
        // The second term includes subarrays with 0, 1, ..., k-1 odd numbers.
        // Subtracting them leaves only the count for exactly k.
        return subarrayAtMostK(nums, k) - subarrayAtMostK(nums, k - 1);
    }


    /**
     * The main method to test the implemented solution.
     */
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;

        // atMost(3) for [1,1,2,1,1] will count all subarrays with 0, 1, 2, or 3 odd numbers.
        // atMost(2) will count all subarrays with 0, 1, or 2 odd numbers.
        // The difference gives the count for exactly 3.
        System.out.println(numberOfSubarrays(nums, k)); // Expected: 2
    }

}