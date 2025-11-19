package leetcode.dp.subsequenceKnapsack;

import java.util.Arrays;

/**
 * LeetCode Problem: 322. Coin Change
 * <p>
 * Question:
 * You are given an integer array `coins` representing coins of different denominations and an integer `amount`
 * representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot
 * be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example 1:
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * <p>
 * Constraints:
 * - 1 <= coins.length <= 12
 * - 1 <= coins[i] <= 2^31 - 1
 * - 0 <= amount <= 10^4
 */
public class CoinChange {

    public static int helper(int[] coins, int amount, int[] memo) {
        // Base case: If the amount is negative, this path is impossible.
        if (amount < 0) return -1;
        // Base case: If the amount is zero, we've successfully made the change. 0 coins needed.
        if (amount == 0) return 0;

        // Memoization check: If we've already computed this amount, return the stored result.
        if (memo[amount] != -2) return memo[amount];

        // Initialize minCount to -1 (representing "impossible" for this amount so far)
        int minCount = -1;

        // Try every possible coin
        for (int coin : coins) {
            // Recursively find the solution for the remaining amount (amount - coin)
            int count = helper(coins, amount - coin, memo);

            // If the recursive call was successful (count >= 0)
            if (count >= 0) {
                // We add 1 to include the current coin
                int currentTotal = 1 + count;

                // If this is the first valid path we've found for this amount,
                // or if this path is better than the previous min, update minCount.
                if (minCount == -1 || currentTotal < minCount) {
                    minCount = currentTotal;
                }
            }
        }

        // Store the result (either the min coins or -1) in the memo table and return it.
        memo[amount] = minCount;
        return memo[amount];
    }


    public static int recursive(int[] coins, int amount) {
        // Initialize memo table. We need `amount + 1` size to store 0 to `amount`.
        int[] memo = new int[amount + 1];
        // Fill with a sentinel value (-2) to indicate "not yet computed".
        Arrays.fill(memo, -2);
        return helper(coins, amount, memo);
    }


    /**
     * Subproblem: dp[i][j] = The fewest coins to make amount j, using only the first i coin types.
     *
     * Example: If coins = {1, 2, 5} and amount = 5:
     * dp[2][5] asks: "What is the fewest coins to make 5, using only the first two coins {1, 2}?"
     * The answer would be 3 (from 1 + 2 + 2).
     */
    public static int iterative(int[] coins, int amount) {
        // dp[i][j] = min coins to make amount `j` using only the first `i` coins.
        int[][] dp = new int[coins.length + 1][amount + 1];

        // Base case: 0 coins are needed to make an amount of 0.
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 0;
        }

        // Base case: With 0 coins, it's impossible to make any amount > 0.
        // We use `amount + 1` as a "infinity" value, since it's an unreachable number of coins.
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = amount + 1;
        }

        // Fill the DP table
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // `coin` is the current coin we are considering
                int coin = coins[i - 1];

                if (coin <= j) {
                    // We have two choices:
                    // 1. Don't use the current coin: `dp[i - 1][j]`
                    // 2. Use the current coin: `1 + dp[i][j - coin]`
                    //    (We use `dp[i]` here, not `dp[i-1]`, because it's an Unbounded Knapsack,
                    //     meaning we can use the same coin multiple times).
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coin]);
                } else {
                    // If the coin is larger than the amount, we can't use it.
                    // So, the result is the same as not having this coin.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Get the final answer for all coins and the target amount.
        int result = dp[coins.length][amount];
        // If the result is still our "infinity" value, it's impossible.
        return result > amount ? -1 : result;
    }


    static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        // Testing the most efficient (1D iterative) solution.
        System.out.println("Min coins for amount 11: " + iterative(coins, amount)); // Expected: 3

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Min coins for amount 3: " + iterative(coins2, amount2)); // Expected: -1
    }

}