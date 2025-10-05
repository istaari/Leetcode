package leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode Problem 1763: Longest Nice Substring
 *
 * A string s is nice if for every letter of the alphabet that s contains, it
 * appears in both uppercase and lowercase. For example, "abABB" is nice because
 * 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not nice because
 * 'b' appears but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice. If there are
 * multiple, return the substring of the earliest occurrence. If there are none,
 * return an empty string.
 *
 * Example 1:
 * Input: s = "YazaAay"
 * Output: "aAa"
 * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the
 * alphabet in s, and both 'A' and 'a' appear. "aAa" is the longest nice substring.
 *
 * Example 2:
 * Input: s = "Bb"
 * Output: "Bb"
 *
 * Example 3:
 * Input: s = "c"
 * Output: ""
 */
public class LongestNiceSubstring {


    public static String longestNiceSubstring(String s) {
        if (s.length() < 2) {
            return ""; // A single character cannot be nice.
        }

        // Use a Set to quickly find all unique characters in the current string s.
        Set<Character> charSet = new HashSet<>();
        for (char c : s.toCharArray()) {
            charSet.add(c);
        }

        // Find the first "bad" character that breaks the nice property.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charSet.contains(Character.toUpperCase(c)) && charSet.contains(Character.toLowerCase(c))) {
                continue; // This character is fine, check the next one.
            }

            // If we are here, `c` is a "bad" character.
            // The longest nice substring must be entirely to its left or entirely to its right.
            // Divide and conquer: split the string at this character and solve for both halves.
            String leftSubstring = longestNiceSubstring(s.substring(0, i));
            String rightSubstring = longestNiceSubstring(s.substring(i + 1));

            // Return the longer of the two results.
            return leftSubstring.length() >= rightSubstring.length() ? leftSubstring : rightSubstring;
        }

        // If the loop completes without finding any "bad" characters,
        // it means the entire string `s` is nice.
        return s;
    }


    /**
     * Brute-force solution.
     * This method checks every possible substring to see if it's "nice" and
     * keeps track of the longest one found.
     * Time Complexity: O(N^3) due to nested loops and substring creation/checking.
     */
    public static String longestNiceSubstringBruteForce(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (isNice(sub)) {
                    if (sub.length() > result.length()) {
                        result = sub;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Helper for the brute-force method to check if a single string is nice.
     */
    private static boolean isNice(String sub) {
        Set<Character> set = new HashSet<>();
        for (char c : sub.toCharArray()) {
            set.add(c);
        }
        for (char c : set) {
            if (Character.isUpperCase(c) && !set.contains(Character.toLowerCase(c))) {
                return false;
            }
            if (Character.isLowerCase(c) && !set.contains(Character.toUpperCase(c))) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String s1 = "YazaAay";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Optimized Output: \"" + longestNiceSubstring(s1) + "\""); // Expected: "aAa"

        String s2 = "Bb";
        System.out.println("\nInput: \"" + s2 + "\"");
        System.out.println("Optimized Output: \"" + longestNiceSubstring(s2) + "\""); // Expected: "Bb"

        String s3 = "c";
        System.out.println("\nInput: \"" + s3 + "\"");
        System.out.println("Optimized Output: \"" + longestNiceSubstring(s3) + "\""); // Expected: ""
    }
}
