package leetcode.dp.string;

import java.util.Arrays;

public class LongestPalindromeSubsequence {

    public static int helper(String s, int i, int j, int[][] dp) {
        if (i > j) return 0;
        if (i == j) return 1;

        if (dp[i][j] != -1) return dp[i][j]; // Use memoized result

        if (s.charAt(i) == s.charAt(j)) {
            dp[i][j] = 2 + helper(s, i + 1, j - 1, dp);
        } else {
            dp[i][j] = Math.max(helper(s, i + 1, j, dp), helper(s, i, j - 1, dp));
        }

        return dp[i][j];
    }


    public static int recursive(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int[] row : dp) Arrays.fill(row, -1);

        return helper(s, 0, s.length() - 1, dp);
    }

    public static int iterative(String s) {
        int n = s.length();

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int j = 0; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }


    public static int longestPalindromeSubseq(String s) {
        return recursive(s);
    }


    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(iterative(s));
    }


}
