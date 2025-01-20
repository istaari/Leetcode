package leetcode.dp.subsequence;

import java.util.Arrays;

public class CoinChange_2 {

    /**
     * Find the number of ways to make the given amount using the available coins.
     * This solution uses dynamic programming with memoization.
     *
     * Without Memoization:
     * Time Complexity: O(2^n), where n is the number of coins.
     * Space Complexity: O(m), where m is the amount (due to the recursion call stack).
     *
     * With Memoization:
     * Time Complexity: O(n * m), where n is the number of coins and m is the amount.
     * Space Complexity: O(n * m), where n is the number of coins and m is the amount (due to the memo[][] array and the recursion call stack).
     */
    private static int helper0(int amount, int[] coins, int index, Integer[][] memo) {
        if (amount == 0) return 1;
        if (amount < 0 || index == coins.length) return 0;

        if (memo[index][amount] != null) return memo[index][amount];

        int include = helper0(amount - coins[index], coins, index, memo);
        int exclude = helper0(amount, coins, index + 1, memo);

        memo[index][amount] = include + exclude;

        return memo[index][amount];
    }


    /**
     * Find the number of ways to make the given amount using the available coins.
     *
     * Without Memoization:
     * Time Complexity: O(n^m), where n is the number of coins and m is the amount.
     * Space Complexity: O(m), where m is the amount (due to the recursion call stack).
     *
     * With Memoization:
     * Time Complexity: O(n * m), where n is the number of coins and m is the amount.
     * Space Complexity: O(n * m), where n is the number of coins and m is the amount (due to the memo[][] array and the recursion call stack).
     */
    private static int helper(int[] coins, int amount, int index, Integer[][] memo) {
        if (amount == 0) return 1;
        if (amount < 0 || index >= coins.length) return 0;

        if (memo[index][amount] != null) return memo[index][amount] ;

        int count = 0;
        for (int i = index; i < coins.length; i++) {
            count += helper(coins, amount - coins[i], i, memo);
        }

        memo[index][amount] = count;
        return count;
    }


    public static int recursive(int amount, int[] coins) {
        Integer[][] memo = new Integer[coins.length][amount + 1];
        return helper(coins, amount, 0, memo);
    }


    public static int Iterative(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {

                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length][amount];
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println(recursive(amount, coins)); // 4
    }
}
