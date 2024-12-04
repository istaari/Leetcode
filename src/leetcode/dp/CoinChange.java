package leetcode.dp;

import java.util.Arrays;

public class CoinChange {

    public static int topDown(int[] coins, int amount, int index, int[][] dp) {
        if (amount == 0) return 0;

        if (amount < 0 || index >= coins.length) return Integer.MAX_VALUE;

        if (dp[index][amount] != -1) return dp[index][amount];

        int include = Integer.MAX_VALUE;

        if (coins[index] <= amount) {
            int subCount = topDown(coins, amount - coins[index], index, dp);

            if (subCount != Integer.MAX_VALUE) {
                include = 1 + subCount;
            }
        }

        int exclude = topDown(coins, amount, index + 1, dp);

        dp[index][amount] = Math.min(include, exclude);

        return dp[index][amount];
    }


    public static int bottomUp(int[] coins, int amount) {
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


    public static int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = topDown(coins, amount, 0, dp);

        return (result == Integer.MAX_VALUE) ? -1 : result;
    }


    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;

        System.out.println(coinChange(coins, amount));
    }

}
