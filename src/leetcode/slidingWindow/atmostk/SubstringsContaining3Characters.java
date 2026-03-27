package leetcode.slidingWindow.atmostk;

/**
 * LeetCode 1358: Number of Substrings Containing All Three Characters
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
 *
 * Given a string s consisting only of characters 'a', 'b', and 'c', return the
 * number of substrings containing at least one occurrence of all three characters.
 *
 * Example 1:
 *   Input: s = "abcabc"
 *   Output: 10
 *   Explanation: Substrings containing a, b, and c are:
 *   "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc", "abc".
 *
 * Example 2:
 *   Input: s = "aaacb"
 *   Output: 3
 *
 * Constraints:
 *   - 3 <= s.length <= 5 * 10^4
 *   - s only consists of a, b, or c characters
 *
 * Approach: Sliding Window (shrink from left when valid)
 *   - Maintain count of a, b, c in the window.
 *   - When all three are present, every substring starting before left and
 *     ending at right is valid — so add `left` to result (all those starting
 *     positions produce valid substrings).
 *   - Shrink left until the window becomes invalid again.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class SubstringsContaining3Characters {

    public static int numberOfSubstrings(String s) {
        int[] charCount = new int[3];
        int result = 0;
        int left = 0;
        int n = s.length();

        for (int right = 0; right < n; ++right) {
            charCount[s.charAt(right) - 'a']++;

            while (charCount[0] > 0 && charCount[1] > 0 && charCount[2] > 0) {
                charCount[s.charAt(left) - 'a']--;
                left++;
            }

            // Add the number of valid substrings ending at the current position
            result += left;
        }

        return result;
    }


    public static void main(String[] args) {
        String s = "abcabc";
        System.out.println(numberOfSubstrings(s));
    }

}
