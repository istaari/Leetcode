package leetcode.dp.subsequence;

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


    // Reverse the string apply iterative LCS
    public static int longestPalindromeSubseq(String s) {
        return recursive(s);
    }


    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(longestPalindromeSubseq(s));
    }


}
