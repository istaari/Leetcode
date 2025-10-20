package leetcode.string;

public class LongestPalindromicSubstringExpandAround {

    public static String findLongestPalindromicSubstring(String s) {
        if (s == null || s.isEmpty()) return "";

        int n = s.length();
        int start = 0;
        int maxLength = 1;

        for (int i = 0; i < n; i++) {

            // Check for odd length palindromes (centered at i)
            int left = i, right = i;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    start = left;
                    maxLength = right - left + 1;
                }
                left--;
                right++;
            }

            // Check for even length palindromes (centered between i-1 and i)
            left = i;
            right = i + 1;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    start = left;
                    maxLength = right - left + 1;
                }
                left--;
                right++;
            }

        }

        return s.substring(start, start + maxLength);
    }


    static void main(String[] args) {
        String s = "babad";
        System.out.println(findLongestPalindromicSubstring(s));
    }

}
