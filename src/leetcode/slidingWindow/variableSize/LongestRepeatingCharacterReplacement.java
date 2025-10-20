package leetcode.slidingWindow.variableSize;

/**
 * LeetCode Problem 424: Longest Repeating Character Replacement
 *
 * You are given a string s and an integer k. You can choose any character of the
 * string and change it to any other uppercase English character. You can perform
 * this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can
 * get after performing the above operations.
 *
 * The solution uses a variable-size sliding window approach.
 *
 * Example 1:
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the 'A' at index 2 with 'B' to get "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 * Example 2:
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 */
public class LongestRepeatingCharacterReplacement {


    public static int characterReplacement(String s, int k) {
        // This array will store the frequency of each character in the current window.
        // 'A' corresponds to index 0, 'B' to 1, and so on.
        int[] count = new int[26];

        // 'left' and 'right' pointers define the current sliding window.
        int left = 0;
        int right = 0;

        // 'result' stores the maximum valid window size found so far.
        int result = 0;

        // 'mostFrequent' tracks the count of the most frequent character in the current window.
        // This is a key optimization for the sliding window condition.
        int mostFrequent = 0;

        // Loop through the string with the 'right' pointer to expand the window.
        while (right < s.length()) {
            // == 1. EXPAND THE WINDOW ==
            char rightChar = s.charAt(right);
            count[rightChar - 'A']++;

            // Update 'mostFrequent'. We only need the max frequency seen so far in any window.
            mostFrequent = Math.max(mostFrequent, count[rightChar - 'A']);

            // == 2. CHECK VALIDITY & SHRINK THE WINDOW IF NEEDED ==
            // A window is valid if: windowLength - mostFrequent <= k.
            // This means the number of characters that need to be replaced is within our limit 'k'.
            // If this condition is not met, the window is invalid, and we must shrink it
            // from the left.
            while ((right - left + 1) - mostFrequent > k) {
                char leftChar = s.charAt(left);
                count[leftChar - 'A']--;
                // Shrink the window by moving the left pointer.
                left++;
            }

            // == 3. UPDATE THE RESULT ==
            // At this point, the window [left, right] is guaranteed to be valid.
            // Update our result with the size of this current valid window if it's larger.
            result = Math.max(result, right - left + 1);

            // Continue expanding the window by moving the right pointer.
            right++;
        }

        // Return the maximum length found throughout the process.
        return result;
    }


    public static void main(String[] args) {
        // Create a test case: s = "AABABBA", k = 1
        String s = "AABABBA";
        int k = 1;

        System.out.println("Input String: " + s);
        System.out.println("Max replacements (k): " + k);

        int result = characterReplacement(s, k);

        System.out.println("Longest repeating character substring length: " + result);
        // Expected Output: 4
        // The substring "AABA" can be made "AAAA" with 1 change. Length is 4.
        // The substring "ABBA" can be made "BBBB" with 1 change. Length is 4.
    }
}