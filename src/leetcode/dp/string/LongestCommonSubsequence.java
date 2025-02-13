package leetcode.dp.string;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static int helper(char[] text1, char[] text2, int i, int j, int[][] dp) {
        if (i >= text1.length || j >= text2.length) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (text1[i] == text2[j]) {
            dp[i][j] = 1 + helper(text1, text2, i + 1, j + 1, dp);

        } else {
            int max1 = helper(text1, text2, i + 1, j, dp);
            int max2 = helper(text1, text2, i, j + 1, dp);

            dp[i][j] = Math.max(max1, max2);
        }

        return dp[i][j];
    }


    public static int recursive(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        for (int[] m : dp) {
            Arrays.fill(m, -1);
        }
        return helper(text1.toCharArray(), text2.toCharArray(), 0, 0, dp);
    }


    public static int iterative(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }


    public static int longestCommonSubsequence(String text1, String text2) {
        return recursive(text1, text2);
    }


    public static void main(String[] args) {
        String text1 = "abcdefghp";
        String text2 = "xazlfnogh";

        System.out.println(longestCommonSubsequence(text1, text2));
    }


}
