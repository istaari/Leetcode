package leetcode.dp.fibonacci;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * Given a string s containing digits, return the number of ways to decode it.
 */
public class DecodeWays {

    public static int recursive(String s, int i, int[] memo) {
        // Base case: if we've reached the end of the string, there's 1 way to decode it
        if (i == s.length()) {
            return 1;
        }

        // If the current character is '0', we can't decode it, so return 0
        if (s.charAt(i) == '0') {
            return 0;
        }


        if (memo[i] != -1) {
            return memo[i];
        }

        // Initialize ways to decode from this index
        int ways = 0;

        // Option 1: Decode the current single character (it must be between '1' and '9')
        ways += recursive(s, i + 1, memo);

        // Option 2: Decode the current two characters (it must be between '10' and '26')
        if (i + 1 < s.length()) {
            int num = Integer.parseInt(s.substring(i, i + 2));
            if (num >= 10 && num <= 26) {
                ways += recursive(s, i + 2, memo);
            }
        }


        memo[i] = ways;
        return ways;
    }


    public static int iterative(String s) {

        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            int firstDigit = Integer.parseInt(s.substring(i - 1, i));
            int secondDigit = Integer.parseInt(s.substring(i - 2, i));
            if (firstDigit >= 1 && firstDigit <= 9) {
                dp[i] += dp[i - 1];
            }

            if (secondDigit >= 10 && secondDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        String s = "06";
        System.out.println(iterative(s));
    }

}
