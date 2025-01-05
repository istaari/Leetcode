package leetcode.stack;

import java.util.Stack;

public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        if (num.length() == k) return "0";

        Stack<Character> stack = new Stack<>();
        for (char ch : num.toCharArray()) {

            while (k > 0 && !stack.isEmpty() && stack.peek() - '0' > ch - '0') {
                k--;
                stack.pop();
            }

            stack.push(ch);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }


        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty() && stack.getFirst() == '0') {
            stack.removeFirst();
        }

        while (!stack.isEmpty()) result.insert(0, stack.pop());

        return (result.isEmpty()) ? "0" : result.toString();
    }


    public static void main(String[] args) {
        String str = "1432219";
        int k = 3;

        str = "10200";
        k = 1;

        System.out.println(removeKdigits(str, k));
    }


}
