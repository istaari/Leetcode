package leetcode.dp.grid;

public class UniquePaths_2 {

    public static int recursive(int m, int n, int[][] obstacleGrid, int[][] memo) {
        // Base case: if we are out of bounds or hit an obstacle, return 0
        if (m < 0 || n < 0 || obstacleGrid[m][n] == 1) {
            return 0;
        }

        // Base case: if we reached the top-left corner, return 1
        if (m == 0 && n == 0) {
            return 1;
        }

        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        // Recursive case: sum the number of ways by moving left and up
        int left = recursive(m, n - 1, obstacleGrid, memo);  // Move left
        int up = recursive(m - 1, n, obstacleGrid, memo);    // Move up

        // Memoize the result
        memo[m][n] = left + up;
        return memo[m][n];
    }


    public static int iterative(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0; // If there is obstacle

                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1; // First row and col

                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(iterative(obstacleGrid));
    }

}