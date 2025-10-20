package leetcode.greedy.constructive;

/**
 * LeetCode Problem: 376. Wiggle Subsequence
 * <p>
 * Question:
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative.
 * The first difference (if one exists) may be either positive or negative. A sequence with one or two differing elements is a wiggle sequence.
 * Given an integer array `nums`, return the length of the longest wiggle subsequence of `nums`.
 * <p>
 * Example:
 * Input: nums = [1, 7, 4, 9, 2, 5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 * <p>
 * Constraints:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class WiggleSubsequence {

    public static int wiggleMaxLength(int[] nums) {
        // --- The Greedy Strategy ---
        // Instead of building the actual subsequence, we can just count its length.
        // A wiggle subsequence is made of alternating "peaks" (upward slopes) and "valleys" (downward slopes).
        // We can track the length of the longest wiggle sequence ending in a peak and the longest ending in a valley.
        // Any single number is a wiggle sequence of length 1, so we start both counts at 1.

        if (nums.length < 2) {
            return nums.length;
        }

        // `peak` stores the length of the longest wiggle subsequence ending with an upward trend (nums[i] > nums[i-1]).
        int peak = 1;
        // `valley` stores the length of the longest wiggle subsequence ending with a downward trend (nums[i] < nums[i-1]).
        int valley = 1;

        // Iterate through the array starting from the second element.
        for (int i = 1; i < nums.length; ++i) {
            // Case 1: Current element is a PEAK (forms an upward slope).
            if (nums[i] > nums[i - 1]) {
                // To form a new peak, we must extend a previous valley.
                // So, the new peak's length is the length of the best valley sequence so far, plus one (for the current element).
                peak = valley + 1;
            }
            // Case 2: Current element is a VALLEY (forms a downward slope).
            else if (nums[i] < nums[i - 1]) {
                // To form a new valley, we must extend a previous peak.
                // So, the new valley's length is the length of the best peak sequence so far, plus one.
                valley = peak + 1;
            }
            // Case 3: nums[i] == nums[i-1] (a flat slope).
            // A flat point cannot extend a wiggle sequence, so we do nothing and the `peak` and `valley` lengths remain unchanged.
        }

        // The longest wiggle subsequence could end in either a peak or a valley.
        // We return the maximum of the two possibilities.
        return Math.max(peak, valley);
    }


    static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println("Longest wiggle subsequence for [1, 7, 4, 9, 2, 5]: " + wiggleMaxLength(nums)); // Expected: 6

        int[] nums2 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println("Longest wiggle subsequence for [1, 17, 5, ...]: " + wiggleMaxLength(nums2)); // Expected: 7
    }
}