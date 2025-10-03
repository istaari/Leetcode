

package leetcode.array;

import java.util.HashMap;
import java.util.Map;


// LeetCode 594: Longest Harmonious Subsequence
//
// Problem Statement:
// We define a harmonious array as an array where the difference between its maximum value and
// its minimum value is exactly 1.
//
// Given an integer array `nums`, return the length of its longest harmonious subsequence
// among all its possible subsequences.
//
// A subsequence of an array is a sequence that can be derived from the array by deleting some
// or no elements without changing the order of the remaining elements.
//
// Example:
// Input: nums = [1,3,2,2,5,2,3,7]
// Output: 5
// Explanation: The longest harmonious subsequence is [3,2,2,2,3]. Its max is 3, min is 2,
// and the difference is 1. The length is 5.
public class LongestHarmoniousSubsequence {

    /**
     * ### Intuition Behind the Solution
     *
     * 1.  **Understanding "Harmonious Subsequence":**
     * - The definition is key: a subsequence where `max - min = 1`.
     * - This means a harmonious subsequence can only be formed by two kinds of numbers: some number `x` and its neighbor `x + 1`.
     * - For example, a subsequence `[2,3,2,3,2]` is harmonious, but `[2,3,4]` is not (max-min=2).
     *
     * 2.  **Simplifying the Problem:**
     * - Since the order of elements in a subsequence doesn't matter for its min/max values, this problem isn't about finding a contiguous subarray. It's about finding counts.
     * - The problem boils down to: "Find a number `x` in the array such that `x+1` also exists, and the sum of their frequencies is maximized."
     *
     * 3.  **The Algorithm's Plan:**
     * - We can't solve this without knowing the counts of each number. The perfect tool for this is a **HashMap** to store the frequency of each number.
     *
     * - **Pass 1: Count Frequencies.**
     * - We iterate through the entire `nums` array once and populate our frequency map. For `[1,3,2,2,5,2,3,7]`, the map would become `{1=1, 2=3, 3=2, 5=1, 7=1}`.
     *
     * - **Pass 2: Find the Longest Pair.**
     * - Now that we have the counts, we can iterate through the numbers again (or more efficiently, through the keys of our map).
     * - For each number `num` we look at, we check if its harmonious partner `num + 1` also exists as a key in our map.
     * - If it does, we calculate the potential length of this harmonious subsequence by adding their counts: `count.get(num) + count.get(num + 1)`.
     * - We keep track of the maximum length we find during this process.
     *
     * - **Final Result:** After checking all the numbers for their harmonious partners, the `maxLen` variable will hold the length of the longest harmonious subsequence. If no such pair was found, it will remain 0.
     *
     * This two-pass approach cleanly and efficiently solves the problem by first gathering the necessary data (counts) and then analyzing it.
     */
    public static int findLHS(int[] nums) {
        // Pass 1: Count the frequency of each number.
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        int maxLen = 0;

        // Pass 2: Iterate through the keys (unique numbers) to find harmonious pairs.
        // Note: Iterating through the keySet is slightly more efficient than iterating through
        // the original nums array again, as it avoids re-checking the same number.
        for (int num : count.keySet()) {
            // Check if the harmonious partner (num + 1) exists in our map.
            if (count.containsKey(num + 1)) {
                // If it exists, calculate the combined length and update the max.
                maxLen = Math.max(maxLen, count.get(num) + count.get(num + 1));
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Example 1: The user's original example
        int[] nums1 = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println("For [1,3,2,2,5,2,3,7], LHS length is: " + findLHS(nums1));
    }
}