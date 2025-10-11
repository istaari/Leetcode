package leetcode.stack.parenthesesPathsExpression;

import java.util.Stack;


/**
 * Solves LeetCode problems related to evaluating arithmetic expressions.
 * This class demonstrates stack-based solutions for both Reverse Polish (postfix), standard Infix, and Polish (prefix) notation.
 */
public class EvaluateNotation {

    //<editor-fold desc="Reverse Polish Notation Methods">

    /**
     * Evaluates the value of an arithmetic expression in Reverse Polish Notation (RPN).
     * <p>
     * Reverse Polish Notation is a mathematical notation in which operators follow their operands.
     * For example, the infix expression `(2 + 1) * 3` is written as `["2", "1", "+", "3", "*"]` in RPN.
     * <p>
     * The algorithm uses a stack:
     * 1. If the token is a number, push it onto the stack.
     * 2. If the token is an operator, pop the top two numbers from the stack, perform the operation,
     * and push the result back onto the stack.
     * The final result is the last number remaining on the stack.
     *
     * @param tokens An array of strings representing the RPN expression. Valid operators are "+", "-", "*", "/".
     *               It is guaranteed that the given RPN expression is always valid.
     * @return The integer result of the expression.
     * @example For tokens = ["2", "1", "+", "3", "*"]:
     * - Push 2. Stack: [2]
     * - Push 1. Stack: [2, 1]
     * - See "+", pop 1, pop 2, calculate 2 + 1 = 3. Push 3. Stack: [3]
     * - Push 3. Stack: [3, 3]
     * - See "*", pop 3, pop 3, calculate 3 * 3 = 9. Push 9. Stack: [9]
     * - Return 9.
     * @see <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">LeetCode Problem 150</a>
     */
    public int evalRPN(String[] tokens) {
        // This stack stores operands as strings, requiring parsing for each operation.
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    // Note: The first pop() gets the second operand (x), the second pop() gets the first (y).
                    int x = Integer.parseInt(stack.pop());
                    int y = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(y + x));
                }
                case "-" -> {
                    int x = Integer.parseInt(stack.pop());
                    int y = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(y - x));
                }
                case "*" -> {
                    int x = Integer.parseInt(stack.pop());
                    int y = Integer.parseInt(stack.pop());
                    stack.push(String.valueOf(y * x));
                }
                case "/" -> {
                    int x = Integer.parseInt(stack.pop());
                    int y = Integer.parseInt(stack.pop());

                    // Division truncates toward zero as per the problem description.
                    stack.push(String.valueOf(y / x));
                }
                default ->
                    // If it's not an operator, it's a number. Push it onto the stack.
                        stack.push(token);
            }
        }
        // The final result is the only element left on the stack.
        return Integer.parseInt(stack.pop());
    }


    //</editor-fold>

    //<editor-fold desc="Infix Notation Methods">

    /**
     * Evaluates a standard infix arithmetic expression with support for +, -, *, /, and parentheses.
     * This method uses Dijkstra's Shunting-yard algorithm logic with two stacks.
     *
     * @param expression A string representing the infix expression, e.g., "10 + 2 * 6".
     * @return The integer result of the calculation.
     */
    public int evalInfix(String expression) {
        Stack<Integer> values = new Stack<>(); // Stack for integer operands
        Stack<Character> ops = new Stack<>();    // Stack for operators

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') continue; // Skip whitespace

            // If the character is a digit, parse the full number
            if (Character.isDigit(c)) {
                StringBuilder sb = new StringBuilder();
                // Handle multi-digit numbers
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                i--; // Correct for the extra increment in the while loop
                values.push(Integer.parseInt(sb.toString()));
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // Solve the entire sub-expression inside the parentheses
                while (ops.peek() != '(') {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop(); // Pop the opening parenthesis
            } else { // The Current token is an operator
                // While the top of the ops stack has the same or greater precedence, apply it
                while (!ops.isEmpty() && hasPrecedence(c, ops.peek())) {
                    values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
                }
                // Push the current operator onto the stack
                ops.push(c);
            }
        }

        // The Entire expression has been parsed, apply remaining operations
        while (!ops.isEmpty()) {
            values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
        }

        // The final result is the top of the values stack
        return values.pop();
    }

    /**
     * Helper method to check operator precedence.
     * Returns true if op2 has higher or same precedence as op1, false otherwise.
     */
    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    /**
     * Helper method to apply an operator to two operands.
     */
    private int applyOperation(char op, int b, int a) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                yield a / b;
            }
            default -> 0;
        };
    }
    //</editor-fold>

    //<editor-fold desc="Prefix Notation Methods">

    /**
     * Evaluates the value of an arithmetic expression in Polish Notation (Prefix).
     * The key is to iterate through the tokens from right to left.
     *
     * @param tokens An array of strings representing the Prefix expression.
     * @return The integer result of the expression.
     * @example For tokens = ["*", "+", "2", "1", "3"]:
     * - Read "3", push 3. Stack: [3]
     * - Read "1", push 1. Stack: [3, 1]
     * - Read "2", push 2. Stack: [3, 1, 2]
     * - Read "+", pop 2 (a), pop 1 (b), calculate 2 + 1 = 3. Push 3. Stack: [3, 3]
     * - Read "*", pop 3 (a), pop 3 (b), calculate 3 * 3 = 9. Push 9. Stack: [9]
     * - Return 9.
     */
    public int evalPrefix(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        // Iterate from right to left
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if ("+".equals(token)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a + b);
            } else if ("-".equals(token)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a - b);
            } else if ("*".equals(token)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a * b);
            } else if ("/".equals(token)) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
    //</editor-fold>


    public static void main(String[] args) {
        EvaluateNotation evaluator = new EvaluateNotation();

        System.out.println("--- Reverse Polish Notation (Postfix) ---");
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println("Expression: [\"2\", \"1\", \"+\", \"3\", \"*\"]");
        System.out.println("Result (Optimized): " + evaluator.evalRPN(tokens1)); // Expected: 9

        System.out.println("\n\n--- Standard Notation (Infix) ---");
        String infix1 = "3 + 5 / 2";
        System.out.println("Expression: \"" + infix1 + "\"");
        System.out.println("Result: " + evaluator.evalInfix(infix1)); // Expected: 5 (integer division)


        System.out.println("\n\n--- Polish Notation (Prefix) ---");
        String[] prefixTokens = {"*", "+", "2", "1", "3"}; // Same as (2+1)*3
        System.out.println("Expression: [\"*\", \"+\", \"2\", \"1\", \"3\"]");
        System.out.println("Result: " + evaluator.evalPrefix(prefixTokens)); // Expected: 9
    }
}

