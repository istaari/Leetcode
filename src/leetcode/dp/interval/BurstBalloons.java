package leetcode.dp.interval;

import java.util.Arrays;

/**
 * 312. Burst Balloons
 * https://leetcode.com/problems/burst-balloons/
 *
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted
 * with a number on it represented by an array nums. You are asked to burst
 * all the balloons.
 *
 * If you burst the i-th balloon, you will get nums[i-1] * nums[i] * nums[i+1] coins.
 * If i-1 or i+1 goes out of bounds, treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 * Example 1: Input: nums = [3,1,5,8] -> Output: 167
 *   Explanation: nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 *                coins = 3*1*5 + 3*5*8 + 1*3*8 + 1*8*1 = 167
 *
 * Constraints:
 *   n == nums.length
 *   1 <= n <= 300
 *   0 <= nums[i] <= 100
 *
 * ---
 * Approach: Interval DP (top-down memoization)
 *
 * Key Insight: Instead of thinking about which balloon to burst FIRST,
 * think about which balloon to burst LAST in the range [i, j].
 *
 * STATE:      dp[i][j] = max coins obtainable by bursting all balloons in [i..j]
 * BASE:       dp[i][j] = 0 when i > j (no balloons)
 * TRANSITION: For each k in [i..j], burst k LAST:
 *             dp[i][j] = max(nums[i-1]*nums[k]*nums[j+1] + dp[i][k-1] + dp[k+1][j])
 *
 * Time:  O(n^3)
 * Space: O(n^2)
 */
@SuppressWarnings("all")
public class BurstBalloons {

    // Top-down: find max coins from bursting all balloons in range [i..j]
    public static int helper(int[] nums, int i, int j, int[][] dp) {
        if (i > j) return 0; // No balloons left in this range

        if (dp[i][j] != -1) return dp[i][j];

        int maxCoins = 0;

        // Try every balloon k as the LAST one to burst in range [i..j]
        for (int k = i; k <= j; k++) {
            int coins = nums[i - 1] * nums[k] * nums[j + 1] // Coins from bursting k-th balloon last
                    + helper(nums, i, k - 1, dp) // coins from left subarray
                    + helper(nums, k + 1, j, dp); // coins from right subarray
            maxCoins = Math.max(maxCoins, coins);
        }

        dp[i][j] = maxCoins;

        return dp[i][j];
    }

    public static int recursive(int[] nums) {
        int n = nums.length;
        // Pad with 1 on both sides: [1, ...original..., 1]
        int[] newNums = new int[n + 2];

        newNums[0] = 1;
        newNums[n + 1] = 1;

        for (int i = 0; i < n; i++) {
            newNums[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];

        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Solve for the range [1..n] (original balloon indices)
        return helper(newNums, 1, n, dp);
    }

    public static int maxCoins(int[] nums) {
        return recursive(nums);
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 5, 8}; // 167
        System.out.println(maxCoins(nums));
    }

}
