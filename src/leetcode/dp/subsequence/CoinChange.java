package leetcode.dp.subsequence;

import java.util.Arrays;

public class CoinChange {

    /**
     * Find the minimum number of coins needed to make the given amount using dynamic programming.
     *
     * Without Memoization:
     * Time Complexity: O(n^m), where n is the number of coins and m is the amount.
     * Space Complexity: O(m), where m is the amount (due to the recursion call stack).
     *
     * With Memoization:
     * Time Complexity: O(n * m), where n is the number of coins and m is the amount.
     * Space Complexity: O(m), where m is the amount (due to the memo[] array and the recursion call stack).
     */
    public static int helper(int[] coins, int amount, int[] memo) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        if (memo[amount] != -2) return memo[amount];

        int minCount = -1;
        for (int coin : coins) {
            int count = 1 + helper(coins, amount - coin, memo);

            if (count > 0) {
                minCount = (minCount < 0) ? count : Math.min(count, minCount);
            }
        }

        memo[amount] = minCount;
        return memo[amount];
    }


    public static int recursive(int[] coins, int amount) {
        int[] memo = new int[amount + 1];
        Arrays.fill(memo, -2);
        return helper(coins, amount, memo);
    }


    public static int iterative(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 0;
        }

        for (int j = 1; j <= amount; j++) {
            dp[0][j] = amount + 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {

                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }


    public static void main(String[] args) {
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(recursive(coins, amount));
    }

}
