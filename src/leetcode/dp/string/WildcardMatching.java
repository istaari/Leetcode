package leetcode.dp.string;

import java.util.Arrays;

public class WildcardMatching {

    private static boolean helper(String s, String p, int i, int j, int[][] dp) {
        // If the pattern is exhausted, check if the string is also exhausted
        if (j == p.length()) {
            return i == s.length();
        }

        // If the string is exhausted but the pattern is not
        if (i == s.length()) {
            // All remaining characters in the pattern must be '*'
            for (int k = j; k < p.length(); k++) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }

        // Check if the result is already computed
        if (dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        // If the characters match or the pattern has '?', move both pointers
        if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
            dp[i][j] = helper(s, p, i + 1, j + 1, dp) ? 1 : 0;
        }
        // If the pattern has a '*' character
        else if (p.charAt(j) == '*') {
            // Try two cases:
            // 1. '*' matches zero characters (skip the '*' in a pattern).
            // 2. '*' matches one or more characters (move to the next character in s).
            dp[i][j] = (helper(s, p, i + 1, j, dp) || helper(s, p, i, j + 1, dp)) ? 1 : 0;
        } else {
            dp[i][j] = 0; // Characters do not match and no special wildcard
        }

        return dp[i][j] == 1;
    }


    public static boolean recursive(String s, String p) {
        int m = s.length();
        int n = p.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(s, p, 0, 0, dp);
    }


    public static boolean iterative(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty pattern matches empty string
        dp[0][0] = true;

        // Fill in the first row (when string is empty but the pattern is not)
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1]; // '*' can match an empty sequence
            }
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1]; // Match the current characters
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // '*' matches zero or more characters
                }
            }
        }

        return dp[m][n];
    }


    public static boolean isMatch(String s, String p) {
        return recursive(s, p);
    }


    public static void main(String[] args) {
        System.out.println(iterative("abc", "a*b*"));
    }

}
