package leetcode.dp.string;

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1: Input: s = "babad" -> Output: "bab" (or "aba")
 * Example 2: Input: s = "cbbd"  -> Output: "bb"
 *
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consist of only digits and English letters.
 *
 * ---
 * Approach: 2D DP
 *
 * dp[j][i] = true if s[j..i] is a palindrome.
 * A substring s[j..i] is a palindrome if:
 *   1. s[j] == s[i], AND
 *   2. The inner substring is a palindrome (i-j <= 2 OR dp[j+1][i-1])
 *
 * Track the longest palindrome found during the fill.
 *
 * Time:  O(n^2)
 * Space: O(n^2)
 */
public class LongestPalindromicSubstring {

    /**
     * Bottom-up iterative DP
     *
     * Example: s = "babad" -> "bab"
     *
     * dp[j][i] = true if substring s[j..i] is a palindrome
     *
     *          b     a     b     a     d
     *     b  [ T,    F,    T,    F,    F ]
     *     a  [ -,    T,    F,    T,    F ]
     *     b  [ -,    -,    T,    F,    F ]
     *     a  [ -,    -,    -,    T,    F ]
     *     d  [ -,    -,    -,    -,    T ]
     *
     * '-' = below diagonal (j > i), not used
     *
     * Trace for dp[0][2] (s[0..2] = "bab"):
     *   s[0]='b' == s[2]='b', i-j=2 <= 2 → T (palindrome!)
     *   length 3 > current best → result = "bab"
     *
     * Trace for dp[1][3] (s[1..3] = "aba"):
     *   s[1]='a' == s[3]='a', i-j=2 <= 2 → T
     *   length 3 = current best → no update
     */
    public static String iterative(String s) {
        String result = "";
        boolean[][] dp = new boolean[s.length()][s.length()];

        // Base case: every single character is a palindrome
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                // s[j..i] is palindrome if ends match AND inner part is valid
                // (i-j <= 2 handles length 2 and 3 substrings directly)
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;

                    // Update result if this palindrome is longer
                    if (i - j + 1 > result.length()) {
                        result = s.substring(j, Math.min(i + 1, s.length()));
                    }
                }
            }
        }

        return result;
    }


    static String longestPalindrome(String s) {
        return iterative(s);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

}
