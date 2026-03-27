package leetcode.dp.linear;

/**
 * 91. Decode Ways
 * https://leetcode.com/problems/decode-ways/
 *
 * A message containing letters from A-Z can be encoded into numbers using
 * the mapping: 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * Example 1: Input: s = "12" -> Output: 2
 *   Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2: Input: s = "226" -> Output: 3
 *   Explanation: "226" could be "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3: Input: s = "06" -> Output: 0
 *   Explanation: "06" cannot be mapped (leading zero is invalid).
 *
 * Constraints:
 *   1 <= s.length <= 100
 *   s contains only digits and may contain leading zeros.
 *
 * ---
 * Approach: Linear DP (similar to Climbing Stairs with constraints)
 *
 * STATE:      dp[i] = number of ways to decode s[0..i-1]
 * BASE:       dp[0] = 1 (empty prefix = 1 way)
 * TRANSITION:
 *   If s[i-1] != '0': dp[i] += dp[i-1]        (single digit decode)
 *   If s[i-2..i-1] forms 10-26: dp[i] += dp[i-2] (two digit decode)
 * ANSWER:     dp[n]
 *
 * Trace: s = "226"
 *
 *   dp[0] = 1   (base: empty string)
 *   dp[1] = 1   s[0]='2' ≠ '0' → dp[0]=1
 *   dp[2] = 2   s[1]='2' ≠ '0' → dp[1]=1; "22" in [10,26] → dp[0]=1; total=2
 *   dp[3] = 3   s[2]='6' ≠ '0' → dp[2]=2; "26" in [10,26] → dp[1]=1; total=3
 *
 * Time:  O(n)
 * Space: O(n), can optimize to O(1) with two variables
 */
public class DecodeWays {

    /**
     * Bottom-up iterative DP
     */
    public static int iterative(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;

        int n = s.length();
        int[] dp = new int[n + 1];

        // Base cases
        dp[0] = 1; // Empty prefix: one way (do nothing)
        dp[1] = 1; // First character is non-zero (checked above)

        for (int i = 2; i <= n; i++) {
            // Single digit decode: s[i-1] must not be '0'
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }

            // Two digit decode: s[i-2..i-1] must be between 10 and 26
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    /**
     * Space-optimized O(1) — only need prev two values
     */
    public static int iterativeOptimized(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;

        int prevPrev = 1; // dp[i-2]
        int prev = 1;     // dp[i-1]

        for (int i = 2; i <= s.length(); i++) {
            int curr = 0;

            // Single digit
            if (s.charAt(i - 1) != '0') {
                curr += prev;
            }

            // Two digit
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                curr += prevPrev;
            }

            prevPrev = prev;
            prev = curr;
        }

        return prev;
    }

    /**
     * Top-down recursive with memoization
     */
    public static int recursive(String s) {
        Integer[] memo = new Integer[s.length()];
        return helper(s, 0, memo);
    }

    private static int helper(String s, int index, Integer[] memo) {
        // Reached the end: one valid decoding found
        if (index == s.length()) return 1;

        // Leading zero: invalid
        if (s.charAt(index) == '0') return 0;

        if (memo[index] != null) return memo[index];

        // Take one digit
        int ways = helper(s, index + 1, memo);

        // Take two digits (if valid)
        if (index + 1 < s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigit <= 26) {
                ways += helper(s, index + 2, memo);
            }
        }

        memo[index] = ways;
        return ways;
    }

    public static int numDecodings(String s) {
        return iterative(s);
    }

    public static void main(String[] args) {
        System.out.println("\"12\":  " + numDecodings("12"));   // 2
        System.out.println("\"226\": " + numDecodings("226"));  // 3
        System.out.println("\"06\":  " + numDecodings("06"));   // 0
        System.out.println("\"11106\": " + numDecodings("11106")); // 2

        System.out.println("--- Recursive ---");
        System.out.println("\"226\": " + recursive("226"));     // 3

        System.out.println("--- Optimized ---");
        System.out.println("\"226\": " + iterativeOptimized("226")); // 3
    }
}
