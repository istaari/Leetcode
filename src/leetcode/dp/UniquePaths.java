package leetcode.dp;

public class UniquePaths {


    public static int uniquePaths(int m, int n) {
        // Create a 2D problems.array to store the number of unique paths
        int[][] dp = new int[m][n];
        // Initialize the top row and left column to 1 (only one way to reach each cell in the top row or left column)
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }


        // Build the DP table by summing the paths from the cell above and the cell to the left
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // The bottom-right cell contains the final result
        return dp[m - 1][n - 1];
    }


    public int uniquePathsSpaceOptimized(int m, int n) {
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
        int paths = uniquePaths(m, n);
        System.out.println("Number of unique paths: " + paths);
    }

}
