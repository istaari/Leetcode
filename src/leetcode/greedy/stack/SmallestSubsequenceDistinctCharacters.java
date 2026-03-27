package leetcode.greedy.stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * 1081. Smallest Subsequence of Distinct Characters
 * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * Return the lexicographically smallest subsequence of s that contains all
 * the distinct characters of s exactly once.
 *
 * This is identical to LC 316 (Remove Duplicate Letters).
 *
 * Example 1: s = "bcabc" -> "abc"
 * Example 2: s = "cbacdcbc" -> "acdb"
 *
 * Constraints:
 *   1 <= s.length <= 1000
 *   s consists of lowercase English letters.
 *
 * ---
 * Approach: Monotonic stack + greedy (same as LC 316)
 *
 * Track last index of each char. Use a stack:
 *   - Skip chars already in stack.
 *   - Pop stack top if it's > current char AND it appears later.
 *   - Push current char.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class SmallestSubsequenceDistinctCharacters {

    public static String smallestSubsequence(String s) {
        Stack<Character> stack = new Stack<>();
        Set<Character> seen = new HashSet<>();
        Map<Character, Integer> lastIndex = new HashMap<>();

        // Build the last index map
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen.contains(c))
                continue;

            while (!stack.isEmpty() && c < stack.peek() && lastIndex.get(stack.peek()) > i) {
                seen.remove(stack.pop());
            }

            stack.push(c);
            seen.add(c);
        }

        // Build result
        StringBuilder result = new StringBuilder();
        for (char c : stack)
            result.append(c);

        return result.toString();
    }

    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(smallestSubsequence(s));
    }

}
