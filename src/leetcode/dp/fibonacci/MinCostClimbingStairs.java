package leetcode.dp.fibonacci;

public class MinCostClimbingStairs {


    public static int recursive(int[] cost, int[] dp, int n) {
        if (n < 0) return 0;

        if (n < 2) return cost[n];

        if (dp[n] != 0) return dp[n];

        dp[n] = cost[n] + Math.min(recursive(cost, dp, n - 1), recursive(cost, dp, n - 2));

        return dp[n];
    }


    public static int iterative(int[] cost, int[] dp, int n) {
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                dp[i] = cost[i];
            } else {
                dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
            }
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }


    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];

        //return Math.min(memoization(cost, dp, n - 1), memoization(cost, dp, n - 2));
        return iterative(cost, dp, n);
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(minCostClimbingStairs(cost)); // 15

        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost2)); // 6
    }

}
