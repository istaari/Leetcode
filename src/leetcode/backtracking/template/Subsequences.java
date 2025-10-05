package leetcode.backtracking.template;

import java.util.ArrayList;
import java.util.List;


public class Subsequences {

    /**
     * Bitwise approach.
     * The idea is to generate all possible subsequences using a bitmask.
     * For a string "abc" of length 3, there are 2^3 = 8 possible subsequences.
     * Each bit in a number from 0 to 7 (000 to 111) corresponds to a character in the string.
     * If the bit is 1, we include the character; otherwise, we don't.
     * 000 -> ""
     * 001 -> "a"
     * 010 -> "b"
     * 011 -> "ab"
     * 100 -> "c"
     * 101 -> "ac"
     * 110 -> "bc"
     * 111 -> "abc"
     *
     * @param s      input string
     * @param result result list to store subsequences
     */
    public static void bitwise(String s, List<String> result) {
        int n = s.length();
        // The total number of subsequences is 2^n - 1
        for (int mask = 1; mask < (1 << n); mask++) {
            StringBuilder subsequence = new StringBuilder();
            for (int i = 0; i < n; i++) {
                // Check if the i-th bit is set in the mask
                if ((mask & (1 << i)) != 0) {
                    subsequence.append(s.charAt(i));
                }
            }
            result.add(subsequence.toString());
        }
    }


    /**
     * Backtracking approach (Iterative choices).
     * This method explores choices iteratively. For each position, it adds the current
     * built subsequence to the result, then iterates through the remaining characters,
     * adding one at a time and recursing.
     *
     * @param s       input string
     * @param temp    a StringBuilder to build the current subsequence
     * @param result  result list
     * @param start   the starting index for the current exploration
     */
    public static void backtrack(String s, StringBuilder temp, List<String> result, int start) {
        if (!temp.isEmpty()) {
            result.add(temp.toString());
        }

        for (int i = start; i < s.length(); i++) {
            // Include the character
            temp.append(s.charAt(i));
            // Recurse for the next characters
            backtrack(s, temp, result, i + 1);
            // Backtrack: remove the character to explore other paths
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    /**
     * Approach: Backtracking (Recursion) - Picking and Not Picking
     * For each character in the string, we have two choices:
     * 1. We pick the character and include it in our subsequence, then move to the next character.
     * 2. We do not pick the character, and simply move to the next character.
     * The base case for the recursion is when we have considered all characters.
     *
     * @param s      input string
     * @param temp   a StringBuilder to build the current subsequence
     * @param result result list
     * @param i      the index of the character to be considered
     */
    public static void picking(String s, StringBuilder temp, List<String> result, int i) {
        // Base case: If we have considered all characters of the string
        if (i == s.length()) {
            if (!temp.isEmpty()) {
                result.add(temp.toString());
            }
            return;
        }

        // --- Pick the current character ---
        temp.append(s.charAt(i));
        picking(s, temp, result, i + 1);

        // Backtrack to remove the character for the "not pick" path
        temp.deleteCharAt(temp.length() - 1);

        // --- Do not pick the current character ---
        picking(s, temp, result, i + 1);
    }


    /**
     * Driver method to generate all subsequences.
     * You can switch which method is called here to test them.
     *
     * @param s The input string.
     * @return A list of all subsequences.
     */
    public static List<String> subsequences(String s) {
        List<String> result = new ArrayList<>();
        // --- Call your preferred method ---

        // Method 1: Bitwise
        // bitwise(s, result);

        // Method 2: Backtracking (Iterative choices)
        // backtrack(s, new StringBuilder(), result, 0);

        // Method 3: Picking / Not Picking (most common for interviews)
        picking(s, new StringBuilder(), result, 0);

        return result;
    }


    public static void main(String[] args) {
        String s = "abc";
        System.out.println("Generating subsequences for \"" + s + "\"");
        List<String> result = subsequences(s);
        System.out.println(result);
    }
}
