package leetcode.slidingWindow.fixedSize;

/**
 * LeetCode Problem: 567. Permutation in String
 * <p>
 * Question:
 * Given two strings `s1` and `s2`, return `true` if `s2` contains a permutation of `s1`, or `false` otherwise.
 * In other words, return `true` if one of `s1`'s permutations is a substring of `s2`.
 * <p>
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * <p>
 * Constraints:
 * - 1 <= s1.length, s2.length <= 10^4
 * - s1 and s2 consist of lowercase English letters.
 */
public class PermutationsInString {

    public static boolean checkInclusion(String s1, String s2) {
        // --- The Sliding Window Strategy ---
        // The core idea is to maintain a "window" in s2 and a frequency map of characters
        // required by s1. We check if the characters within the window match the frequency map.

        if (s1.length() > s2.length()) {
            return false;
        }

        int left = 0; // The left pointer of our sliding window.
        // `count` array acts as a frequency map for characters needed by s1.
        int[] count = new int[26];

        // Step 1: Populate the frequency map with characters from s1.
        // This tells us which characters we "need" to find in our window.
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
        }

        // Step 2: Slide the window across s2 using the right pointer.
        for (int right = 0; right < s2.length(); right++) {
            char current = s2.charAt(right);
            // As a character enters the window from the right, we decrement its required count.
            count[current - 'a']--;

            // Step 3: Ensure the window is valid.
            // If a count becomes negative, it means our current window has too many of that character.
            // We must shrink the window from the left until it becomes valid again.
            while (count[current - 'a'] < 0) {
                // As the left-most character leaves the window, we add its count back.
                count[s2.charAt(left) - 'a']++;
                left++; // Shrink the window by moving the left pointer.
            }

            // Step 4: Check if a valid permutation is found.
            // If the window size equals the length of s1, it means the window contains
            // exactly the characters needed for a permutation.
            if (right - left + 1 == s1.length()) {
                return true;
            }
        }

        // If we finish iterating and haven't found a valid window, no permutation exists.
        return false;
    }


    static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        // The window will slide until it becomes "ba".
        // At that point, the window size is 2 (same as s1.length()), and the character counts match.
        System.out.println(checkInclusion(s1, s2)); // Expected: true
    }
}