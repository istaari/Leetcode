package leetcode.stack.parenthesesPathsExpression;

import java.util.Stack;


class RemoveOutermostParentheses {

    /**
     * A valid parentheses string is primitive if it is nonempty, and it does not have a way to be split into s = A + B,
     * with A and B nonempty valid parentheses strings.
     * <p>
     * Given a valid parentheses string s, consider its primitive decomposition: s = P1 + P2 + ... + Pk,
     * where Pi are primitive valid parentheses strings.
     * <p>
     * Return s after removing the outermost parentheses of every primitive string in the primitive decomposition of s.
     *
     * @param s The input string consisting of '(' and ')'. It is guaranteed to be a valid parentheses string.
     * @return The string s after removing the outermost parentheses of each primitive component.
     * @example For s = "(()())(())":
     * - The primitive decomposition is P1 = "(()())" and P2 = "(())".
     * - Removing the outer parentheses of P1 gives "()()".
     * - Removing the outer parentheses of P2 gives "()".
     * - The final result is "()()" + "()" = "()()()".
     */
    public static String removeOuterParentheses(String s) {
        // A stack can be used to track the depth of nested parentheses.
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (currentChar == '(') {
                // Before pushing, check the stack size. If the stack is not empty,
                // this opening parenthesis is NOT an outermost one, so we append it.
                if (!stack.isEmpty()) {
                    result.append(currentChar);
                }
                // Push every opening parenthesis to track depth.
                stack.push(currentChar);
            } else { // currentChar is ')'
                // Pop first to correctly assess the depth *before* this parenthesis.
                stack.pop();

                // After popping, if the stack is still not empty,
                // it means this closing parenthesis was not an outermost one.
                if (!stack.isEmpty()) {
                    result.append(currentChar);
                }
            }
        }
        return result.toString();
    }


    public static void main(String[] args) {
        String p = "(()())(())";
        System.out.println("Input: " + p);
        System.out.println("Output: " + removeOuterParentheses(p)); // Expected: "()()()"

        String p2 = "()()";
        System.out.println("\nInput: " + p2);
        System.out.println("Output: " + removeOuterParentheses(p2)); // Expected: ""
    }
}
