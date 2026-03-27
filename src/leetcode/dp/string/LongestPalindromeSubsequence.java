package leetcode.dp.string;

import java.util.Arrays;

/**
 * 516. Longest Palindromic Subsequence
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 * A subsequence is derived by deleting some or no characters without
 * changing the relative order of the remaining characters.
 *
 * Example 1: Input: s = "bbbab" -> Output: 4  ("bbbb")
 * Example 2: Input: s = "cbbd"  -> Output: 2  ("bb")
 *
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists only of lowercase English letters.
 *
 * ---
 * Approach: Interval DP (top-down + bottom-up)
 *
 * STATE:      dp[i][j] = length of longest palindromic subsequence in s[i..j]
 * BASE:       dp[i][i] = 1 (single character is a palindrome)
 * TRANSITION: If s[i] == s[j]: dp[i][j] = 2 + dp[i+1][j-1]
 *             Else: dp[i][j] = max(dp[i+1][j], dp[i][j-1])
 * ANSWER:     dp[0][n-1]
 *
 * Time:  O(n^2)
 * Space: O(n^2)
 */
public class LongestPalindromeSubsequence {

    // Top-down: find longest palindromic subsequence in s[i..j]
    public static int helper(String s, int i, int j, int[][] dp) {
        if (i > j) return 0;           // Invalid range
        if (i == j) return 1;           // Single character palindrome

        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == s.charAt(j)) {
            // Both ends match: include them + solve inner substring
            dp[i][j] = 2 + helper(s, i + 1, j - 1, dp);
        } else {
            // Ends don't match: try excluding one end at a time
            dp[i][j] = Math.max(helper(s, i + 1, j, dp), helper(s, i, j - 1, dp));
        }

        return dp[i][j];
    }


    public static int recursive(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int[] row : dp) Arrays.fill(row, -1);

        return helper(s, 0, s.length() - 1, dp);
    }

    /**
     * Bottom-up: fill diagonally from single chars toward full string
     *
     * Example: s = "cbbd" -> 2 (LPS = "bb")
     *
     * dp[i][j] = length of longest palindromic subsequence in s[i..j]
     *
     *          c    b    b    d
     *     c  [ 1,   1,   2,   2 ]   <- dp[0][3] = 2 (answer)
     *     b  [ -,   1,   2,   2 ]   dp[1][2]: b==b → 2+0=2
     *     b  [ -,   -,   1,   1 ]   dp[2][3]: b≠d → max(1,1)=1
     *     d  [ -,   -,   -,   1 ]   <- base: single chars = 1
     *
     * '-' = unused (i > j)
     *
     * Trace for dp[0][3] (s="cbbd"):
     *   s[0]='c' != s[3]='d' → max(dp[1][3], dp[0][2]) = max(2, 2) = 2
     */
    public static int iterative(String s) {
        int n = s.length();

        int[][] dp = new int[n][n];

        // Base case: every single character is a palindrome of length 1
        for (int i = 0; i < n; i++) dp[i][i] = 1;

        // Fill column by column; for each j, process all i < j (bottom-up)
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
