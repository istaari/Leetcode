package leetcode.greedy.frequencyCounting;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * LeetCode Problem: 767. Reorganize String
 * <p>
 * Question:
 * Given a string `s`, rearrange the characters of `s` so that any two adjacent characters are not the same.
 * Return any possible rearrangement of `s` or return "" if it is impossible.
 * <p>
 * Example 1:
 * Input: s = "aab"
 * Output: "aba"
 * <p>
 * Example 2:
 * Input: s = "aaab"
 * Output: ""
 * <p>
 * Constraints:
 * - 1 <= s.length <= 500
 * - s consists of lowercase English letters.
 */
public class ReorganizeString {

    public static String reorganizeString(String s) {
        // --- The Greedy Strategy ---
        // The core idea is to always append the most frequent character available that is different
        // from the last character added. A max heap is the perfect data structure for this,
        // as it always gives us the character with the highest remaining frequency.

        // Step 1: Count the frequency of each character.
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Step 2: Create a max heap (PriorityQueue) to store characters.
        // The character with the highest frequency will have the highest priority.
        Queue<Character> queue = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        queue.addAll(map.keySet());

        StringBuilder result = new StringBuilder();

        // Step 3: Greedily build the new string.
        // We pull the two most frequent characters from the heap at each step.
        // This ensures that the most frequent character is always separated by the second most frequent one.
        while (queue.size() >= 2) {
            Character char1 = queue.poll();
            Character char2 = queue.poll();

            // Append them to the result
            result.append(char1);
            result.append(char2);

            // Decrement their counts
            map.put(char1, map.get(char1) - 1);
            map.put(char2, map.get(char2) - 1);

            // If the characters still have remaining counts, add them back to the heap.
            // The heap will automatically re-order them based on their new frequencies.
            if (map.get(char1) > 0) {
                queue.add(char1);
            }
            if (map.get(char2) > 0) {
                queue.add(char2);
            }
        }

        // Step 4: Handle the last remaining character, if any.
        if (!queue.isEmpty()) {
            Character lastChar = queue.poll();
            // If the last remaining character has a frequency greater than 1, it's impossible.
            // This means we have, for example, 'a' left, but the string ends in 'a', so we can't place it.
            if (map.get(lastChar) > 1) {
                return "";
            }
            // Otherwise, it's safe to append the last character.
            result.append(lastChar);
        }

        return result.toString();
    }


    static void main(String[] args) {
        String s1 = "aab";
        System.out.println("Reorganized '" + s1 + "': " + reorganizeString(s1)); // Expected: "aba"

        String s2 = "aaab";
        System.out.println("Reorganized '" + s2 + "': " + reorganizeString(s2)); // Expected: ""

        String s3 = "aaabc";
        System.out.println("Reorganized '" + s3 + "': " + reorganizeString(s3)); // Expected: "abaca"
    }

}