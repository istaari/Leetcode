package leetcode.dp.string;

/**
 * 97. Interleaving String
 * https://leetcode.com/problems/interleaving-string/
 *
 * Given strings s1, s2, and s3, determine whether s3 is formed by an
 * interleaving of s1 and s2. An interleaving is defined as choosing
 * characters from s1 and s2 such that their relative order is preserved.
 *
 * Example 1: Input: s1="aabcc", s2="dbbca", s3="aadbbcbcac" -> Output: true
 * Example 2: Input: s1="aabcc", s2="dbbca", s3="aadbbbaccc" -> Output: false
 * Example 3: Input: s1="", s2="", s3="" -> Output: true
 *
 * Constraints:
 *   0 <= s1.length, s2.length <= 100
 *   0 <= s3.length <= 200
 *   s1, s2, and s3 consist of lowercase English letters.
 *
 * ---
 * Approach: 2D DP (top-down + bottom-up)
 *
 * STATE:      dp[i][j] = true if s1[0..i-1] and s2[0..j-1] interleave to form s3[0..i+j-1]
 * BASE:       dp[0][0] = true; first row/col check prefix matches
 * TRANSITION: dp[i][j] = (s1[i-1]==s3[k] && dp[i-1][j]) || (s2[j-1]==s3[k] && dp[i][j-1])
 *             where k = i + j - 1
 * ANSWER:     dp[n][m]
 *
 * Time:  O(n * m)
 * Space: O(n * m)
 */
public class InterleavingString {

    // Top-down: check if s1[i..] and s2[j..] can interleave to form s3[k..]
    public static boolean helper(String s1, String s2, String s3, int i, int j, int k, Boolean[][] dp) {
        // Both strings fully consumed -> s3 is fully matched
        if (i == s1.length() && j == s2.length()) return true;

        if (dp[i][j] != null) return dp[i][j];

        // Try taking the next character from s1
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            dp[i][j] = helper(s1, s2, s3, i + 1, j, k + 1, dp);

            if (dp[i][j]) return true;
        }

        // Try taking the next character from s2
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            dp[i][j] = helper(s1, s2, s3, i, j + 1, k + 1, dp);

            if (dp[i][j]) return true;
        }

        // Neither character matches -> interleaving not possible from here
        return dp[i][j] = false;
    }

    public static boolean recursive(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        Boolean[][] dp = new Boolean[s1.length() + 1][s2.length() + 1];
        helper(s1, s2, s3, 0, 0, 0, dp);

        return helper(s1, s2, s3, 0, 0, 0, dp);
    }


    /**
     * Bottom-up iterative DP
     *
     * Example: s1 = "ab", s2 = "cd", s3 = "acbd" -> true
     *   Interleaving: a(s1) c(s2) b(s1) d(s2) = "acbd"
     *
     * dp[i][j] = can s1[0..i-1] and s2[0..j-1] interleave to form s3[0..i+j-1]?
     *
     *          ""    c     d
     *     ""  [ T,   F,    F ]   s2[0]='c' != s3[0]='a'
     *     a   [ T,   T,    F ]   s1[0]='a'==s3[0], s2[0]='c'==s3[1]
     *     b   [ F,   T,    T ]   <- answer: dp[2][2] = T
     *
     * Trace for dp[2][2] (s3[3]='d'):
     *   s1[1]='b' != s3[3]='d' → skip
     *   s2[1]='d' == s3[3]='d' && dp[2][1]=T → T
     */
    public static boolean iterative(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();

        if (n + m != s3.length()) return false;

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        // Fill the first column (considering only s1)
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // Fill the first row (considering only s2)
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // current index in s3 that we are checking
                int k = (i + j) - 1;
                // Check if the current character from s1 or s2 matches the character at index k in s3.
                // If it matches, inherit truth from previous valid states.
                // dp[i][j] checks if s1[0..i-1] and s2[0..j-1] match s3[0..i+j-1].
                dp[i][j] = (s1.charAt(i - 1) == s3.charAt(k) && dp[i - 1][j]) || (s2.charAt(j - 1) == s3.charAt(k) && dp[i][j - 1]);
            }
        }

        return dp[n][m];
    }


    public static boolean isInterleave(String s1, String s2, String s3) {
        return true;
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac"; // "aa dbbc bc a c";
        System.out.println(iterative(s1, s2, s3));
    }
}
