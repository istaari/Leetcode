package leetcode.dp.string;

import java.util.Arrays;

/**
 * 1143. Longest Common Subsequence
 * https://leetcode.com/problems/longest-common-subsequence/
 *
 * Given two strings text1 and text2, return the length of their longest
 * common subsequence. If there is no common subsequence, return 0.
 *
 * Example 1: Input: text1 = "abcde", text2 = "ace" -> Output: 3
 *   Explanation: The longest common subsequence is "ace".
 *
 * Example 2: Input: text1 = "abc", text2 = "abc" -> Output: 3
 * Example 3: Input: text1 = "abc", text2 = "def" -> Output: 0
 *
 * Constraints:
 *   1 <= text1.length, text2.length <= 1000
 *   text1 and text2 consist of only lowercase English characters.
 *
 * ---
 * Approach: 2D DP (top-down + bottom-up)
 *
 * STATE:      dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1]
 * BASE:       dp[0][j] = dp[i][0] = 0 (empty string has LCS of 0)
 * TRANSITION: If chars match: dp[i][j] = dp[i-1][j-1] + 1
 *             Else: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
 * ANSWER:     dp[m][n]
 *
 * Time:  O(m * n)
 * Space: O(m * n)
 */
public class LongestCommonSubsequence {

    // Top-down: find LCS of text1[i..] and text2[j..]
    public static int helper(char[] text1, char[] text2, int i, int j, int[][] dp) {
        // Base case: one string is exhausted
        if (i >= text1.length || j >= text2.length) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (text1[i] == text2[j]) {
            // Characters match: include in LCS and advance both
            dp[i][j] = 1 + helper(text1, text2, i + 1, j + 1, dp);

        } else {
            // Don't match: try skipping one character from each string
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


    /**
     * Bottom-up iterative DP
     *
     * Example: text1 = "abcde", text2 = "ace" -> 3 (LCS = "ace")
     *
     * dp[i][j] = LCS length of text1[0..i-1] and text2[0..j-1]
     *
     *          ""  a  c  e
     *     ""  [ 0, 0, 0, 0 ]   <- base: LCS with empty = 0
     *     a   [ 0, 1, 1, 1 ]   a==a → 0+1=1
     *     b   [ 0, 1, 1, 1 ]   b≠a,c,e → carry forward
     *     c   [ 0, 1, 2, 2 ]   c==c → 1+1=2
     *     d   [ 0, 1, 2, 2 ]   d≠a,c,e → carry forward
     *     e   [ 0, 1, 2, 3 ]   e==e → 2+1=3  <- answer
     *
     * Trace for dp[5][3] (text1="abcde", text2="ace"):
     *   'e' == 'e' → dp[4][2] + 1 = 2 + 1 = 3
     */
    public static int iterative(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Base cases: dp[i][0] = 0, dp[0][j] = 0 (already zero-initialized)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // Characters match: extend LCS by 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // Skip one character from either string
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
        String text1 = "mom";
        String text2 = "mom";

        System.out.println(longestCommonSubsequence(text1, text2));
    }


}
