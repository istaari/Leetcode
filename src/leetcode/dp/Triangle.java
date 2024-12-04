package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings("all")
public class Triangle {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size()];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        return bottomUp(triangle);

        //return topDown(triangle, 0, 0, dp);
    }

    public static int topDown(List<List<Integer>> triangle, int row, int col, int[][] dp) {
        if (row == triangle.size() - 1) return triangle.get(row).get(col);

        if (dp[row][col] != Integer.MAX_VALUE) return dp[row][col];

        int nextRow = topDown(triangle, row + 1, col, dp);
        int diagonal = topDown(triangle, row + 1, col + 1, dp);

        dp[row][col] = triangle.get(row).get(col) + Math.min(nextRow, diagonal);

        return dp[row][col];
    }


    public static int bottomUp(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        // Fill the last row
        for (int i = 0; i < triangle.get(n - 1).size(); i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {

            for (int j = 0; j < triangle.get(i).size(); j++) {

                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }

        }

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
