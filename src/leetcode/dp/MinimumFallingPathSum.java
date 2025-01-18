package leetcode.dp;

import java.util.Arrays;


@SuppressWarnings("all")
public class MinimumFallingPathSum {

    public static int helper(int[][] matrix, int row, int col, int[][] dp) {
        if (row >= matrix.length || col >= matrix[0].length || row < 0 || col < 0) return Integer.MAX_VALUE;

        if (row == matrix.length - 1) return matrix[row][col];

        if (dp[row][col] != Integer.MAX_VALUE) return dp[row][col];

        int down = helper(matrix, row + 1, col, dp);
        int dLeftDiagonal = helper(matrix, row + 1, col - 1, dp);
        int dRightDiagonal = helper(matrix, row + 1, col + 1, dp);

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


    public static int iterative(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n + 2];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            dp[0][i + 1] = matrix[0][i];
        }

        for (int i = 1; i < m; i++) {

            for (int j = 0; j < n; j++) {

                int up = dp[i - 1][j + 1];
                int left = dp[i - 1][j];
                int right = dp[i - 1][j + 2];

                dp[i][j + 1] = matrix[i][j] + Math.min(up, Math.min(left, right));
            }

        }

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
