package leetcode.dp;

public class MinimumPathSum {

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // Base Case
        dp[0][0] = grid[0][0];

        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int min(int[][] grid, int row, int col) {

        if (row == 0 && col == 0)
            return grid[row][col]; // this is the exit of the recursion

        if (row == 0)
            return grid[row][col] + min(grid, row, col - 1); /** when we reach the first row, we could only move horizontally.*/

        if (col == 0)
            return grid[row][col] + min(grid, row - 1, col); /** when we reach the first column, we could only move vertically.*/

        return grid[row][col] + Math.min(min(grid, row - 1, col), min(grid, row, col - 1)); /** we want the min sum path so we pick the cell with the less value */

    }


    public static void main(String[] args) {
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid1));
    }

}
