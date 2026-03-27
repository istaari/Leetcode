package leetcode.slidingWindow.variableSize;

/**
 * LeetCode 76: Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Given two strings s and t of lengths m and n respectively, return the minimum
 * window substring of s such that every character in t (including duplicates)
 * is included in the window. If there is no such substring, return "".
 *
 * Example 1:
 *   Input: s = "ADOBECODEBANC", t = "ABC"
 *   Output: "BANC"
 *
 * Example 2:
 *   Input: s = "a", t = "a"
 *   Output: "a"
 *
 * Constraints:
 *   - m == s.length, n == t.length
 *   - 1 <= m, n <= 10^5
 *   - s and t consist of uppercase and lowercase English letters
 *
 * Approach: Variable-size Sliding Window with Frequency Array
 *   - Use a frequency array (size 128 for ASCII) to count required chars from t.
 *   - Expand right: decrement count; if count was > 0, increment matched counter.
 *   - When all chars matched, shrink from left to find minimum window.
 *   - Track minimum length window and its start index.
 *
 * Time Complexity: O(m + n)
 * Space Complexity: O(1) — fixed 128-size array
 */
public class MinimumWindowSubstring {


    public static String minWindow(String s, String t) {

        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }


        int[] count = new int[128];
        // Count the frequency of target
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i)]++;
        }

        int startIndex = - 1;
        int minLen = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int counter = 0;

        while (right < s.length()) {

            if (count[s.charAt(right)] > 0) {
                counter++;
            }

            count[s.charAt(right)]--;

            while (counter == t.length()) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                count[s.charAt(left)]++;
                if (count[s.charAt(left)] > 0) {
                    counter--;
                }

                left++;
            }

            right++;
        }

        return (startIndex == -1) ? "" : s.substring(startIndex, startIndex + minLen);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }


}
