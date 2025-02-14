package leetcode.dp.string;

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


    static String longestPalindrome(String s) {
        return iterative(s);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(longestPalindrome(s));
    }

}
