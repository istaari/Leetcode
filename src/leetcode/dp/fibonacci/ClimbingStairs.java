package leetcode.dp.fibonacci;

/**
 * LeetCode Problem: 70. Climbing Stairs
 * <p>
 * Question:
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * <p>
 * Example:
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * <p>
 * Constraints:
 * - 1 <= n <= 45
 */
public class ClimbingStairs {


    public static int iterative(int n) {
        // Base Cases: If there are 1 or 2 stairs, the number of ways is simply n.
        if (n <= 2) {
            return n;
        }

        // dp[i] will store the number of ways to reach stair 'i'.
        int[] dp = new int[n + 1];

        // Initialize the base cases for the DP table.
        // There is 1 way to reach the 1st stair.
        dp[1] = 1;
        // There are 2 ways to reach the 2nd stair (1+1 or 2).
        dp[2] = 2;

        // Fill the DP table from the 3rd stair up to the n-th stair.
        for (int i = 3; i <= n; i++) {
            // The number of ways to reach stair 'i' is the sum of the ways to reach
            // stair 'i-1' (and taking one step) and the ways to reach stair 'i-2'
            // (and taking two steps). This is the core recurrence relation.
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // The final answer is the number of ways to reach the n-th stair.
        return dp[n];
    }


    public static int recursive(int n, int[] dp) {
        // Base Cases: If there are 1 or 2 stairs, the number of ways is n.
        if (n <= 2) {
            return n;
        }

        // Memoization Check: If we have already computed the result for stair 'n',
        // return the stored value instead of re-calculating.
        if (dp[n] != 0) {
            return dp[n];
        }

        // Recursive Step: If the result is not in our memoization table, calculate it by
        // breaking it down into smaller subproblems.
        // The logic is the same: ways(n) = ways(n-1) + ways(n-2).
        dp[n] = recursive(n - 1, dp) + recursive(n - 2, dp);

        // Store and return the computed result.
        return dp[n];
    }


    /**
     * This is the main wrapper function that sets up the environment for the recursive solution.
     *
     * @param n The total number of stairs.
     * @return The number of distinct ways to climb to the top.
     */
    public static int climbStairs(int n) {
        // Create a memoization array (dp table) of size n+1.
        // It's initialized to all zeros by default in Java.
        int[] dp = new int[n + 1];
        // Call the recursive helper function to start the computation.
        return recursive(n, dp);
    }


    /**
     * The main method to test the implemented solutions.
     */
    static void main(String[] args) {
        // The climbStairs method is called, which in turn uses the recursive approach.
        // You could also call the iterative method directly for comparison.
        System.out.println("Ways to climb 2 stairs: " + climbStairs(2)); // Expected: 2
        System.out.println("Ways to climb 3 stairs: " + climbStairs(3)); // Expected: 3
        System.out.println("Ways to climb 4 stairs: " + climbStairs(4)); // Expected: 5
        System.out.println("Ways to climb 5 stairs: " + climbStairs(5)); // Expected: 8
    }
}