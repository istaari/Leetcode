package leetcode.dp.grid;

import java.util.Arrays;

/**
 * LeetCode Problem: 62. Unique Paths
 * <p>
 * Question:
 * A robot is located at the top-left corner of a `m x n` grid.
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 * <p>
 * Example:
 * Input: m = 3, n = 7
 * Output: 28
 * <p>
 * Constraints:
 * - 1 <= m, n <= 100
 */
public class UniquePaths {

    public static int recursive(int m, int n, int[][] memo) {
        // Base case: If we are in the first row (m=1) or first column (n=1),
        // there is only one way to reach any cell from the start.
        if (m == 0 || n == 0) {
            return 1;
        }

        // Memoization check: If we've already computed the result for this cell, return it.
        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        // Recurrence Relation: The number of ways to reach cell (m, n) is the sum of
        // ways to reach the cell to its left (m, n-1) and the cell above it (m-1, n).
        int fromLeft = recursive(m, n - 1, memo);
        int fromUp = recursive(m - 1, n, memo);

        // Store the result in the memoization table before returning.
        memo[m][n] = fromLeft + fromUp;
        return memo[m][n];
    }


    public static int iterative(int m, int n) {
        // dp[i][j] will store the number of unique paths to reach cell (i, j).
        int[][] dp = new int[m][n];

        // Base Cases: Initialize the first row and first column to 1.
        // There is only one way to reach any cell in the first row/column (by only moving right/down).
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill the rest with the DP table.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // Recurrence Relation: The number of ways to reach cell (i, j) is the sum of
                // ways to reach the cell above (i-1, j) and the cell to the left (i, j-1).
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // The final answer is in the bottom-right corner of the grid.
        return dp[m - 1][n - 1];
    }


    public static int iterativeOptimized(int m, int n) {
        // --- The Space Optimization Insight ---
        // To calculate the values for the current row, we only need the values from the previous row.
        // We can optimize from O(m*n) space to O(n) space by only keeping track of two rows.

        // Create a 2-row DP array. We'll use the modulo operator to alternate between them.
        int[][] dp = new int[2][n];

        // Initialize the first row (which represents the base case).
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill the DP table row by row, alternating between dp[0] and dp[1].
        for (int i = 1; i < m; i++) {
            // The current row we are filling is `i % 2`.
            // The previous row is `(i - 1) % 2`.

            // Base case for the current row (first column is always 1).
            dp[i % 2][0] = 1;
            for (int j = 1; j < n; j++) {
                // `dp[(i - 1) % 2][j]` is the cell from the previous row (move down).
                // `dp[i % 2][j - 1]` is the cell from the current row's left (move right).
                dp[i % 2][j] = dp[(i - 1) % 2][j] + dp[i % 2][j - 1];
            }
        }

        // The result is in the last cell of the row corresponding to the last row of the grid (m-1).
        return dp[(m - 1) % 2][n - 1];
    }


    static void main(String[] args) {
        int m = 6;
        int n = 6;
        // Using the space-optimized iterative approach for the test.
        int paths = iterativeOptimized(m, n);
        System.out.println("Number of unique paths for a " + m + "x" + n + " grid: " + paths);


        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        int pathsRecursive = recursive(m - 1, n - 1, memo);
        System.out.println("Number of unique paths (recursive) for a " + m + "x" + n + " grid: " + pathsRecursive);
    }

}