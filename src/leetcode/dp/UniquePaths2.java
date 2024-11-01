package leetcode.dp;

public class UniquePaths2 {

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                { 0, 0, 0 },
                { 0, 1, 0 },
                { 0, 0, 0 } };

        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;

                } else if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                    
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }

            }
        }

        return dp[m - 1][n - 1];
    }
}