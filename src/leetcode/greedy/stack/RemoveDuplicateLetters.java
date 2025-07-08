package leetcode.greedy.stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

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
