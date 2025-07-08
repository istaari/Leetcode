package leetcode.dp.subsequence;

public class CoinChange_2 {

    // Include and Exclude method
    private static int helper0(int amount, int[] coins, int i, Integer[][] dp) {
        if (amount == 0) return 1;
        if (amount < 0 || i == coins.length) return 0;

        if (dp[i][amount] != null) return dp[i][amount];

        int include = helper0(amount - coins[i], coins, i, dp);
        int exclude = helper0(amount, coins, i + 1, dp);

        dp[i][amount] = include + exclude;

        return dp[i][amount];
    }

   // Using loop
    private static int helper(int[] coins, int amount, int index, Integer[][] dp) {
        if (amount == 0) return 1;
        if (amount < 0 || index >= coins.length) return 0;

        if (dp[index][amount] != null) return dp[index][amount];

        int count = 0;
        for (int i = index; i < coins.length; i++) {
            count += helper(coins, amount - coins[i], i, dp);
        }

        dp[index][amount] = count;
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


    public static int iterativeOptimized(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;
        System.out.println(iterativeOptimized(coins, amount)); // 4
    }
}
