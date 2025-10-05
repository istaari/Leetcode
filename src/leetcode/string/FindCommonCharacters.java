package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * LeetCode Problem 1002: Find Common Characters
 *
 * Given a string array `words`, return an array of all characters that show up
 * in all strings within the `words` (including duplicates). You may return the
 * answer in any order.
 *
 * For example, if a character occurs 3 times in all strings but not 4 times,
 * you need to include that character three times in the final answer.
 *
 * Example 1:
 * Input: words = ["bella","label","roller"]
 * Output: ["e","l","l"]
 *
 * Example 2:
 * Input: words = ["cool","lock","cook"]
 * Output: ["c","o"]
 */
public class FindCommonCharacters {

    public static List<String> commonChars(String[] words) {
        // `mainCount` will store the minimum frequency of each character ('a' through 'z')
        // found across all words. We initialize it with a very large number so that
        // the first word's frequencies will become the initial minimums.
        int[] mainCount = new int[26];
        Arrays.fill(mainCount, Integer.MAX_VALUE);

        // Iterate through each word in the input array.
        for (String word : words) {
            // For each word, calculate its own character frequency count.
            int[] wordCount = new int[26];
            for (char ch : word.toCharArray()) {
                wordCount[ch - 'a']++;
            }

            // Update the mainCount. The new minimum frequency for each character
            // is the smaller of its previous minimum and the frequency in the current word.
            // This step effectively finds the intersection of character counts.
            for (int i = 0; i < 26; i++) {
                mainCount[i] = Math.min(mainCount[i], wordCount[i]);
            }
        }

        // After checking all words, `mainCount` holds the exact number of times
        // each character appeared in all strings. Now, build the result list.
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            // Add the character to the result list `mainCount[i]` times.
            while (mainCount[i] > 0) {
                // Convert the index `i` back to a character.
                result.add(String.valueOf((char) (i + 'a')));
                mainCount[i]--;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        String[] words1 = {"bella", "label", "roller"};
        System.out.println("Input: " + Arrays.toString(words1));
        System.out.println("Output: " + commonChars(words1)); // Expected: [e, l, l]

        String[] words2 = {"cool", "lock", "cook"};
        System.out.println("\nInput: " + Arrays.toString(words2));
        System.out.println("Output: " + commonChars(words2)); // Expected: [c, o]
    }
}
