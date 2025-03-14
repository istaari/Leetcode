package leetcode.dp.interval;

import java.util.Arrays;

@SuppressWarnings("all")
public class BurstBalloons {

    public static int helper(int[] nums, int i, int j, int[][] dp) {
        if (i > j) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int maxCoins = 0;

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
