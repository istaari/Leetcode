package leetcode.dp.grid;

/**
 * 221. Maximal Square
 * https://leetcode.com/problems/maximal-square/
 *
 * Given an m x n binary matrix filled with 0's and 1's, find the largest
 * square containing only 1's and return its area.
 *
 * Example 1:
 *   Input: matrix = [["1","0","1","0","0"],
 *                    ["1","0","1","1","1"],
 *                    ["1","1","1","1","1"],
 *                    ["1","0","0","1","0"]]
 *   Output: 4  (the 2x2 square at rows 1-2, cols 2-3)
 *
 * Example 2: Input: matrix = [["0","1"],["1","0"]] -> Output: 1
 * Example 3: Input: matrix = [["0"]] -> Output: 0
 *
 * Constraints:
 *   m == matrix.length, n == matrix[i].length
 *   1 <= m, n <= 300
 *   matrix[i][j] is '0' or '1'.
 *
 * ---
 * Approach: 2D DP
 *
 * dp[i][j] = side length of the largest square with bottom-right corner at (i, j).
 *
 * If matrix[i][j] == '1':
 *   dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
 *   (limited by the smallest of: above, left, and diagonal neighbor)
 *
 * If matrix[i][j] == '0':
 *   dp[i][j] = 0
 *
 * Example trace: matrix = [["1","0","1","0","0"],
 *                          ["1","0","1","1","1"],
 *                          ["1","1","1","1","1"],
 *                          ["1","0","0","1","0"]]
 *
 *   dp matrix:
 *         [ 1, 0, 1, 0, 0 ]
 *         [ 1, 0, 1, 1, 1 ]
 *         [ 1, 1, 1, 2, 2 ]   <- dp[2][3]=min(1,1,1)+1=2 -> area=4
 *         [ 1, 0, 0, 1, 0 ]
 *
 *   Trace for dp[2][4]:
 *     matrix[2][4]='1', min(dp[1][4], dp[2][3], dp[1][3]) = min(1,2,1) = 1
 *     dp[2][4] = 1 + 1 = 2
 *
 * ANSWER: maxSide^2
 *
 * Time:  O(m * n)
 * Space: O(m * n), can optimize to O(n) using single row
 */
public class MaximalSquare {

    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // dp[i][j] = side length of largest square ending at (i, j)
        int[][] dp = new int[m][n];
        int maxSide = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        // First row or first column: can only form a 1x1 square
                        dp[i][j] = 1;
                    } else {
                        // The square is limited by the smallest neighbor
                        // Think of it as: to form a kxk square at (i,j), we need
                        // a (k-1)x(k-1) square at each of the three neighbors
                        dp[i][j] = 1 + Math.min(dp[i - 1][j],
                                       Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
                // If matrix[i][j] == '0', dp[i][j] stays 0
            }
        }

        return maxSide * maxSide; // Area = side^2
    }

    /**
     * Space-optimized: use only two rows instead of full m*n matrix.
     */
    public static int maximalSquareOptimized(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] prev = new int[n]; // Previous row
        int[] curr = new int[n]; // Current row
        int maxSide = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        curr[j] = 1;
                    } else {
                        curr[j] = 1 + Math.min(prev[j],
                                       Math.min(curr[j - 1], prev[j - 1]));
                    }
                    maxSide = Math.max(maxSide, curr[j]);
                } else {
                    curr[j] = 0;
                }
            }
            // Swap rows
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println("Example 1: " + maximalSquare(matrix1));          // 4
        System.out.println("Optimized: " + maximalSquareOptimized(matrix1)); // 4

        char[][] matrix2 = {{'0', '1'}, {'1', '0'}};
        System.out.println("Example 2: " + maximalSquare(matrix2));          // 1

        char[][] matrix3 = {{'0'}};
        System.out.println("Example 3: " + maximalSquare(matrix3));          // 0
    }
}
