package leetcode.dp.lis;


import java.util.Arrays;

/**
 * LeetCode Problem: 300. Longest Increasing Subsequence
 *
 * Question:
 * Given an integer array `nums`, return the length of the longest strictly increasing subsequence.
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: nums = [10, 9, 2, 5, 3, 7, 101, 18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 *
 * Constraints:
 * - 1 <= nums.length <= 2500
 * - -10^4 <= nums[i] <= 10^4
 */
public class LIS {

    // Subproblem: dp[i] = The length of the longest increasing subsequence that *ends* at index `i`.
    public static int iterative(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];
        // Base case: Every element by itself is an increasing subsequence of length 1.
        Arrays.fill(dp, 1);

        int maxLIS = 1;

        // Build the solution from left to right.
        for (int i = 1; i < n; i++) {
            // For each element `i`, check all previous elements `j`.
            for (int j = 0; j < i; j++) {
                // If `nums[i]` can extend the subsequence ending at `j`...
                if (nums[i] > nums[j]) {
                    // then the new LIS length ending at `i` could be `dp[j] + 1`. 
                    // We take the maximum of what we already had for `dp[i]` and this new possibility.
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLIS = Math.max(maxLIS, dp[i]);
        }

        return maxLIS;
    }

    static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Iterative (O(n^2)): " + iterative(nums)); // Expected: 4
    }

}