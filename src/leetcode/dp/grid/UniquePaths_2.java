package leetcode.dp.grid;

import java.util.Arrays;

/**
 * LeetCode Problem: 63. Unique Paths II
 * <p>
 * Question:
 * You are given an `m x n` integer array `grid`. There is a robot on the top-left corner (i.e., `grid[0][0]`).
 * The robot tries to move to the bottom-right corner (i.e., `grid[m - 1][n - 1]`). The robot can only move either down or right at any point in time.
 * An obstacle and space are marked as `1` or `0` respectively in the grid. A path that the robot takes cannot include any square that is an obstacle.
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * <p>
 * Example:
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * <p>
 * Constraints:
 * - m == obstacleGrid.length
 * - n == obstacleGrid[i].length
 * - 1 <= m, n <= 100
 * - obstacleGrid[i][j] is 0 or 1.
 */
public class UniquePaths_2 {

    /**
     * Solves the problem using a recursive, top-down DP approach with memoization.
     * This function calculates the number of paths to reach cell (m, n) from (0, 0).
     *
     * @param m            The current row index.
     * @param n            The current column index.
     * @param obstacleGrid The grid with obstacles.
     * @param memo         A memoization table to store results of subproblems.
     * @return The number of unique paths to cell (m, n).
     */
    public static int recursive(int m, int n, int[][] obstacleGrid, int[][] memo) {
        // Base case: If we are out of bounds or have hit an obstacle, this path is invalid. Return 0.
        if (m < 0 || n < 0 || obstacleGrid[m][n] == 1) {
            return 0;
        }

        // Base case: If we have successfully reached the starting corner (0,0), this is one valid path.
        if (m == 0 && n == 0) {
            return 1;
        }

        // Memoization check: If we've already computed the result for this cell, return it.
        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        // Recursive Step (Recurrence Relation):
        // The number of ways to reach cell (m, n) is the sum of the ways to reach the cell
        // above it (m-1, n) and the cell to its left (m, n-1).
        int fromUp = recursive(m - 1, n, obstacleGrid, memo);
        int fromLeft = recursive(m, n - 1, obstacleGrid, memo);

        // Store the result in the memoization table before returning.
        memo[m][n] = fromLeft + fromUp;
        return memo[m][n];
    }


    /**
     * Solves the problem using an iterative, bottom-up DP approach.
     * NOTE: Your original implementation had a bug in the base case handling for the first row/column.
     * This is the corrected and explained version.
     *
     * @param obstacleGrid The grid with obstacles.
     * @return The number of unique paths to the bottom-right corner.
     */
    public static int iterative(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // If the starting cell itself is an obstacle, no paths are possible.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // dp[i][j] will store the number of unique paths to reach cell (i, j).
        int[][] dp = new int[m][n];

        // Base Case: There is one way to reach the starting cell.
        dp[0][0] = 1;

        // Fill the first column. A cell is reachable only if the cell above it was reachable
        // and the current cell is not an obstacle.
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1) {
                dp[i][0] = 1;
            }
        }

        // Fill the first row. A cell is reachable only if the cell to its left was reachable
        // and the current cell is not an obstacle.
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 0 && dp[0][j - 1] == 1) {
                dp[0][j] = 1;
            }
        }

        // Fill the rest of the DP table using the recurrence relation.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // If the current cell is an obstacle, it's unreachable.
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    // Otherwise, the number of paths is the sum from the cell above and the cell to the left.
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // The final answer is in the bottom-right corner of the grid.
        return dp[m - 1][n - 1];
    }

    static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println("Iterative result: " + iterative(obstacleGrid)); // Expected: 2

        // Setup for the recursive solution
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        System.out.println("Recursive result: " + recursive(m - 1, n - 1, obstacleGrid, memo)); // Expected: 2
    }

}