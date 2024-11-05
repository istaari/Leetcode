package alogorithm.dp;

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

    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        int paths = uniquePaths(m, n);
        System.out.println("Number of unique paths: " + paths);
    }

}
