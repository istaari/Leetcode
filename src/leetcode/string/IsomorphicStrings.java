package leetcode.string;

import java.util.HashMap;

/**
 * LeetCode Problem 205: Isomorphic Strings
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same
 * character, but a character may map to itself.
 *
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 *
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 *
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 */
public class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {
        // Arrays to store the last seen index of each character.
        // We use 256 to cover the extended ASCII character set.
        int[] mapS = new int[256];
        int[] mapT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check if the mapping is consistent.
            // If the last seen index of charS is different from the last seen index of charT,
            // it means one character has been mapped before while the other hasn't,
            // or they were mapped to different characters in a previous step.
            if (mapS[charS] != mapT[charT]) {
                return false;
            }

            // Update the last seen position for both characters.
            // We use `i + 1` instead of `i` to distinguish between a character
            // not seen yet (default value 0) and a character seen at index 0.
            mapS[charS] = i + 1;
            mapT[charT] = i + 1;
        }

        return true;
    }

    public static void main(String[] args) {
        String s1 = "paper";
        String t1 = "title";
        System.out.println("s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Using Array Map: " + isIsomorphic(s1, t1)); // Expected: true

        String s2 = "foo";
        String t2 = "bar";
        System.out.println("\ns = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Using Array Map: " + isIsomorphic(s2, t2)); // Expected: false

        String s3 = "badc";
        String t3 = "baba";
        System.out.println("\ns = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("Using Array Map: " + isIsomorphic(s3, t3)); // Expected: false
    }

}

