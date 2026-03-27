package leetcode.greedy.stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * 316. Remove Duplicate Letters
 * https://leetcode.com/problems/remove-duplicate-letters/
 *
 * Given a string s, remove duplicate letters so that every letter appears once
 * and only once. You must make sure your result is the smallest in
 * lexicographical order among all possible results.
 *
 * Example 1: s = "bcabc" -> "abc"
 * Example 2: s = "cbacdcbc" -> "acdb"
 *
 * Constraints:
 *   1 <= s.length <= 10^4
 *   s consists of lowercase English letters.
 *
 * ---
 * Approach: Monotonic stack + greedy
 *
 * Use a stack to build the result. For each character:
 *   1. If already in stack, skip (duplicates handled).
 *   2. While stack top > current char AND stack top appears later in string,
 *      pop it (we can add it back later for a smaller result).
 *   3. Push current char.
 *
 * "Appears later" is tracked via a remaining-count map.
 *
 * This is the SAME problem as LC 1081 (Smallest Subsequence of Distinct Characters).
 *
 * Time:  O(n)
 * Space: O(1) (26 letters max in stack)
 */
public class RemoveDuplicateLetters {

    public static String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Stack<Character> stack = new Stack<>();
        HashSet<Character> seen = new HashSet<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.get(c) - 1);
            if (seen.contains(c)) continue;

            while (!stack.isEmpty() && stack.peek() > c && map.get(stack.peek()) > 0) {
                seen.remove(stack.pop());
            }

            stack.push(c);
            seen.add(c);
        }

        StringBuilder result = new StringBuilder();
        for (Character c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(removeDuplicateLetters(s));
    }

}
