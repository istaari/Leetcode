package leetcode.dp.string;

import java.util.Arrays;

/**
 * 115. Distinct Subsequences
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * Given two strings s and t, return the number of distinct subsequences of s
 * which equals t.
 *
 * A subsequence of a string is a new string formed by deleting some (or no)
 * characters without disturbing the relative positions of the remaining characters.
 *
 * Example 1: Input: s = "rabbbit", t = "rabbit" -> Output: 3
 * Example 2: Input: s = "babgbag", t = "bag"    -> Output: 5
 *
 * Constraints:
 *   1 <= s.length, t.length <= 1000
 *   s and t consist of English letters.
 *
 * ---
 * Approach: 2D DP (top-down memoization + bottom-up iterative)
 *
 * STATE:      dp[i][j] = number of ways to form t[0..i-1] from s[0..j-1]
 * BASE:       dp[0][j] = 1 for all j (empty t matches any prefix of s exactly once)
 * TRANSITION: If t[i-1] == s[j-1]: dp[i][j] = dp[i-1][j-1] + dp[i][j-1]
 *               (match this char OR skip s[j-1])
 *             Else: dp[i][j] = dp[i][j-1] (skip s[j-1])
 * ANSWER:     dp[t.length][s.length]
 *
 * Time:  O(m * n)
 * Space: O(m * n)
 */
public class DistinctSubsequences {

    // Top-down: count ways to match t[j..] using s[i..]
    public static int helper(String s, String t, int i, int j, int[][] dp) {
        // All of t is matched
        if (j >= t.length()) return 1;
        // s is exhausted but t is not
        if (i >= s.length()) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            // Two choices: use s[i] to match t[j], or skip s[i]
            dp[i][j] = helper(s, t, i + 1, j + 1, dp) + helper(s, t, i + 1, j, dp);

        } else {
            // Characters don't match, must skip s[i]
            dp[i][j] = helper(s, t, i + 1, j, dp);
        }

        return dp[i][j];
    }

    public static int recursive(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int[] m : dp) Arrays.fill(m, -1);

        return helper(s, t, 0, 0, dp);
    }

    /**
     * Bottom-up iterative DP
     *
     * Example: s = "babgbag", t = "bag" -> 5
     *
     * dp[i][j] = number of ways to form t[0..i-1] from s[0..j-1]
     *
     *          ""  b  a  b  g  b  a  g
     *     ""  [ 1, 1, 1, 1, 1, 1, 1, 1 ]   <- base: empty t matches any prefix once
     *     b   [ 0, 1, 1, 2, 2, 3, 3, 3 ]
     *     a   [ 0, 0, 1, 1, 1, 1, 4, 4 ]
     *     g   [ 0, 0, 0, 0, 1, 1, 1, 5 ]   <- answer: dp[3][7] = 5
     *
     * Trace for dp[2][6] (t="ba", s="babgba"):
     *   t[1]='a' == s[5]='a' → dp[1][5] + dp[2][5] = 3 + 1 = 4
     *   (3 ways to match 'b' in "babgb" + 1 way already found for "ba" in "babgb")
     */
    public static int iterative(String s, String t) {
        int m = t.length(); // Target
        int n = s.length(); // Source
        int[][] dp = new int[m + 1][n + 1];

        // Base case: empty t can be formed from any prefix of s in exactly 1 way
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    // Match s[j-1] with t[i-1] + skip s[j-1]
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    // Skip s[j-1]
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[t.length()][s.length()];
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
