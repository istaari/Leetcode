package leetcode.dp.fibonacci;

/**
 * LeetCode Problem: 746. Min Cost Climbing Stairs
 * <p>
 * Question:
 * You are given an integer array `cost` where `cost[i]` is the cost of the `i`-th step on a staircase.
 * Once you pay the cost, you can either climb one or two steps.
 * You can either start from the step with index 0, or the step with index 1.
 * Return the minimum cost to reach the top of the floor. The top of the floor is considered one step past the last index.
 * <p>
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: You will start at index 1. Pay 15 and climb two steps to reach the top. The total cost is 15.
 * <p>
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: You will start at index 0. Pay 1, climb two steps to index 2. Pay 1, climb two steps to index 4. Pay 1, climb two steps to index 6. Pay 1, climb one step to index 7. Pay 1, climb two steps to index 9. Pay 1, climb one step to reach the top. The total cost is 6.
 * <p>
 * Constraints:
 * - 2 <= cost.length <= 1000
 * - 0 <= cost[i] <= 999
 */
public class MinCostClimbingStairs {

    public static int recursive(int[] cost, int[] dp, int n) {
        // Base case: If we are out of bounds, the cost is 0.
        if (n < 0) return 0;

        // Base cases: For the first two stairs (0 and 1), the cost to reach them is just their own cost.
        if (n < 2) return cost[n];

        // Memoization check: If we have already calculated the min cost for stair 'n', return it.
        if (dp[n] != 0) return dp[n];

        // Recursive Step (Recurrence Relation):
        // The minimum cost to reach stair 'n' is its own cost plus the minimum of the costs
        // to reach the previous two stairs (n-1 or n-2).
        dp[n] = cost[n] + Math.min(recursive(cost, dp, n - 1), recursive(cost, dp, n - 2));

        return dp[n];
    }

    public static int iterative(int[] cost, int[] dp, int n) {
        // Iterate through each stair to calculate the minimum cost to reach it.
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                // For the first two stairs, the min cost is just their own cost,
                // as we can start from either of them.
                dp[i] = cost[i];
            } else if(i == 2){
                // Recurrence Relation: The min cost to reach stair 'i' is its own cost
                // plus the minimum of the costs to reach the previous two stairs.
                dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
            }else{

                dp[i] = cost[i] + dp[i - 1];
            }
        }

        // The "top" of the floor is past the last step. To get there, we could have
        // come from the last step (n-1) or the second-to-last step (n-2).
        // We return the minimum of these two paths.
        return Math.min(dp[n - 1], dp[n - 2]);
    }


    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        // The dp array will store the minimum cost to reach step 'i'.
        int[] dp = new int[n];

        // Call the iterative (bottom-up) solution.
        return iterative(cost, dp, n);
        // return Math.min(recursive(cost, dp, n - 1), recursive(cost, dp, n - 2));
    }


    static void main(String[] args) {
        int[] cost1 = {10, 15, 20};
        System.out.println("Min cost for [10, 15, 20]: " + minCostClimbingStairs(cost1)); // Expected: 15

        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println("Min cost for the long array: " + minCostClimbingStairs(cost2)); // Expected: 6
    }
}