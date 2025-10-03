package leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LeetCode 409: Longest Palindrome
//
// Problem Statement:
// Given a string `s` which consists of lowercase or uppercase English letters, return the
// length of the longest palindrome that can be built with those letters.
//
// Note that this is case-sensitive, so "a" and "A" are considered different characters.
// We are not finding a palindromic substring within `s`, but rearranging the letters of `s`
// to form the longest possible new string that is a palindrome.
//
// Example:
// Input: s = "abccccdd"
// Output: 7
// Explanation: One longest palindrome that can be built is "dccaccd", which has a length of 7.

public class LongestPalindrome {

    public static int longestPalindrome(String s) {
        // This 'count' is actually counting the number of PAIRS of characters.
        int count = 0;
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // The remove() method returns true if the element was in the set.
            // This is a clever trick to check for existence and remove in one step.
            if (set.remove(c)) {
                // If we successfully removed the character, it means we found its pair.
                count++;
            } else {
                // If the character was not in the set, add it. It's now waiting for a pair.
                set.add(c);
            }
        }

        // Each pair contributes 2 to the length of the palindrome.
        int lengthFromPairs = count * 2;

        // If the set is not empty, it means there are leftover unpaired characters.
        // We can place exactly ONE of these in the center of the palindrome.
        if (!set.isEmpty()) {
            return lengthFromPairs + 1;
        } else {
            // If the set is empty, all characters formed perfect pairs. No center is needed.
            return lengthFromPairs;
        }
    }

    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));
    }

}
