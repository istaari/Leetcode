package leetcode.stack.monotonic;

import java.util.Stack;

// Question: Given a non-negative integer `num` represented as a string,
// remove `k` digits from the number so that the new number is the smallest possible.
//
// Example 1:
// Input: num = "1432219", k = 3
// Output: "1219"
// Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
//
// Example 2:
// Input: num = "10200", k = 1
// Output: "200"
// Explanation: Remove the leading 1 and the new number is 200. Note that the output must not have leading zeroes.
public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {
        // If we need to remove all digits, the result is "0".
        if (num.length() == k) return "0";

        // We use a stack to build the result. The goal is to keep the digits
        // in the stack in an increasing order (a "monotonic stack").
        Stack<Character> stack = new Stack<>();

        for (char ch : num.toCharArray()) {
            // This is the core logic. While we still have digits to remove (k > 0)
            // and the previous digit on the stack is GREATER than the current digit,
            // we should remove the previous digit. This is because removing a larger digit
            // from a more significant position (further to the left) yields a smaller number.
            while (k > 0 && !stack.isEmpty() && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            // Add the current digit to the stack.
            stack.push(ch);
        }

        // After the first loop, if k is still > 0, it means the number's
        // digits were in increasing order (e.g., "12345"). To make it smallest,
        // we must remove the largest digits from the end.
        while (k > 0) {
            stack.pop();
            k--;
        }

        // Now, we construct the final number from the stack.
        StringBuilder result = new StringBuilder();

        // The stack contains the result, but we need to put it into a string.
        // We'll build it up and then handle leading zeros.
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        // Remove any leading zeros from the final result.
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        // If the result is empty after all operations (e.g., input was "10", k=2), return "0".
        return (result.isEmpty()) ? "0" : result.toString();
    }


    public static void main(String[] args) {
        String str = "1432219";
        int k = 3;
        System.out.println("Removing " + k + " digits from " + str + " -> " + removeKdigits(str, k)); // Expected: 1219

        str = "10200";
        k = 1;
        System.out.println("Removing " + k + " digits from " + str + " -> " + removeKdigits(str, k)); // Expected: 200

        str = "10";
        k = 2;
        System.out.println("Removing " + k + " digits from " + str + " -> " + removeKdigits(str, k)); // Expected: 0
    }
}