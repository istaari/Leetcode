package alogorithm.dp;

public class UnboundedKnapsack {

    // use
    public static int unboundedKnapsack2D(int[] values, int[] weights, int target) {
        int n = weights.length;
        int[][] dp = new int[n + 1][target + 1];

        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= target; j++) {

                if (i == 0 || j == 0) dp[i][j] = 0;

                else if (weights[i - 1] <= j) {
                    //  dp[i - 1][j]: This represents the maximum value that can be obtained without including item i
                    //  values[i - 1]: Includes the current item
                    //  dp[i][j - weights[i - 1]]: Find the cost of remaining capacity by considering the same items (i)

                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weights[i - 1]] + values[i - 1]);

                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];
    }


    /**
     * Unbounded Knapsack Problem
     * <p>
     * Given weights and values of n items, put these items in a knapsack of
     * capacity W to get the maximum total value in the knapsack. In the unbounded
     * knapsack, an item can be used multiple times.
     * <p>
     * Time Complexity: O(n * target)
     * Space Complexity: O(target)
     *
     * @param values  - values of items
     * @param weights - weights of items
     * @param target  - maximum weight that knapsack can hold
     * @return maximum value that can be obtained
     */
    public static int unboundedKnapsackOptimization(int[] values, int[] weights, int target) {
        int n = weights.length;
        int[] dp = new int[target + 1];

        for (int c = 0; c <= target; c++) {
            for (int i = 0; i < n; i++) {
                if (weights[i] <= c) {
                    //System.out.println("Remaining Amount: " + (c - weights[i]));
                    dp[c] = Math.max(dp[c], values[i] + dp[c - weights[i]]);
                }
            }
        }
        return dp[target];
    }


    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        // 60 * 5 = 300 Select 5 items of weight 10 to get maximum value 300
        System.out.println("Maximum value that can be obtained: " + unboundedKnapsackOptimization(values, weights, capacity));
        System.out.println("Maximum value that can be obtained: " + unboundedKnapsack2D(values, weights, capacity));
    }

}
