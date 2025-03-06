package leetcode.dp.grid;

public class UniquePaths {

    public static int recursive(int m, int n, int[][] memo) {
        // Base case: if we're at the first row or first column, there's only one way to reach the destination
        if (m == 1 || n == 1) {
            return 1;
        }

        if (memo[m][n] != -1) {
            return memo[m][n];
        }

        // Compute the number of unique paths by moving either down or right
        int left = recursive(m, n - 1, memo);  // Move left
        int up = recursive(m - 1, n, memo);    // Move up

        memo[m][n] = left + up;

        return memo[m][n];
    }

    public static int iterative(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }


        return dp[m - 1][n - 1];
    }


    public static int iterativeOptimized(int m, int n) {
        // Create a 2-row array for optimization
        int[][] dp = new int[2][n];

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill the dp array row by row
        for (int i = 1; i < m; i++) {
            dp[i % 2][0] = 1; // First column is always 1
            for (int j = 1; j < n; j++) {
                dp[i % 2][j] = dp[(i - 1) % 2][j] + dp[i % 2][j - 1];
            }
        }

        // The result is stored in the last cell of the row corresponding to (m-1)
        return dp[(m - 1) % 2][n - 1];
    }


    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int paths = iterativeOptimized(m, n);
        System.out.println("Number of unique paths: " + paths);
    }

}
