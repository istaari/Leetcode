package leetcode.stack;

import java.util.Stack;

public class BasicCalculator {

    public static int calculate(String s) {
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        int current = 0;

        for (int i = 0; i < s.length(); i++) {

            if (Character.isDigit(s.charAt(i))) {
                current = s.charAt(i) - '0';
            }

            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {

                if (sign == '-') {
                    stack.push(-current);
                }
                if (sign == '+') {
                    stack.push(current);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * current);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / current);
                }

                sign = s.charAt(i);
                current = 0;
            }
        }


        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "3+2*2";
        System.out.println(calculate(s));
    }

}
