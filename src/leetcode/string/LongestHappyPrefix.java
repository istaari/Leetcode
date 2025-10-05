package leetcode.string;

/**
 * LeetCode Problem 1392: Longest Happy Prefix
 *
 * A string is called a happy prefix if it is a non-empty prefix of the string
 * that is also a suffix of the string (excluding the string itself).
 *
 * Given a string s, return its longest happy prefix. Return an empty string ""
 * if no such prefix exists.
 *
 * A "proper prefix" is a prefix that is not equal to the whole string.
 * A "proper suffix" is a suffix that is not equal to the whole string.
 * The problem asks for the longest proper prefix that is also a proper suffix.
 *
 * This is a classic application of the Knuth-Morris-Pratt (KMP) algorithm's
 * preprocessing step, which builds a Longest Proper Prefix Suffix (LPS) array.
 *
 * Example 1:
 * Input: s = "level"
 * Output: "l"
 * Explanation: The prefixes are "l", "le", "lev", "leve". The suffixes are "l", "el", "vel", "evel".
 * The longest common prefix-suffix is "l".
 *
 * Example 2:
 * Input: s = "ababab"
 * Output: "abab"
 *
 * Example 3:
 * Input: s = "leetcodeleet"
 * Output: "leet"
 */
public class LongestHappyPrefix {


    public static String longestPrefix(String s) {
        if (s.length() <= 1) {
            return "";
        }

        // The `lps` array stores the length of the longest proper prefix of the substring
        // s[0...i] that is also a suffix of that substring.
        int[] lps = new int[s.length()];

        // `lpsIndex` (often called `len` or `j`) tracks the length of the
        // current longest prefix suffix. It points to the character *after* the prefix.
        int lpsIndex = 0;

        // `i` is the main pointer that iterates through the string to build the lps array.
        int i = 1;

        while (i < s.length()) {
            // Case 1: The characters match.
            // This means we have extended the current longest prefix-suffix by one character.
            if (s.charAt(lpsIndex) == s.charAt(i)) {
                lpsIndex++;
                lps[i] = lpsIndex;
                i++;
            } else {
                // Case 2: The characters do not match.
                if (lpsIndex != 0) {
                    // This is the core trick of KMP. We don't start over from scratch.
                    // We "fall back" by looking for the lps of the previous prefix.
                    // This tells us the length of the next best prefix to try matching.
                    lpsIndex = lps[lpsIndex - 1];
                } else {
                    // We have fallen all the way back to a prefix of length 0.
                    // We record that lps[i] is 0 and move to the next character.
                    lps[i] = 0;
                    i++;
                }
            }
        }

        // The length of the longest happy prefix for the entire string `s`
        // is the last value stored in the `lps` array.
        int longestPrefixLength = lps[s.length() - 1];

        return s.substring(0, longestPrefixLength);
    }


    public static void main(String[] args) {
        String s1 = "level";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Longest Happy Prefix: \"" + longestPrefix(s1) + "\""); // Expected: "l"

        String s2 = "ababab";
        System.out.println("\nInput: \"" + s2 + "\"");
        System.out.println("Longest Happy Prefix: \"" + longestPrefix(s2) + "\""); // Expected: "abab"

        String s3 = "acccbaaacccbaac";
        System.out.println("\nInput: \"" + s3 + "\"");
        System.out.println("Longest Happy Prefix: \"" + longestPrefix(s3) + "\""); // Expected: "ac"
    }
}
