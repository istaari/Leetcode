package leetcode.dp.grid;

import java.util.Arrays;

/**
 * 931. Minimum Falling Path Sum
 * https://leetcode.com/problems/minimum-falling-path-sum/
 *
 * Given an n x n array of integers matrix, return the minimum sum of any
 * falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element
 * in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) is
 * (row+1, col-1), (row+1, col), or (row+1, col+1).
 *
 * Example 1: Input: matrix = [[2,1,3],[6,5,4],[7,8,9]] -> Output: 13
 *   Explanation: Falling paths: 1->5->7 = 13, 1->4->8 = 13
 *
 * Example 2: Input: matrix = [[-19,57],[-40,-5]] -> Output: -59
 *
 * Constraints:
 *   n == matrix.length == matrix[i].length
 *   1 <= n <= 100
 *   -100 <= matrix[i][j] <= 100
 *
 * ---
 * Approach: DP (top-down memoization + bottom-up iterative)
 *
 * STATE:   dp[row][col] = minimum falling path sum starting from (row, col).
 * BASE:    dp[lastRow][col] = matrix[lastRow][col]
 * TRANSITION: dp[row][col] = matrix[row][col] + min(dp[row+1][col-1], dp[row+1][col], dp[row+1][col+1])
 * ANSWER:  min(dp[0][col]) for all columns
 *
 * Time:  O(n^2)
 * Space: O(n^2) for memoization, can be optimized to O(n)
 */
@SuppressWarnings("all")
public class MinimumFallingPathSum {

    // Top-down: recursively find the min falling path sum starting at (row, col)
    public static int helper(int[][] matrix, int row, int col, int[][] dp) {
        // Out of bounds -> return MAX so this path is never chosen
        if (row >= matrix.length || col >= matrix[0].length || row < 0 || col < 0) return Integer.MAX_VALUE;

        // Base case: last row, the falling path ends here
        if (row == matrix.length - 1) return matrix[row][col];

        // Memoization check
        if (dp[row][col] != Integer.MAX_VALUE) return dp[row][col];

        // Explore all three directions: down, down-left diagonal, down-right diagonal
        int down = helper(matrix, row + 1, col, dp);
        int dLeftDiagonal = helper(matrix, row + 1, col - 1, dp);
        int dRightDiagonal = helper(matrix, row + 1, col + 1, dp);

        // Pick the direction that gives the smallest sum
        int min = Math.min(down, dLeftDiagonal);
        min = Math.min(min, dRightDiagonal);

        dp[row][col] = matrix[row][col] + min;

        return dp[row][col];
    }

    public static int recursive(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            min = Math.min(helper(matrix, 0, i, dp), min);
        }

        return min;
    }


    // Bottom-up iterative approach
    // Uses n+2 columns (padding on both sides) to avoid boundary checks
    public static int iterative(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        // Extra 2 columns act as sentinels (INT_MAX) so we don't go out of bounds
        int[][] dp = new int[m][n + 2];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Base case: copy first row (offset by 1 due to padding)
        for (int i = 0; i < n; i++) {
            dp[0][i + 1] = matrix[0][i];
        }

        // Fill row by row: each cell = matrix value + min(up-left, up, up-right)
        for (int i = 1; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int up = dp[i - 1][j + 1];     // directly above
                int left = dp[i - 1][j];        // upper-left diagonal
                int right = dp[i - 1][j + 2];   // upper-right diagonal

                dp[i][j + 1] = matrix[i][j] + Math.min(up, Math.min(left, right));
            }

        }

        // Answer: minimum value in the last row
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(dp[matrix.length - 1][i + 1], min);
        }

        return min;
    }


    public static int minFallingPathSum(int[][] matrix) {
        return recursive(matrix);
    }


    public static void main(String[] args) {
        int[][] matrix = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(minFallingPathSum(matrix));
    }

}
