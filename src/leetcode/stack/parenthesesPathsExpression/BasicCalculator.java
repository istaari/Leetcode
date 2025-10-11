package leetcode.stack.parenthesesPathsExpression;

import java.util.Stack;

// Question: Given a string `s` which represents a mathematical expression,
// implement a basic calculator to evaluate it and return the result.
//
// The expression string contains non-negative integers and the operators +, -, *, /.
// Integer division should truncate toward zero.
// You can assume the given expression is always valid.
//
// Note: This solution correctly handles operator precedence (* and / before + and -).
//
// Example:
// Input: s = "3+2*2"
// Output: 7
//
// Input: s = " 3/2 "
// Output: 1
public class BasicCalculator {

    public static int calculate(String s) {
        // `sign` stores the operator that comes *before* the current number.
        // We initialize it to '+' for the very first number.
        char sign = '+';
        Stack<Integer> stack = new Stack<>();
        int current = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If the character is a digit, build the current number.
            // This handles multi-digit numbers (e.g., "42").
            if (Character.isDigit(ch)) {
                current = current * 10 + (ch - '0');
            }

            // This block triggers when we hit an operator OR the end of the string.
            // It's time to process the `current` number with its preceding `sign`.
            if (!Character.isDigit(ch) && ch != ' ' || i == s.length() - 1) {

                // Based on the *previous* sign, perform an action.
                if (sign == '-') {
                    stack.push(-current);
                }
                if (sign == '+') {
                    stack.push(current);
                }
                // For * and /, they have higher precedence. We pop the last number from the stack,
                // perform the operation, and push the result back immediately.
                if (sign == '*') {
                    stack.push(stack.pop() * current);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / current);
                }

                // Update the sign for the next number and reset current.
                sign = ch;
                current = 0;
            }
        }

        // After the loop, the stack contains only numbers that need to be added.
        // The * and / operations have already been resolved.
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    /**
     * Main method for testing the function.
     */
    public static void main(String[] args) {
        String s = "3+2*2";
        System.out.println(s + " = " + calculate(s)); // Expected: 7

        String s2 = " 42 - 10 / 5 ";
        System.out.println(s2 + " = " + calculate(s2)); // Expected: 40
    }
}