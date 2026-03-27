package leetcode.dp.grid;

/**
 * 64. Minimum Path Sum
 * https://leetcode.com/problems/minimum-path-sum/
 *
 * Given a m x n grid filled with non-negative numbers, find a path from
 * top left to bottom right, which minimizes the sum of all numbers along
 * its path. You can only move either down or right at any point in time.
 *
 * Example 1: Input: grid = [[1,3,1],[1,5,1],[4,2,1]] -> Output: 7
 *   Explanation: Path 1->3->1->1->1 minimizes the sum.
 *
 * Example 2: Input: grid = [[1,2,3],[4,5,6]] -> Output: 12
 *
 * Constraints:
 *   m == grid.length, n == grid[i].length
 *   1 <= m, n <= 200
 *   0 <= grid[i][j] <= 200
 *
 * ---
 * Approach: DP (bottom-up iterative + top-down recursive)
 *
 * STATE:      dp[i][j] = minimum path sum to reach cell (i, j)
 * BASE:       dp[0][0] = grid[0][0]; first row/col are prefix sums
 * TRANSITION: dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
 * ANSWER:     dp[m-1][n-1]
 *
 * Time:  O(m * n)
 * Space: O(m * n), can be optimized to O(n)
 */
public class MinimumPathSum {

    // Bottom-up iterative DP
    public static int iterative(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        // Base Case: starting cell
        dp[0][0] = grid[0][0];

        // First row: can only come from the left
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        // First column: can only come from above
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        // Fill rest: each cell = its value + min(from above, from left)
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    public static int recursive(int[][] grid, int row, int col) {
        if (row == 0 && col == 0)
            return grid[row][col];

        if (row == 0)
            /* when we reach the first row, we could only move horizontally.*/
            return grid[row][col] + recursive(grid, row, col - 1);

        if (col == 0)
            /* when we reach the first column, we could only move vertically.*/
            return grid[row][col] + recursive(grid, row - 1, col);

        /* we want the min sum path so we pick the cell with the less value */
        return grid[row][col] + Math.min(recursive(grid, row - 1, col), recursive(grid, row, col - 1));
    }


    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(iterative(grid));
        System.out.println(recursive(grid, grid.length - 1, grid[0].length - 1));
    }

}
