package leetcode.string;

import java.util.Arrays;

/**
 * LeetCode Problem 443: String Compression
 * * Given an array of characters chars, compress it using the following algorithm:
 * * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * - If the group's length is 1, append the character to s.
 * - Otherwise, append the character followed by the group's length.
 * * The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
 * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * * After you are done modifying the input array, return the new length of the array.
 * * You must write an algorithm that uses only constant extra space.
 * * Example 1:
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 * * Example 2:
 * Input: chars = ["a"]
 * Output: Return 1, and the first character of the input array should be: ["a"]
 * * Example 3:
 * Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"]
 */

public class StringCompression {

    /**
     * Compresses the character array in-place.
     * This is an optimized solution using a two-pointer approach (a read pointer and a write pointer).
     *
     * @param chars The input array of characters to be compressed.
     * @return The new length of the compressed array.
     */
    public static int compress(char[] chars) {
        // 'writeIndex' is the pointer where we will write the next compressed character.
        int writeIndex = 0;
        // 'readIndex' is the pointer that scans through the original array.
        int readIndex = 0;

        // Loop until we have scanned the entire array.
        while (readIndex < chars.length) {
            char currentChar = chars[readIndex];
            int count = 0;

            // Count consecutive occurrences of the currentChar.
            while (readIndex < chars.length && chars[readIndex] == currentChar) {
                count++;
                readIndex++; // Move the read pointer forward.
            }

            // Write the character to the compressed part of the array.
            chars[writeIndex++] = currentChar;

            // If the character was repeated more than once, write its count.
            if (count > 1) {
                // Convert the count to a string to handle multi-digit numbers (e.g., 12).
                String countStr = String.valueOf(count);
                for (char digit : countStr.toCharArray()) {
                    chars[writeIndex++] = digit;
                }
            }
        }

        // 'writeIndex' is now at the end of the compressed content, which is the new length.
        return writeIndex;
    }

    public static void main(String[] args) {
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int newLength1 = compress(chars1);
        System.out.println("Test Case 1:");
        System.out.println("New Length: " + newLength1); // Expected: 6
        System.out.println("Modified Array: " + Arrays.toString(Arrays.copyOf(chars1, newLength1))); // Expected: [a, 2, b, 2, c, 3]
        System.out.println("--------------------");

        char[] chars2 = {'a'};
        int newLength2 = compress(chars2);
        System.out.println("Test Case 2:");
        System.out.println("New Length: " + newLength2); // Expected: 1
        System.out.println("Modified Array: " + Arrays.toString(Arrays.copyOf(chars2, newLength2))); // Expected: [a]
        System.out.println("--------------------");

        char[] chars3 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int newLength3 = compress(chars3);
        System.out.println("Test Case 3:");
        System.out.println("New Length: " + newLength3); // Expected: 4
        System.out.println("Modified Array: " + Arrays.toString(Arrays.copyOf(chars3, newLength3))); // Expected: [a, b, 1, 2]
    }
}
