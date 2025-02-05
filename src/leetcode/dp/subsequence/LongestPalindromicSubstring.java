package leetcode.dp.subsequence;

public class LongestPalindromicSubstring {

    // Iterative solution for longest palindromic substring
    public static String iterative(String s) {
        String result = "";
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;

                    if (i - j + 1 > result.length()) {
                        result = s.substring(j, Math.min(i + 1, s.length()));
                    }
                }
            }
        }

        return result;
    }


    public static String expandAroundCenter(String s, int left, int right) {
        int n = s.length();
        String result = "";
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > result.length()) {
                result = s.substring(left, Math.min(right + 1, n));
            }

            left--;
            right++;
        }

        return result;
    }

    public static String findLongestPalindromicSubstring(String s) {
        int n = s.length();
        String result = "";

        for (int i = 0; i < n; i++) {
            String odd = expandAroundCenter(s, i, i);
            String even = expandAroundCenter(s, i, i + 1);

            if (odd.length() > result.length()) {
                result = odd;
            }

            if (even.length() > result.length()) {
                result = even;
            }
        }

        return result;
    }


    static String longestPalindrome(String s) {
        return findLongestPalindromicSubstring(s);
    }


    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
        s = "cbbd";
        System.out.println(longestPalindrome(s));
    }


}
