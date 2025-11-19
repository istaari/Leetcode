package leetcode.dp.subsequenceKnapsack;

/**
 * LeetCode Problem: 518. Coin Change 2
 * <p>
 * Question:
 * You are given an integer array `coins` representing coins of different denominations and an integer `amount`
 * representing a total amount of money.
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by
 * any combination of the coins, return 0.
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example:
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: There are four ways to make up the amount:
 * 5 = 5
 * 5 = 2 + 2 + 1
 * 5 = 2 + 1 + 1 + 1
 * 5 = 1 + 1 + 1 + 1 + 1
 * <p>
 * Constraints:
 * - 1 <= coins.length <= 300
 * - 1 <= coins[i] <= 5000
 * - 0 <= amount <= 5000
 */
public class CoinChange_2 {

    // The state is: dp[index][amount] = "Number of ways to make amount using only coins from index onwards."
    private static int helper(int[] coins, int amount, int index, Integer[][] dp) {
        // Base case: We found a valid combination.
        if (amount == 0) return 1;
        // Base case: This path is invalid.
        if (amount < 0) return 0;

        // Memoization check
        if (dp[index][amount] != null) return dp[index][amount];

        // --- The Loop Choice ---
        // Instead of a binary choice, we iterate through all possible coins we can use.
        int count = 0;
        for (int i = index; i < coins.length; i++) {
            // Try using `coins[i]`. The recursive call passes `i` (not `i+1`)
            // because we can use the same coin `i` again.
            // By starting the loop at `index`, we avoid duplicate combinations (e.g., [1,2] and [2,1]).
            count =  count + helper(coins, amount - coins[i], i, dp);
        }

        dp[index][amount] = count;
        return count;
    }


    public static int recursive(int amount, int[] coins) {
        Integer[][] memo = new Integer[coins.length][amount + 1];
        // You can call either helper0 or helper here, as they solve the same problem.
        return helper(coins, amount, 0, memo);
    }


    /**
     * Subproblem: dp[i][j] = The number of combinations to make amount j, using only the first i coin types.
     *
     * Example: If coins = {1, 2, 5} and amount = 5:
     * dp[2][5] asks: "How many ways are there to make 5, using only {1, 2}?"
     * The answer would be 3 (from [1,1,1,1,1], [1,1,1,2], and [1,2,2]).
     */
    public static int Iterative(int amount, int[] coins) {
        // dp[i][j] = number of ways to make amount `j` using the first `i` coins.
        int[][] dp = new int[coins.length + 1][amount + 1];

        // Base case: There is 1 way to make amount 0 (by using no coins).
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                int coin = coins[i - 1];

                // Recurrence Relation:
                // We add two possibilities:
                // 1. Ways to make amount `j` WITHOUT using coin `i` (exclude): dp[i - 1][j]
                // 2. Ways to make amount `j` WITH coin `i` (include):
                //    This is `dp[i][j - coin]`. We look at the same row `i` because we can use the coin again.
                if (coin <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                } else {
                    // If the coin is larger than the amount, we can't use it.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // The final answer is in the bottom-right corner.
        return dp[coins.length][amount];
    }


    static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println(recursive(amount, coins)); // Expected: 4
    }
}