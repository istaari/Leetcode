package leetcode.dp.string;

/**
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * Given an input string s and a pattern p, implement regular expression matching
 * with support for '.' and '*' where:
 *   '.' Matches any single character.
 *   '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the ENTIRE input string (not partial).
 *
 * Example 1: s = "aa", p = "a"   -> false
 * Example 2: s = "aa", p = "a*"  -> true  (a* means zero or more 'a')
 * Example 3: s = "ab", p = ".*"  -> true  (.* means zero or more of any char)
 *
 * Constraints:
 *   1 <= s.length <= 20
 *   1 <= p.length <= 20
 *   s contains only lowercase English letters.
 *   p contains only lowercase English letters, '.', and '*'.
 *   Each '*' has a previous valid character to repeat.
 *
 * ---
 * Approach: 2D DP
 *
 * STATE:  dp[i][j] = does s[0..i-1] match p[0..j-1]?
 * BASE:   dp[0][0] = true (empty string matches empty pattern)
 *         dp[0][j] = true if p[j-1]=='*' and dp[0][j-2] (pattern like a*b*c* can match "")
 * TRANSITION:
 *   If p[j-1] == s[i-1] or p[j-1] == '.':
 *     dp[i][j] = dp[i-1][j-1]   (characters match, move both pointers)
 *   If p[j-1] == '*':
 *     dp[i][j] = dp[i][j-2]     (use * as zero occurrences, skip X* pair)
 *          OR   dp[i-1][j]       (if p[j-2] matches s[i-1], use * for one more)
 *
 * KEY DIFFERENCE from Wildcard Matching (LC 44):
 *   - In Wildcard, '*' matches any sequence independently
 *   - Here, '*' modifies the PRECEDING character (X* = zero or more X)
 *   - So we always look at p[j-2] when handling '*'
 *
 * DP Matrix trace: s = "aab", p = "c*a*b"
 *
 *          ""    c     *     a     *     b
 *    ""  [ T,    F,    T,    F,    T,    F ]
 *     a  [ F,    F,    F,    T,    T,    F ]
 *     a  [ F,    F,    F,    F,    T,    F ]
 *     b  [ F,    F,    F,    F,    F,    T ]
 *
 *   dp[0][2]: p[1]='*', dp[0][0]=T -> T (c* matches "")
 *   dp[0][4]: p[3]='*', dp[0][2]=T -> T (c*a* matches "")
 *   dp[1][3]: p[2]='a' == s[0]='a', dp[0][2]=T -> T
 *   dp[1][4]: p[3]='*', dp[1][2]=F (zero a) OR (p[2]='a'==s[0]='a', dp[0][4]=T) -> T
 *   dp[2][4]: p[3]='*', dp[2][2]=F OR (p[2]='a'==s[1]='a', dp[1][4]=T) -> T
 *   dp[3][5]: p[4]='b'==s[2]='b', dp[2][4]=T -> T  ✓
 *
 * ANSWER: dp[m][n]
 *
 * Time:  O(m * n) where m = s.length(), n = p.length()
 * Space: O(m * n)
 */
public class RegularExpressionMatching {

    // -------------------- Recursive + Memoization --------------------
    public static boolean isMatchRecursive(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return solve(s, p, 0, 0, memo);
    }

    private static boolean solve(String s, String p, int i, int j, Boolean[][] memo) {
        // Base case: pattern exhausted
        if (j == p.length()) return i == s.length();

        if (memo[i][j] != null) return memo[i][j];

        // Check if current characters match
        boolean firstMatch = (i < s.length()) &&
                             (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        boolean result;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // Two choices for X*:
            // 1. Skip X* entirely (zero occurrences) -> advance pattern by 2
            // 2. If first char matches, consume one char from s, keep pattern (more occurrences)
            result = solve(s, p, i, j + 2, memo) ||             // zero occurrences
                     (firstMatch && solve(s, p, i + 1, j, memo)); // one+ occurrences
        } else {
            // No star ahead: must match current character and advance both
            result = firstMatch && solve(s, p, i + 1, j + 1, memo);
        }

        memo[i][j] = result;
        return result;
    }

    // -------------------- Iterative (Bottom-Up) --------------------
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string matches empty pattern
        dp[0][0] = true;

        // Fill first row: patterns like a*, a*b*, a*b*c* can match ""
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; // X* matches zero X's
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1); // Current char in s
                char pc = p.charAt(j - 1); // Current char in p

                if (pc == '.' || pc == sc) {
                    // Characters match: inherit diagonal
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // Star: always try zero occurrences first
                    dp[i][j] = dp[i][j - 2]; // Zero occurrences of p[j-2]

                    // If the preceding pattern char matches s[i-1], try one more occurrence
                    char preceding = p.charAt(j - 2);
                    if (preceding == '.' || preceding == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                        // dp[i-1][j] means: s up to i-1 already matched with same pattern,
                        // so we can extend by consuming one more character
                    }
                }
                // else: mismatch, dp[i][j] stays false
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));       // false
        System.out.println(isMatch("aa", "a*"));      // true
        System.out.println(isMatch("ab", ".*"));      // true
        System.out.println(isMatch("aab", "c*a*b"));  // true
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false

        System.out.println("--- Recursive ---");
        System.out.println(isMatchRecursive("aab", "c*a*b"));  // true
        System.out.println(isMatchRecursive("ab", ".*"));       // true
    }
}
