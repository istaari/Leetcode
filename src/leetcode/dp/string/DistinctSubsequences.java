package leetcode.dp.string;

import java.util.Arrays;

public class DistinctSubsequences {

    public static int helper(String s, String t, int i, int j, int[][] dp) {
        if (j >= t.length()) return 1;

        if (i >= s.length()) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            dp[i][j] = helper(s, t, i + 1, j + 1, dp) + helper(s, t, i + 1, j, dp);

        } else {
            dp[i][j] = helper(s, t, i + 1, j, dp);
        }

        return dp[i][j];

    }

    public static int iterative(String s, String t) {
        int m = t.length(); // Target
        int n = s.length(); // Source
        int[][] dp = new int[m + 1][n + 1];

        for (int j = 0; j <= n; j++) {
            // The first row is set to 1 because there's one way to match an empty string t in any prefix of s: by deleting all characters.
            dp[0][j] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[t.length()][s.length()];
    }

    public static int recursive(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int[] m : dp) Arrays.fill(m, -1);

        return helper(s, t, 0, 0, dp);
    }

    public static int numDistinct(String s, String t) {
        return recursive(s, t);
    }


    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";

        System.out.println(numDistinct(s, t));
    }


}
