package leetcode.stack.parenthesesPathsExpression;

import java.util.Stack;


// Question: Given an encoded string, return its decoded string.
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the
// square brackets is being repeated exactly k times. k is guaranteed to be a positive integer.
// You may assume that the input string is always valid; there are no extra white spaces,
// square brackets are well-formed, etc.
//
// Example 1:
// Input: s = "3[a]2[bc]"
// Output: "aaabcbc"
//
// Example 2:
// Input: s = "3[a2[c]]"
// Output: "accaccacc"
//
// Example 3:
// Input: s = "2[abc]3[cd]ef"
// Output: "abcabccdcdcdef"
public class DecodeString {

    @SuppressWarnings("all")
    public static String decodeString(String s) {
        // Stack to store the repeat counts (the 'k' values).
        Stack<Integer> countStack = new Stack<>();
        // Stack to store the partially built string from outer layers.
        Stack<String> stringStack = new Stack<>();
        // This will hold the string being built at the CURRENT level of nesting.
        String result = "";

        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);

            // Case 1: The character is a digit.
            if (Character.isDigit(ch)) {
                int count = 0;
                // Parse the entire number (e.g., "100" instead of just "1").
                while (Character.isDigit(s.charAt(i))) {
                    count = count * 10 + (s.charAt(i) - '0');
                    i++;
                }
                // Push the repeat count for the upcoming string.
                countStack.push(count);
            }
            // Case 2: The character is an opening bracket '['.
            else if (ch == '[') {
                // We are starting a new, nested string.
                // Save the `result` from the PREVIOUS level onto the stack.
                stringStack.push(result);
                // Reset `result` to start building the new nested string.
                result = "";
                i++;
            }
            // Case 3: The character is a closing bracket ']'.
            else if (ch == ']') {
                // We have finished building the nested string (currently in `result`).
                // Pop the string from the PREVIOUS level to append to.
                StringBuilder temp = new StringBuilder(stringStack.pop());

                // Pop the repeat count for the nested string we just finished.
                int repeatTimes = countStack.pop();

                // Repeat the nested string `repeatTimes` and append it.
                temp.append(result.repeat(repeatTimes));

                // This combined string is now the new "current" result.
                result = temp.toString();
                i++;
            }
            // Case 4: The character is a letter.
            else {
                // Simply append the letter to the current level's string.
                result += ch;
                i++;
            }
        }

        return result;
    }

    /**
     * Main method for testing the function.
     */
    public static void main(String[] args) {
        String s = "3[a2[c]]";
        System.out.println("Encoded: " + s);
        System.out.println("Decoded: " + decodeString(s)); // Expected: "accaccacc"
    }
}