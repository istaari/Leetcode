package leetcode.slidingWindow.variableSize;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 3: Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string s, find the length of the longest substring without
 * repeating characters.
 *
 * Example 1:
 *   Input: s = "abcabcbb"
 *   Output: 3 ("abc")
 *
 * Example 2:
 *   Input: s = "bbbbb"
 *   Output: 1 ("b")
 *
 * Example 3:
 *   Input: s = "pwwkew"
 *   Output: 3 ("wke")
 *
 * Constraints:
 *   - 0 <= s.length <= 5 * 10^4
 *   - s consists of English letters, digits, symbols and spaces
 *
 * Approach: Variable-size Sliding Window + HashSet
 *   - Expand right: add character to set.
 *   - If duplicate found, shrink from left until the duplicate is removed.
 *   - Track maximum window size.
 *
 * Time Complexity: O(n) — each char is added/removed from set at most once
 * Space Complexity: O(min(n, m)) where m is the character set size
 */
public class LongestSubstringWithoutRepeating {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int result = 0;

        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));
            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating sol = new LongestSubstringWithoutRepeating();
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // 3
    }
}
