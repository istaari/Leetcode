package neetcode150.dynamicProgramming1D;

public class PalindromicSubstrings_647 {

    /**
     * @param s input string
     * @return Total palindromic substring
     */
    public static int countSubstrings(String s) {
        return dp(s);
    }

    //-----------------------------------DP Solution-----------------------------------//
    public static int dp(String s) {
        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    count++;
                }
            }
        }
        return count;
    }


    //-----------------------------------Extend Method-----------------------------------//
    /**
     * @param s input string
     * @return Total palindromic substring
     */
    public static int extendPalindrome(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int even = palindromeCount(s, i, i + 1);
            int odd = palindromeCount(s, i - 1, i + 1);
            ans += even + odd + 1;
        }
        return ans;
    }

    /**
     * @param s input string
     * @param left left index
     * @param right right index
     * @return count of palindromic substring
     */
    public static int palindromeCount(String s, int left, int right) {
        int count = 0;
        int n = s.length();
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }


    public static void main(String[] args) {
        System.out.println(extendPalindrome("ababdc"));
    }

}
