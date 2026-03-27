package leetcode.dp.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. Triangle
 * https://leetcode.com/problems/triangle/
 *
 * Given a triangle array, return the minimum path sum from top to bottom.
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row, you may move
 * to either index i or index i + 1 on the next row.
 *
 * Example 1: Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]] -> Output: 11
 *   Explanation: The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11.
 *
 * Example 2: Input: triangle = [[-10]] -> Output: -10
 *
 * Constraints:
 *   1 <= triangle.length <= 200
 *   triangle[0].length == 1
 *   triangle[i].length == triangle[i - 1].length + 1
 *   -10^4 <= triangle[i][j] <= 10^4
 *
 * ---
 * Approach: DP (top-down memoization + bottom-up with O(n) space)
 *
 * STATE:      dp[j] = minimum path sum from row i down to the bottom, at column j
 * BASE:       dp[j] = last row values
 * TRANSITION: dp[j] = triangle[i][j] + min(dp[j], dp[j+1])  (process bottom-up)
 * ANSWER:     dp[0] after processing all rows
 *
 * Time:  O(n^2) where n = number of rows
 * Space: O(n)   using 1D dp array
 */
@SuppressWarnings("all")
public class Triangle {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size()];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        return iterative(triangle);
    }

    // Top-down: from (row, col), find the minimum path sum to the bottom
    public static int recursive(List<List<Integer>> triangle, int row, int col, int[][] dp) {
        // Base case: reached the last row
        if (row == triangle.size() - 1) return triangle.get(row).get(col);

        if (dp[row][col] != Integer.MAX_VALUE) return dp[row][col];

        // Two choices: go straight down (col) or go diagonally (col+1)
        int nextRow = recursive(triangle, row + 1, col, dp);
        int diagonal = recursive(triangle, row + 1, col + 1, dp);

        dp[row][col] = triangle.get(row).get(col) + Math.min(nextRow, diagonal);

        return dp[row][col];
    }


    // Bottom-up iterative: process from last row upward, reusing a 1D array
    public static int iterative(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        // Base case: fill dp with the last row of the triangle
        for (int i = 0; i < triangle.get(n - 1).size(); i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // Work upward: for each cell, pick the min of the two children below
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        // dp[0] holds the minimum path sum from top to bottom
        return dp[0];
    }


    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(List.of(2));
        triangle.add(List.of(3, 4));
        triangle.add(List.of(6, 5, 7));
        triangle.add(List.of(4, 1, 8, 3));
        // Print the triangle
        System.out.println(minimumTotal(triangle));
    }


}
