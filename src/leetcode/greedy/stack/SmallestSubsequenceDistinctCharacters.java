package leetcode.greedy.stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
