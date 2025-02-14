package leetcode.dp.string;

public class LongestPalindromicSubstringE {

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


    public static void main(String[] args) {
        String s = "babad";
        System.out.println(findLongestPalindromicSubstring(s));
    }

}
